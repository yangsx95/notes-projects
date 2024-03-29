package io.github.yangsx95.notes.json.fastjson.enumcvt;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

public class MyParseConfig extends ParserConfig {
    /*@Override
    public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {
        ObjectDeserializer derializer;
        if (clazz.isEnum()) {
            Class<?> deserClass = null;
            JSONType jsonType = clazz.getAnnotation(JSONType.class);
            if (jsonType != null) {
                deserClass = jsonType.deserializer();
                try {
                    derializer = (ObjectDeserializer) deserClass.newInstance();
                    this.putDeserializer(type, derializer);
                    return derializer;
                } catch (Throwable error) {
                    // skip
                }
            }
            //这里替换了原来的反序列化器。
            derializer = new ResultEnumSerializer();
            return derializer;
        }
        return super.getDeserializer(clazz, type);
    }*/

    @Override
    public ObjectDeserializer getDeserializer(Class<?> clazz, Type type) {
        JSONType jsonType = clazz.getAnnotation(JSONType.class);
        // 对CommonEnum枚举进行处理
        if (clazz.isEnum() && jsonType == null && BaseEnum.class.isAssignableFrom(clazz)) {
            return new ResultEnumSerializer();
        }
        return super.getDeserializer(clazz, type);
    }
}
