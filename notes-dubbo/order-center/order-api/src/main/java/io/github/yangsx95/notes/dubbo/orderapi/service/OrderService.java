package io.github.yangsx95.notes.dubbo.orderapi.service;

import io.github.yangsx95.notes.dubbo.orderapi.dto.AddOrderReq;
import io.github.yangsx95.notes.dubbo.orderapi.dto.QueryOrderByIdReq;
import io.github.yangsx95.notes.dubbo.orderapi.dto.QueryOrderByIdResp;

/**
 * 订单相关接口
 * @author Feahters
 * @date 2019/3/20
 */
public interface OrderService {

    void addOrder(AddOrderReq req);

    QueryOrderByIdResp queryOrderById(QueryOrderByIdReq req);
}
