package io.github.yangsx95.notes.spring.ioc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 对Bean命名
 * @author Feahters
 * @version 1.0
 * @date 2019/3/31
 */
public class NameBeansTest {

    private ApplicationContext applicationContext;

    @Before
    public void applicationContext() {
        applicationContext= new ClassPathXmlApplicationContext("name-beans.xml");
    }
    
    @Test
    public void id() {
        Assert.assertNotNull(applicationContext.getBean("service"));
    }
    
    @Test
    public void names() {
        Assert.assertNotNull(applicationContext.getBean("service2"));
    }
    
    @Test
    public void alias () {
        Assert.assertNotNull(applicationContext.getBean("serviceAlias"));
    }
    
}
