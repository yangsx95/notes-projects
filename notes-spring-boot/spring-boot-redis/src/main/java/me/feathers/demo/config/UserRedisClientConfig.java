package me.feathers.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis.standalone.userRedis")
public class UserRedisClientConfig extends BaseRedisClientConfig {
    
}
