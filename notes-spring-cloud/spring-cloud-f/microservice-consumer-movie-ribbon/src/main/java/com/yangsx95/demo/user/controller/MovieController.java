package com.yangsx95.demo.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yangsx95.demo.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public User findById(@PathVariable Long id) {
        // RestTemplate 具有占位符的能力
        // 指定目标服务名称，交由Ribbon从Eureka中获取并进行负载算法返回服务地址
        // 注意，目标服务名称在Ribbon中称为虚拟主机名，主机名是不能包含下划线等特殊字符的，不可配置spring.application.name = xxx_xxx 
        // 如果必须要配置带有下划线的项目名，可以另外指定eureka.instance.virtual-host-name = 一个合法的主机名，Ribbon将会使用这个主机名称
        User user = this.restTemplate.getForObject("http://microservice-provider-user/users/{id}", User.class, id);
        // 电影微服务业务逻辑 ...
        return user;
    }

    /**
     * 可以通过 /actuator/health 查看断路器的状态
     * 
     * 可以使用fallback进行操作回退   
     */
    public User findByIdFallback(Long id) {
        return new User(id, "默认用户", "默认用户", 0, new BigDecimal(1));
    }

}