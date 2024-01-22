package me.feathers.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * TemplateController
 * <p>
 *
 * @author Feathers
 * @date 2018-03-31 16:20
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    // 使用thymeleaf
    @GetMapping("/hello")
    public String demo1() {
        return "hello";
    }

    // 使用freemarker
    @GetMapping("/demo2")
    public ModelAndView demo2() {
        return new ModelAndView("helloFtl");
    }

    // 二者可以共存
}
