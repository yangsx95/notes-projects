package me.feathers.online.order.dao;

import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderItem;
import me.feathers.online.entity.User;
import me.feathers.online.user.dao.UserDao;
import me.feathers.online.util.BeansFactory;

/**
 * OrderDao 测试
 *
 * @author Feathers
 * @create 2017-07-02-18:15
 */
public class OrderDaoTest {

    private OrderDao dao = (OrderDao) BeansFactory.getBean("orderDao");
    private UserDao userDao = (UserDao) BeansFactory.getBean("userDao");

    @Test
    public void testSave(){
        Set<OrderItem> set = new HashSet<>();

        Order order = new Order();

        OrderItem o1 = new OrderItem();
        o1.setPrice(200);
        o1.setImageUrl("/jj.jpg");
        o1.setCount(2);
        o1.setBook_name("哈利模特");
        o1.setBook_author("嘻嘻");
        o1.setOrder(order);
        set.add(o1);

        OrderItem o2 = new OrderItem();
        o2.setPrice(50);
        o2.setImageUrl("/jj.jpg");
        o2.setCount(1);
        o2.setBook_name("嘻嘻的作用");
        o2.setBook_author("嘻嘻");
        o2.setOrder(order);
        set.add(o2);

        OrderItem o3 = new OrderItem();
        o3.setPrice(55);
        o3.setImageUrl("/afs.jpg");
        o3.setCount(3);
        o3.setBook_name("魔都");
        o3.setBook_author("小泽");
        o3.setOrder(order);
        set.add(o3);


        order.setCreateDate(new Date());
        order.setItems(set);
        dao.save(order);
    }

    @Test
    public void testFindById(){
        Order byId = dao.findById(1351L);
        System.out.println("=====" + byId);
    }

    @Test
    public void testDelete(){

    }

    @Test
    public void testSelectByPage(){

    }

    @Test
    public void testRowCount(){

    }

    @Test
    public void testFindByPage(){
        User user = userDao.findById(1L);
        List<Order> byPage = dao.findByPage(user, null, null, null, null, null, null, null);
        System.out.println("list" + byPage);
    }

}
