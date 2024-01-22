package me.feathers.lifecycle;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * spring bean生命周期测试
 *
 * @author Feahters
 * @version 1.0
 * @date 2019/3/28
 */
public class LifecycleTest {

    @Test
    public void testLifeCycle() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:lifecycle/bean-lifecycle.xml");
        Object bean = ac.getBean("lifecycleTestBean");
        Assert.assertNotNull(bean);
        Assert.assertTrue(bean instanceof BeanLifecycleTestBean);
        ac.close(); // 关闭容器，此时bean会被销毁
    }

}
