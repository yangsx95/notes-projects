package com.yangsx95.notes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

// 只扫描Controller
@ComponentScan(
        basePackages = "com.yangsx95.notes",
        includeFilters = {
                @ComponentScan.Filter(Controller.class)
        },
        useDefaultFilters = false  // 这里需要禁用默认规则
)
public class WebConfig {
}
