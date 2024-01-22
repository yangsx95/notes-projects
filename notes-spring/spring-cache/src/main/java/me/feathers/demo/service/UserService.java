package me.feathers.demo.service;

import me.feathers.demo.bean.User;
import me.feathers.demo.constant.CacheName;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {

    private static final Map<Integer, User> users = new HashMap<>();

    // 添加用户
    @CachePut(value = CacheName.USER_CACHE, key = "'userInfo_'+#p0.id")
    public User insertUser(User user) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "：正在操作数据库....");
        users.put(user.getId(), user);
        return user;
    }

    // 删除用户
    @CacheEvict(value = CacheName.USER_CACHE, key = "'userInfo_'+#root.args[0]")
    public User deleteUserById(Integer id) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "：正在操作数据库....");
        return users.remove(id);
    }

    // 修改用户
    @CachePut(value = CacheName.USER_CACHE, key = "'userInfo_'+#user.id")
    public User modifyUser(User user) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "：正在操作数据库....");
        return users.put(user.getId(), user);
    }

    // 查询用户
    @Cacheable(value = CacheName.USER_CACHE, key = "'userInfo_'+#id")
    public User getUserInfo(Integer id) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + "：正在操作数据库....");
        return users.get(id);
    }

}
