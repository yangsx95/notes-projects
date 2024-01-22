package com.yangsx95.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * EnableFeignClients 启用feign客户端
 * 
 * defaultConfiguration 用于指定feign的全局默认配置
 * 默认情况下，yml配置优先于java代码，如果想要让java优于yml，可以使用 feign.client.default-to-properties=false 设置
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@EnableFeignClients// (defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class ConsumerMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieApplication.class, args);
    }
}