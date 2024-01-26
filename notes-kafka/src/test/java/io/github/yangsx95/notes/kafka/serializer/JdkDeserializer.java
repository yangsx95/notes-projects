package io.github.yangsx95.notes.kafka.serializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * 使用Java序列化。反序列化
 */
public class JdkDeserializer implements Deserializer<Object> {

    /**
     * 启动时，获取生产者信息的回调. 在反射创建UserDefineDeserializer会被调用，只会被调用一次
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        System.out.println("--configure--" + configs + " " + isKey);
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return SerializationUtils.deserialize(data);
    }

    /**
     * 生产者关闭时的回调，通常用于资源释放
     */
    @Override
    public void close() {
        System.out.println("--close--");
    }
}
