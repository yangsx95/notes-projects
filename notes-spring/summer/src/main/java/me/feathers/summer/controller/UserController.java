package me.feathers.summer.controller;

import me.feathers.summer.anno.FAutowired;
import me.feathers.summer.anno.FController;
import me.feathers.summer.anno.FRequestMapping;
import me.feathers.summer.anno.FRequestParam;
import me.feathers.summer.service.impl.UserServiceImpl;

@FController
@FRequestMapping("/user")
public class UserController {

    @FAutowired
    private UserServiceImpl userService;

    @FRequestMapping("/sayHello")
    public String sayHello(@FRequestParam("name") String name) {
        return userService.sayHello(name);
    }

}
