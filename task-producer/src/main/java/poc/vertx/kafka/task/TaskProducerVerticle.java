package poc.vertx.kafka.task;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import io.vertx.kafka.client.producer.RecordMetadata;
import io.vertx.kafka.client.serialization.JsonObjectSerializer;
import poc.vertx.kafka.model.Task;
import poc.vertx.kafka.model.TaskStatus;


public class TaskProducerVerticle extends AbstractVerticle {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskProducerVerticle.class);
	private Integer port = 8090;
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new TaskProducerVerticle());
	}

	@Override
	public void start() throws Exception {
		Properties config = new Properties();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config().getString("kafka.topic.address", "localhost") + ":" + config().getInteger("kafka.topic.port", 9092));
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonObjectSerializer.class);
		config.put(ProducerConfig.ACKS_CONFIG, "1");
		KafkaProducer<String, JsonObject> producer = KafkaProducer.create(vertx, config);
		producer.partitionsFor("tasks-topic", done -> {
			done.result().forEach(p -> LOGGER.info("Partition: id={}, topic={}", p.getPartition(), p.getTopic()));
		});
		
		Router router = Router.router(vertx);
		router.route("/task/*").handler(ResponseContentTypeHandler.create());
		router.route(HttpMethod.POST, "/task").handler(BodyHandler.create());
		router.post("/task").produces("application/json").handler(rc -> {
			Task t = Json.decodeValue(rc.getBodyAsString(), Task.class);
			KafkaProducerRecord<String, JsonObject> record = KafkaProducerRecord.create("tasks-topic", null, rc.getBodyAsJson(), t.getPartition().ordinal());
			producer.write(record, done -> {
				if (done.succeeded()) {
					RecordMetadata recordMetadata = done.result();
					LOGGER.info("Record sent: msg={}, destination={}, partition={}, offset={}", record.value(), recordMetadata.getTopic(), recordMetadata.getPartition(), recordMetadata.getOffset());
					t.setId(recordMetadata.getOffset());
					t.setStatus(TaskStatus.PROCESSING);					
				} else {
					Throwable th = done.cause();
					LOGGER.error("Error sent to topic: {}", th.getMessage());
					t.setStatus(TaskStatus.REJECTED);
				}
				rc.response().end(Json.encodePrettily(t));
			});
		});
		vertx.createHttpServer().requestHandler(router::accept).listen(port);
		
	}	
	
}
