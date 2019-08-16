/*
 Navicat Premium Data Transfer

 Source Server         : wangjiangfei
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : article_auth

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 16/08/2019 16:05:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id，主键',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章内容',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_user_id` int(11) NULL DEFAULT NULL COMMENT '创建改文章的用户id',
  `update_user_id` int(11) NULL DEFAULT NULL COMMENT '最新修改文章的用户id',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1.有效  2无效',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `create_user_id`(`create_user_id`) USING BTREE,
  INDEX `update_user_id`(`update_user_id`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`create_user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `article_ibfk_2` FOREIGN KEY (`update_user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发布号作者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (5, '莎士比亚', '2017-10-25 09:08:45', '2019-05-30 16:08:20', 10003, 10003, '1');
INSERT INTO `article` VALUES (6, '亚里士多德', '2017-10-26 10:49:28', '2019-05-31 09:17:14', 10007, 10009, '1');
INSERT INTO `article` VALUES (10, '亚历山大', '2017-10-26 14:57:45', '2019-05-30 16:08:52', 10009, 10009, '1');
INSERT INTO `article` VALUES (11, '李白', '2017-10-26 15:23:42', '2019-05-30 16:08:59', 10009, 10009, '1');
INSERT INTO `article` VALUES (19, '文章test2', '2017-11-18 13:37:07', '2019-05-30 16:09:05', 10009, 10009, '1');
INSERT INTO `article` VALUES (23, '<p>啊啊啊</p>', '2019-06-07 09:43:45', '2019-06-07 09:43:45', 10004, 10004, '1');
INSERT INTO `article` VALUES (24, '<p>我是24</p>', '2019-06-07 19:48:33', '2019-06-11 21:21:06', 10004, 10004, '1');
INSERT INTO `article` VALUES (25, NULL, '2019-06-07 19:48:59', '2019-06-07 19:48:59', 10004, 10004, '1');
INSERT INTO `article` VALUES (26, NULL, '2019-06-07 19:49:51', '2019-06-07 19:49:51', 10004, 10004, '1');
INSERT INTO `article` VALUES (27, '<p>aaaaaaaaaaaaaa</p>', '2019-06-07 20:02:19', '2019-06-07 20:02:19', 10004, 10004, '1');
INSERT INTO `article` VALUES (28, '<p>bbbbbbbbbbbb</p>', '2019-06-07 20:02:59', '2019-06-07 20:02:59', 10004, 10004, '1');
INSERT INTO `article` VALUES (29, '<p>你好世界</p>', '2019-06-08 10:26:40', '2019-06-08 10:26:40', 10004, 10004, '1');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `permission_id` int(11) NOT NULL DEFAULT 0 COMMENT '自定id,主要供前端展示权限列表分类排序使用.',
  `menu_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` tinyint(1) NULL DEFAULT 2 COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是\"列表\"权限是必选',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (101, 'article', '文章管理', 'article:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (102, 'article', '文章管理', 'article:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (103, 'article', '文章管理', 'article:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (601, 'user', '用户', 'user:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (602, 'user', '用户', 'user:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (603, 'user', '用户', 'user:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (701, 'role', '角色权限', 'role:list', '列表', 1);
INSERT INTO `sys_permission` VALUES (702, 'role', '角色权限', 'role:add', '新增', 2);
INSERT INTO `sys_permission` VALUES (703, 'role', '角色权限', 'role:update', '修改', 2);
INSERT INTO `sys_permission` VALUES (704, 'role', '角色权限', 'role:delete', '删除', 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色 ID，自增',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '2017-11-22 16:24:34', '2017-11-22 16:24:52', '1');
INSERT INTO `sys_role` VALUES (2, '作家', '2017-11-22 16:24:34', '2017-11-22 16:24:52', '1');
INSERT INTO `sys_role` VALUES (3, '程序员', '2017-11-22 16:28:47', '2017-11-22 16:28:47', '1');
INSERT INTO `sys_role` VALUES (4, '只看文章用户列表', '2019-05-29 10:22:13', '2019-06-10 17:21:34', '1');
INSERT INTO `sys_role` VALUES (7, '新角色aaa', '2019-06-05 21:12:47', '2019-06-08 10:52:27', '1');
INSERT INTO `sys_role` VALUES (8, '新角色bbb', '2019-06-09 21:10:57', '2019-06-09 21:10:57', '1');
INSERT INTO `sys_role` VALUES (9, '新角色ddd', '2019-06-09 21:12:58', '2019-06-10 15:39:02', '1');
INSERT INTO `sys_role` VALUES (10, '新角色ccc', '2019-06-10 15:35:33', '2019-06-10 15:35:33', '1');
INSERT INTO `sys_role` VALUES (11, '新角色eee', '2019-06-10 15:47:30', '2019-06-10 15:47:30', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `rp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色-权限id，主键',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) NULL DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效 1有效     2无效',
  PRIMARY KEY (`rp_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`permission_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1, 101, '2019-06-05 17:40:09', '2019-06-05 17:40:09', '1');
INSERT INTO `sys_role_permission` VALUES (2, 1, 102, '2019-06-05 17:40:50', '2019-06-05 17:40:50', '1');
INSERT INTO `sys_role_permission` VALUES (3, 1, 103, '2019-06-05 17:41:07', '2019-06-05 17:42:12', '1');
INSERT INTO `sys_role_permission` VALUES (4, 1, 601, '2019-06-05 17:41:34', '2019-06-05 17:42:23', '1');
INSERT INTO `sys_role_permission` VALUES (5, 1, 602, '2019-06-05 17:41:46', '2019-06-05 17:42:27', '1');
INSERT INTO `sys_role_permission` VALUES (6, 1, 603, '2019-06-05 17:42:45', '2019-06-05 17:42:45', '1');
INSERT INTO `sys_role_permission` VALUES (7, 1, 701, '2019-06-05 17:43:47', '2019-06-05 17:43:47', '1');
INSERT INTO `sys_role_permission` VALUES (8, 1, 702, '2019-06-05 17:44:00', '2019-06-05 17:44:00', '1');
INSERT INTO `sys_role_permission` VALUES (9, 1, 703, '2019-06-05 17:44:15', '2019-06-05 17:44:15', '1');
INSERT INTO `sys_role_permission` VALUES (10, 1, 704, '2019-06-05 17:47:17', '2019-06-05 17:47:17', '1');
INSERT INTO `sys_role_permission` VALUES (11, 2, 101, '2017-11-22 16:26:21', '2019-06-05 17:46:42', '1');
INSERT INTO `sys_role_permission` VALUES (12, 2, 102, '2017-11-22 16:26:21', '2019-06-05 17:46:36', '1');
INSERT INTO `sys_role_permission` VALUES (13, 2, 602, '2017-11-22 16:28:28', '2019-06-05 17:48:02', '1');
INSERT INTO `sys_role_permission` VALUES (14, 2, 601, '2017-11-22 16:28:28', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (15, 2, 603, '2017-11-22 16:28:28', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (16, 2, 703, '2017-11-22 16:28:28', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (17, 2, 701, '2017-11-22 16:28:28', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (18, 2, 702, '2017-11-22 16:28:28', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (19, 2, 704, '2017-11-22 16:28:31', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (20, 2, 103, '2017-11-22 16:28:31', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (21, 3, 601, '2017-11-22 16:28:47', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (22, 3, 701, '2017-11-22 16:28:47', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (23, 3, 702, '2017-11-22 16:35:01', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (24, 3, 704, '2017-11-22 16:35:01', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (25, 3, 102, '2017-11-22 16:35:01', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (26, 3, 101, '2017-11-22 16:35:01', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (27, 3, 603, '2017-11-22 16:35:01', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (28, 4, 101, '2019-05-29 10:22:29', '2019-06-05 17:48:12', '1');
INSERT INTO `sys_role_permission` VALUES (29, 7, 101, '2019-06-05 21:12:47', '2019-06-08 10:52:39', '1');
INSERT INTO `sys_role_permission` VALUES (30, 7, 102, '2019-06-05 21:12:47', '2019-06-05 21:21:43', '2');
INSERT INTO `sys_role_permission` VALUES (31, 7, 103, '2019-06-05 21:21:43', '2019-06-05 21:27:06', '2');
INSERT INTO `sys_role_permission` VALUES (32, 8, 102, '2019-06-09 21:10:57', '2019-06-09 21:10:57', '1');
INSERT INTO `sys_role_permission` VALUES (33, 8, 101, '2019-06-09 21:10:57', '2019-06-09 21:10:57', '1');
INSERT INTO `sys_role_permission` VALUES (34, 9, 102, '2019-06-09 21:12:58', '2019-06-10 15:39:19', '1');
INSERT INTO `sys_role_permission` VALUES (35, 9, 101, '2019-06-09 21:12:58', '2019-06-10 15:39:22', '1');
INSERT INTO `sys_role_permission` VALUES (36, 10, 101, '2019-06-10 15:35:34', '2019-06-10 15:35:34', '1');
INSERT INTO `sys_role_permission` VALUES (37, 10, 102, '2019-06-10 15:35:34', '2019-06-10 15:35:34', '1');
INSERT INTO `sys_role_permission` VALUES (38, 11, 101, '2019-06-10 15:47:30', '2019-06-10 15:47:30', '1');
INSERT INTO `sys_role_permission` VALUES (39, 4, 601, '2019-06-10 17:21:34', '2019-06-10 17:21:34', '1');
INSERT INTO `sys_role_permission` VALUES (40, 4, 701, '2019-06-10 17:32:47', '2019-06-10 17:32:47', '1');
INSERT INTO `sys_role_permission` VALUES (41, 11, 601, '2019-07-14 07:41:31', '2019-07-14 07:41:40', '2');

-- ----------------------------
-- Table structure for sys_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_token`;
CREATE TABLE `sys_token`  (
  `token_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'TOKEN ID，自增',
  `token_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'TOKEN 内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'token 创建时间',
  `effective_minutes` int(10) NULL DEFAULT NULL COMMENT 'token 有效时间（分钟）',
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户 ID，外键，用户表',
  PRIMARY KEY (`token_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `sys_token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_token
-- ----------------------------
INSERT INTO `sys_token` VALUES (1, 'd3bef89a-07e2-430c-8f07-50bb5906609a', '2019-07-14 08:06:02', 60, 10003);
INSERT INTO `sys_token` VALUES (2, 'bcff586c-fcaa-445d-843a-9e781c6df3d9', '2019-07-14 08:06:45', 60, 10004);
INSERT INTO `sys_token` VALUES (3, '2cf37321-596c-4d08-aec6-8fa8e0f047b1', '2019-06-02 11:06:55', 60, 10009);
INSERT INTO `sys_token` VALUES (4, 'f28be9c7-ae4b-4231-b855-0236c874f915', '2019-06-07 10:03:09', 60, 10011);
INSERT INTO `sys_token` VALUES (5, '4309114e-ee5c-4a35-9045-86edd9291cee', '2019-06-07 10:13:12', 60, 10005);
INSERT INTO `sys_token` VALUES (6, '0427b0c5-e47e-4069-a102-362f0ed0abcd', '2019-06-10 17:33:33', 60, 10014);
INSERT INTO `sys_token` VALUES (7, 'd4882da3-89ea-4631-9f1b-259333520e25', '2019-06-10 17:08:41', 60, 10007);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `role_id` int(11) NULL DEFAULT 0 COMMENT '角色ID',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `delete_status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10015 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运营后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10003, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级用户23', 1, '2017-10-30 11:52:38', '2019-05-29 21:54:31', '1');
INSERT INTO `sys_user` VALUES (10004, 'user', 'e10adc3949ba59abbe56e057f20f883e', '莎士比亚', 2, '2017-10-30 16:13:02', '2019-05-29 21:54:35', '1');
INSERT INTO `sys_user` VALUES (10005, 'aaa', 'e10adc3949ba59abbe56e057f20f883e', 'abba', 1, '2017-11-15 14:02:56', '2019-05-29 21:54:39', '1');
INSERT INTO `sys_user` VALUES (10007, 'test', 'e10adc3949ba59abbe56e057f20f883e', '就看看列表', 3, '2017-11-22 16:29:41', '2019-06-10 17:04:40', '1');
INSERT INTO `sys_user` VALUES (10009, 'bbb', 'e10adc3949ba59abbe56e057f20f883e', '测试昵称', 4, '2019-05-29 10:23:35', '2019-05-29 21:54:42', '1');
INSERT INTO `sys_user` VALUES (10010, 'user2', '123456', '是的方式发生', 2, '2019-06-05 20:00:46', '2019-06-08 10:03:41', '1');
INSERT INTO `sys_user` VALUES (10011, 'user3', 'e10adc3949ba59abbe56e057f20f883e', 'nic', 3, '2019-06-05 20:02:34', '2019-06-07 09:59:23', '1');
INSERT INTO `sys_user` VALUES (10012, 'user4', '123456', 'd', 4, '2019-06-07 19:59:48', '2019-06-07 19:59:48', '1');
INSERT INTO `sys_user` VALUES (10013, 'user5', 'e10adc3949ba59abbe56e057f20f883e', 'dd', 3, '2019-06-07 20:01:24', '2019-06-08 10:25:28', '1');
INSERT INTO `sys_user` VALUES (10014, 'user6', 'e10adc3949ba59abbe56e057f20f883e', 'user6', 4, '2019-06-08 10:30:08', '2019-06-08 10:53:28', '1');

SET FOREIGN_KEY_CHECKS = 1;
