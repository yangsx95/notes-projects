package io.github.yangsx95.notes.ssm.service;

import io.github.yangsx95.notes.ssm.pojo.User;

import java.util.List;

/**
 * UserService
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 13:11
 */
public interface UserService {
    User findById(Integer id);

    Long save(User user);

    void update(User user);

    List<User> findAll(String name);

    void delById(Integer id);
}
