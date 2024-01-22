package me.feathers.service;

/**
 * Bean
 * @author Feahters
 * @version 1.0
 * @date 2019/3/27
 */
public class TestService {

    private String property;

    public TestService() {

    }

    public TestService(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public static TestService createInstance() {
        return new TestService("staticFactoryMethod");  
    }
    
}
