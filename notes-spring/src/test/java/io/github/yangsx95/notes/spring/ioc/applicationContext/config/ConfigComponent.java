package io.github.yangsx95.notes.spring.ioc.applicationContext.config;

import io.github.yangsx95.notes.spring.pojo.SimplePojo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConfigComponent {

    @Bean
    public SimplePojo simplePojoBean() {
        SimplePojo pojo = new SimplePojo();
        pojo.setName("李四");
        pojo.setAge(11);
        pojo.setHobby(Arrays.asList("学习", "捣蛋"));
        return pojo;
    }

}
