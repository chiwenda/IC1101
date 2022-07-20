package com.ic1101.middle.kafka.nativer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author cwd
 * @date 7/20/22 5:30 PM
 */
public class ProducerMain {

    public static Producer<String, String> createProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("acks", "1");
        properties.put("retries", 3);
        properties.put("key.serializer", StringSerializer.class.getName()); // 消息的 key 的序列化方式
        properties.put("value.serializer", StringSerializer.class.getName()); // 消息的 value 的序列化方式
        return new KafkaProducer<>(properties);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建 KafkaProducer 对象
        Producer<String, String> producer = createProducer();
        // 创建消息。传入的三个参数，分别是 Topic ，消息的 key ，消息的 message 。
        ProducerRecord<String, String> message = new ProducerRecord<>("TestTopic", "key", "cwd");
        Future<RecordMetadata> sendResultFuture = producer.send(message);

        RecordMetadata result = sendResultFuture.get();
        System.out.println("message sent to " + result.topic() + ", partition " + result.partition() + ", offset " + result.offset());
    }
}
