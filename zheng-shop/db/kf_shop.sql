DROP TABLE IF EXISTS `shop_product`;

CREATE TABLE `shop_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` decimal(21,6) NOT NULL COMMENT '价格',
  `number` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `pro_product` */

insert  into `shop_product`(`id`,`name`,`price`,`number`) values (1,'小米6X','1499.000000',1000);
insert  into `shop_product`(`id`,`name`,`price`,`number`) values (2,'小米8 透明探索版','3699.000000',100);
insert  into `shop_product`(`id`,`name`,`price`,`number`) values (3,'小米电视4C 50英寸','1999.990000',800);
