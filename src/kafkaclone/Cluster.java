package kafkaclone;
import java.util.ArrayList;

public class Cluster {
	private int brokerCount;
	ArrayList<Broker> BrokerList;
	String leader;
	
	private static Cluster obj=null;
	private Cluster()
	{
		
	}
	private Cluster(int brokerCount, ArrayList<Broker> brokerList, String leader) {
		super();
		this.brokerCount = brokerCount;
		this.BrokerList = brokerList;
		this.leader = leader;
	}
	
	public static synchronized Cluster getInstance()
	{
		if(obj==null)
		{
			obj=new Cluster();
		}
		
		return obj;
		
	}

	public int getBrokerCount() {
		return brokerCount;
	}

	public void setBrokerCount(int brokerCount) {
		this.brokerCount = brokerCount;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	public ArrayList<Broker> getBrokerList() {
		return BrokerList;
	}

	public void setBrokerList(ArrayList<Broker> brokerList) {
		BrokerList = brokerList;
	}
	
	String getLeader()
	{
		return "";
	}
	
	String setLeader()
	{
		return "";
	}
	
	void initializeBrokers()
	{
		Broker b1 = new Broker();
		Broker b2 = b1.cloneBroker();
		Broker b3 = b1.cloneBroker();
		
		BrokerList.add(b1);
		BrokerList.add(b2);
		BrokerList.add(b3);
		
	}

}
