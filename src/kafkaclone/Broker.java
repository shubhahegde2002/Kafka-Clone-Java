package kafkaclone;
import java.util.ArrayList;

import javax.swing.JTextArea;

import java.io.*;
import java.net.*;
import java.sql.*;


public class Broker extends AbstractBroker implements HeartInterface,Replication {
	private int brokerId;
	int clusterId;
	static int count1=0;
	public Broker() {
		super();
	}
	public Broker(int brokerId, int clusterId) {
		super();
		this.brokerId = brokerId;
		this.clusterId = clusterId;
	}
	
	public Broker cloneBroker()
	{
		return new Broker(this);
		
	}
	public Broker(Broker broker)
	{
		super();
		this.brokerId=count1++;
		this.clusterId=broker.clusterId;
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public int getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}
	
	public void sendHeartbeat() throws InterruptedException 
	{
		
		try {
			int count=0;
            Socket socket = new Socket("localhost", 1257);
            OutputStream out = socket.getOutputStream();
            while(true) {
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(count);
            Thread.sleep(5);
            count++;
            writer.close();
            out.close();
            socket.close();
            }
        } catch (IOException e) {
        	System.out.println("Heartbeat stopped");
        }
    }

	public static void recStore() {
        try {
            
            while(true) {
            ServerSocket serverSocket = new ServerSocket(1234);
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String message = reader.readLine();
            String topic=reader.readLine();
            System.out.println("Received message from producer: " + message);
            reader.close();
            in.close();
            socket.close();
            serverSocket.close();
           
            // Save message to database
            String url = "jdbc:mysql://localhost:3306/kafka";
            String user = "root";
            String password = "^loggeringlogging#2019$";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement1 = conn.createStatement();
            ResultSet resultSet1 = statement1.executeQuery("SELECT messageId FROM message order by messageId desc limit 1");
            if(resultSet1.next())
            {
            	String count123 = resultSet1.getString("messageId");
            	count1 = Integer.parseInt(count123)+1;
            }
            String sql = "INSERT INTO message (messageInfo, messageId, topicName) VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, message);
            statement.setInt(2, count1);
            statement.setString(3, topic);
            String sql1 = "INSERT INTO message1 (messageInfo, messageId, topicName) VALUES (?,?,?)";
            PreparedStatement statement2 = conn.prepareStatement(sql1);
            statement2.setString(1, message);
            statement2.setInt(2, count1);
            statement2.setString(3, topic);
            String sql2 = "INSERT INTO message2 (messageInfo, messageId, topicName) VALUES (?,?,?)";
            PreparedStatement statement3 = conn.prepareStatement(sql2);
            statement3.setString(1, message);
            statement3.setInt(2, count1);
            statement3.setString(3, topic);
            count1++;
            statement.executeUpdate();
            statement.close();
            statement2.executeUpdate();
            statement2.close();
            statement3.executeUpdate();
            statement3.close();
            conn.close();}
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }}
        
        
        public static void deleteAll()
    	{
    		try {
                String url = "jdbc:mysql://localhost:3306/kafka";
                String user = "root";
                String password = "^loggeringlogging#2019$";
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement statement = conn.createStatement();
                String sql = "TRUNCATE TABLE message";
                statement.executeUpdate(sql);
                System.out.println("Messages have been deleted");
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    	}
		@Override
		public void replicate(ArrayList<Message> message) {
			
		}
	
}