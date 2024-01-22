package me.feathers.dependencies;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Idref 属性的特点
 * @author Feahters
 * @version 1.0
 * @date 2019/4/1
 */
public class IdrefTest {
    
    @Test
    public void idref() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dependencies/idref.xml");
        System.out.println("----ApplicationContext创建完毕-----");
        ClientBean clientBean = (ClientBean) ac.getBean("theClientBean");
        Assert.assertNotNull(clientBean);
    }
    
    @Test
    public void ref() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dependencies/ref.xml");
        System.out.println("----ApplicationContext创建完毕-----");
        ClientBean clientBean = (ClientBean) ac.getBean("theClientBean");
        Assert.assertNotNull(clientBean);
    }
    
    @Test
    public void value() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:dependencies/value.xml");
        System.out.println("----ApplicationContext创建完毕-----");
        ClientBean clientBean = (ClientBean) ac.getBean("theClientBean");
        Assert.assertNotNull(clientBean);
        System.out.println(clientBean.getTargetName());
    }
}
