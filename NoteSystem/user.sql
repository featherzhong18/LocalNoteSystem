/*
 Navicat Premium Data Transfer

 Source Server         : zhong
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : localnote

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 15/04/2021 22:24:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` int(0) NULL DEFAULT NULL,
  `user_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhong', '123456', 0, '2021-04-10');
INSERT INTO `user` VALUES (2, 'zhang', '111111', 0, '2021-04-06');
INSERT INTO `user` VALUES (3, 'javafx', '888888', 1, '2021-04-01');
INSERT INTO `user` VALUES (5, 'zhong1', '333333', 0, '2021-04-06');
INSERT INTO `user` VALUES (6, 'java', '666666', 1, '2021-04-06');
INSERT INTO `user` VALUES (8, '张三', '222222', 0, '2021-04-10');
INSERT INTO `user` VALUES (9, 'Sebastian', '233333', 0, '2021-04-15');

SET FOREIGN_KEY_CHECKS = 1;
