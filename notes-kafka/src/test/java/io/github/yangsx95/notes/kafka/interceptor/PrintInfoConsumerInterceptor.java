package io.github.yangsx95.notes.kafka.interceptor;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

/**
 * 消费者消费拦截器， 用于拦截处理制定的消息
 */
public class PrintInfoConsumerInterceptor implements ConsumerInterceptor<String, Integer> {

    /**
     * KafkaConsumer#poll(Duration)返回记录之前，该方法被调用。
     */
    @Override
    public ConsumerRecords<String, Integer> onConsume(ConsumerRecords<String, Integer> records) {
        System.out.println("onConsumer == " + records);
        return records;
    }

    /**
     * offset位移被提交时，该方法被调用。
     */
    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        System.out.println("当前消费位移： " + offsets);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
