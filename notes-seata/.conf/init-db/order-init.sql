CREATE DATABASE IF NOT EXISTS `order`;
create table IF NOT EXISTS `order`.t_order
(
    id         bigint auto_increment,
    order_no   varchar(20)    not null comment '订单号',
    product_no varchar(20)    not null comment '目标产品编号',
    account_no varchar(20)    not null comment '支付账户',
    count      int(10)        not null comment '产品数量',
    amount     decimal(11, 2) not null comment '订单金额',
    constraint t_order_pk primary key (id)
);
