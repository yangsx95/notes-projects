package me.feathers.online.order.service.impl;

import me.feathers.online.entity.OrderItem;
import me.feathers.online.order.dao.OrderItemDao;
import me.feathers.online.order.service.OrderItemService;
import me.feathers.online.util.BeansFactory;

/**
 * OrderItem服务层实现类
 *
 * @author Feathers
 * @create 2017-07-02-21:08
 */
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDao dao = (OrderItemDao) BeansFactory.getBean("orderItemDao");

    @Override
    public void save(OrderItem item) {
        dao.save(item);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        dao.update(orderItem);
    }

    @Override
    public OrderItem findById(Long id) {
        return dao.findById(id);
    }
}
