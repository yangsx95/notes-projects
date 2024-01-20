package com.yangsx95.demo.mapper;

import com.yangsx95.demo.pojo.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserMapperTest
 * Spring整合测试
 * <p>
 *
 * @author Feathers
 * @date 2018-05-26 23:11
 */

@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserMapperTest extends TestCase {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<User> list = userMapper.findAll("赵");
        assertTrue(list.size() > 0);
        System.out.println(list);
    }
}