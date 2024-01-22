package com.yangsx95.notes.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 实现ApplicationListener接口，定义事件监听器，注入到容器中，监控某类型事件
 *
 * @author 杨顺翔
 * @since 2022/08/27
 */
@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent contextStartedEvent) {
        System.out.println("MyEventListener监听到事件: " + contextStartedEvent.toString());
    }
}
