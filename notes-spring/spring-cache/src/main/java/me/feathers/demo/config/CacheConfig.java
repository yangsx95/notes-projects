package me.feathers.demo.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 使用注解和配置类配置SpringCache，推荐使用
@Configuration
// 启动SpringCache注解驱动功能，类似xml文件中的 `<cache:annotation-driven/>` 标签
// proxyTargetClass 默认为false ，作用：
// 当需要代理的类是一个接口或者是一个动态生成的代理类时使用JdkProxy代理；
// 而当要代理的类是一个具体类时，使用cglib来代理。
// mode指定spring cache的实现方式，是使用 Proxy代理方式，还是原生的ASPECTJ方式
@EnableCaching(proxyTargetClass = true, mode = AdviceMode.PROXY)
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override
    public CacheManager cacheManager() {
        return super.cacheManager();
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return super.keyGenerator();
    }

    /**
     * CacheResolver 缓存解析器
     */
    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return super.cacheResolver();
    }

    /**
     * CacheErrorHandler用于处理缓存相关的错误
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            // 获取缓存时出现异常的回调
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                System.err.println("get cache occur exception: " + exception);
            }

            // 存放缓存时出现异常的回调
            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                System.err.println("put cache occur exception: " + exception);
            }

            // 删除异常时出现的回调
            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                System.err.println("evict cache occur exception: " + exception);
            }

            // 清空缓存时出现异常的回调
            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                System.err.println("clear cache occur exception: " + exception);
            }
        };
    }
}
