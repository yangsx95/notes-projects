package me.feathers.online.order.service;

import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderItem;
import me.feathers.online.entity.PageBean;
import me.feathers.online.entity.User;
import me.feathers.online.user.service.UserService;
import me.feathers.online.util.BeansFactory;

/**
 * @author Feathers
 * @create 2017-07-03-10:35
 */
public class OrderServiceTest {

    OrderService orderService = (OrderService) BeansFactory.getBean("orderService");
    UserService userService = (UserService) BeansFactory.getBean("userService");

    @Test
    public void testSave() {

        Set<OrderItem> set = new HashSet<>();
        Order order = new Order();

        OrderItem o1 = new OrderItem();
        o1.setPrice(100);
        o1.setImageUrl("/dd.jpg");
        o1.setCount(1);
        o1.setBook_name("西瓜的故事");
        o1.setBook_author("哈哈");
        o1.setOrder(order);
        set.add(o1);

        OrderItem o2 = new OrderItem();
        o2.setPrice(50);
        o2.setImageUrl("/ww.jpg");
        o2.setCount(1);
        o2.setBook_name("小马的故事");
        o2.setBook_author("顺势");
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
        orderService.save(order);
    }

    @Test
    public void findByPage(){
        User user = userService.findById(1L);
        PageBean<Order> byPage = orderService.findByPage(user, 1, 1, null, null, null, null, null);
        System.out.println("byPage" + byPage.getDatas().get(0).getItems());
    }

}
