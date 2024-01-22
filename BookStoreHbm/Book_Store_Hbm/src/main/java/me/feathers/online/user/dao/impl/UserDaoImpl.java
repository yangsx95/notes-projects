package me.feathers.online.user.dao.impl;

import org.hibernate.criterion.Restrictions;

import me.feathers.online.entity.User;
import me.feathers.online.user.dao.UserDao;
import me.feathers.online.util.HibernateTemplate;

/**
 * 用户持久层实现类
 *
 * @author Feathers
 * @create 2017-06-28-9:30
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User findById(Long id) {
        return (User) HibernateTemplate.execute(ses -> {
            return ses.get(User.class, id);
        });
    }

    @Override
    public User findByUserName(String username) {
        return (User) HibernateTemplate.execute(ses -> {
            return ses.createCriteria(User.class, "u")
                    .add(Restrictions.eq("u.userName", username))
                    .uniqueResult();
        });
    }

    @Override
    public void save(User user) {
        HibernateTemplate.execute(ses -> {
            ses.save(user);
            return null;
        });
    }
}
