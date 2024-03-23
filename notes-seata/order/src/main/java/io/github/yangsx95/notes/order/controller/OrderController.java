package io.github.yangsx95.notes.order.controller;

import io.github.yangsx95.notes.order.entity.Order;
import io.github.yangsx95.notes.order.feign.PayFeign;
import io.github.yangsx95.notes.order.feign.ProductFeign;
import io.github.yangsx95.notes.order.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author yangshunxiang
 * @since 2024/3/23
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private PayFeign payFeign;

    @Resource
    private ProductFeign productFeign;

    @GlobalTransactional // 声明一个全局事务
    @GetMapping(value = "/add")
    public Long addOrder(@RequestParam("orderNo") String orderNo,
                         @RequestParam("productNo") String productNo,
                         @RequestParam("count") Integer count,
                         @RequestParam("accountNo") String accountNo,
                         @RequestParam("amount") BigDecimal amount) {
        // 创建订单
        Order order = new Order(null, orderNo, productNo, accountNo, count, amount);
        orderMapper.insert(order);

        // 扣减库存
        productFeign.deductionInventory(productNo, count);

        // 扣减金额
        payFeign.payMoney(accountNo, amount);

        return order.getId();
    }

}
