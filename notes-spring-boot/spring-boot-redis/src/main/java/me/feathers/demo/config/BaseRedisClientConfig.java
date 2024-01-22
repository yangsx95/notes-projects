package me.feathers.demo.config;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.time.Duration;
import java.util.Optional;

@Data
abstract class BaseRedisClientConfig {
    private String host;
    private String password;
    private boolean userSsl = false;
    private boolean usePooling = false;
    private String clientName = "";
    private int connectTimeout = 10;
    private int readTimeout = 0;

    public JedisClientConfiguration buildJedisClientConfiguration() {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        builder.clientName("test");
        return builder.build();
    }
}
