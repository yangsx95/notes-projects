package me.feathers.demo.configclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/config")
public class TestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 使用配置中心的配置就像使用SpringBoot本地配置相同
     */
    @Value("${info.profile}")
    private String profile;

    @GetMapping("/profile")
    public String getProfile() {
        return profile;
    }

    @GetMapping("/logback")
    public String logback() {
        logger.info("我是测试内容");
        return "..";
    }

}
