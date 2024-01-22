package me.feathers.demo.keygen;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 建生成器
 */
@Component("userKeyGenerator")
public class UserKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return "user_" + method + "_" + Arrays.toString(params);
    }
}
