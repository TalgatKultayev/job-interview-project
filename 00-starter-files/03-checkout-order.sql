
CREATE TABLE `product_directory`.`checkout_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `product_price` decimal(13,2) DEFAULT NULL,
  `product_quantity` int DEFAULT NULL,
  `unique_order_id` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_order_id_UNIQUE` (`unique_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;