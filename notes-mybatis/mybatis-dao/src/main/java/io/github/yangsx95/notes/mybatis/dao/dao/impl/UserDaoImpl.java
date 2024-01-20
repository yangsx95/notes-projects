package io.github.yangsx95.notes.mybatis.dao.dao.impl;

import io.github.yangsx95.notes.mybatis.dao.dao.UserDao;
import io.github.yangsx95.notes.mybatis.dao.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * UserDaoImpl
 * <p>
 *
 * @author Feathers
 * @date 2018-05-23 21:55
 */
public class UserDaoImpl implements UserDao {

    /**
     * 用于生产SqlSession
     */
    private final SqlSessionFactory ssf;

    public UserDaoImpl(SqlSessionFactory ssf) {
        this.ssf = ssf;
    }

    @Override
    public User save(User user) {
        try (SqlSession s = ssf.openSession()) {
            s.insert("userMapper.save", user);
            s.commit();
        }
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user;
        try (SqlSession s = ssf.openSession()) {
            user = s.selectOne("userMapper.findById", id);
            s.commit();
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (SqlSession s = ssf.openSession()) {
            s.update("userMapper.update", user);
            s.commit();
        }
    }

    @Override
    public List<User> findAll(String name) {

        List<User> result;
        try(SqlSession s = ssf.openSession()) {
            result = s.selectList("userMapper.findAll", name);
            s.commit();
        }
        return result;
    }

    @Override
    public int delById(Integer id) {
        int delCount;
        try (SqlSession s = ssf.openSession()) {
            delCount = s.delete("userMapper.delById", id);
            s.commit();
        }
        return delCount;
    }
}
