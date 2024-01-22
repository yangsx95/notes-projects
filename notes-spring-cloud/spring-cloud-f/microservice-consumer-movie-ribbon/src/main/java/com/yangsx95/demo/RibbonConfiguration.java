package com.yangsx95.demo;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * 
 * @author yangsx
 * @version 1.0
 * @date 2019/5/10
 */
@Configuration
public class RibbonConfiguration {
    
    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则，改为随机
        return new RandomRule();
    }
    
}
