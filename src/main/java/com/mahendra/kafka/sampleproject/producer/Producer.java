package com.mahendra.kafka.sampleproject.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * A Simple Kafka Producer that will add few records to a topic
 */
public class Producer {
    private static final String TOPIC = "test-topic";

    public static void main(String[] args) {
        Properties settings = setUpProperties();
        KafkaProducer<String, String> producer = new KafkaProducer<>(settings);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Stopping Producer.");
                producer.close();
            }
        });

        for(int i = 0; i < 5; i++) {
            final String key = "key-" + i;
            final String value = "value-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, value);

            producer.send(record);
        }
    }

    private static Properties setUpProperties() {
        Properties settings = new Properties();
        settings.put(ProducerConfig.CLIENT_ID_CONFIG, "basic-producer");
        // update the bootstrap server
        settings.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return settings;
    }
}
