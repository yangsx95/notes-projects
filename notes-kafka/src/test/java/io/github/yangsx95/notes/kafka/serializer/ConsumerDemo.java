package io.github.yangsx95.notes.kafka.serializer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {

    private KafkaConsumer<String, User> consumer;

    @Before
    public void before() {
        Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "s2.svc.com:9092,s3.svc.com:9092,s4.svc.com:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // key 反序列化方式
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JdkDeserializer.class.getName()); // value 反序列化，指定我们定义的方式
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "g-serializer"); // 消费者组

        consumer = new KafkaConsumer<>(prop);
        consumer.subscribe(Collections.singleton("topic-serializer")); // 订阅
    }

    @After
    public void after() {
        consumer.close();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Test
    public void receive() {
        while (true) {
            ConsumerRecords<String, User> records = consumer.poll(Duration.ofSeconds(1));// 每隔一秒， 取出一条消息
            if (!records.isEmpty()) { // 如果去取了数据
                for (ConsumerRecord<String, User> record : records) {
                    System.out.println("消费消息： topic=" + record.topic()
                            + "  partition=" + record.partition() // 分区
                            + "  offset=" + record.offset() // 消费队列索引
                            + "  timestamp=" + record.timestamp() // 消息发送时间
                            + "  key=" + record.key()
                            + "  value=" + record.value()
                    );
                }
            }
        }
    }

}
