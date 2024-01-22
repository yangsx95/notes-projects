package com.yangsx95.notes.spring.ioc.util;

import org.springframework.beans.factory.ListableBeanFactory;

public class Util {

    public static void printApplicationContextBeans(ListableBeanFactory listableBeanFactory) {
        for (String beanDefinitionName : listableBeanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

}
