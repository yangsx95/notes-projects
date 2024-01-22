package io.github.yangsx95.notes.spring.ioc;

import io.github.yangsx95.notes.spring.ioc.util.Util;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class AnnotationComponentScanTest {

    @Configuration
    @ComponentScan("com.yangsx95.notes.spring.ioc.scan")
    static class ComponentScanAnnotationTestConfig {
    }

    @Test
    public void testComponentScan() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanAnnotationTestConfig.class);
        Util.printApplicationContextBeans(context);
    }

}
