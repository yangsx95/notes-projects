package io.github.yangsx95.notes.kafka.offset;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 手动提交offset
 */
public class ManualCommitOffsetConsumerDemo {

    private KafkaConsumer<String, String> consumer;

    /**
     * 创建kafka 生产者客户端
     */
    @Before
    public void before() {
        Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "s2.svc.com:9092,s3.svc.com:9092,s4.svc.com:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "g-offset");
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // 关闭自动提交
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
     * 注意， 要先启动消费者，先订阅报社才会给你发送消息
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Test
    public void receive() {
        // 订阅主题Topic02
        consumer.subscribe(Collections.singleton("topic-quickstart")); // 也可以传入正则， 订阅匹配正则的主题
        // 便利消息队列
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));// 每隔一秒，从队列中去除未消费的消息，可能是0条也可能是n条
            if (!records.isEmpty()) { // 如果去取了数据
                // 手动记录记录偏移量
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("消费消息：\t\ttopic=" + record.topic()
                            + "\t\tpartition=" + record.partition() // 分区
                            + "\t\toffset=" + record.offset() // 消费队列索引
                            + "\t\ttimestamp=" + record.timestamp() // 消息发送时间
                            + "\t\tkey=" + record.key()
                            + "\t\tvalue=" + record.value()
                    );
                    // 此次消费完毕
                    Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                    offsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset()));
                    // 消费者异步提交offset，需要一个OffsetCommitCallback回调
                    consumer.commitAsync(offsets, (offsets1, exception) -> System.out.println("完成：" + record.offset() + "提交！"));
                }
            }
        }
    }

}
