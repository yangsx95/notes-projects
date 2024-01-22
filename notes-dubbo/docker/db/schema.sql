create database `product_center` default character set utf8 collate utf8_general_ci;
use product_center;

drop table if exists `product`;
create table `product`
(
  `id`           bigint(20)  AUTO_INCREMENT,
  `product_code` varchar(20) NOT NULL,
  `product_name` varchar(20) NOT NULL,
  `money`        double      NOT NULL,
  `stock`        integer     NOT NULL,
  `update_time`  varchar(255) DEFAULT NULL,
  `create_time`  varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;
insert into `product` (`product_code`, `product_name`, `money`, `stock`, `create_time`)
  values
  ('pro100', '扫把', 17.9, 10, now()),
  ('pro101', '盆', 15, 10, now());



create database `order_center` default character set utf8 collate utf8_general_ci;
use order_center;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
  `id`           bigint(20)   AUTO_INCREMENT,
  `username`     varchar(20)  NOT NULL,
  `product_code` varchar(20)      NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `money`        double       NOT NULL,
  `num`          smallint     NOT NULL,
  `create_time`  varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;
INSERT INTO `order` (`username`, `product_code`, `product_name`, `money`, `num`, `create_time`)
  VALUES
  ('张三', 'pro100', '扫把', 17.9, 1, now()),
  ('李四', 'pro101', '盆', 15, 1, now());


