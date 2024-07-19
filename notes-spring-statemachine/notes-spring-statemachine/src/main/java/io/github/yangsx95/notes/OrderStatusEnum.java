package io.github.yangsx95.notes;

/**
 * 订单状态
 */
public enum OrderStatusEnum {
    // 待支付，待发货，待收货，订单结束
    WAIT_PAYMENT, WAIT_DELIVER, WAIT_RECEIVE, FINISH;
}
