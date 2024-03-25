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
CREATE TABLE IF NOT EXISTS `order`.`undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';
ALTER TABLE `order`.`undo_log` ADD INDEX `ix_log_created` (`log_created`);