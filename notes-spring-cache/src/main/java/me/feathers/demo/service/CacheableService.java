package me.feathers.demo.service;

import me.feathers.demo.bean.User;
import me.feathers.demo.constant.CacheName;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 针对注解 @Cacheable 的测试
 * 标记在方法上：缓存方法返回值到指定名称的cache
 * 标记在类上：表示所有方法都支持缓存
 */
@Component
public class CacheableService {

    /**
     * 使用默认的缓存名称，缓存到 userCache缓存中
     * 默认会使用 @code{org.springframework.cache.interceptor.SimpleKey} 作为key
     * 而Simple可以会以 `Simple [param1, param2]` 作为key，params代表用户传入的方法参数的值
     * <p>
     * ehcache和redis则不遵循此规则
     */
    @Cacheable(CacheName.USER_CACHE)
    public String simpleKey() {
        System.out.println("从数据库中获取了值~");
        return "数据";
    }

    @Cacheable(CacheName.USER_CACHE)
    public String simpleKeyWithParam(String name, String age) {
        return "数据1" + name + "|" + age;
    }

    /**
     * 用参数 userId_userId 作为key
     */
    @Cacheable(value = {CacheName.USER_CACHE}, key = "'userId_'.concat(#userId)")
//    @Cacheable(value = {CacheName.USER_CACHE}, key = "'userId_'.concat(#p0)")
    public String withParam(Integer userId) {
        return "userId:" + userId;
    }

    /**
     * 用参数对象的某个属性作为key
     * <p>
     * 并且用户id存在时，才进行缓存
     */
    @Cacheable(value = {CacheName.USER_CACHE}, key = "#user.name", condition = "#user.id!=null && #user.id!=0")
    public String withBeanParamAndCondition(User user) {
        return user.getPassword();
    }

    /**
     * SpringCache默认缓存null值，如果不想缓存null，有两种方式设置
     * 1. condition 设置必须满足的条件 设置返回结果不为null #result代表方法的返回结果
     * 2. unless 设置缓存排除条件，满足条件则不会进行缓存，这里设置#result==null
     */
    @Cacheable(value = {CacheName.USER_CACHE}, key = "'nullTest'", unless = "#result==null")
//    @Cacheable(value = {CacheName.USER_CACHE}, key = "'nullTest'", condition = "#result!=null")
    public String nullT(String name) {
        return null;
    }

    /**
     * root对象测试
     * org.springframework.cache.interceptor.CacheExpressionRootObject
     */
    @Cacheable(value = {CacheName.USER_CACHE}, key = "#root")
    public String testRoot() {
        return "testRoot";
    }

    /**
     * root对象是 CacheExpressionRootObject 类的实例
     * 使用root对象获取
     * 被调用的方法名
     * 被调用的方法的参数数组
     * 调用者对象
     * 被调用者class对象
     * 缓存实例
     */
    // 获取method对象
    // @Cacheable(value = {CacheName.USER_CACHE}, key = "#root.method")
    // 获取传入的方法参数，通过args
    // @Cacheable(value = {CacheName.USER_CACHE}, key = "#root.args[0]")
    // 获取调用者对象
    // @Cacheable(value = {CacheName.USER_CACHE}, key = "#root.target")
    // 获取调用者对象class对象
    // @Cacheable(value = {CacheName.USER_CACHE}, key = "#root.targetClass")
    // 获取缓存实例
    @Cacheable(value = {CacheName.USER_CACHE}, key = "#root.caches[0].name")
    public String testRoot2() {
        return "test6";
    }

    /**
     * 组合模式key
     */
    @Cacheable(value = {CacheName.USER_CACHE}, key = "T(String).valueOf(#name).concat('-').concat(#password)")
    public String complex(String name, String password) {
        return "test11" + name;
    }


    /**
     * 使用自定义的keyGenerator
     */
    @Cacheable(value = {CacheName.USER_CACHE}, keyGenerator = "userKeyGenerator")
    public String keyGen(String name) {
        return "test14" + name;
    }
}
