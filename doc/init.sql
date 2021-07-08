/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 127.0.0.1:3306
 Source Schema         : perm_template

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 08/07/2021 21:58:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_alipay_order
-- ----------------------------
DROP TABLE IF EXISTS `t_alipay_order`;
CREATE TABLE `t_alipay_order`  (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
                                   `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                   `delete_status` tinyint(2) NULL DEFAULT 0,
                                   `seller_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `invoice_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `notify_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `buyer_logon_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `total_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `out_trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `notify_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `trade_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `order_real_status` int(11) NULL DEFAULT NULL,
                                   `receipt_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `buyer_pay_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `gmt_create` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `gmt_payment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `seller_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `qr_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                   `charge_email_credit` int(11) NULL DEFAULT NULL,
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_alipay_order
-- ----------------------------
INSERT INTO `t_alipay_order` VALUES (1, '2021-06-15 16:44:02', '2021-07-05 11:42:07', 0, 'hggcuc0407@sandbox.com', '20.00', '2021-06-15 16:44:34', 'plx***@sandbox.com', '20.00', '2021061522001448090500939431', 'MB_20210615164401286567', '2021061500222164434048090511847582', 'TRADE_SUCCESS', 1, '20.00', '20.00', '2021-06-15 16:44:08', '2021-06-15 16:44:33', '脉邦_0615164401771530', '2088102175005362', 'https://qr.alipay.com/bax023977sxuunuj4qrg00d4', '366', 200);
INSERT INTO `t_alipay_order` VALUES (2, '2021-06-17 18:14:04', '2021-07-05 11:42:07', 0, '', '', '', '', '10.00', '', 'MB_20210617181403809166', '', '', 0, '', '', '', '', '脉邦_0617181403246727', '', 'https://qr.alipay.com/bax00366hyhwpp2ioxou0057', '366', 100);
INSERT INTO `t_alipay_order` VALUES (3, '2021-06-17 21:05:14', '2021-07-05 11:42:07', 0, '', '', '', '', '10.00', '', 'MB_20210617210513744221', '', '', 0, '', '', '', '', '脉邦_0617210513206836', '', 'https://qr.alipay.com/bax08838xoqvtcu7hifm00b4', '366', 100);
INSERT INTO `t_alipay_order` VALUES (4, '2021-06-17 21:07:01', '2021-07-05 11:42:07', 0, 'hggcuc0407@sandbox.com', '10.00', '2021-06-17 21:08:06', 'plx***@sandbox.com', '10.00', '2021061722001448090500940590', 'MB_20210617210700349111', '2021061700222210806048090511888296', 'TRADE_SUCCESS', 1, '10.00', '10.00', '2021-06-17 21:07:58', '2021-06-17 21:08:06', '脉邦_0617210700890746', '2088102175005362', 'https://qr.alipay.com/bax08069scxynvpmvzfo00d4', '366', 100);
INSERT INTO `t_alipay_order` VALUES (5, '2021-06-17 22:49:45', '2021-07-05 11:42:07', 0, 'hggcuc0407@sandbox.com', '20.00', '2021-06-17 22:50:12', 'plx***@sandbox.com', '20.00', '2021061722001448090500940591', 'MB_20210617224944269169', '2021061700222225012048090511894523', 'TRADE_SUCCESS', 1, '20.00', '20.00', '2021-06-17 22:50:04', '2021-06-17 22:50:11', '脉邦_0617224944174552', '2088102175005362', 'https://qr.alipay.com/bax04504iivkmfw53uvq0073', '366', 200);
INSERT INTO `t_alipay_order` VALUES (6, '2021-06-17 22:53:28', '2021-07-05 11:42:07', 0, '', '', '', '', '10.00', '', 'MB_20210617225327883861', '', '', 0, '', '', '', '', '脉邦_0617225327947830', '', 'https://qr.alipay.com/bax05520j25d82d2bnjw00f3', '366', 100);
INSERT INTO `t_alipay_order` VALUES (7, '2021-06-18 19:35:16', '2021-07-05 11:42:07', 0, 'hggcuc0407@sandbox.com', '10.00', '2021-06-18 19:35:37', 'plx***@sandbox.com', '10.00', '2021061822001448090500941123', 'MB_20210618193515476932', '2021061800222193537048090511898858', 'TRADE_SUCCESS', 1, '10.00', '10.00', '2021-06-18 19:35:26', '2021-06-18 19:35:36', '脉邦_0618193515292820', '2088102175005362', 'https://qr.alipay.com/bax09996hq88nd0xtgnx0044', '366', 100);
INSERT INTO `t_alipay_order` VALUES (8, '2021-06-21 17:29:45', '2021-07-05 11:42:07', 0, 'hggcuc0407@sandbox.com', '10.00', '2021-06-21 17:30:12', 'plx***@sandbox.com', '10.00', '2021062122001448090500942089', 'MB_20210621172944125969', '2021062100222173012048090511948566', 'TRADE_SUCCESS', 1, '10.00', '10.00', '2021-06-21 17:30:01', '2021-06-21 17:30:12', '脉邦_0621172944065852', '2088102175005362', 'https://qr.alipay.com/bax03044pmtnxynilvs200bb', '366', 100);
INSERT INTO `t_alipay_order` VALUES (9, '2021-06-21 17:42:08', '2021-07-05 11:42:07', 0, 'hggcuc0407@sandbox.com', '100.00', '2021-06-21 17:42:20', 'plx***@sandbox.com', '100.00', '2021062122001448090500941944', 'MB_20210621174207674375', '2021062100222174220048090511943800', 'TRADE_SUCCESS', 1, '100.00', '100.00', '2021-06-21 17:42:13', '2021-06-21 17:42:19', '脉邦_0621174207429610', '2088102175005362', 'https://qr.alipay.com/bax0887599m9udsycaoz005d', '366', 1000);
INSERT INTO `t_alipay_order` VALUES (10, '2021-06-28 09:40:40', '2021-07-05 11:42:07', 0, '', '', '', '', '300.00', '', 'MB_20210628094039850929', '', '', 0, '', '', '', '', '脉邦_0628094039134775', '', 'https://qr.alipay.com/bax07665amantiyex93b0026', '13584845885', 3000);
INSERT INTO `t_alipay_order` VALUES (11, '2021-06-28 11:07:51', '2021-07-05 11:42:07', 0, '', '', '', '', '3000.00', '', 'MB_20210628110749113329', '', '', 0, '', '', '', '', '脉邦_0628110749937377', '', 'https://qr.alipay.com/bax04929tcvhpm0i8bfx00e6', '13812656082', 30000);
INSERT INTO `t_alipay_order` VALUES (12, '2021-06-29 09:38:46', '2021-07-05 11:42:07', 0, '', '', '', '', '30.00', '', 'MB_20210629093845706488', '', '', 0, '', '', '', '', '脉邦_0629093845678494', '', 'https://qr.alipay.com/bax01277qudcbzxur5ud0046', '13913587336', 300);
INSERT INTO `t_alipay_order` VALUES (13, '2021-07-01 10:01:34', '2021-07-05 11:42:07', 0, '', '', '', '', '100.00', '', 'MB_20210701100133237468', '', '', 0, '', '', '', '', '脉邦_0701100133434887', '', 'https://qr.alipay.com/bax03803kowhqaeoey24009e', '13517229859', 1000);
INSERT INTO `t_alipay_order` VALUES (14, '2021-07-02 17:19:19', '2021-07-05 11:42:07', 0, '', '', '', '', '100.00', '', 'MB_20210702171917467646', '', '', 0, '', '', '', '', '脉邦_0702171917903643', '', 'https://qr.alipay.com/bax02787s1vaep85qcwu0067', '13584845885', 1000);
INSERT INTO `t_alipay_order` VALUES (15, '2021-07-03 21:44:44', '2021-07-05 11:42:07', 0, '', '', '', '', '2.20', '', 'MB_20210703214443886409', '', '', 0, '', '', '', '', '脉邦_0703214443555349', '', 'https://qr.alipay.com/bax072990s4gxumptbwb00a2', 'maibang1', 22);

-- ----------------------------
-- Table structure for t_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions`;
CREATE TABLE `t_permissions`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `perm_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
                                  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT 'url地址',
                                  `perm_alias` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限简称例如 system:index:main',
                                  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级Id',
                                  `icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
                                  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  `delete_status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除状态=={\\\"0\\\":\\\"正常\\\",\\\"1\\\":\\\"禁用\\\"}',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_permissions
-- ----------------------------
INSERT INTO `t_permissions` VALUES (1, '系统', '#', 'system', 0, NULL, '2019-08-15 16:21:54', '2021-07-05 11:56:11', 0);
INSERT INTO `t_permissions` VALUES (2, '首页', '#', 's:dashbord:menu', 1, NULL, '2019-08-15 16:22:39', '2021-07-05 14:21:24', 0);
INSERT INTO `t_permissions` VALUES (3, '系统权限', '#', 's:system:menu', 1, NULL, '2019-08-15 16:23:45', '2021-07-05 14:21:37', 0);
INSERT INTO `t_permissions` VALUES (4, 'testtest', '#', NULL, 1, NULL, '2021-07-05 10:58:08', '2021-07-05 11:02:41', 1);
INSERT INTO `t_permissions` VALUES (5, 'testtest', '#', NULL, 1, NULL, '2021-07-05 11:01:28', '2021-07-05 11:02:43', 1);
INSERT INTO `t_permissions` VALUES (6, '角色管理', '#', 'system:role:menu', 1, NULL, '2021-07-05 11:02:30', '2021-07-05 11:57:45', 1);
INSERT INTO `t_permissions` VALUES (7, '角色设置', '#', 'system:role_set:menu', 1, NULL, '2021-07-05 11:04:58', '2021-07-05 11:57:47', 1);
INSERT INTO `t_permissions` VALUES (8, 'testtest', '#', NULL, 1, NULL, '2021-07-05 11:55:19', '2021-07-05 11:55:22', 1);
INSERT INTO `t_permissions` VALUES (9, '系统用户', '#', 's:system:sys_user:menu', 3, NULL, '2021-07-05 11:56:55', '2021-07-05 14:22:01', 0);
INSERT INTO `t_permissions` VALUES (10, '权限管理', '#', 's:system:sys_perm:menu', 3, NULL, '2021-07-05 11:57:10', '2021-07-05 14:22:08', 0);
INSERT INTO `t_permissions` VALUES (11, '角色管理', '#', 's:system:sys_role:menu', 3, NULL, '2021-07-05 11:57:19', '2021-07-05 14:22:15', 0);
INSERT INTO `t_permissions` VALUES (12, '角色设置', '#', 's:system:sys_role_set:menu', 3, NULL, '2021-07-05 11:57:27', '2021-07-05 14:22:24', 0);
INSERT INTO `t_permissions` VALUES (13, '订单管理', '#', 's:order:menu', 1, NULL, '2021-07-05 11:57:51', '2021-07-05 14:22:35', 0);
INSERT INTO `t_permissions` VALUES (14, '支付宝订单', '#', 's:order:alipay_order:menu', 13, NULL, '2021-07-05 11:58:02', '2021-07-05 14:22:57', 0);
INSERT INTO `t_permissions` VALUES (15, '添加订单', '#', 's:order:alipay_order:btn_add:btn', 14, NULL, '2021-07-05 14:23:03', '2021-07-05 15:49:18', 1);
INSERT INTO `t_permissions` VALUES (16, '添加支付宝订单', '#', 's:order:alipay_order:btn_add:btn', 13, NULL, '2021-07-05 15:49:21', '2021-07-05 15:49:34', 0);
INSERT INTO `t_permissions` VALUES (17, '查看支付宝订单详情', '#', 's:order:alipay_order:btn_query:btn', 13, NULL, '2021-07-06 10:41:56', '2021-07-06 10:43:14', 0);
INSERT INTO `t_permissions` VALUES (18, '常用工具', '#', 's:tools:menu', 1, NULL, '2021-07-08 13:36:05', '2021-07-08 13:36:39', 0);
INSERT INTO `t_permissions` VALUES (19, '阿里云oss', '#', 's:tools:oss:menu', 18, NULL, '2021-07-08 13:36:58', '2021-07-08 13:37:12', 0);
INSERT INTO `t_permissions` VALUES (20, '代码生成', '#', 's:tools:generate:menu', 18, NULL, '2021-07-08 13:37:16', '2021-07-08 13:37:28', 0);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `role_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
                           `role_alias` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '别名',
                           `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                           `delete_status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除状态=={\\\"0\\\":\\\"正常\\\",\\\"1\\\":\\\"禁用\\\"}',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', 'ROLE_ADMIN', '2019-08-15 14:16:27', '2019-08-15 14:22:46', 0);
INSERT INTO `t_role` VALUES (2, '用户', 'ROLE_USER', '2019-08-15 16:07:15', '2019-08-15 16:07:15', 0);

-- ----------------------------
-- Table structure for t_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `t_role_perm`;
CREATE TABLE `t_role_perm`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `role_id` int(11) NOT NULL COMMENT '角色ID',
                                `perm_id` int(11) NOT NULL COMMENT '权限id',
                                `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                `delete_status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除状态=={\\\"0\\\":\\\"正常\\\",\\\"1\\\":\\\"禁用\\\"}',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role_perm
-- ----------------------------
INSERT INTO `t_role_perm` VALUES (1, 1, 1, '2021-07-05 10:44:42', '2021-07-05 11:58:28', 1);
INSERT INTO `t_role_perm` VALUES (2, 1, 2, '2021-07-05 10:44:42', '2021-07-05 11:58:28', 1);
INSERT INTO `t_role_perm` VALUES (3, 1, 3, '2021-07-05 10:44:42', '2021-07-05 11:58:28', 1);
INSERT INTO `t_role_perm` VALUES (4, 2, 1, '2021-07-05 10:44:53', '2021-07-05 11:58:33', 1);
INSERT INTO `t_role_perm` VALUES (5, 2, 2, '2021-07-05 10:44:53', '2021-07-05 11:58:33', 1);
INSERT INTO `t_role_perm` VALUES (6, 2, 3, '2021-07-05 10:44:53', '2021-07-05 11:58:33', 1);
INSERT INTO `t_role_perm` VALUES (7, 1, 1, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (8, 1, 2, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (9, 1, 3, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (10, 1, 9, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (11, 1, 10, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (12, 1, 11, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (13, 1, 12, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (14, 1, 13, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (15, 1, 14, '2021-07-05 11:58:28', '2021-07-05 14:24:01', 1);
INSERT INTO `t_role_perm` VALUES (16, 2, 2, '2021-07-05 11:58:33', '2021-07-05 15:37:47', 1);
INSERT INTO `t_role_perm` VALUES (17, 2, 13, '2021-07-05 11:58:33', '2021-07-05 15:37:47', 1);
INSERT INTO `t_role_perm` VALUES (18, 2, 14, '2021-07-05 11:58:33', '2021-07-05 15:37:47', 1);
INSERT INTO `t_role_perm` VALUES (19, 1, 1, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (20, 1, 2, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (21, 1, 3, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (22, 1, 9, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (23, 1, 10, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (24, 1, 11, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (25, 1, 12, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (26, 1, 13, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (27, 1, 14, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (28, 1, 15, '2021-07-05 14:24:01', '2021-07-05 15:49:49', 1);
INSERT INTO `t_role_perm` VALUES (29, 2, 2, '2021-07-05 15:37:47', '2021-07-05 15:48:25', 1);
INSERT INTO `t_role_perm` VALUES (30, 2, 10, '2021-07-05 15:37:47', '2021-07-05 15:48:25', 1);
INSERT INTO `t_role_perm` VALUES (31, 2, 11, '2021-07-05 15:37:47', '2021-07-05 15:48:25', 1);
INSERT INTO `t_role_perm` VALUES (32, 2, 13, '2021-07-05 15:37:47', '2021-07-05 15:48:25', 1);
INSERT INTO `t_role_perm` VALUES (33, 2, 14, '2021-07-05 15:37:47', '2021-07-05 15:48:25', 1);
INSERT INTO `t_role_perm` VALUES (34, 2, 2, '2021-07-05 15:48:25', '2021-07-05 15:49:52', 1);
INSERT INTO `t_role_perm` VALUES (35, 2, 10, '2021-07-05 15:48:25', '2021-07-05 15:49:52', 1);
INSERT INTO `t_role_perm` VALUES (36, 2, 11, '2021-07-05 15:48:25', '2021-07-05 15:49:52', 1);
INSERT INTO `t_role_perm` VALUES (37, 1, 1, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (38, 1, 2, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (39, 1, 3, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (40, 1, 9, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (41, 1, 10, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (42, 1, 11, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (43, 1, 12, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (44, 1, 13, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (45, 1, 14, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (46, 1, 16, '2021-07-05 15:49:49', '2021-07-06 10:45:56', 1);
INSERT INTO `t_role_perm` VALUES (47, 2, 2, '2021-07-05 15:49:52', '2021-07-05 17:42:24', 1);
INSERT INTO `t_role_perm` VALUES (48, 2, 10, '2021-07-05 15:49:52', '2021-07-05 17:42:24', 1);
INSERT INTO `t_role_perm` VALUES (49, 2, 11, '2021-07-05 15:49:52', '2021-07-05 17:42:24', 1);
INSERT INTO `t_role_perm` VALUES (50, 2, 14, '2021-07-05 15:49:52', '2021-07-05 17:42:24', 1);
INSERT INTO `t_role_perm` VALUES (51, 2, 2, '2021-07-05 17:42:24', '2021-07-05 17:43:53', 1);
INSERT INTO `t_role_perm` VALUES (52, 2, 10, '2021-07-05 17:42:24', '2021-07-05 17:43:53', 1);
INSERT INTO `t_role_perm` VALUES (53, 2, 11, '2021-07-05 17:42:24', '2021-07-05 17:43:53', 1);
INSERT INTO `t_role_perm` VALUES (54, 2, 12, '2021-07-05 17:42:24', '2021-07-05 17:43:53', 1);
INSERT INTO `t_role_perm` VALUES (55, 2, 14, '2021-07-05 17:42:24', '2021-07-05 17:43:53', 1);
INSERT INTO `t_role_perm` VALUES (56, 2, 1, '2021-07-05 17:43:53', '2021-07-06 10:43:26', 1);
INSERT INTO `t_role_perm` VALUES (57, 2, 2, '2021-07-05 17:43:53', '2021-07-06 10:43:26', 1);
INSERT INTO `t_role_perm` VALUES (58, 2, 3, '2021-07-05 17:43:53', '2021-07-06 10:43:26', 1);
INSERT INTO `t_role_perm` VALUES (59, 2, 9, '2021-07-05 17:43:53', '2021-07-06 10:43:26', 1);
INSERT INTO `t_role_perm` VALUES (60, 2, 12, '2021-07-05 17:43:53', '2021-07-06 10:43:26', 1);
INSERT INTO `t_role_perm` VALUES (61, 2, 13, '2021-07-05 17:43:53', '2021-07-06 10:43:26', 1);
INSERT INTO `t_role_perm` VALUES (62, 2, 14, '2021-07-05 17:43:53', '2021-07-06 10:43:26', 1);
INSERT INTO `t_role_perm` VALUES (63, 2, 1, '2021-07-06 10:43:26', '2021-07-06 10:43:26', 0);
INSERT INTO `t_role_perm` VALUES (64, 2, 2, '2021-07-06 10:43:26', '2021-07-06 10:43:26', 0);
INSERT INTO `t_role_perm` VALUES (65, 2, 3, '2021-07-06 10:43:26', '2021-07-06 10:43:26', 0);
INSERT INTO `t_role_perm` VALUES (66, 2, 9, '2021-07-06 10:43:26', '2021-07-06 10:43:26', 0);
INSERT INTO `t_role_perm` VALUES (67, 2, 12, '2021-07-06 10:43:26', '2021-07-06 10:43:26', 0);
INSERT INTO `t_role_perm` VALUES (68, 2, 13, '2021-07-06 10:43:26', '2021-07-06 10:43:26', 0);
INSERT INTO `t_role_perm` VALUES (69, 2, 14, '2021-07-06 10:43:26', '2021-07-06 10:43:26', 0);
INSERT INTO `t_role_perm` VALUES (70, 1, 1, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (71, 1, 2, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (72, 1, 3, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (73, 1, 9, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (74, 1, 10, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (75, 1, 11, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (76, 1, 12, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (77, 1, 13, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (78, 1, 14, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (79, 1, 16, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (80, 1, 17, '2021-07-06 10:45:56', '2021-07-08 13:37:33', 1);
INSERT INTO `t_role_perm` VALUES (81, 1, 1, '2021-07-08 13:37:33', '2021-07-08 13:37:33', 0);
INSERT INTO `t_role_perm` VALUES (82, 1, 2, '2021-07-08 13:37:33', '2021-07-08 13:37:33', 0);
INSERT INTO `t_role_perm` VALUES (83, 1, 3, '2021-07-08 13:37:33', '2021-07-08 13:37:33', 0);
INSERT INTO `t_role_perm` VALUES (84, 1, 9, '2021-07-08 13:37:33', '2021-07-08 13:37:33', 0);
INSERT INTO `t_role_perm` VALUES (85, 1, 10, '2021-07-08 13:37:33', '2021-07-08 13:37:33', 0);
INSERT INTO `t_role_perm` VALUES (86, 1, 11, '2021-07-08 13:37:33', '2021-07-08 13:37:33', 0);
INSERT INTO `t_role_perm` VALUES (87, 1, 12, '2021-07-08 13:37:33', '2021-07-08 13:37:33', 0);
INSERT INTO `t_role_perm` VALUES (88, 1, 13, '2021-07-08 13:37:34', '2021-07-08 13:37:34', 0);
INSERT INTO `t_role_perm` VALUES (89, 1, 14, '2021-07-08 13:37:34', '2021-07-08 13:37:34', 0);
INSERT INTO `t_role_perm` VALUES (90, 1, 16, '2021-07-08 13:37:34', '2021-07-08 13:37:34', 0);
INSERT INTO `t_role_perm` VALUES (91, 1, 17, '2021-07-08 13:37:34', '2021-07-08 13:37:34', 0);
INSERT INTO `t_role_perm` VALUES (92, 1, 18, '2021-07-08 13:37:34', '2021-07-08 13:37:34', 0);
INSERT INTO `t_role_perm` VALUES (93, 1, 19, '2021-07-08 13:37:34', '2021-07-08 13:37:34', 0);
INSERT INTO `t_role_perm` VALUES (94, 1, 20, '2021-07-08 13:37:34', '2021-07-08 13:37:34', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                           `real_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
                           `english_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名',
                           `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                           `telephone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
                           `job_no` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
                           `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
                           `gender` tinyint(2) NULL DEFAULT NULL COMMENT '性别=={\\\"0\\\":\\\"男\\\",\\\"1\\\":\\\"女\\\"}',
                           `birth_day` date NULL DEFAULT NULL COMMENT '出生日期',
                           `head_img` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
                           `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
                           `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                           `delete_status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除状态=={\\\"0\\\":\\\"正常\\\",\\\"1\\\":\\\"禁用\\\"}',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'user', '老张', NULL, NULL, NULL, '2', '$2a$10$xYYzL.2xpdKX90.Qxx1MVuJgQJf6mpxSGB9L4Nz3caQRF5TxNeoQi', 1, NULL, NULL, NULL, '2019-08-15 16:02:28', '2021-07-05 10:38:15', 0);
INSERT INTO `t_user` VALUES (2, 'admin', '管理员', NULL, NULL, NULL, '1', '$2a$10$N6HcFnHsUoTQMGqGsM.F9.YXyfZkLY77W1jr5GcPvZf9OpwCS6NwG', 0, NULL, NULL, NULL, '2021-04-25 11:36:32', '2021-07-05 10:37:12', 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `user_id` int(11) NOT NULL COMMENT '用户id',
                                `role_id` int(11) NOT NULL COMMENT '角色ID',
                                `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                `delete_status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除状态=={\\\"0\\\":\\\"正常\\\",\\\"1\\\":\\\"禁用\\\"}',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 2, '2019-08-15 14:16:34', '2021-07-05 10:38:15', 0);
INSERT INTO `t_user_role` VALUES (2, 2, 1, '2019-08-15 16:10:59', '2021-07-05 14:41:41', 0);

SET FOREIGN_KEY_CHECKS = 1;
