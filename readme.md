# poc-vertx-kafka
This is the repository for the example of using Vertx and Kafka 


## Requirements to build this project

1.    Java 8
2.    Maven

## Requirements to run the examples

1.    [kafka](https://kafka.apache.org/downloads) version kafka_2.11-1.0.0 see the section marked "Binary downloads"


## Setup Instructions

#### Extact the kafka_2.11-1.0.0.tgz file ####
      tar -xvzf kafka_2.11-1.0.0.tgz


#### Start zookeeper and kafka
```
      kafka-install-dir/bin/zookeeper-server-start.sh kafka-install-dir/config/zookeeper.properties
      kafka-install-dir/bin/kafka-server-start.sh kafka-install-dir/config/server.properties
```

#### Setup the poc-vertx-kafka repo
Clone or fork the repo
```
     git clone https://github.com/francois-poirier/poc-vertx-kafka.git
     cd poc-vertx-kafka
```
Build all projects
```
     mvn package
```
Create the topic required by the POC
```
     kafka-install-dir/bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic tasks-topic --partitions 2 --replication-factor 1 
     
```

### Running the TaskProducerVerticle in new terminal ###
     cd <dir>/task-producer/
     java -jar ./target/task-producer-1.0-SNAPSHOT-shaded.jar

### Running the TaskConsumerVerticle in new terminal ###
     cd <dir>/task-consumer/
     java -jar ./target/task-consumer-1.0-SNAPSHOT-shaded.jar

### Running the TaskMultipleConsumerVerticle in new terminal ###
     cd <dir>/task-multiple-consumer/
     java -jar ./target/task-multiple-consumer-1.0-SNAPSHOT-shaded.jar

### Running the TaskAllConsumerVerticle in new terminal ###
     cd <dir>/task-all-consumer/
     java -jar ./target/task-all-consumer-1.0-SNAPSHOT-shaded.jar
     
 ### Running script simulate.sh in new terminal ###
     cd <dir>/task-producer/
     ./simulate.sh
     
     
