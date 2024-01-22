package com.yangsx95.consul.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  // 自动刷新配置
public class HelloController {

    // 对应在consul配置中心中的key为
    // 路径/服务名称/key
    // 这里是
    // /config/producer/data
    // 如果需要不同的profile区分，那么则应该是
    // /config/producer:dev/dta


    @Value("${config.info}")
    private String configInfo;

    @RequestMapping("/hello")
    public String hello() {
        return "hello " + configInfo;
    }
}