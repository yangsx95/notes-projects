package com.yangsx95.notes.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * 事件发布类，也就是event provider，需要注入容器中ApplicationEventPublisher对象，并调用其 publish 方法发布一个事件
 * 另外，页提供了一个Aware接口可以获取到事件发布对象
 *
 * @author 杨顺翔
 * @since 2022/08/27
 */
@Component
public class MyEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
    public void sayHello() {
        System.out.println("hello");
        MyEvent e = new MyEvent("hello");
        applicationEventPublisher.publishEvent(e);
    }
}
