package com.yangsx95.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@SpringBootApplication
@EnableZuulProxy
public class RateLimitApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RateLimitApplication.class, args);
    }

/*    @Bean
    public RateLimitKeyGenerator ratelimitKeyGenerator(RateLimitProperties properties, RateLimitUtils rateLimitUtils) {
        return new DefaultRateLimitKeyGenerator(properties, rateLimitUtils) {
            @Override
            public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
                return super.key(request, route, policy) + ":" + request.getMethod();
            }
        };
    }*/

    
}
