package me.feathers.online.user.dao;

import org.testng.annotations.Test;

import java.util.List;

import me.feathers.online.entity.Address;
import me.feathers.online.util.BeansFactory;

/**
 * 地址dao层测试类
 *
 * @author Feathers
 * @create 2017-07-01-9:44
 */
public class AddressDaoTest {

    private AddressDao addressDao = (AddressDao) BeansFactory.getBean("addressDao");
    private UserDao userDao = (UserDao) BeansFactory.getBean("userDao");

    @Test
    public void testSave() {
        Address address = new Address();
        address.setArea("姑苏区");
        address.setTel("18361507620");
        address.setDetailAddress("汇通大厦311");
        address.setEmailCode("221011");
        address.setReceiver("奥大丽赫本");
        address.setIsDefault("1");
        address.setUser(userDao.findById(1L));
        addressDao.save(address);
    }

    @Test
    public void testFindByUserId(){
        List<Address> list = addressDao.findByUserId(1L);
        list.forEach(System.out :: println);
    }

    @Test
    public void testDeleteById() {
        addressDao.deleteById(1L);
    }

    @Test
    public void testFindById() {
        Address address = addressDao.findById(1L);
        System.out.println("当前类--AddressDaoTest,address====" + address);
    }

    @Test
    public void testUpdate(){
        Address add = addressDao.findById(1L);
        add.setIsDefault("1");
        add.setReceiver("张女士");
        addressDao.update(add);
    }
}
