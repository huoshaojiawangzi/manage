/*
Navicat MySQL Data Transfer

Source Server         : SDHJ
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : manage

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2019-07-01 09:49:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for c_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `c_dictionary`;
CREATE TABLE `c_dictionary` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `label` varchar(255) COLLATE utf8_bin NOT NULL,
  `sort` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8_bin NOT NULL,
  `value` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnxwruu3dul7as179b7iuekb8x` (`create_by`),
  KEY `FKnslk0wdp023h8w6q8wr935ntd` (`update_by`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_dictionary
-- ----------------------------
INSERT INTO `c_dictionary` VALUES ('402881ef6b9d67e1016b9d6c6e9d0000', '2019-06-28 09:30:58', '0', '不良类别', '2019-06-28 09:30:58', '潜在不良', '1', 'loan_type', '1', '1', '1');

-- ----------------------------
-- Table structure for c_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_menu`;
CREATE TABLE `c_menu` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `hidden` bit(1) NOT NULL,
  `icon_cls` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `path` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKax6mmgo6ke0jetyf3mgrp4fov` (`create_by`),
  KEY `FKsfm5gdkrugp4mda3jfebd611c` (`update_by`),
  KEY `FKa6tx0mnac35r31olr82okbbqq` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_menu
-- ----------------------------
INSERT INTO `c_menu` VALUES ('1', null, '0', null, null, '0', '\0', null, '顶级菜单', null, null, null, null);
INSERT INTO `c_menu` VALUES ('2', null, '0', null, '2019-06-28 07:31:51', '0', '\0', 'el-icon-setting', '系统管理', null, null, '1', '1');
INSERT INTO `c_menu` VALUES ('3', null, '0', null, '2019-06-28 07:43:44', '2', '\0', null, '菜单管理', null, null, '1', '2');
INSERT INTO `c_menu` VALUES ('4', null, '0', null, '2019-06-28 07:52:02', '0', '\0', 'el-icon-search', '菜单', '/menu/list', null, '1', '3');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d01b8eb0000', '2019-06-28 07:34:24', '0', null, '2019-06-28 07:34:24', '1', '\0', 'el-icon-edit', '菜单录入', '/menu/form', '1', '1', '3');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d026d060001', '2019-06-28 07:35:10', '0', null, '2019-06-28 07:48:07', '4', '\0', null, '角色管理', null, null, '1', '2');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d02ae220002', '2019-06-28 07:35:27', '0', null, '2019-06-28 07:52:12', '0', '\0', 'el-icon-search', '角色', '/role/list', null, '1', '402881ef6b9ccdf5016b9d026d060001');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d03006c0003', '2019-06-28 07:35:48', '0', null, '2019-06-28 07:49:50', '4', '\0', 'el-icon-edit', '角色录入', '/role/form', null, '1', '402881ef6b9ccdf5016b9d026d060001');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d048c470004', '2019-06-28 07:37:29', '0', null, '2019-06-28 07:42:49', '1', '\0', null, '用户管理', null, null, '1', '2');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d074a190006', '2019-06-28 07:40:29', '0', null, '2019-06-28 07:51:58', '0', '\0', 'el-icon-search', '用户', '/user/list', null, '1', '402881ef6b9ccdf5016b9d048c470004');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d07ce2e0007', '2019-06-28 07:41:03', '0', null, '2019-06-28 07:41:03', '1', '\0', 'el-icon-edit', '用户录入', '/user/form', '1', '1', '402881ef6b9ccdf5016b9d048c470004');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d095c230008', '2019-06-28 07:42:45', '0', null, '2019-06-28 07:42:45', '0', '\0', null, '机构管理', null, '1', '1', '2');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d09b2ba0009', '2019-06-28 07:43:07', '0', null, '2019-06-28 07:51:50', '0', '\0', 'el-icon-search', '机构', '/office/list', null, '1', '402881ef6b9ccdf5016b9d095c230008');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d0a0fd4000a', '2019-06-28 07:43:31', '0', null, '2019-06-28 07:43:31', '1', '\0', 'el-icon-edit', '机构录入', '/office/form', '1', '1', '402881ef6b9ccdf5016b9d095c230008');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d0b773a000b', '2019-06-28 07:45:03', '0', null, '2019-06-28 07:47:56', '3', '\0', null, '权限管理', '', null, '1', '2');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d0d94a3000c', '2019-06-28 07:47:21', '0', null, '2019-06-28 07:52:06', '0', '\0', 'el-icon-search', '权限', '/permission/list', null, '1', '402881ef6b9ccdf5016b9d0b773a000b');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d0def82000d', '2019-06-28 07:47:45', '0', null, '2019-06-28 07:47:45', '1', '\0', 'el-icon-edit', '权限录入', '/permission/form', '1', '1', '402881ef6b9ccdf5016b9d0b773a000b');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d0e76dc000e', '2019-06-28 07:48:19', '0', null, '2019-06-28 07:48:19', '0', '\0', null, '字典管理', null, '1', '1', '2');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d0edfef000f', '2019-06-28 07:48:46', '0', null, '2019-06-28 07:51:54', '0', '\0', 'el-icon-search', '字典', '/dictionary/list', null, '1', '402881ef6b9ccdf5016b9d0e76dc000e');
INSERT INTO `c_menu` VALUES ('402881ef6b9ccdf5016b9d0f43740010', '2019-06-28 07:49:12', '0', null, '2019-06-28 07:49:12', '1', '\0', 'el-icon-edit', '字典录入', '/dictionary/form', '1', '1', '402881ef6b9ccdf5016b9d0e76dc000e');

-- ----------------------------
-- Table structure for c_permission
-- ----------------------------
DROP TABLE IF EXISTS `c_permission`;
CREATE TABLE `c_permission` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `tag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrxo2jdvr2xx64l6wogsarvge0` (`create_by`),
  KEY `FKqj1j9uskc51cerxxavivvbegd` (`update_by`),
  KEY `FK9jotvicdlrrntjk0hbt1ki8w3` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_permission
-- ----------------------------
INSERT INTO `c_permission` VALUES ('1', null, '0', null, null, '0', '顶级权限', null, null, null, null, null);
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d16fe8d0013', '2019-06-28 07:57:38', '0', null, '2019-06-28 09:15:20', '0', '机构', '', null, null, '1', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d1732a90014', '2019-06-28 07:57:52', '0', null, '2019-06-28 09:26:51', '0', '字典', '', null, null, '1', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d176d810015', '2019-06-28 07:58:07', '0', null, '2019-06-28 09:34:16', '0', '用户', '', null, null, '1', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d178aa50016', '2019-06-28 07:58:14', '0', null, '2019-06-28 09:34:21', '0', '菜单', '', null, null, '1', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d17ebdf0017', '2019-06-28 07:58:39', '0', null, '2019-06-28 09:34:48', '0', '权限', '', null, null, '1', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d1821f50018', '2019-06-28 07:58:53', '0', null, '2019-06-28 09:34:41', '0', '角色', '', null, null, '1', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d18aa3c0019', '2019-06-28 07:59:28', '0', null, '2019-06-28 09:15:14', '0', '系统管理', '', null, null, '1', '1');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d1dbd6c001a', '2019-06-28 08:05:00', '0', null, '2019-06-28 08:17:46', '0', '机构查询', 'sys:office:list', '/home/office/find-roots', null, '1', '402881ef6b9ccdf5016b9d16fe8d0013');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d1e56b4001b', '2019-06-28 08:05:40', '0', null, '2019-06-28 09:34:33', '0', '机构录入', 'sys:office:save', '/home/office/save', null, '1', '402881ef6b9ccdf5016b9d16fe8d0013');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d1ef307001c', '2019-06-28 08:06:20', '0', null, '2019-06-28 09:27:48', '0', '字典查询', 'sys:dictionary:list', '/home/dictionary/find-page', null, '1', '402881ef6b9ccdf5016b9d1732a90014');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d1f4a36001d', '2019-06-28 08:06:42', '0', null, '2019-06-28 09:28:00', '0', '字典录入', 'sys:dictionary:save', '/home/dictionary/save', null, '1', '402881ef6b9ccdf5016b9d1732a90014');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d20173b001e', '2019-06-28 08:07:34', '0', null, '2019-06-28 08:21:24', '0', '用户查询', 'sys:user:list', '/home/user/find-page', null, '1', '402881ef6b9ccdf5016b9d176d810015');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d2066fc001f', '2019-06-28 08:07:55', '0', null, '2019-06-28 09:28:23', '0', '用户录入', 'sys:user:save', '/home/user/save', null, '1', '402881ef6b9ccdf5016b9d176d810015');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d20cea80020', '2019-06-28 08:08:21', '0', null, '2019-06-28 09:29:07', '0', '菜单查询', 'sys:menu:list', '/home/menu/find-roots', null, '1', '402881ef6b9ccdf5016b9d178aa50016');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d2136b50021', '2019-06-28 08:08:48', '0', null, '2019-06-28 09:28:39', '0', '菜单录入', 'sys:menu:save', '/home/menu/save', null, '1', '402881ef6b9ccdf5016b9d178aa50016');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d2198480022', '2019-06-28 08:09:13', '0', null, '2019-06-28 09:29:12', '0', '权限查询', 'sys:permission:list', '/home/permission/find-roots', null, '1', '402881ef6b9ccdf5016b9d17ebdf0017');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d22186b0023', '2019-06-28 08:09:46', '0', null, '2019-06-28 09:29:20', '0', '权限录入', 'sys:permission:save', '/home/permission/save', null, '1', '402881ef6b9ccdf5016b9d17ebdf0017');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d2272f90024', '2019-06-28 08:10:09', '0', null, '2019-06-28 09:29:34', '0', '角色查询', 'sys:role:list', '/home/role/find-page', null, '1', '402881ef6b9ccdf5016b9d1821f50018');
INSERT INTO `c_permission` VALUES ('402881ef6b9ccdf5016b9d22bb5f0025', '2019-06-28 08:10:28', '0', null, '2019-06-28 09:29:42', '0', '角色录入', 'sys:role:save', '/home/role/save', null, '1', '402881ef6b9ccdf5016b9d1821f50018');

-- ----------------------------
-- Table structure for c_role
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKchv7jvpvgx34ryp2wivobif5l` (`create_by`),
  KEY `FKrbc2tsjbibtrhiwn3lvwc20k7` (`update_by`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_role
-- ----------------------------
INSERT INTO `c_role` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', null, '0', null, '2019-06-28 09:51:44', '管理员', null, '402881ef6b9d29ae016b9d2bc8d40001');
INSERT INTO `c_role` VALUES ('402881ef6b9ccdf5016b9d06187a0005', null, '0', null, '2019-07-01 01:22:58', '超级管理员', null, '1');

-- ----------------------------
-- Table structure for c_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_role_menu`;
CREATE TABLE `c_role_menu` (
  `role_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `menu_id` varchar(255) COLLATE utf8_bin NOT NULL,
  KEY `FKg50otjbawgmrmw60mcnc5jml8` (`menu_id`),
  KEY `FK2shgd4xp621xu25rob36twc4p` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_role_menu
-- ----------------------------
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d074a190006');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '2');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '1');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d07ce2e0007');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d074a190006');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d048c470004');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d0f43740010');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d0edfef000f');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d0e76dc000e');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d0a0fd4000a');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d048c470004');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d0f43740010');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d0edfef000f');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d0e76dc000e');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d09b2ba0009');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d095c230008');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d0a0fd4000a');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d09b2ba0009');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d095c230008');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '2');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '1');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d07ce2e0007');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '3');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '4');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d01b8eb0000');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d0b773a000b');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d0d94a3000c');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d0def82000d');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d026d060001');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d02ae220002');
INSERT INTO `c_role_menu` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d03006c0003');

-- ----------------------------
-- Table structure for c_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `c_role_permission`;
CREATE TABLE `c_role_permission` (
  `role_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `permission_id` varchar(255) COLLATE utf8_bin NOT NULL,
  KEY `FKdoqras2bo5e71ao8qeur7q53r` (`permission_id`),
  KEY `FK1j2vdl3ofyw80eq02qjl2upii` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_role_permission
-- ----------------------------
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '1');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d2066fc001f');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d20173b001e');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d176d810015');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d1f4a36001d');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d1ef307001c');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d16fe8d0013');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d1dbd6c001a');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d1e56b4001b');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d14fe6e0012', '402881ef6b9ccdf5016b9d1732a90014');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d2136b50021');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d20cea80020');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d178aa50016');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d2066fc001f');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d20173b001e');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d176d810015');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d1f4a36001d');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d1ef307001c');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d1732a90014');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d1e56b4001b');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d1dbd6c001a');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d16fe8d0013');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d18aa3c0019');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '1');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d17ebdf0017');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d2198480022');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d22186b0023');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d1821f50018');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d2272f90024');
INSERT INTO `c_role_permission` VALUES ('402881ef6b9ccdf5016b9d06187a0005', '402881ef6b9ccdf5016b9d22bb5f0025');

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `role_index` int(11) NOT NULL,
  `user_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfnqjsjqsb195msmbdggbjpw7d` (`create_by`),
  KEY `FKjdkvf5qs9hl67ppcvfu36u5ee` (`update_by`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES ('1', null, '0', null, null, '超管', 'e10adc3949ba59abbe56e057f20f883e', '0', 'super', null, null);

-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_user_role`;
CREATE TABLE `c_user_role` (
  `user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(255) COLLATE utf8_bin NOT NULL,
  KEY `FKqkttlsjph70tjbvk868to1wyx` (`role_id`),
  KEY `FKj41wwslfkn2ck1oerjkhqe3d3` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of c_user_role
-- ----------------------------
INSERT INTO `c_user_role` VALUES ('1', '402881ef6b9ccdf5016b9d06187a0005');
INSERT INTO `c_user_role` VALUES ('402881ef6b9d29ae016b9d2bc8d40001', '402881ef6b9ccdf5016b9d14fe6e0012');

-- ----------------------------
-- Table structure for t_office
-- ----------------------------
DROP TABLE IF EXISTS `t_office`;
CREATE TABLE `t_office` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `manager` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKafpp9bo32ww5m4rj4i8p5y86` (`create_by`),
  KEY `FKaw6553nwe2j4ukeiet1to0e7x` (`update_by`),
  KEY `FKrtm8peahykg8p1g1f7moa5jpu` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_office
-- ----------------------------
INSERT INTO `t_office` VALUES ('1', null, '0', null, null, '0', null, null, '顶级机构', null, null, null, null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `common_user_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `office_id` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf43b7yr3vl6wg2fj725ld10bs` (`create_by`),
  KEY `FKm7f3t9ncmn2jjuxcn2kfxnxn2` (`update_by`),
  KEY `FKrerjl1q7yo78ljfldokkbj1yv` (`common_user_id`),
  KEY `FKlkji77pqbwfw26vkq84j7dmyf` (`office_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
