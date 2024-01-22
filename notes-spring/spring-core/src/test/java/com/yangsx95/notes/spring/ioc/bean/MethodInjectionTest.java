package com.yangsx95.notes.spring.ioc.bean;

import com.yangsx95.notes.spring.pojo.A;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 方法注入
 */
public class MethodInjectionTest {

    /**
     * 问题演示
     */
    @Test
    public void problem() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("com/yangsx95/notes/spring/ioc/bean/application-aware.xml");
        Assert.assertSame(ac.getBean("A", A.class).getB(), ac.getBean("A", A.class).getB());
    }

    @Test
    public void aware() {

    }


}
