package com.yangsx95.demo.sqlattack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/6/22
 */
@RestController
@RequestMapping("/sqlattack")
public class TestController {
    
    @GetMapping("/hello")
    public String hello() {
        return "success";
    }
    
}
