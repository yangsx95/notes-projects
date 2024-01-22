package me.feathers.online.order.dao;

import java.util.Date;
import java.util.List;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderStatus;
import me.feathers.online.entity.PageBean;
import me.feathers.online.entity.User;

/**
 * 订单dao层
 *
 * @author Feathers
 * @create 2017-07-01-10:37
 */
public interface OrderDao {

    /**
     * 保存订单
     *
     * @param o
     */
    void save(Order o);

    /*****
     * 按主键查询
     * @param id
     */
    Order findById(Long id);

    /****
     * 删除
     * @param o
     */
    void delete(Order o);

    /**
     * 分页查询订单
     *
     * @param user
     * @return
     */
    List<Order> findByPage(User user, Integer pageNow, Integer pageSize, OrderStatus
            status, String orderBy, String orderNum,
                           Date start, Date end);

    /******
     * 获取指定用户的总订单数
     * @return
     */
    long rowCount(User user, OrderStatus status);


}
