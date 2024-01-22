package me.feathers.online.order.dao.impl;

import me.feathers.online.entity.OrderItem;
import me.feathers.online.order.dao.OrderItemDao;
import me.feathers.online.util.HibernateTemplate;

/**
 * OrderItemDao实现类
 *
 * @author Feathers
 * @create 2017-07-02-18:19
 */
public class OrderItemDaoImpl implements OrderItemDao {
    @Override
    public void save(OrderItem item) {
        HibernateTemplate.execute(ses -> {
            ses.save(item);
            return null;
        });
    }

    @Override
    public void deleteById(Long id) {
        HibernateTemplate.execute(ses -> {
            OrderItem or = this.findById(id);
            ses.delete(or);
            return null;
        });
    }

    @Override
    public void update(OrderItem orderItem) {
        HibernateTemplate.execute(ses -> {
            ses.update(orderItem);
            return null;
        });
    }

    @Override
    public OrderItem findById(Long id) {
        return (OrderItem) HibernateTemplate.execute(ses -> {
            return ses.get(OrderItem.class, id);
        });
    }
}
