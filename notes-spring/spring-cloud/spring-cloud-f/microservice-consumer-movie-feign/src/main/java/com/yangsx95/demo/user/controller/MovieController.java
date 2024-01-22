package com.yangsx95.demo.user.controller;

import com.yangsx95.demo.user.feign.UserFeignClient;
import com.yangsx95.demo.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/5/9
 */
@RestController
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        // RestTemplate 具有占位符的能力
        User user = userFeignClient.findById(id);
        // 电影微服务业务逻辑 ...
        return user;
    }
    
}
