package io.github.yangsx95.notes.kafka.serializer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerDemo {

    private KafkaProducer<String, User> producer;

    @Before
    public void before() {
        Properties prop1 = new Properties();
        prop1.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "s2.svc.com:9092,s3.svc.com:9092,s4.svc.com:9092");
        AdminClient kafkaAdminClient = KafkaAdminClient.create(prop1);
        kafkaAdminClient.createTopics(Collections.singleton(new NewTopic("Topic-serializer", 1, (short) 1)));

        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "s2.svc.com:9092,s3.svc.com:9092,s4.svc.com:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // key 序列化方式
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JdkSerializer.class.getName()); // value 序列化，指定我们定义的方式
        producer = new KafkaProducer<>(prop);
    }

    @After
    public void after() {
        producer.close();
    }

    @Test
    public void sendRecord() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            User user = new User(i, "王" + i, new Date());
            ProducerRecord<String, User> record = new ProducerRecord<>("topic-serializer", "key" + i, user);
            Future<RecordMetadata> send = producer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println("发送成功 " + recordMetadata + "  " + record.value().toString());
        }
    }
}
