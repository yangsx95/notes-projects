CREATE DATABASE IF NOT EXISTS `pay`;
create table IF NOT EXISTS `pay`.t_account
(
    id              bigint auto_increment,
    account_no      varchar(20)    not null comment '账户编号',
    account_balance decimal(11, 2) not null comment '账户余额',
    constraint t_product_pk primary key (id)
);
