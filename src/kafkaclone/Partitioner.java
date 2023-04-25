package kafkaclone;
import java.util.ArrayList;

public class Partitioner {
	int numberOfPartitions;
	String key;
	String partitionId;
	
	public Partitioner(int numberOfPartitions, String key, String partitionId) {
		super();
		this.numberOfPartitions = numberOfPartitions;
		this.key = key;
		this.partitionId = partitionId;
	}
	public int getNumberOfPartitions() {
		return numberOfPartitions;
	}
	public void setNumberOfPartitions(int numberOfPartitions) {
		this.numberOfPartitions = numberOfPartitions;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPartitionId() {
		return partitionId;
	}
	public void setPartitionId(String partitionId) {
		this.partitionId = partitionId;
	}
	
	ArrayList<Message> makePartition()
	{
		return new ArrayList<Message>();
		
	}

}
