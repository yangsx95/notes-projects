package me.feathers.online.user.dao;

import org.testng.annotations.Test;

import me.feathers.online.entity.User;
import me.feathers.online.util.BeansFactory;

/**
 * User持久层的测试类
 *
 * @author Feathers
 * @create 2017-06-28-9:39
 */
public class UserDaoTest {

    private UserDao userDao = (UserDao) BeansFactory.getBean("userDao");

    @Test
    public void testSave() {
        userDao.save(new User("admin", "admin", "616510229@qq.com", "18361507620", "大神", "火星"));
        userDao.save(new User("root", "root", "789546@qq.com", "15122522222", "小神仙", "水星"));
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(1L);
        System.out.println("当前类--UserDaoTest,user--" + user);
    }

    @Test
    public void testFindByUserName(){
        User user = userDao.findByUserName("admin");
        System.out.println("当前类--UserDaoTest,user---" + user);
    }

}
