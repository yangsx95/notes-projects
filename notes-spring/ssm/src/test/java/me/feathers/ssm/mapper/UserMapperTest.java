package me.feathers.ssm.mapper;

import me.feathers.ssm.AbsBaseTest;
import me.feathers.ssm.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * UserMapperTest
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 11:16
 */
public class UserMapperTest extends AbsBaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<User> list = userMapper.findAll("张");
        assertTrue(list.size() > 0);
        System.out.println(list);
    }

    @Test
    public void findById() {
        User byId = userMapper.findById(1);
        System.out.println(byId);
    }
}