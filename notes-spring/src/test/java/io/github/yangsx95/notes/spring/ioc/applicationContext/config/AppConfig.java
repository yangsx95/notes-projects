package io.github.yangsx95.notes.spring.ioc.applicationContext.config;

import io.github.yangsx95.notes.spring.pojo.SimplePojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean
    public SimplePojo simplePojo() {
        SimplePojo pojo = new SimplePojo();
        pojo.setName("张三");
        pojo.setAge(12);
        pojo.setHobby(Arrays.asList("篮球", "游戏"));
        return pojo;
    }

}
