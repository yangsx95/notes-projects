package com.yangsx95.demo.dao.impl;

import com.yangsx95.demo.dao.UserDao;
import com.yangsx95.demo.pojo.User;
import com.yangsx95.demo.util.MyBatisCallback;
import com.yangsx95.demo.util.MybatisTemplate;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * UserDaoImplWithTemplate
 * 使用模板工具类，重写UserDao实现
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 9:51
 */
public class UserDaoImplWithTemplate implements UserDao {

    @Override
    public User save(final User user) {
        return (User) MybatisTemplate.excute(ses -> {
            ses.insert("userMapper.save", user);
            return user;
        });
    }

    @Override
    public User findById(final Integer id) {
        return (User) MybatisTemplate.excute(new MyBatisCallback() {
            @Override
            public Object doInMyBatis(SqlSession ses) {
                return ses.selectOne("findById", id);
            }
        });
    }

    @Override
    public void update(final User user) {
        MybatisTemplate.excute(new MyBatisCallback() {
            @Override
            public Object doInMyBatis(SqlSession ses) {
                ses.update("userMapper.update", user);
                return null;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll(final String name) {
        return (List<User>) MybatisTemplate.excute(ses -> ses.selectList("userMapper.findAll", name));
    }

    @Override
    public int delById(final Integer id) {
        return (int) MybatisTemplate.excute(new MyBatisCallback() {
            @Override
            public Object doInMyBatis(SqlSession ses) {
                return ses.delete("userMapper.delById", id);
            }
        });
    }
}
