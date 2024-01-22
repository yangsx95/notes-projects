package com.yangsx95.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@SpringBootApplication
public class ConsumerMovieApplication {

    /**
     * 引入RestTemplate，是Spring对Rest接口的请求工具类
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieApplication.class, args);
    }
}