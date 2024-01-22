package me.feathers.summer.service.impl;

import me.feathers.summer.anno.FService;
import me.feathers.summer.service.UserService;

@FService
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "hello~" + name;
    }
}
