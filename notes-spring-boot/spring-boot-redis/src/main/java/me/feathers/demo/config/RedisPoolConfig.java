package me.feathers.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;


@Component
@ConfigurationProperties(prefix = "redis.pool")
public class RedisPoolConfig extends JedisPoolConfig {
}
