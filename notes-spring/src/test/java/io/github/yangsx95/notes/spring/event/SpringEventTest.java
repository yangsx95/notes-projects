package io.github.yangsx95.notes.spring.event;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Event 事件处理，观察者模式
 */
@ComponentScan
@Configuration
public class SpringEventTest {

    @Test
    public void testContextStartedEvent() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringEventTest.class);
        context.start();
        // 业务的某个方法触发了某个事件
        // 如果没有加@Async注解，时间处理就是同步的，他与观察者模式别无二致
        MyEventPublisher myEventPublisher = context.getBean(MyEventPublisher.class);
        myEventPublisher.sayHello();
        // 停止容器
        context.stop();
        // 重新打开容器
        context.start();
        // 关闭容器
        context.close();
    }
    
}
