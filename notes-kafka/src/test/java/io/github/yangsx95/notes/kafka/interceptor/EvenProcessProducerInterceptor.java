package io.github.yangsx95.notes.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 消息消息拦截器，用于拦截生产者发送给的消息并处理，这里处理偶数并乘以二
 */
public class EvenProcessProducerInterceptor implements ProducerInterceptor<String, Integer> {

    /**
     * 每条消息发送之前，都会调用此方法， 在此方法中，可以拦截要发送的消息， 修改它的状态
     */
    @Override
    public ProducerRecord<String, Integer> onSend(ProducerRecord<String, Integer> record) {
        if (record.value() % 2 == 0) { // 如果是偶数， 乘以2返回
            return new ProducerRecord<>(record.topic(), record.key(), record.value() * 2);
        }
        return record;
    }

    /**
     * 每条消息发送之后， 无论成功还是失败，都会调用此方法， 所以此方法中不应做一些耗时操作
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        System.out.println("metadata: " + metadata + "  exception:" + exception);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
