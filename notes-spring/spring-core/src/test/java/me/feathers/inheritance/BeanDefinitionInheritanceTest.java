package me.feathers.inheritance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanIsAbstractException;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Bean定义的继承
 *
 * @author Feahters
 * @version 1.0
 * @date 2019/4/5
 */
public class BeanDefinitionInheritanceTest {

    ApplicationContext applicationContext;

    /**
     * 编程方式创建子Bean
     */
    @Test
    public void javaCodeChildBeanDefinition() {
        GenericApplicationContext ac = new GenericApplicationContext();
        
        // 定义父Bean
        GenericBeanDefinition parentBean = new GenericBeanDefinition();
        parentBean.setBeanClass(ParentTestBean.class);
        parentBean.setAbstract(true);
        parentBean.getPropertyValues().add("name", "父亲");
        parentBean.getPropertyValues().add("age", 50);
        ac.registerBeanDefinition("parentBean", parentBean);
        
        // 定义子Bean，并继承父bean
        ChildBeanDefinition childBeanDefinition = new ChildBeanDefinition("parentBean");
        childBeanDefinition.setParentName("parentBean");
        childBeanDefinition.getPropertyValues().add("name", "儿子");
        ac.registerBeanDefinition("sonBean", childBeanDefinition);
    }

    @Before
    public void loadApplicationContext() {
        applicationContext = new ClassPathXmlApplicationContext("inheritance/inheritance.xml");
    }

    /**
     * 被指定为abstract=true的bean，即使本身不是抽象类，也无法被初始化
     * 将会抛出BeanIsAbstractException异常
     * 当然未被指定的非abstract类是没有问题的
     */
    @Test(expected = BeanIsAbstractException.class)
    public void abstractParentCannotGetBean() {
        Object inheritedTestBean = applicationContext.getBean("parentTestBean");
    }

    /**
     * 子Bean会继承父Bean的属性，也会重写父Bean的属性
     */
    @Test
    public void childBeanInheritanceProperty() {
        DerivedTestBean derivedTestBean = (DerivedTestBean) applicationContext.getBean("childTestBean");
        
        Integer age = derivedTestBean.getAge();
        Assert.assertEquals(Integer.valueOf(1), age);

        String name = derivedTestBean.getName();
        Assert.assertEquals("override", name);
    }
}
