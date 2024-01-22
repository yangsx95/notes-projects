package me.feathers.demo.service;

import me.feathers.demo.constant.CacheName;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictService {
    
    /*
    @CacheEvict 用来标注在需要清除缓存元素。
    value       表示清除操作是发生在哪些Cache上的（对应Cache的名称）
    key         表示需要清除的是哪个key，如未指定则会使用默认策略生成的key
    condition   表示清除操作发生的条件
    allEntries  表示是否需要清除缓存中的所有元素 默认false，当为true时，SpringCache将会忽略key，清除该Cache下的所有内容
    beforeInvocation        清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。
                            使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，
                            Spring会在调用该方法之前清除缓存中的指定元素。
     */

    @CacheEvict(value = CacheName.USER_CACHE, key = "#methodName", allEntries = true, beforeInvocation = true)
    public String test() {
        return "test";
    }

}
