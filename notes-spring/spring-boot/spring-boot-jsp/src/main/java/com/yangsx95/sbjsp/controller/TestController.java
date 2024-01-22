package com.yangsx95.sbjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yangsx
 * @version 1.0
 * @date 2019/8/25
 */
@Controller
public class TestController {

    @RequestMapping("/welcome")
    public String index(Model model) {
        System.out.println("--------");
        model.addAttribute("name", "zhangsan");
        return "welcome";
    }

}
