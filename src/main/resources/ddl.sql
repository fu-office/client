create database if not exists lbyt;
-- client table
drop table if exists lbyt_client;
CREATE TABLE if not exists `lbyt_client` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `card_no` varchar(16) NOT NULL,
  `register_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `client_name` varchar(30) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `tel_number` varchar(20) DEFAULT NULL,
  `post_code` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `shop_name` varchar(100) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `card_no` (`card_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- area table
drop table if exists area;
create table if not exists area(
	id int unsigned primary key auto_increment,
	long_name varchar(100) unique not null,
	prov_name varchar(50) unique not null,
	city_name varchar(50) not null,
	create_date date
);

--gifts table
drop table if exists gifts;
create table gifts(
	id int unsigned primary key auto_increment,
	c_id int,
	phone varchar(20),
	c_name varchar(40),
	c_date date
);