package me.feathers.demo;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class SpringBootMessageConvertersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMessageConvertersApplication.class, args);
    }


    /**
     * 配置消息转换器
     * 方式一：配置json转换器
     */
    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        *//*
     * 1. 定义convert消息转换对象
     * 2. 添加fastjson的配置信息 是否格式化返回的json数据
     * 3. 在convert添加配置信息
     * 4. 将convert中添加converters中
     *//*
        //1.定义convert消息转换对象
        FastJsonHttpMessageConverter fjhmc = new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息 是否格式化返回的json数据
        FastJsonConfig fjc = new FastJsonConfig();
        fjc.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3.在convert添加配置信息
        fjhmc.setFastJsonConfig(fjc);
        //4.将convert中添加converters中
        converters.add(fjhmc);
    }*/

    // 方式二:注入一个json转换器的Bean
    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverter() {
        //1.定义convert消息转换对象
        FastJsonHttpMessageConverter fjhmc = new FastJsonHttpMessageConverter();
        //2.添加fastjson的配置信息 是否格式化返回的json数据
        FastJsonConfig fjc = new FastJsonConfig();
        fjc.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3.在convert添加配置信息
        fjhmc.setFastJsonConfig(fjc);
        //4.返回消息转换器，将其加入到ioc容器中
        return fjhmc;
    }
}
