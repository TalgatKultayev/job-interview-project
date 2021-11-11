CREATE DATABASE `product_directory` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `product_directory`.product_price_table (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_name_UNIQUE` (`product_name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

INSERT INTO `product_directory`.`product_price_table`
(`id`,
`product_name`,
`product_price`)
VALUES
(1,'Flour',0.64),
(2,'Rice',1.78),
(3,'Sugar',0.94),
(4,'Salt',0.13),
(5,'Milk', 0.74),
(6,'Eggs',1.22),
(7,'Cooking Oil', 1.57),
(8,'Beef',7.44),
(9,'Poultry',2.37),
(10,'Potatoes',0.60),
(11,'Onions', 0.28),
(12,'Carrots',0.31),
(13,'Beans', 4.25),
(14,'Bananas',1.22),
(15,'Apples',1.15),
(16,'Coca-Cola',0.40),
(17,'Orange Juice', 1.26),
(18, 'Bottled Water', 0.73),
(19,'Red Bull', 1.22),
(20, 'Nescafe Gold', 8.28);