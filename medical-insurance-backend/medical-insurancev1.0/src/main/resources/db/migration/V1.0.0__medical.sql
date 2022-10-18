/*
Navicat MySQL Data Transfer

Source Server         : sql_conn_47.103.119.53
Source Server Version : 80026
Source Host           : 47.103.119.53:3306
Source Database       : medicalV1.0

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-08-06 18:31:03
*/
-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
    `id` int(2) unsigned NOT NULL AUTO_INCREMENT,
    `codeid` char(3) NOT NULL,
    `code` varchar(3) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for baoxiaoshenpi
-- ----------------------------
DROP TABLE IF EXISTS `baoxiaoshenpi`;
CREATE TABLE `baoxiaoshenpi` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `shenpiid` varchar(32) NOT NULL,
  `identity` varchar(18) NOT NULL,
  `scardnum` varchar(9) NOT NULL,
  `codeid` char(3) NOT NULL,
  `processtime` datetime NOT NULL,
  `total` double(6,2) DEFAULT NULL,
  `operationid` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- ----------------------------
-- Table structure for druginfo
-- ----------------------------
DROP TABLE IF EXISTS `druginfo`;
CREATE TABLE `druginfo` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `drugid` char(32) NOT NULL,
  `drugname` varchar(20) NOT NULL,
  `price` double(7,2) NOT NULL,
  `qualification` char(1) NOT NULL,
  `approval` char(1) NOT NULL,
  `manufactor` varchar(80) DEFAULT NULL,
  `drugtypeid` char(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for drugtype
-- ----------------------------
DROP TABLE IF EXISTS `drugtype`;
CREATE TABLE `drugtype` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `drugtypeid` char(3) DEFAULT NULL,
  `drugtypename` varchar(5) DEFAULT NULL,
  `discount` double(3,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(18) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `roleid` char(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for medicalparam
-- ----------------------------
DROP TABLE IF EXISTS `medicalparam`;
CREATE TABLE `medicalparam` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `regionid` char(16) NOT NULL,
  `start` double(6,2) NOT NULL,
  `end` double(8,2) NOT NULL,
  `firstlevel` double(6,2) NOT NULL,
  `secondlevel` double(8,2) NOT NULL,
  `firstdiscount` double(3,2) NOT NULL,
  `seconddiscount` double(3,2) NOT NULL,
  `thirddiscount` double(3,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `operationid` char(32) NOT NULL,
  `identity` varchar(18) NOT NULL,
  `scardnum` char(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `permissionid` char(4) NOT NULL,
  `permissionname` varchar(10) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` char(3) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` char(3) NOT NULL,
  `permissionid` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for socialcard
-- ----------------------------
DROP TABLE IF EXISTS `socialcard`;
CREATE TABLE `socialcard` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `scardnum` char(16) NOT NULL,
  `money` double(10,2) NOT NULL,
  `regionid` char(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for specialsolution
-- ----------------------------
DROP TABLE IF EXISTS `specialsolution`;
CREATE TABLE `specialsolution` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `shenpiid` varchar(32) DEFAULT NULL,
  `operationid` varchar(32) NOT NULL,
  `codeid` char(3) NOT NULL,
  `sfinishtime` datetime DEFAULT NULL,
  `total` double(8,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for treatmentinfo
-- ----------------------------
DROP TABLE IF EXISTS `treatmentinfo`;
CREATE TABLE `treatmentinfo` (
  `id`int(6) unsigned NOT NULL AUTO_INCREMENT,
  `treatmentid` char(32) NOT NULL,
  `scardnum` char(16) DEFAULT NULL,
  `drugid` char(32) DEFAULT NULL,
  `drugnum` int DEFAULT NULL,
  `totalprice` double(7,2) DEFAULT NULL,
  `treatmenttime` datetime NOT NULL,
  `shenpiid` varchar(32) DEFAULT NULL, 
  `codeid` char(3) DEFAULT NULL,
  `checkouttime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(18) NOT NULL,
  `name` varchar(20) NOT NULL,
  `identity` char(18) NOT NULL,
  `codeid` char(3) NOT NULL,
  `type` varchar(5) NOT NULL,
  `birthday` date NOT NULL,
  `nation` varchar(6) NOT NULL,
  `address` varchar(40) NOT NULL,
  `phonenum` varchar(11) DEFAULT NULL,
  `scardnum` char(16) DEFAULT NULL,
  `registedinfo` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
