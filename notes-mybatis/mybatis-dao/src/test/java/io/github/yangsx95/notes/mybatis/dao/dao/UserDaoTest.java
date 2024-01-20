package io.github.yangsx95.notes.mybatis.dao.dao;

import io.github.yangsx95.notes.mybatis.dao.dao.UserDao;
import io.github.yangsx95.notes.mybatis.dao.dao.impl.UserDaoImplWithTemplate;
import io.github.yangsx95.notes.mybatis.dao.enums.Gender;
import io.github.yangsx95.notes.mybatis.dao.pojo.User;
import io.github.yangsx95.notes.mybaitis.common.FlywayH2MybatisTools;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * UserDaoTest
 * <p>
 * -----------------注意，使用UserDao需要在配置文件中注释掉UserMapper的包扫描，否则会造成冲突。
 * <p>
 *
 * @author Feathers
 * @date 2018-05-23 21:57
 */
public class UserDaoTest {

    private static UserDao userDao;
    
    private static void initMybatis() throws Exception {
        // 加载核心配置文件
        InputStream sqlMapConfig = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(sqlMapConfig);
        // 实例化UserDao
        //userDao = new UserDaoImpl(ssf);
        userDao = new UserDaoImplWithTemplate();
    }
    
    @BeforeClass
    public static void prepare() throws Exception {
        FlywayH2MybatisTools.initH2();
        initMybatis();
    }

    @Test
    public void save() {
        User user = new User();
        user.setGender(Gender.M);
        user.setUsername("王五");
        user.setPassword("123456");
        User savedUser = userDao.save(user);
        System.out.println(savedUser);
    }

    @Test
    public void findById() {
        User user = userDao.findById(1);
        assertNotNull(user);
        assertEquals("张三", user.getUsername());
    }

    @Test
    public void update() {
        User user = userDao.findById(1);
        System.out.println(user);
        user.setUsername("张铁蛋");
        user.setStatus(0);
        user.setDelStatus(0);
        userDao.update(user);
    }

    @Test
    public void findAll() {
        List<User> list = userDao.findAll("张");
        assertNotNull(list);
        assertTrue(list.size() > 0);
        System.out.println(list);
    }

    @Test
    public void delById() {
        int i = userDao.delById(1);
        assertEquals(1, i);
    }
}