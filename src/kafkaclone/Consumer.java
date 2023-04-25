package kafkaclone;
import javax.swing.*;
import java.sql.*;

public class Consumer extends AbstractConsumer implements Display,Subscription {
	
	public Consumer(String conId, boolean activeStatus) {
		super();
		this.conId = conId;
		this.activeStatus = activeStatus;
	}
	
	public Consumer()
	{
		
	}
	
	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	private String conId;
	boolean activeStatus;
	
	public static void getAll(String topic, JTextArea consumerTextArea)
	{
		try {
            String url = "jdbc:mysql://localhost:3306/kafka";
            String user = "root";
            String password = "^loggeringlogging#2019$";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM message where topicName='" + topic + "'");
            while (resultSet.next()) {
                String message = resultSet.getString("messageInfo");
                System.out.println("Received message from database: " + message);
                consumerTextArea.append(message + "\n");
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	public void subscribe(String topic)
	{
		topicsSubscribed.add(topic);
	}
	
	public void unsubscribe(String topic)
	{
		topicsSubscribed.remove(topic);
	}
	
	public void openReceivePort()
	{
     //open port for receiving the consumer message		
	}
	
	public static void rec(String[] args, JTextArea consumerTextArea){
	        try {
	            String url = "jdbc:mysql://localhost:3306/kafka";
	            String user = "root";
	            String password = "^loggeringlogging#2019$";
	            Connection conn = DriverManager.getConnection(url, user, password);
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM message order by messageId desc limit 1");
	            while (resultSet.next()) {
	                String message = resultSet.getString("messageInfo");
	                System.out.println("Received message from database: " + message);
	                consumerTextArea.append(message + "\n");
	            }
	            resultSet.close();
	            statement.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



