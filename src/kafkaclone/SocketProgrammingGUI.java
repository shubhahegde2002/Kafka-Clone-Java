package kafkaclone;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SocketProgrammingGUI extends JFrame{
	private JTextArea producerTextArea, topicTextArea, consumerTextArea, brokerTextArea;
    private JButton sendButton, retrieveButton,getAllButton,quitBrokerButton,deleteButton,recHeart;
    private static SocketProgrammingGUI instance;
    
    public SocketProgrammingGUI() {
    	instance = this;
        // Set up the GUI components
        producerTextArea = new JTextArea(5, 20);
        topicTextArea = new JTextArea(5, 20);
        consumerTextArea = new JTextArea(5, 20);
        brokerTextArea = new JTextArea(5,20);
        sendButton = new JButton("Send");
        retrieveButton = new JButton("Retrieve");
        getAllButton=new JButton("GetAll");
        quitBrokerButton=new JButton("Stop Broker");
        deleteButton=new JButton("Delete Messages");
        recHeart=new JButton("Receive heartbeat");
        Thread myThread = new Thread() {
            public void run() {
                Broker broker=new Broker();
				// do some work
            	try {
					broker.sendHeartbeat();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Interrupted");
				}
            }
        };

        myThread.start(); // start the thread

      // Add action listeners to the buttons
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 new Thread(() -> Producer.send( producerTextArea.getText() , topicTextArea.getText())).start();
            }
        });
       
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> Consumer.rec(null, consumerTextArea)).start();
            }
        });
        getAllButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new Thread(()->Consumer.getAll(topicTextArea.getText(),consumerTextArea)).start();
        	}
        });
        quitBrokerButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new Thread(()->Zookeeper.getInstance().leaderElection(Cluster.getInstance().BrokerList,brokerTextArea));
        		brokerTextArea.setText("Broker changed");
        		try {
                    Thread.sleep(5000);
                    myThread.interrupt(); // set the interrupt status flag
                } catch (InterruptedException e1) {
                    // handle the exception
                	System.out.println("Stopped ");
                }
        		
        	}
        });
       deleteButton.addActionListener(new ActionListener() {
    	   @Override
    	   public void actionPerformed(ActionEvent e) {
//       		System.out.println(Cluster.getInstance());
       		new Thread(()->Broker.deleteAll()).start();
    	   }
       });
        // Set up the layout of the GUI
        JPanel producerPanel = new JPanel();
        producerPanel.add(new JLabel("Producer:"));
        producerPanel.add(new JScrollPane(producerTextArea));
        producerPanel.add(sendButton);
       
        JPanel brokerPanel = new JPanel();
        brokerPanel.add(new JLabel("Topic:"));
        brokerPanel.add(new JScrollPane(topicTextArea));
        brokerPanel.add(quitBrokerButton);
        brokerPanel.add(new JLabel("Leader:"));
        brokerPanel.add(new JScrollPane(brokerTextArea));
        brokerPanel.add(deleteButton);        
        JPanel consumerPanel = new JPanel();
        consumerPanel.add(new JLabel("Consumer:"));
        consumerPanel.add(new JScrollPane(consumerTextArea));
        consumerPanel.add(retrieveButton);
        consumerPanel.add(getAllButton);
        
       
        setLayout(new GridLayout(3, 1));
        add(producerPanel);
        add(brokerPanel);
        add(consumerPanel);
       
        // Set up the JFrame
        setTitle("Socket Programming GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
	public static void main(String[] args) {
        new SocketProgrammingGUI();
    }

}
