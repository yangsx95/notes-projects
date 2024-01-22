package me.feathers.online.order.service;

import me.feathers.online.entity.Order;
import me.feathers.online.entity.OrderItem;
import me.feathers.online.entity.OrderStatus;
import me.feathers.online.entity.PageBean;

/**
 * 订单明细服务层
 *
 * @author Feathers
 * @create 2017-07-01-10:45
 */
public interface OrderItemService {

    // 保存一条订单明细
    void save(OrderItem item);

    // 删除一条订单明细
    void deleteById(Long id);

    // 修改一条订单明细
    void update(OrderItem orderItem);

    // 根据id查找一条订单明细
    OrderItem findById(Long id);
}
