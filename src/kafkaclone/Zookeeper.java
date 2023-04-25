package kafkaclone;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Zookeeper {
	private static Zookeeper obj=null;
	private Zookeeper()
	{
		
	}
	    
	public static synchronized Zookeeper getInstance()
	{
		if(obj==null)
		{
			obj=new Zookeeper();
		}
		
		return obj;	
	}
	
	ArrayList<Broker> checkBroker()
	{
		return new ArrayList<Broker>();
	}
	
	int leaderElection(ArrayList<Broker> brokers,JTextArea brokerTextArea)
	{
		int max1=-1;
		for (Broker broker : brokers) {
		        if (broker.getBrokerId() > max1) {
		            max1 = broker.getBrokerId();
		        }
		    }
		String max2=String.format("%d", max1);
			System.out.println(max2);
		brokerTextArea.setText(max2);
		    return max1;
	}
}