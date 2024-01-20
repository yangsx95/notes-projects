package com.yangsx95.notes;

import com.yangsx95.notes.bean.User;
import com.yangsx95.notes.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class SpringbootMybatisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<User> all = userMapper.findAll("张");
        Assertions.assertTrue(all.size() > 0);
    }

    @Test
    public void findAllWithoutCondition() {
        List<User> all = userMapper.findAllWithoutCondition();
        Assertions.assertTrue(all.size() > 0);
    }

}
