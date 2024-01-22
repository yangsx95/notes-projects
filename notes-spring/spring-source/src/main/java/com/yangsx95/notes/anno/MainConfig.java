package com.yangsx95.notes.anno;

import com.yangsx95.notes.anno.dto.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainConfig {

    @Bean
    public Person lisi() {
        Person person = new Person();
        person.setName("李四");
        person.setAge(40);
        return person;
    }

}
