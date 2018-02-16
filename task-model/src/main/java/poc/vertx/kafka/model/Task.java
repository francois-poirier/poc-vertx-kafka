package poc.vertx.kafka.model;

public class Task {
	private Long id;
	private String name;
	private String description;
	private TaskStatus status;
	private Partition partition;
	private Long relatedTaskId;
	private Integer hours;
	
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(Long id, String name, String description, TaskStatus status, Partition partition, Long relatedTaskId, Integer hours) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.partition=partition;
		this.relatedTaskId= relatedTaskId;
		this.hours=hours;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public Partition getPartition() {
		return partition;
	}

	public Integer getHours() {
		return hours;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Long getRelatedTaskId() {
		return relatedTaskId;
	}

	public void setRelatedTaskId(Long relatedTaskId) {
		this.relatedTaskId = relatedTaskId;
	}


	
}
