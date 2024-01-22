package me.feathers.demo.service;

import me.feathers.demo.BaseTest;
import me.feathers.demo.bean.User;
import me.feathers.demo.constant.CacheName;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class CacheableServiceTest extends BaseTest {
    @Autowired
    private CacheableService cacheableService;

    @Autowired
    private CacheManager cacheManager;

    public Cache getDefaultCache() {
        return cacheManager.getCache("default");
    }

    @After
    public void print() {
        Cache cache = cacheManager.getCache(CacheName.USER_CACHE);
        System.out.println("缓存的数据类型为：" + cache.getNativeCache().getClass());
        System.out.println("userCache缓存中的内容为：" + cache.getNativeCache());
    }

    @Test
    public void simpleKey() {
        // 只有第一次调用了test方法，打印了日志
        for (int i = 0; i < 5; i++) {
            System.out.println(cacheableService.simpleKey());
        }
        // {SimpleKey []=数据}
    }

    @Test
    public void simpleKeyWithParam() {
        System.out.println(cacheableService.simpleKeyWithParam("李小龙", "200岁"));
        // {SimpleKey [李小龙,200岁]=数据1李小龙|200岁}
    }

    @Test
    public void withParam() {
        cacheableService.withParam(1);
        // {userId_1=userId:1}
    }

    @Test
    public void withBeanParamAndCondition() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("王五");
        user1.setPassword("123456");

        User user2 = new User();
        user2.setName("赵六");
        user2.setPassword("123456");

        cacheableService.withBeanParamAndCondition(user1);
        cacheableService.withBeanParamAndCondition(user2);

        // {王五=123456}
    }

    @Test
    public void nullT() {
        cacheableService.nullT("test");
    }

    @Test
    public void testRoot() {
        cacheableService.testRoot();
        // {org.springframework.cache.interceptor.CacheExpressionRootObject@1c7696c6=testRoot}
    }

    @Test
    public void testRoot2() {
        cacheableService.testRoot2();
    }

    @Test
    public void complex() {
        cacheableService.complex("小红", "123456");
        // {小红-123456=test11小红}
    }

    @Test
    public void keyGen() {
        cacheableService.keyGen("大熊");
    }

}
