package me.feathers.online.order.dao;

import org.testng.annotations.Test;

import me.feathers.online.entity.OrderItem;
import me.feathers.online.util.BeansFactory;

/**
 * OrderItemDao测试类
 *
 * @author Feathers
 * @create 2017-07-02-18:31
 */
public class OrderItemTest {


    private OrderItemDao dao = (OrderItemDao) BeansFactory.getBean("orderItemDao");

    // 保存一条订单明细
    @Test
    public void testSave(){
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(100);
        orderItem.setImageUrl("/hh.jpg");
        orderItem.setCount(1);
        orderItem.setBook_name("东游记");
        orderItem.setBook_author("哈哈大王");
        dao.save(orderItem);
    }

    // 删除一条订单明细
    @Test
    public void testDeleteById(){
        dao.deleteById(1L);
    }

    // 修改一条订单明细
    @Test
    public void testUpdate(){
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(100);
        orderItem.setImageUrl("/hh.jpg");
        orderItem.setCount(1);
        orderItem.setBook_name("男游记");
        orderItem.setBook_author("哈哈大王");
        orderItem.setItemId(1L);
        dao.update(orderItem);
    }

    // 根据id查找一条订单明细
    @Test
    public void testFindById(){
        OrderItem or = dao.findById(1L);
        System.out.println("当前类--OrderItemTest,or" + or);
    }

}
