package com.yangsx95.demo;

import org.springframework.boot.SpringApplication;

/**
 * 配置中心服务端，提供了几个API可以查看git仓库中的配置文件
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/5/12
 */
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
