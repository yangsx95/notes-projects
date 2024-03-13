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

CREATE
DATABASE db_contract_0 CHARACTER SET 'utf8';
CREATE TABLE db_contract_0.t_contract_0
(
    `cid`       bigint(20) NOT NULL,
    `user_id`   bigint(20) DEFAULT NULL,
    `corder_no` bigint(20) DEFAULT NULL,
    `cname`     varchar(50) DEFAULT NULL,
    `brief`     varchar(50) DEFAULT NULL,
    `price` double DEFAULT NULL,
    `status`    int(11) DEFAULT NULL,
    PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE db_contract_0.t_contract_1
(
    `cid`       bigint(20) NOT NULL,
    `user_id`   bigint(20) DEFAULT NULL,
    `corder_no` bigint(20) DEFAULT NULL,
    `cname`     varchar(50) DEFAULT NULL,
    `brief`     varchar(50) DEFAULT NULL,
    `price` double DEFAULT NULL,
    `status`    int(11) DEFAULT NULL,
    PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE
DATABASE db_contract_1 CHARACTER SET 'utf8';
CREATE TABLE db_contract_1.t_contract_0
(
    `cid`       bigint(20) NOT NULL,
    `user_id`   bigint(20) DEFAULT NULL,
    `corder_no` bigint(20) DEFAULT NULL,
    `cname`     varchar(50) DEFAULT NULL,
    `brief`     varchar(50) DEFAULT NULL,
    `price` double DEFAULT NULL,
    `status`    int(11) DEFAULT NULL,
    PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE db_contract_1.t_contract_1
(
    `cid`       bigint(20) NOT NULL,
    `user_id`   bigint(20) DEFAULT NULL,
    `corder_no` bigint(20) DEFAULT NULL,
    `cname`     varchar(50) DEFAULT NULL,
    `brief`     varchar(50) DEFAULT NULL,
    `price` double DEFAULT NULL,
    `status`    int(11) DEFAULT NULL,
    PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE db_contract_0.`t_contract_section_0`
(
    `id`           bigint(11) NOT NULL,
    `cid`          bigint(11) DEFAULT NULL,
    `corder_no`    bigint(20) DEFAULT NULL,
    `user_id`      bigint(20) DEFAULT NULL,
    `section_name` varchar(50) DEFAULT NULL,
    `status`       int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE db_contract_0.`t_contract_section_1`
(
    `id`           bigint(11) NOT NULL,
    `cid`          bigint(11) DEFAULT NULL,
    `corder_no`    bigint(20) DEFAULT NULL,
    `user_id`      bigint(20) DEFAULT NULL,
    `section_name` varchar(50) DEFAULT NULL,
    `status`       int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE db_contract_1.`t_contract_section_0`
(
    `id`           bigint(11) NOT NULL,
    `cid`          bigint(11) DEFAULT NULL,
    `corder_no`    bigint(20) DEFAULT NULL,
    `user_id`      bigint(20) DEFAULT NULL,
    `section_name` varchar(50) DEFAULT NULL,
    `status`       int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE db_contract_1.`t_contract_section_1`
(
    `id`           bigint(11) NOT NULL,
    `cid`          bigint(11) DEFAULT NULL,
    `corder_no`    bigint(20) DEFAULT NULL,
    `user_id`      bigint(20) DEFAULT NULL,
    `section_name` varchar(50) DEFAULT NULL,
    `status`       int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
