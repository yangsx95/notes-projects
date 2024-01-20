package io.github.yangsx95.notes.mybatis.mapper;

import io.github.yangsx95.notes.mybatis.mapper.enums.Gender;
import io.github.yangsx95.notes.mybatis.mapper.mapper.UserMapper;
import io.github.yangsx95.notes.mybatis.mapper.pojo.User;
import io.github.yangsx95.notes.mybaitis.common.FlywayH2MybatisTools;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * UserMapperTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 10:27
 */
public class UserMapperTest {

    private static final UserMapper userMapper = FlywayH2MybatisTools.getMapper("SqlMapConfig.xml", UserMapper.class);

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