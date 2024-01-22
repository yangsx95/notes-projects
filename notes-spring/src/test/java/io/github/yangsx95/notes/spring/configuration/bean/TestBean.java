package io.github.yangsx95.notes.spring.configuration.bean;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候会执行该方法
 */
public class TestBean implements InitializingBean {

    private String username;
    private String url;
    private String password;

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("TestBean post construct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("TestBean pre destroy");
    }

    public void init() {
        System.out.println("TestBean 初始化。。。");
    }

    public void destroy() {
        System.out.println("TestBean 销毁。。。");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("TestBean afterPropertiesSet..");
    }
}
