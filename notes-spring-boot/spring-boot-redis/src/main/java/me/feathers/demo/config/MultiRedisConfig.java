package me.feathers.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;

@Configuration
public class MultiRedisConfig {
    
    @Bean
    public JedisConnectionFactory userJedisConnectionFactory(@Autowired RedisPoolConfig redisPoolConfig) {

        JedisConnectionFactory jcf = new JedisConnectionFactory(redisPoolConfig);
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        
        return null;
    }

    /**
     * 用户redis
     *
     * @param config 配置
     * @return JedisConnectionFactory实例
     */
    @Bean
    public JedisPool userRedisConnection(@Autowired RedisPoolConfig config,
                                         @Value("redis.userRedis.host") String host,
                                         @Value("redis.userRedis.port") int port,
                                         @Value("redis.userRedis.timeout") Integer timeout,
                                         @Value("redis.userRedis.password") String password) {
        return null;
    }
    
    
    
}
