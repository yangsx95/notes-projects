package com.yangsx95.notes.spring.event;

import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

/**
 * 此外，Spring内部提供了几个标准的事件
 * (较高版本的Spring在定义Listener时，不用集成ApplicationListener了，可以直接给方法增加 @EventListener注解标记该方法是一个时间处理方法)
 *
 * @author 杨顺翔
 * @since 2022/08/27
 */
@Component
public class StandardEventListener {

    /**
     * spring context 已启动事件处理
     */
    @EventListener(ContextStartedEvent.class)
    public void contextStated() {
        System.out.println("context started");
    }

    /**
     * 所有Bean已经刷新事件
     */
    @EventListener
    public void contextRefreshed(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("context refreshed " + contextRefreshedEvent.getSource());
    }

    /**
     * 容器已经停止运行
     */
    @EventListener(ContextStoppedEvent.class)
    public void contextStopped() {
        System.out.println("context stopped");
    }

    /**
     * 容器已经完全关闭
     */
    @EventListener(ContextClosedEvent.class)
    public void contextClosed() {
        System.out.println("context closed");
    }
}
