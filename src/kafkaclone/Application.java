package kafkaclone;
import java.io.IOException;
//import java.lang.*;
import javax.swing.SwingUtilities;

public class Application {
    public static void main(String[] args) {
        // Start the GUI on the event dispatch thread
        new Thread(()->{
        	
        	Heartbeat h1=new Heartbeat();
            h1.recHeartbeat();
            System.out.println("receiving");
        }).start();
        new Thread(()->{
			try {
				new KafkaClient("123456789").startKafka();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        SwingUtilities.invokeLater(() -> new SocketProgrammingGUI());
  
        // Start the broker on a separate thread
        new Thread(() -> Broker.recStore()).start();
        
    }
}
