package kafkaclone;

import java.util.ArrayList;

public interface Replication {
	void replicate(ArrayList<Message> message);

}
