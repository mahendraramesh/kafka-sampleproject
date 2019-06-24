# Apache Kafka - Simple examples of Kafka Producer and Consumer

### Start zookeeper

```
 bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```

### Start Kafka Server

```
bin/windows/kafka-server-start.bat config/server.properties
```

### Create a new topic(test-topic) with single partition and replica

```
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test-topic
```

### List the topics

```
bin/windows/kafka-topics.bat --list --bootstrap-server localhost:9092
```

### Start a producer and sends messages from the command line

```
bin/windows/kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic
```

### Start a consumer and receive messages
```
bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 --from-beginning --topic test-topic
```