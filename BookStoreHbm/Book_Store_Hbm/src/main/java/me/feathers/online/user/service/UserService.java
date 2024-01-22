package me.feathers.online.user.service;

import me.feathers.online.entity.User;

/**
 * @author Feathers
 * @create 2017-06-28-10:00
 */
public interface UserService {

    User findById(Long id);

    User findByUserName(String username);

    void save(User user);
}
