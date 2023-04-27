# Kafka-Clone-Java

Apache Kafka is a distributed event store and stream-processing
platform, used for building real-time data pipeline and streaming
applications. 

It has become one of the most popular open-source
message brokers, due to its fast, reliable, and scalable nature. It
provides a publish-subscribe-based messaging system that is highly
scalable and fault-tolerant which allows for the efficient distribution
of messages from producers to consumers, making it a popular
choice for building real-time data pipelines and streaming
applications.
Kafka Clone aims to mimic the functionalities of Apache Kafka, and
provide a similar experience to Apache Kafka users.

## Implementation details :

-> Producer is responsible for sending records to the Kafka Cluster
through the broker, under a particular topic.

-> Consumer subscribes to a particular topic they are interested
in. Whenever the producer sends a record to the topic which is
stored in the database, the consumers who are subscribed to
that topic receive the message.

-> Broker writes the record to the respective topic which resides
in the database and helps facilitate the communication
between the producers and the consumers by sending the
subscribed records to prospective consumers

-> Zookeeper is responsible for the health of the Kafka cluster by
monitoring the functionalities of the broker through repeated
heartbeats sent at repeated time intervals. If the zookeeper
doesn’t receive the heartbeats from the active broker in the
stipulated threshold, the broker is assumed to be down.

-> Multiple brokers exist but only one of them is required to be
active. If this fails, we need to elect a new broker to take-over
the functionalities of the inactive broker. To ensure fault
tolerance in the system, we utilize a Leader Election Algorithm.

–> the Bully Algorithm – which assigns random integer values to
each of the broker other than the inactive one, after which it
chooses the one with maximum integer value as the new
brokers.

##Class Diagram
![Class](https://user-images.githubusercontent.com/73905298/234780250-b5d8fbbf-71b9-4461-8a83-31c019117e1b.jpeg)
