package com.yangsx95.notes.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件对象
 *
 * @author 杨顺翔
 * @since 2022/08/27
 */
public class MyEvent extends ApplicationEvent {

    private static final long serialVersionUID = 3223648309069468451L;

    public MyEvent(String message) {
        super(message);
    }

}
