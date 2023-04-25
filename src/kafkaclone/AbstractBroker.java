package kafkaclone;

import java.util.ArrayList;

public class AbstractBroker {
	ArrayList<Message> message;
	void sendHeartbeat() throws InterruptedException{}
	public AbstractBroker cloneBroker() {
		return new AbstractBroker();
	}

}
