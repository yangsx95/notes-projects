package me.feathers.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SecondaryDaoTest
 * <p>
 *
 * @author Feathers
 * @date 2018-06-01 14:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecondaryDaoTest {

    @Autowired
    private SecondaryDao secondaryDao;

    @Test
    public void queryUsers() {
        System.out.println(secondaryDao.queryUsers());
    }
}