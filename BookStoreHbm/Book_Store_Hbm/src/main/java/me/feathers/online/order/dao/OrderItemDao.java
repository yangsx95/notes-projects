package me.feathers.online.order.dao;

import java.util.List;

import me.feathers.online.entity.OrderItem;

/**
 * 订单明细dao
 *
 * @author Feathers
 * @create 2017-07-01-10:41
 */
public interface OrderItemDao {

    // 保存一条订单明细
    void save(OrderItem item);

    // 删除一条订单明细
    void deleteById(Long id);

    // 修改一条订单明细
    void update(OrderItem orderItem);

    // 根据id查找一条订单明细
    OrderItem findById(Long id);
}
