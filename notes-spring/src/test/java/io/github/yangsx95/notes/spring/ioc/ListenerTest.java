package io.github.yangsx95.notes.spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

public class ListenerTest {

    @Configuration
    @Import(MyApplicationListener.class)
    public static class ListenerTestConfig {
    }

    /**
     * 自定义监听器：
     *  1. 实现ApplicationListener接口
     *  2. 指定ApplicationListener泛型，代表指定该监听器的监听的事件类型
     *  3. 注册监听器到IoC容器中
     *  3. 当容器发布ApplicationEvent事件时，该类的onApplicationEvent方法就会被调用
     */
    @Component
    public static class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
        @Override
        public void onApplicationEvent(ApplicationEvent event) {
            System.out.println("收到spring内置事件：" + event);
        }
    }

    /**
     * 监听spring内置事件
     */
    @Test
    public void listenInnerEvent() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ListenerTestConfig.class);
        context.close();
    }

    @Configuration
    public static class  ListenInnerEventWithAnnotationTestConfig {

        @EventListener
        public void listenInnerEvent(ApplicationEvent event) {
            System.out.println("收到spring内置事件：" + event);
        }
    }

    /**
     * 使用@EventListener定义方法级别的监听器
     */
    @Test
    public void listenInnerEventWithAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ListenInnerEventWithAnnotationTestConfig.class);
        context.close();
    }

}
