package com.yangsx95.demo.user;

import com.yangsx95.demo.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * microservice-provider-user 服务Ribbon配置
 * @author yangsx
 * @version 1.0
 * @date 2019/5/10
 */
@Configuration
@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class)
public class UserRibbonConfiguration {
}
