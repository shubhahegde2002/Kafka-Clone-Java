package kafkaclone;

public interface Subscription {
	void subscribe(String topic);
	void unsubscribe(String topic);

}
