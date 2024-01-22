package me.feathers.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * PrimaryDaoTest
 * <p>
 *
 * @author Feathers
 * @date 2018-06-01 14:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PrimaryDaoTest {

    @Autowired
    private PrimaryDao primaryDao;

    @Test
    public void queryUsers() {
        System.out.println(primaryDao.queryUsers());
    }
}