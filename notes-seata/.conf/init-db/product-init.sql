CREATE DATABASE IF NOT EXISTS `product`;
create table IF NOT EXISTS `product`.t_product
(
    id         bigint auto_increment,
    product_no varchar(20) not null comment '目标产品编号',
    inventory  int(10)     not null comment '剩余库存',
    constraint t_product_pk primary key (id)
);

