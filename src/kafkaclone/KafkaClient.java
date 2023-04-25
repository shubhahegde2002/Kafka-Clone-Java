package kafkaclone;
import java.io.IOException;
import java.lang.*;
import java.net.Socket;
import java.util.*;
public class KafkaClient {
	
	private String clientId;
	
	public KafkaClient(String clientId) {
		super();
		this.clientId = clientId;
	}

	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	void startKafka() throws IOException, InterruptedException
	{
		System.out.println("Inside startKafka");
		Socket socket=new Socket();
		Zookeeper zoo = Zookeeper.getInstance();
		Cluster cluster = Cluster.getInstance();
		cluster.initializeBrokers();
		
		
		Producer producer = new Producer();
		Producer producer2 = new Producer();
		Consumer consumer = new Consumer();
		Consumer consumer2 = new Consumer();
		consumer.openReceivePort();
		consumer2.openReceivePort();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		String selection=sc.next();
		if(selection.equals("Consumer1"))
		{
			while(true)
			{
				String topic = sc.next();
				String message = "This is consumer 1";
				System.out.println(message);
				if(topic=="quit")
				{
					break;
				}
			}
		}
		else if(selection.equals("consumer2"))
		{
			while(true)
			{
				String topic = sc.next();
				String message = "This is consumer 2";
				System.out.println(message);
				if(topic=="quit")
				{
					break;
				}
			}
		}
		else if(selection.equals("producer"))
		{
			while(true)
			{
				String topic = sc.next();
				System.out.println("topic:"+topic);
				System.out.println("What message? Tell:");
				String message = sc.next();
				System.out.println("message:"+message);
			}
		}
		else if(selection.equals("producer2"))
		{
			while(true)
			{
				String topic = sc.next();
				System.out.println("topic2:"+topic);
				System.out.println("What message? Tell:");
				String message = sc.next();
				System.out.println("Message2:"+message);
			}
		}		
	}
	
	void StopKafka()
	{
		
	}
	

}
