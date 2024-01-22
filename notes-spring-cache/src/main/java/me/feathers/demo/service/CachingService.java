package me.feathers.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

/**
 * @Caching 可以为我们在一个方法上，添加多个SpringCache相关注解
 */
@Component
public class CachingService {

    @Caching(cacheable = @Cacheable("default"),
            evict = {@CacheEvict("activityCache")},
            put = {@CachePut("awardsCache")}
    )
    public String find(Integer id) {
        return "user:" + id;
    }
}
