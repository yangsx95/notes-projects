package me.feathers.online.order.service.impl;

import java.util.Date;
import java.util.List;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderStatus;
import me.feathers.online.entity.PageBean;
import me.feathers.online.entity.User;
import me.feathers.online.order.dao.OrderDao;
import me.feathers.online.order.service.OrderService;
import me.feathers.online.util.BeansFactory;

/**
 * OrderService实现类
 *
 * @author Feathers
 * @create 2017-07-02-21:09
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao dao = (OrderDao) BeansFactory.getBean("orderDao");

    @Override
    public void save(Order o) {
        dao.save(o);
    }

    @Override
    public Order findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public void delete(Order o) {
        dao.delete(o);
    }

    @Override
    public long rowCount(User user, OrderStatus status) {
        return dao.rowCount(user, status);
    }

    @Override
    public PageBean<Order> findByPage(User user, Integer pageNow,
                                      Integer pageSize, OrderStatus
                                        status, String orderBy, String orderNum,
                                      Date start, Date end) {

        PageBean<Order> pageBean = new PageBean<>();
        long count = this.rowCount(user, status);
        pageBean.setCount(count);
        // 如果pageSize伪null，设置一个默认为2的pageSize
        if(pageSize==null){
            pageSize = 2;
        }
        pageBean.setPageSize(pageSize);
        pageBean.setPageCount((int) ((count % pageSize == 0) ?
                (count / pageSize) : (count / pageSize + 1)));
        if (pageNow == null){
            pageNow = 1;
        }
        pageBean.setPageNow(pageNow);
        List<Order> list = dao.findByPage(user, pageNow, pageSize, status, orderBy, orderNum,
                start, end);
        pageBean.setDatas(list);
        return pageBean;
    }
}
