package me.feathers.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 * 抛出一个异常，会被ControllerAdvice处理
 * <p>
 *
 * @author Feathers
 * @date 2018-04-01 20:22
 */
@RestController
@RequestMapping("/exception")
public class TestController {

    @GetMapping("/throw/{param}")
    public String throwException(@PathVariable String param) {
        if ("1".equals(param))
            return "success";
        else
            throw new NullPointerException();
    }

}
