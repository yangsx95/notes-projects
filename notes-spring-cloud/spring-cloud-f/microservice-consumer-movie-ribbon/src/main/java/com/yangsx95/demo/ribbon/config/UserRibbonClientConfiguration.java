package com.yangsx95.demo.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 指定 microservice-provider-user 的负载均衡策略
 * @author yangsx
 * @version 1.0
 * @date 2019/5/10
 */
@Configuration
@RibbonClient(name = "microservice-provider-user", configuration = DefaultRibbonConfig.class)
public class UserRibbonClientConfiguration {
}
