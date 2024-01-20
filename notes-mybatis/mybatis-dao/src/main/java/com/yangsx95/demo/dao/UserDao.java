package com.yangsx95.demo.dao;

import com.yangsx95.demo.pojo.User;

import java.util.List;

/**
 * UserDao
 * 使用Mybatis进行原始Dao开发
 * <p>
 *
 * @author Feathers
 * @date 2018-05-23 21:54
 */
public interface UserDao {

    User save(User user);

    User findById(Integer id);

    void update(User user);

    List<User> findAll(String name);

    int delById(Integer id);
}
