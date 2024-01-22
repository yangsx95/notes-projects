package io.github.yangsx95.notes.spring.configuration.bean;

import org.springframework.stereotype.Component;

@Component("testComponent")
public class TestComponent {

    public void sayHello() {
        System.out.println("TestComponent sayHello...");
    }
    
}
