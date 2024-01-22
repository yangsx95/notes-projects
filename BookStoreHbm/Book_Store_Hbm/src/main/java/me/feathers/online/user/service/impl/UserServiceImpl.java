package me.feathers.online.user.service.impl;

import me.feathers.online.entity.User;
import me.feathers.online.user.dao.UserDao;
import me.feathers.online.user.service.UserService;
import me.feathers.online.util.BeansFactory;

/**
 * @author Feathers
 * @create 2017-06-28-10:01
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = (UserDao) BeansFactory.getBean("userDao");

    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public User findByUserName(String username) {
        return dao.findByUserName(username);
    }

    @Override
    public void save(User user) {
        dao.save(user);
    }
}
