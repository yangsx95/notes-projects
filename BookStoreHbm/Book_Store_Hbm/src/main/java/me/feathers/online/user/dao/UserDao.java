package me.feathers.online.user.dao;

import me.feathers.online.entity.User;

/**
 * 用户持久层
 *
 * @author Feathers
 * @create 2017-06-28-9:22
 */
public interface UserDao {

    User findById(Long id);

    User findByUserName(String username);

    /**
     * 保存一个用户
     * @param user
     */
    void save(User user);

}
