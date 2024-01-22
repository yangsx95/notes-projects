package com.yangsx95.notes.spring.ioc.applicationContext;

import com.yangsx95.notes.spring.ioc.applicationContext.config.AppConfig;
import com.yangsx95.notes.spring.ioc.applicationContext.config.ConfigComponent;
import com.yangsx95.notes.spring.pojo.SimplePojo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfigApplicationContextTests {

    @Test
    @Lookup
    public void testConfigurationConstructBuild() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        SimplePojo simplePojo = ac.getBean("simplePojo", SimplePojo.class);
        Assert.assertEquals("张三", simplePojo.getName());
    }

    @Test
    public void testConfigurationRegisterBuilder() {
        String canonicalName = AnnotationConfigApplicationContextTests.class.getCanonicalName();
        System.out.println(canonicalName);
    }

    @Test
    public void testComponentConstructBuild() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ConfigComponent.class);
        SimplePojo simplePojoBean = ac.getBean("simplePojoBean", SimplePojo.class);
        Assert.assertEquals("李四", simplePojoBean.getName());
    }
}