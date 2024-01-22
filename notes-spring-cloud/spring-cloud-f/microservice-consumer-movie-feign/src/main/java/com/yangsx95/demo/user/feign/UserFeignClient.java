package com.yangsx95.demo.user.feign;

import com.yangsx95.demo.user.entity.User;
import feign.Logger;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

/**
 * 使用configuration指定此feign客户端的配置类
 * 除了使用fallback指定断路处理，还可以使用
 *
 * @author yangsx
 * @version 1.0
 * @date 2019/5/11
 */
@FeignClient(name = "microservice-provider-user",
        configuration = UserFeignConfig.class
        , fallback = UserFeignClientFallback.class
        //, fallbackFactory = UserFeignClientFallbackFactory.class
)
// 可以通过此种方式直连
//@FeignClient(name = "abcde", url = "http://localhost:8000/") 
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);

}

/**
 * 将此类public后，将会称为公共配置，会被所有的feign client共享，就无法作为细粒度配置
 */
@Configuration
class UserFeignConfig {

    /**
     * 配置feign的日志级别，共有四种类型：
     * NONE【性能最佳，适用于生产】：不记录任何日志（默认值）。
     * BASIC【适用于生产环境追踪问题】：仅记录请求方法、URL、响应状态代码以及执行时间。
     * HEADERS：记录BASIC级别的基础上，记录请求和响应的header。
     * FULL【比较适用于开发及测试环境定位问题】：记录请求和响应的header、body和元数据
     * <p>
     * 日志的配置也可以在 yml 中定义，直接使用logging标签为指定包或者类进行细粒度的配置
     */
    @Bean
    public Logger.Level logger() {
        return Logger.Level.FULL;
    }
}

@Component
class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User findById(Long id) {
        return new User(id, "默认用户", "默认用户", 0, new BigDecimal(1));
    }
}

@Component
@Slf4j
class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                log.error("进入回退逻辑", throwable);
                return new User(id, "默认用户", "默认用户", 0, new BigDecimal(1));
            }
        };
    }
}