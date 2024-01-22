package me.feathers.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 后置处理器， 通常用于将创建出来的bean进行验证或者代理
 *
 * @author Feahters
 * @version 1.0
 * @date 2019/3/28
 */
public class MyBeanProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor： bean的后置处理器，目标bean-" + beanName + ", 在初始化之前执行吗，初始化是指InitializingBean#beforePropertiesSet");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor： bean的后置处理器，目标bean-" + beanName + "，在初始化之后执行");
        return bean;
    }
}
