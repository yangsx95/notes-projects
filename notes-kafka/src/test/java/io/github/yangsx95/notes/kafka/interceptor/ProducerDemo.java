package io.github.yangsx95.notes.kafka.interceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerDemo {

    private KafkaProducer<String, Integer> producer;

    /**
     * 创建kafka 生产者客户端
     */
    @Before
    public void before() {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "s2.svc.com:9092,s3.svc.com:9092,s4.svc.com:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key 序列化方式
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName()); // value 序列化
        prop.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, EvenProcessProducerInterceptor.class.getName()); // value 序列化

        producer = new KafkaProducer<>(prop);
    }

    /**
     * 执行完毕后，关闭生产者
     */
    @After
    public void after() {
        producer.close();
    }

    /**
     * 发送一百条消息
     */
    @Test
    public void sendRecord() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, Integer> record = new ProducerRecord<>("topic-interceptor", "key" + i, i);
            Future<RecordMetadata> send = producer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println("发送成功 " + recordMetadata.toString());
        }
    }

}
