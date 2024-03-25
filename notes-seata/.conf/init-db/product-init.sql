CREATE DATABASE IF NOT EXISTS `product`;
create table IF NOT EXISTS `product`.t_product
(
    id         bigint auto_increment,
    product_no varchar(20) not null comment '目标产品编号',
    inventory  int(10)     not null comment '剩余库存',
    constraint t_product_pk primary key (id)
);

CREATE TABLE IF NOT EXISTS `product`.`undo_log`
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
ALTER TABLE `product`.`undo_log` ADD INDEX `ix_log_created` (`log_created`);
