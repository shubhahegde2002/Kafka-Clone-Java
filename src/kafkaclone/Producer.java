package kafkaclone;

import java.net.InetAddress;
import java.util.ArrayList;
import java.io.*;
import java.net.InetAddress;
import java.net.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
import java.net.*;

public class Producer {
	private String prodId;
	boolean activeStatus;
	public Producer()
	{
		
	}
	public Producer(String prodId, boolean activeStatus) 
	{
		super();
		this.prodId = prodId;
		this.activeStatus = activeStatus;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public boolean isActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	static int count1=0;
	static void send(String message, String topic) 
	{
		try {
            Socket socket = new Socket("localhost", 1234);
            OutputStream out = socket.getOutputStream();
            while(true) {
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(message);
            writer.println(topic);
            writer.close();
            out.close();
            socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	String receivePartition(String l)
	{		
		return "receive";
	}
	
}


