package me.feathers.online.order.service;

import java.util.Date;
import java.util.List;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderStatus;
import me.feathers.online.entity.PageBean;
import me.feathers.online.entity.User;

/**
 * 订单服务层
 *
 * @author Feathers
 * @create 2017-07-01-10:44
 */
public interface OrderService {

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

    /******
     * 获取指定用户的总订单数
     * @return
     */
    long rowCount(User user, OrderStatus status);

    /**
     * 分页查询
     * @param pageNow 当前页
     * @param pageSize 每页大小
     * @param status 查询指定状态
     * @param orderBy 排序方式
     * @return 分页数据
     */
    PageBean<Order> findByPage(User user, Integer pageNow, Integer pageSize,
                               OrderStatus status, String orderBy, String orderNum,
                               Date start, Date end);

}
