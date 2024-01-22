package me.feathers.demo.service;

import me.feathers.demo.BaseTest;
import me.feathers.demo.constant.CacheName;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.concurrent.ConcurrentHashMap;

public class CachePutServiceTest extends BaseTest {

    @Autowired
    private CachePutService cachePutService;

    @Autowired
    private CacheManager cacheManager;

    @After
    public void print() {
        Cache cache = cacheManager.getCache(CacheName.USER_CACHE);
        assert cache != null;
        ConcurrentHashMap nativeCache = (ConcurrentHashMap) cache.getNativeCache();
        System.out.println("userCache缓存中的内容为：" + nativeCache);
    }

    @Test
    public void test() {
        // 每次都会进行存储
        for (int i = 0; i < 5; i++)
            cachePutService.test();
    }
}