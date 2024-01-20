package com.yangsx95.demo.mapper;

import com.yangsx95.demo.enums.Gender;
import com.yangsx95.demo.pojo.User;
import com.yangsx95.mybaitis.common.FlywayH2MybatisTools;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.jdbc.DriverDataSource;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * UserMapperTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 10:27
 */
public class UserMapperTest {

    private static final  UserMapper userMapper = FlywayH2MybatisTools.getMapper("SqlMapConfig.xml", UserMapper.class);

    @Test
    public void findById() {
        //调用代理对象方法
        User user = userMapper.findById(1);
        assertEquals("张铁蛋", user.getUsername());
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("王老湿");
        user.setPassword("!@#123");
        user.setGender(Gender.M);
        Long saveId = userMapper.save(user);
        System.out.println(saveId);
    }

    @Test
    public void findAll() {
        List<User> lis = userMapper.findAll("张");
        assertNotNull(lis);
        assertTrue(lis.size() > 0);
    }

    @Test
    public void update() {
        User user = userMapper.findById(1);
        user.setGender(Gender.U);
        userMapper.update(user);
    }

    @Test
    public void delById() {
        userMapper.delById(1);
    }
}