package me.feathers.demo.service;

import me.feathers.demo.BaseTest;
import me.feathers.demo.bean.User;
import me.feathers.demo.constant.CacheName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setPassword("12345");

        User user2 = new User();
        user2.setId(2);
        user2.setName("李四");
        user2.setPassword("3455643");

        userService.insertUser(user);
        userService.insertUser(user2);
    }

    @Test
    public void insertUser() {
        Cache cache = cacheManager.getCache(CacheName.USER_CACHE);
        Cache.ValueWrapper valueWrapper = cache.get("userInfo_1");
        User o = (User) valueWrapper.get();
        assertNotNull(o);
        assertEquals(o.getId(), Integer.valueOf(1));
    }

    @Test
    public void deleteUserById() {
        userService.deleteUserById(1);
    }

    @Test
    public void getUserInfo() {
        User userInfo = userService.getUserInfo(1);
        System.out.println(userInfo);
    }

    @Test
    public void modifyUser() {
        User userInfo = userService.getUserInfo(1);
        userInfo.setName("赵丽颖");
        userService.modifyUser(userInfo);
    }


    @After
    public void tearDown() throws Exception {
        Object nativeCache = cacheManager.getCache(CacheName.USER_CACHE).getNativeCache();
        System.out.println("当前缓存中的内容为：" + nativeCache);
    }
}