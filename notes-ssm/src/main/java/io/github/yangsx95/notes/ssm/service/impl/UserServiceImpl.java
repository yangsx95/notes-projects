package io.github.yangsx95.notes.ssm.service.impl;

import io.github.yangsx95.notes.ssm.mapper.UserMapper;
import io.github.yangsx95.notes.ssm.pojo.User;
import io.github.yangsx95.notes.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserServiceImpl
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 13:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public Long save(User user) {
        return userMapper.save(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public List<User> findAll(String name) {
        return userMapper.findAll(name);
    }

    @Override
    public void delById(Integer id) {
        userMapper.delById(id);
    }
}
