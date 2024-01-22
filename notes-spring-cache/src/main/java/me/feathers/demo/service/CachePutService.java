package me.feathers.demo.service;

import me.feathers.demo.constant.CacheName;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Component
public class CachePutService {
    /*
    @Cachable的缓存，Spring在每次执行都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行方法，否则执行并缓存
    @CachePut的缓存，CachePut在执行前不会去检查是否存在已有的缓存，而是每次都会执行该方法，并放入到缓存中
    CachePut也可以放到类上
    */

    @CachePut(value = CacheName.USER_CACHE, key = "#root.methodName")
    public String test() {
        System.out.println("---reset----");
        return "test";
    }
}
