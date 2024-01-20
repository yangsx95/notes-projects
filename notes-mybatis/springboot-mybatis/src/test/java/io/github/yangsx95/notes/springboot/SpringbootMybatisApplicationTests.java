package io.github.yangsx95.notes.springboot;

import io.github.yangsx95.notes.springboot.bean.User;
import io.github.yangsx95.notes.springboot.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
