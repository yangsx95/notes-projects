CREATE
DATABASE db_order CHARACTER SET 'utf8';
CREATE TABLE db_order.`pay_order`
(
    `order_id`     bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`      int(11) DEFAULT NULL,
    `product_name` varchar(128) DEFAULT NULL,
    `COUNT`        int(11) DEFAULT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12345679 DEFAULT CHARSET=utf8;

CREATE
DATABASE db_user CHARACTER SET 'utf8';
CREATE TABLE db_user.`users`
(
    `id`       int(11) NOT NULL,
    `username` varchar(255) NOT NULL COMMENT '用户昵称',
    `phone`    varchar(255) NOT NULL COMMENT '注册手机',
    `PASSWORD` varchar(255) DEFAULT NULL COMMENT '用户密码',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE db_order.t_course_1
(
    `cid`     BIGINT(20) NOT NULL,
    `user_id` BIGINT(20) DEFAULT NULL,
    `cname`   VARCHAR(50) DEFAULT NULL,
    `brief`   VARCHAR(50) DEFAULT NULL,
    `price` DOUBLE DEFAULT NULL,
    `status`  INT(11) DEFAULT NULL,
    PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE db_order.t_course_2
(
    `cid`     BIGINT(20) NOT NULL,
    `user_id` BIGINT(20) DEFAULT NULL,
    `cname`   VARCHAR(50) DEFAULT NULL,
    `brief`   VARCHAR(50) DEFAULT NULL,
    `price` DOUBLE DEFAULT NULL,
    `status`  INT(11) DEFAULT NULL,
    PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;