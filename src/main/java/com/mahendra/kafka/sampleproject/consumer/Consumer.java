package com.mahendra.kafka.sampleproject.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * A Simple Kafka Consumer that polls for records in a topic
 */
public class Consumer {
    private static final String TOPIC = "test-topic";

    public static void main(String[] args) {
        Properties settings = setUpProperties();

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(settings);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping consumer");
        }));

        consumer.subscribe(Collections.singletonList(TOPIC));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record : records) {
                System.out.printf("New Message: Offset = %d, Key = %s, Value = %s\n",
                        record.offset(), record.key(), record.value());
            }
        }
    }

    private static Properties setUpProperties() {
        Properties settings = new Properties();
        settings.put(ConsumerConfig.GROUP_ID_CONFIG, "basic-producer");
        settings.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        settings.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        settings.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        settings.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        settings.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        settings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return settings;
    }
}
