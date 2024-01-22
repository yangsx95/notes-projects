package io.github.yangsx95.notes.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.*;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Bean 生命周期测试
 * <p>
 * 注意： Aware接口是Spring为bean本身提供的感知接口
 * 默认情况下，Bean无法感知到自己的Name、Classloader、ApplicationContext等信息的，需要借助这些接口来获取并作出相应的操作
 *
 * @author Feahters
 * @version 1.0
 * @date 2019/3/28
 */
public class BeanLifecycleTestBean implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware,
        ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware, ApplicationContextAware, ServletContextAware,
        InitializingBean {

    private String property1;

    private PropertyBean propertyBean;
    
    private String constructArgs;

    private ConstructBean constructBean;
    
    public BeanLifecycleTestBean(String constructArgs, ConstructBean constructBean) {
        this.constructArgs = constructArgs;
        this.constructBean = constructBean;
        System.out.println("construct 创建对象");
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware：感知到bean的ClassLoader");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactory：感知到IoC容器, 可以使用容器管理bean");
    }

    public void setBeanName(String s) {
        System.out.println("BeanNameAware:感知到id-" + s);
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("ResourceLoaderAware:感知到Bean的ResourceLoader,可以使用resourceLoader加载资源");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet: 初始化时执行自定义方法，此效率较高，但是与Bean耦合高");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware: 感知到ApplicationContext, 类似BeanFactory");
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("ApplicationEventPublisherAware#setApplicationEventPublisher: 事件发布对象感知,获取bean的事件发布者");
    }

    public void setMessageSource(MessageSource messageSource) {
        System.out.println("MessageSource：MessageSource对象感知，此对象通常用于消息处理，比如国际化");
    }

    public void setServletContext(ServletContext servletContext) {
        System.out.println("ServletContext#setServletContext: ServletContext对象感知");
    }

    public void init() {
        System.out.println("init 初始化调用方法");
    }

    public void destroy() {
        System.out.println("destroy 结束被销毁方法");
    }

    public void setProperty1(String property1) {
        System.out.println("set property: bean的setter方法");
        this.property1 = property1;
    }

    public String getProperty1() {
        return property1;
    }
    
    public void setPropertyBean(PropertyBean propertyBean) {
        this.propertyBean = propertyBean;
        System.out.println("set bean property，调用setter注入复杂属性");
    }

    public PropertyBean getPropertyBean() {
        return propertyBean;
    }
}
