package com.yangsx95.demo.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon全局配置
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/5/10
 */
@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
public class RibbonClientGlobalConfiguration {
}

@Configuration
class DefaultRibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

}
