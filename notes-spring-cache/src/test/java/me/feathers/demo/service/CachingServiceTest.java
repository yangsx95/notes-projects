package me.feathers.demo.service;

import me.feathers.demo.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

public class CachingServiceTest extends BaseTest {

    @Autowired
    private CachingService cachingService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void find() {
        cachingService.find(1);
        System.out.println(cacheManager.getCache("default").getNativeCache());
        System.out.println(cacheManager.getCache("activityCache").getNativeCache());
        System.out.println(cacheManager.getCache("awardsCache").getNativeCache());
    }

}
