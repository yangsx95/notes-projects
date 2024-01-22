package io.github.yangsx95.notes.spring.configuration;

import io.github.yangsx95.notes.spring.configuration.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * spring 使用 @me.feathers.configuration 注解，配置spring
 */
@Configuration
//既然@Bean的作用是注册bean对象，那么完全可以使用@Component、@Controller、@Service、@Ripository等注解注册bean，当然需要配置@ComponentScan注解进行自动扫描。
@ComponentScan(basePackages = {"me.feathers"})
public class SpringConfiguration {

    public SpringConfiguration() {
        System.out.println("TestConfiguration配置对象初始化。。。");
    }

    /*
     * @code{@Bean} 注解可以注册Bean到IoC容器中，并指定销毁和初始化方法
     * name和value的作用相同，当只有一个name属性时，可以使用value替代，这样不用谢 name= 或者 value=
     *
     * @Scope 作用域，包含一下几个模式（对应的xml<me.feathers.bean id="role" class="xxx" scope="singleton"/>）
     * singleton 默认，单例模式  IOC容器中只会存在一个共享的bean实例
     * prototype 作用域部署的bean，每一次请求（将其注入到另一个bean中，或者以程序的方式调用容器的 getBean()方法）都会产生一个新的bean实例，相当与一个new的操作
     * request 表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP request内有效
     * session 作用域表示该针对每一次HTTP请求都会产生一个新的bean，同时该bean仅在当前HTTP session内有效
     * global session 作用域类似于标准的HTTP Session作用域，不过它仅仅在基于portlet的web应用中才有意义
     */
    @Bean(name = "testBean", autowire = Autowire.BY_NAME, initMethod = "init", destroyMethod = "destroy")
    @Scope()
    public TestBean testBean() {
        return new TestBean();
    }

}
