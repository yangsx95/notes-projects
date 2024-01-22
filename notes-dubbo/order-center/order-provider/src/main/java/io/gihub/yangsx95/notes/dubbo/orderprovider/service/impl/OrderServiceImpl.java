package io.gihub.yangsx95.notes.dubbo.orderprovider.service.impl;

import io.github.yangsx95.notes.dubbo.orderapi.dto.AddOrderReq;
import io.github.yangsx95.notes.dubbo.orderapi.dto.QueryOrderByIdReq;
import io.github.yangsx95.notes.dubbo.orderapi.dto.QueryOrderByIdResp;
import io.github.yangsx95.notes.dubbo.orderapi.service.OrderService;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/3/20
 */
//@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Override
    public void addOrder(AddOrderReq req) {
        System.out.println("添加了一个订单" + req);
    }

    @Override
    public QueryOrderByIdResp queryOrderById(QueryOrderByIdReq req) {
        QueryOrderByIdResp resp 
                = new QueryOrderByIdResp();
        resp.setId(req.getOrderId());
        resp.setUsername("张三");
        resp.setMoney(10.5);
        resp.setNum(1);
        resp.setProductName("扫把");
        resp.setCreateTime("2018-01-01");
        return resp;
    }
}
