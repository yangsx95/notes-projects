package io.github.yangsx95.notes.ssm.controller;

import io.github.yangsx95.notes.ssm.pojo.User;
import io.github.yangsx95.notes.ssm.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserController
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 13:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    public UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(String name) {
        return userService.findAll(name);
    }

}
