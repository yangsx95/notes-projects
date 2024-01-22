package me.feathers;

import me.feathers.service.AccountService;
import me.feathers.service.TestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 实例化bean
 *
 * @author Feahters
 * @version 1.0
 * @date 2019/3/31
 */
public class InstantiationBeansTest {
    private ApplicationContext applicationContext;

    @Before
    public void applicationContext() {
        applicationContext = new ClassPathXmlApplicationContext("instantiation-beans.xml");
    }

    @Test
    public void emptyArgsConstruct() {
        TestService service = (TestService) applicationContext.getBean("service");
        Assert.assertNotNull(service);
    }

    @Test
    public void hasArgsConstruct() {
        TestService serviceHasArgs = (TestService) applicationContext.getBean("serviceHasArgs");
        Assert.assertNotNull(serviceHasArgs);
        Assert.assertEquals("testProperty", serviceHasArgs.getProperty());
    }
    
    @Test
    public void serviceStaticFactory() {
        TestService testService = (TestService) applicationContext.getBean("serviceStaticFactory");
        Assert.assertNotNull(testService);
        Assert.assertEquals("staticFactoryMethod" , testService.getProperty());
    }
    
    @Test
    public void serviceInstanceFactory() {
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        Assert.assertNotNull(accountService);

        TestService testService = (TestService) applicationContext.getBean("testService");
        Assert.assertNotNull(testService);
    }

    
}
