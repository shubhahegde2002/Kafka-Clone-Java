package kafkaclone;

public class Topic {
	String topicId;
	String topicName;
	String timestamp;
	
	public Topic(String topicId, String topicName, String timestamp) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.timestamp = timestamp;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	void addTopic(String topic)
	{
		
	}
	
	void deleteTopic(String topic)
	{
		
	}
	
	void updateTopic(String topic)
	{
		
	}
	
}
