package me.feathers.ssm.mapper;

import io.github.yangsx95.notes.ssm.mapper.UserMapper;
import me.feathers.ssm.AbsBaseTest;
import io.github.yangsx95.notes.ssm.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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