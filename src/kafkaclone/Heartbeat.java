package kafkaclone;

import java.io.BufferedReader;

//public class Heartbeat {
//	int heartbeat;
//
//	public Heartbeat(int heartbeat) {
//		super();
//		this.heartbeat = heartbeat;
//	}
//	
//	public Heartbeat()
//	{
//		
//	}
//
//	public int getHeartbeat() {
//		return heartbeat;
//	}
//
//	public void setHeartbeat(int heartbeat) {
//		this.heartbeat = heartbeat;
//	}
//	
//	void recHeartbeat()
//	{
//		//heart beat is a flag for failure detection (;-
//	}
//	
//	void faildetect()
//	{
//		
//	}
//	
//
//}


//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.Socket;
//
//public class Heartbeat implements Runnable {
//    private Socket socket;
//    private SocketProgrammingGUI gui;
//
//    public Heartbeat(Socket socket, SocketProgrammingGUI gui) {
//        this.socket = socket;
//        this.gui = gui;
//    }
//
//    @Override
//    public void run() {
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            while (true) {
//                String message = reader.readLine();
//                if (message != null) {
//                    gui.addBrokerMessage(message);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//   
//}

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Heartbeat implements Runnable {
    private Socket socket;
    private ObjectInputStream objectInputStream;
    public Heartbeat() {}
    public Heartbeat(Socket socket) {
        this.socket = socket;
    }
    public void recHeartbeat() {
        try {
            
            while(true) {
            ServerSocket serverSocket = new ServerSocket(1257);
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String message = reader.readLine();
            System.out.println("Receiving heartbeat");
            reader.close();
            in.close();
            socket.close();
            serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        

    }

    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            while (true) {
                String signal = (String) objectInputStream.readObject();

                if (signal.equals("HEARTBEAT")) {
                    System.out.println("Received heartbeat signal from broker at " + new Date());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}