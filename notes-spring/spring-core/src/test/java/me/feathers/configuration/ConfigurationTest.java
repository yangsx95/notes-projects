package me.feathers.configuration;

import me.feathers.configuration.bean.AppContext;
import me.feathers.configuration.bean.TestBean;
import me.feathers.configuration.bean.TestComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        TestBean testBean = (TestBean) ac.getBean("testBean");
        testBean.sayHello();

        // 上面的执行结果总结， 这几种初始化方法中，按照如下顺序执行
        // 1. 构造器
        // 2. @PostConstruct
        // 3. afterPropertiesSet
        // 4. init-method
        
        
        // 使用Component和ComponentScan
        TestComponent testComponent = (TestComponent) ac.getBean("testComponent");
        testComponent.sayHello();
        
        // 将AppContext对象直接实例化后，放入到IoC中，只适合拥有无参构造的对象
        ac.register(AppContext.class);
        
        // AnnotationConfigWebApplicationContext 支持web应用
    }
}
