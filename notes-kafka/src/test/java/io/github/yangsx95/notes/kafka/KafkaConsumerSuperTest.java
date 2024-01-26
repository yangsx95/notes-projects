package io.github.yangsx95.notes.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerSuperTest {

    private KafkaConsumer<String, String> consumer;

    /**
     * 创建kafka 生产者客户端
     */
    @Before
    public void before() {
        Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "s2.svc.com:9092,s3.svc.com:9092,s4.svc.com:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // key 反序列化方式
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // value 反序列化
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "g1"); // 消费者组

        consumer = new KafkaConsumer<>(prop);
    }

    /**
     * 执行完毕后，关闭生产者
     */
    @After
    public void after() {
        consumer.close();
    }

    /**
     * 注意， 要先启动消费者， 先订阅报社才会给你发送消息
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Test
    public void receive() {
        // 订阅主题Topic02的第一个分区
        List<TopicPartition> partitionList = Collections.singletonList(new TopicPartition("Topic02", 0));
        consumer.assign(partitionList);
        // 指定消费分区位置
//        consumer.seekToBeginning(partitionList); // 从头读取,也就是 offset=0
        consumer.seek(new TopicPartition("Topic02", 0), 1); // offset=1 从第一个位置读取， 指定分区为0

        // 便利消息队列
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));// 每隔一秒， 取出一条消息
            if (!records.isEmpty()) { // 如果去取了数据
                for (ConsumerRecord<String, String> record : records) {
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
