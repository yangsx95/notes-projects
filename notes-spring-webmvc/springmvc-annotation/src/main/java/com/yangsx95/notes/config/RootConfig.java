package com.yangsx95.notes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

// 根容器不扫描Controller
@ComponentScan(basePackages = "com.yangsx95.notes", excludeFilters = {
        @ComponentScan.Filter(Controller.class)
})
public class RootConfig {
}
