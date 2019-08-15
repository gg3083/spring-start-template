/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : perm_template

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-08-15 16:44:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions`;
CREATE TABLE `t_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perm_name` varchar(16) NOT NULL COMMENT '权限名称',
  `url` varchar(128) DEFAULT '#' COMMENT 'url地址',
  `perm_alias` varchar(32) DEFAULT NULL COMMENT '权限简称例如 system:index:main',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级Id',
  `icon` varchar(128) DEFAULT NULL COMMENT '图标',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除状态=={\\"0\\":\\"正常\\",\\"1\\":\\"禁用\\"}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限表';

-- ----------------------------
-- Records of t_permissions
-- ----------------------------
INSERT INTO `t_permissions` VALUES ('1', '系统设置', '#', 'system', '0', null, '2019-08-15 16:21:54', '2019-08-15 16:23:58', '0');
INSERT INTO `t_permissions` VALUES ('2', '用户管理', '#', 'system:user:list', '1', null, '2019-08-15 16:22:39', '2019-08-15 16:24:03', '0');
INSERT INTO `t_permissions` VALUES ('3', '权限管理', '#', 'system:role:list', '1', null, '2019-08-15 16:23:45', '2019-08-15 16:24:12', '0');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) NOT NULL COMMENT '角色名称',
  `role_alias` varchar(16) NOT NULL COMMENT '别名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除状态=={\\"0\\":\\"正常\\",\\"1\\":\\"禁用\\"}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', 'ROLE_ADMIN', '2019-08-15 14:16:27', '2019-08-15 14:22:46', '0');
INSERT INTO `t_role` VALUES ('2', '用户', 'ROLE_USER', '2019-08-15 16:07:15', '2019-08-15 16:07:15', '0');

-- ----------------------------
-- Table structure for t_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `t_role_perm`;
CREATE TABLE `t_role_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `perm_id` int(11) NOT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除状态=={\\"0\\":\\"正常\\",\\"1\\":\\"禁用\\"}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限关联表';

-- ----------------------------
-- Records of t_role_perm
-- ----------------------------
INSERT INTO `t_role_perm` VALUES ('1', '1', '1', '2019-08-15 16:24:23', '2019-08-15 16:24:23', '0');
INSERT INTO `t_role_perm` VALUES ('2', '1', '2', '2019-08-15 16:24:25', '2019-08-15 16:24:25', '0');
INSERT INTO `t_role_perm` VALUES ('3', '1', '3', '2019-08-15 16:24:28', '2019-08-15 16:24:28', '0');
INSERT INTO `t_role_perm` VALUES ('4', '2', '1', '2019-08-15 16:24:31', '2019-08-15 16:24:31', '0');
INSERT INTO `t_role_perm` VALUES ('5', '2', '2', '2019-08-15 16:24:35', '2019-08-15 16:24:35', '0');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `real_name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `english_name` varchar(32) DEFAULT NULL COMMENT '英文名',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `telephone` varchar(16) DEFAULT NULL COMMENT '电话',
  `job_no` varchar(8) DEFAULT NULL COMMENT '工号',
  `password` varchar(128) NOT NULL COMMENT '登录密码',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别=={\\"0\\":\\"男\\",\\"1\\":\\"女\\"}',
  `birth_day` date DEFAULT NULL COMMENT '出生日期',
  `head_img` varchar(64) DEFAULT NULL COMMENT '头像',
  `address` varchar(128) DEFAULT NULL COMMENT '住址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除状态=={\\"0\\":\\"正常\\",\\"1\\":\\"禁用\\"}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '老管', null, null, null, null, '123456', null, null, null, null, '2019-08-15 14:16:15', '2019-08-15 14:16:15', '0');
INSERT INTO `t_user` VALUES ('2', 'user', null, null, null, null, null, '$2a$10$xYYzL.2xpdKX90.Qxx1MVuJgQJf6mpxSGB9L4Nz3caQRF5TxNeoQi', null, null, null, null, '2019-08-15 16:02:28', '2019-08-15 16:02:28', '0');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '删除状态=={\\"0\\":\\"正常\\",\\"1\\":\\"禁用\\"}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1', '2019-08-15 14:16:34', '2019-08-15 14:16:34', '0');
INSERT INTO `t_user_role` VALUES ('2', '2', '2', '2019-08-15 16:10:59', '2019-08-15 16:10:59', '0');
