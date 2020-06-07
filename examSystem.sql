/*
 Navicat Premium Data Transfer

 Source Server         : exam
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 47.96.152.133:3306
 Source Schema         : javaweb_exam

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 07/06/2020 19:20:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (23, 'yywSpace', '698d51a19d8a121ce581499d7b701668');
INSERT INTO `admin` VALUES (24, 'teacher', '698d51a19d8a121ce581499d7b701668');
INSERT INTO `admin` VALUES (25, '1', 'c4ca4238a0b923820dcc509a6f75849b');
INSERT INTO `admin` VALUES (26, '101010', '698d51a19d8a121ce581499d7b701668');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacherId` int(11) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `paperName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `startTime` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `autoStart` tinyint(4) NULL DEFAULT 0,
  `uploadExamPaper` tinyint(4) NULL DEFAULT 0,
  `running` tinyint(4) NULL DEFAULT 0,
  `finished` tinyint(4) NULL DEFAULT 0,
  `archived` tinyint(4) NULL DEFAULT 0,
  `cleaned` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_exam_teacher_idx`(`teacherId`) USING BTREE,
  CONSTRAINT `fk_exam_teacher` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (24, 67, 'GuoTest', 'GuoTeaTest.txt', '2020-06-01 13:19:01', 1, 1, 0, 1, 1, 0);
INSERT INTO `exam` VALUES (25, 67, '学生名单', 'student.xlsx', '2020-06-01 13:20:57', 0, 1, 0, 1, 1, 0);
INSERT INTO `exam` VALUES (27, 67, 'Test2', 'student.xlsx', '2020-06-01 14:02:39', 0, 1, 0, 1, 0, 0);
INSERT INTO `exam` VALUES (30, 67, 'test4', '第三章作业.docx', '2020-06-01 15:16:43', 0, 1, 0, 1, 0, 0);
INSERT INTO `exam` VALUES (38, 67, 'JmeterMuti-upload', 'student.xlsx', '2020-06-02 13:06:20', 0, 1, 0, 1, 1, 0);
INSERT INTO `exam` VALUES (43, 67, 'test3', '第一章作业.docx', '2020-06-05 10:55:15', 1, 1, 0, 1, 1, 1);
INSERT INTO `exam` VALUES (44, 67, 'test', '第一章作业.docx', '2020-06-05 10:58:09', 0, 1, 0, 1, 0, 0);
INSERT INTO `exam` VALUES (53, 67, 'w', 'student.xlsx', '2020-06-06 19:42:43', 0, 1, 0, 1, 0, 0);
INSERT INTO `exam` VALUES (54, 67, 'test1', '新建文本文档.txt', '2020-06-07 10:26:09', 0, 1, 0, 1, 0, 0);
INSERT INTO `exam` VALUES (55, 67, 'test1', '新建文本文档.txt', '2020-06-07 10:32:13', 0, 1, 1, 0, 0, 0);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `examId` int(11) NULL DEFAULT NULL,
  `time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_message_to_exam_idx`(`examId`) USING BTREE,
  CONSTRAINT `fk_message_to_exam` FOREIGN KEY (`examId`) REFERENCES `exam` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (12, 27, '2020-06-01 15:29:27', '考试时间90分钟');
INSERT INTO `message` VALUES (13, 44, '2020-06-05 14:55:52', '还有十分钟结束了哦');
INSERT INTO `message` VALUES (15, 55, '2020-06-07 10:35:41', '还有十分钟就交卷咯');
INSERT INTO `message` VALUES (16, 55, '2020-06-07 10:37:05', '还有十分钟就交卷咯');
INSERT INTO `message` VALUES (17, 55, '2020-06-07 10:58:45', '还有十分钟就交卷咯');
INSERT INTO `message` VALUES (18, 55, '2020-06-07 10:59:01', '还有十分钟就交卷咯');
INSERT INTO `message` VALUES (19, 55, '2020-06-07 10:59:23', '还有十分钟就交卷咯');

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dutyCycle` int(11) NULL DEFAULT 1,
  `pageCount` int(11) NULL DEFAULT 10,
  `timeThreshold` int(11) NULL DEFAULT 30,
  `uploadBytesUpper` int(11) NULL DEFAULT 5120,
  `uploadBytesLower` int(11) NULL DEFAULT 0,
  `allowClearAndDelete` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES (1, 1, 10, 30, 51200100, 1, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sClass` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('0', '0', '0', '');
INSERT INTO `student` VALUES ('1', '1', '1', '172.16.188.110');
INSERT INTO `student` VALUES ('10', '姓名-10', '17-1', '');
INSERT INTO `student` VALUES ('100', '姓名-100', '17-1', '');
INSERT INTO `student` VALUES ('101', '姓名-101', '17-1', '');
INSERT INTO `student` VALUES ('102', '姓名-102', '17-1', '');
INSERT INTO `student` VALUES ('1029', '郭', '17-3', '');
INSERT INTO `student` VALUES ('103', '姓名-103', '17-1', '');
INSERT INTO `student` VALUES ('104', '姓名-104', '17-1', '');
INSERT INTO `student` VALUES ('105', '姓名-105', '17-1', '');
INSERT INTO `student` VALUES ('106', '姓名-106', '17-1', '');
INSERT INTO `student` VALUES ('107', '姓名-107', '17-1', '');
INSERT INTO `student` VALUES ('108', '姓名-108', '17-1', '');
INSERT INTO `student` VALUES ('109', '姓名-109', '17-1', '');
INSERT INTO `student` VALUES ('11', '11', '11', '');
INSERT INTO `student` VALUES ('110', '姓名-110', '17-1', '');
INSERT INTO `student` VALUES ('111', '姓名-111', '17-1', '');
INSERT INTO `student` VALUES ('1119', '李', '17-1', '');
INSERT INTO `student` VALUES ('112', '姓名-112', '17-1', '');
INSERT INTO `student` VALUES ('1129', '郑', '17-3', '');
INSERT INTO `student` VALUES ('113', '姓名-113', '17-1', '');
INSERT INTO `student` VALUES ('114', '姓名-114', '17-1', '');
INSERT INTO `student` VALUES ('115', '姓名-115', '17-1', '');
INSERT INTO `student` VALUES ('116', '姓名-116', '17-1', '');
INSERT INTO `student` VALUES ('117', '姓名-117', '17-1', '');
INSERT INTO `student` VALUES ('118', '姓名-118', '17-1', '');
INSERT INTO `student` VALUES ('119', '姓名-119', '17-1', '');
INSERT INTO `student` VALUES ('12', '姓名-12', '17-1', '');
INSERT INTO `student` VALUES ('120', '姓名-120', '17-1', '');
INSERT INTO `student` VALUES ('121', '姓名-121', '17-1', '');
INSERT INTO `student` VALUES ('122', '姓名-122', '17-1', '');
INSERT INTO `student` VALUES ('123', '姓名-123', '17-1', '');
INSERT INTO `student` VALUES ('124', '姓名-124', '17-1', '');
INSERT INTO `student` VALUES ('125', '姓名-125', '17-1', '');
INSERT INTO `student` VALUES ('126', '姓名-126', '17-1', '');
INSERT INTO `student` VALUES ('127', '姓名-127', '17-1', '');
INSERT INTO `student` VALUES ('128', '姓名-128', '17-1', '');
INSERT INTO `student` VALUES ('129', '姓名-129', '17-1', '');
INSERT INTO `student` VALUES ('13', '姓名-13', '17-1', '');
INSERT INTO `student` VALUES ('130', '姓名-130', '17-1', '');
INSERT INTO `student` VALUES ('131', '姓名-131', '17-1', '');
INSERT INTO `student` VALUES ('132', '姓名-132', '17-1', '');
INSERT INTO `student` VALUES ('1329', '郑', '2', '');
INSERT INTO `student` VALUES ('133', '姓名-133', '17-1', '');
INSERT INTO `student` VALUES ('134', '姓名-134', '17-1', '');
INSERT INTO `student` VALUES ('135', '姓名-135', '17-1', '');
INSERT INTO `student` VALUES ('136', '姓名-136', '17-1', '');
INSERT INTO `student` VALUES ('137', '姓名-137', '17-1', '');
INSERT INTO `student` VALUES ('138', '姓名-138', '17-1', '');
INSERT INTO `student` VALUES ('139', '姓名-139', '17-1', '');
INSERT INTO `student` VALUES ('14', '姓名-14', '17-1', '');
INSERT INTO `student` VALUES ('140', '姓名-140', '17-1', '');
INSERT INTO `student` VALUES ('141', '姓名-141', '17-1', '');
INSERT INTO `student` VALUES ('142', '姓名-142', '17-1', '');
INSERT INTO `student` VALUES ('143', '姓名-143', '17-1', '');
INSERT INTO `student` VALUES ('144', '姓名-144', '17-1', '');
INSERT INTO `student` VALUES ('145', '姓名-145', '17-1', '');
INSERT INTO `student` VALUES ('146', '姓名-146', '17-1', '');
INSERT INTO `student` VALUES ('147', '姓名-147', '17-1', '');
INSERT INTO `student` VALUES ('148', '姓名-148', '17-1', '');
INSERT INTO `student` VALUES ('149', '姓名-149', '17-1', '');
INSERT INTO `student` VALUES ('15', '姓名-15', '17-1', '');
INSERT INTO `student` VALUES ('150', '姓名-150', '17-1', '');
INSERT INTO `student` VALUES ('151', '姓名-151', '17-1', '');
INSERT INTO `student` VALUES ('152', '姓名-152', '17-1', '');
INSERT INTO `student` VALUES ('153', '姓名-153', '17-1', '');
INSERT INTO `student` VALUES ('154', '姓名-154', '17-1', '');
INSERT INTO `student` VALUES ('155', '姓名-155', '17-1', '');
INSERT INTO `student` VALUES ('156', '姓名-156', '17-1', '');
INSERT INTO `student` VALUES ('157', '姓名-157', '17-1', '');
INSERT INTO `student` VALUES ('158', '姓名-158', '17-1', '');
INSERT INTO `student` VALUES ('159', '姓名-159', '17-1', '');
INSERT INTO `student` VALUES ('16', '姓名-16', '17-1', '');
INSERT INTO `student` VALUES ('160', '姓名-160', '17-1', '');
INSERT INTO `student` VALUES ('161', '姓名-161', '17-1', '');
INSERT INTO `student` VALUES ('162', '姓名-162', '17-1', '');
INSERT INTO `student` VALUES ('163', '姓名-163', '17-1', '');
INSERT INTO `student` VALUES ('164', '姓名-164', '17-1', '');
INSERT INTO `student` VALUES ('165', '姓名-165', '17-1', '');
INSERT INTO `student` VALUES ('166', '姓名-166', '17-1', '');
INSERT INTO `student` VALUES ('167', '姓名-167', '17-1', '');
INSERT INTO `student` VALUES ('168', '姓名-168', '17-1', '');
INSERT INTO `student` VALUES ('169', '姓名-169', '17-1', '');
INSERT INTO `student` VALUES ('17', '姓名-17', '17-1', '');
INSERT INTO `student` VALUES ('170', '姓名-170', '17-1', '');
INSERT INTO `student` VALUES ('171', '姓名-171', '17-1', '');
INSERT INTO `student` VALUES ('1710121129', '王烁', '17-3', '');
INSERT INTO `student` VALUES ('1710121329', '郑贺岩', '17-3', '');
INSERT INTO `student` VALUES ('172', '姓名-172', '17-1', '');
INSERT INTO `student` VALUES ('173', '姓名-173', '17-1', '');
INSERT INTO `student` VALUES ('174', '姓名-174', '17-1', '');
INSERT INTO `student` VALUES ('175', '姓名-175', '17-1', '');
INSERT INTO `student` VALUES ('176', '姓名-176', '17-1', '');
INSERT INTO `student` VALUES ('177', '姓名-177', '17-1', '');
INSERT INTO `student` VALUES ('178', '姓名-178', '17-1', '');
INSERT INTO `student` VALUES ('179', '姓名-179', '17-1', '');
INSERT INTO `student` VALUES ('18', '姓名-18', '17-1', '');
INSERT INTO `student` VALUES ('180', '姓名-180', '17-1', '');
INSERT INTO `student` VALUES ('181', '姓名-181', '17-1', '');
INSERT INTO `student` VALUES ('182', '姓名-182', '17-1', '');
INSERT INTO `student` VALUES ('183', '姓名-183', '17-1', '');
INSERT INTO `student` VALUES ('184', '姓名-184', '17-1', '');
INSERT INTO `student` VALUES ('185', '姓名-185', '17-1', '');
INSERT INTO `student` VALUES ('186', '姓名-186', '17-1', '');
INSERT INTO `student` VALUES ('187', '姓名-187', '17-1', '');
INSERT INTO `student` VALUES ('188', '姓名-188', '17-1', '');
INSERT INTO `student` VALUES ('189', '姓名-189', '17-1', '');
INSERT INTO `student` VALUES ('19', '姓名-19', '17-1', '');
INSERT INTO `student` VALUES ('190', '姓名-190', '17-1', '');
INSERT INTO `student` VALUES ('191', '姓名-191', '17-1', '');
INSERT INTO `student` VALUES ('192', '姓名-192', '17-1', '');
INSERT INTO `student` VALUES ('193', '姓名-193', '17-1', '');
INSERT INTO `student` VALUES ('194', '姓名-194', '17-1', '');
INSERT INTO `student` VALUES ('195', '姓名-195', '17-1', '');
INSERT INTO `student` VALUES ('196', '姓名-196', '17-1', '');
INSERT INTO `student` VALUES ('197', '姓名-197', '17-1', '');
INSERT INTO `student` VALUES ('198', '姓名-198', '17-1', '');
INSERT INTO `student` VALUES ('199', '姓名-199', '17-1', '');
INSERT INTO `student` VALUES ('2', '2', '2', '');
INSERT INTO `student` VALUES ('20', '姓名-20', '17-1', '');
INSERT INTO `student` VALUES ('200', '姓名-200', '17-1', '');
INSERT INTO `student` VALUES ('21', '姓名-21', '17-1', '');
INSERT INTO `student` VALUES ('22', '姓名-22', '17-1', '');
INSERT INTO `student` VALUES ('23', '姓名-23', '17-1', '');
INSERT INTO `student` VALUES ('24', '姓名-24', '17-1', '');
INSERT INTO `student` VALUES ('25', '姓名-25', '17-1', '');
INSERT INTO `student` VALUES ('26', '姓名-26', '17-1', '');
INSERT INTO `student` VALUES ('27', '姓名-27', '17-1', '');
INSERT INTO `student` VALUES ('28', '姓名-28', '17-1', '');
INSERT INTO `student` VALUES ('29', '姓名-29', '17-1', '');
INSERT INTO `student` VALUES ('3', '姓名-3', '17-1', '');
INSERT INTO `student` VALUES ('30', '姓名-30', '17-1', '');
INSERT INTO `student` VALUES ('31', '姓名-31', '17-1', '');
INSERT INTO `student` VALUES ('32', '姓名-32', '17-1', '');
INSERT INTO `student` VALUES ('33', '姓名-33', '17-1', '');
INSERT INTO `student` VALUES ('34', '姓名-34', '17-1', '');
INSERT INTO `student` VALUES ('35', '姓名-35', '17-1', '');
INSERT INTO `student` VALUES ('36', '姓名-36', '17-1', '');
INSERT INTO `student` VALUES ('37', '姓名-37', '17-1', '');
INSERT INTO `student` VALUES ('38', '姓名-38', '17-1', '');
INSERT INTO `student` VALUES ('39', '姓名-39', '17-1', '');
INSERT INTO `student` VALUES ('4', '姓名-4', '17-1', '');
INSERT INTO `student` VALUES ('40', '姓名-40', '17-1', '');
INSERT INTO `student` VALUES ('41', '姓名-41', '17-1', '');
INSERT INTO `student` VALUES ('42', '姓名-42', '17-1', '');
INSERT INTO `student` VALUES ('43', '姓名-43', '17-1', '');
INSERT INTO `student` VALUES ('44', '姓名-44', '17-1', '');
INSERT INTO `student` VALUES ('45', '姓名-45', '17-1', '');
INSERT INTO `student` VALUES ('46', '姓名-46', '17-1', '');
INSERT INTO `student` VALUES ('47', '姓名-47', '17-1', '');
INSERT INTO `student` VALUES ('48', '姓名-48', '17-1', '');
INSERT INTO `student` VALUES ('49', '姓名-49', '17-1', '');
INSERT INTO `student` VALUES ('5', '姓名-5', '17-1', '');
INSERT INTO `student` VALUES ('50', '姓名-50', '17-1', '');
INSERT INTO `student` VALUES ('51', '姓名-51', '17-1', '');
INSERT INTO `student` VALUES ('52', '姓名-52', '17-1', '');
INSERT INTO `student` VALUES ('53', '姓名-53', '17-1', '');
INSERT INTO `student` VALUES ('54', '姓名-54', '17-1', '');
INSERT INTO `student` VALUES ('55', '姓名-55', '17-1', '');
INSERT INTO `student` VALUES ('56', '姓名-56', '17-1', '');
INSERT INTO `student` VALUES ('57', '姓名-57', '17-1', '');
INSERT INTO `student` VALUES ('58', '姓名-58', '17-1', '');
INSERT INTO `student` VALUES ('59', '姓名-59', '17-1', '');
INSERT INTO `student` VALUES ('6', '姓名-6', '17-1', '');
INSERT INTO `student` VALUES ('60', '姓名-60', '17-1', '');
INSERT INTO `student` VALUES ('61', '姓名-61', '17-1', '');
INSERT INTO `student` VALUES ('62', '姓名-62', '17-1', '');
INSERT INTO `student` VALUES ('63', '姓名-63', '17-1', '');
INSERT INTO `student` VALUES ('64', '姓名-64', '17-1', '');
INSERT INTO `student` VALUES ('65', '姓名-65', '17-1', '');
INSERT INTO `student` VALUES ('66', '姓名-66', '17-1', '');
INSERT INTO `student` VALUES ('67', '姓名-67', '17-1', '');
INSERT INTO `student` VALUES ('68', '姓名-68', '17-1', '');
INSERT INTO `student` VALUES ('69', '姓名-69', '17-1', '');
INSERT INTO `student` VALUES ('7', '姓名-7', '17-1', '');
INSERT INTO `student` VALUES ('70', '姓名-70', '17-1', '');
INSERT INTO `student` VALUES ('71', '姓名-71', '17-1', '');
INSERT INTO `student` VALUES ('72', '姓名-72', '17-1', '');
INSERT INTO `student` VALUES ('73', '姓名-73', '17-1', '');
INSERT INTO `student` VALUES ('74', '姓名-74', '17-1', '');
INSERT INTO `student` VALUES ('75', '姓名-75', '17-1', '');
INSERT INTO `student` VALUES ('76', '姓名-76', '17-1', '');
INSERT INTO `student` VALUES ('77', '姓名-77', '17-1', '');
INSERT INTO `student` VALUES ('78', '姓名-78', '17-1', '');
INSERT INTO `student` VALUES ('79', '姓名-79', '17-1', '');
INSERT INTO `student` VALUES ('8', '姓名-8', '17-1', '');
INSERT INTO `student` VALUES ('80', '姓名-80', '17-1', '');
INSERT INTO `student` VALUES ('81', '姓名-81', '17-1', '');
INSERT INTO `student` VALUES ('82', '姓名-82', '17-1', '');
INSERT INTO `student` VALUES ('83', '姓名-83', '17-1', '');
INSERT INTO `student` VALUES ('84', '姓名-84', '17-1', '');
INSERT INTO `student` VALUES ('85', '姓名-85', '17-1', '');
INSERT INTO `student` VALUES ('86', '姓名-86', '17-1', '');
INSERT INTO `student` VALUES ('87', '姓名-87', '17-1', '');
INSERT INTO `student` VALUES ('88', '姓名-88', '17-1', '');
INSERT INTO `student` VALUES ('89', '姓名-89', '17-1', '');
INSERT INTO `student` VALUES ('9', '姓名-9', '17-1', '');
INSERT INTO `student` VALUES ('90', '姓名-90', '17-1', '');
INSERT INTO `student` VALUES ('91', '姓名-91', '17-1', '');
INSERT INTO `student` VALUES ('92', '姓名-92', '17-1', '');
INSERT INTO `student` VALUES ('93', '姓名-93', '17-1', '');
INSERT INTO `student` VALUES ('94', '姓名-94', '17-1', '');
INSERT INTO `student` VALUES ('95', '姓名-95', '17-1', '');
INSERT INTO `student` VALUES ('96', '姓名-96', '17-1', '');
INSERT INTO `student` VALUES ('97', '姓名-97', '17-1', '');
INSERT INTO `student` VALUES ('98', '姓名-98', '17-1', '');
INSERT INTO `student` VALUES ('99', '姓名-99', '17-1', '');

-- ----------------------------
-- Table structure for studentAnswer
-- ----------------------------
DROP TABLE IF EXISTS `studentAnswer`;
CREATE TABLE `studentAnswer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `examId` int(11) NOT NULL,
  `answerFileName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answerFileSize` int(11) NULL DEFAULT NULL,
  `answerFileTime` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_answer_to_exam`(`examId`) USING BTREE,
  INDEX `fk_answer_to_student_idx`(`studentId`) USING BTREE,
  CONSTRAINT `fk_answer_to_exam` FOREIGN KEY (`examId`) REFERENCES `exam` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_answer_to_student` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 579 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of studentAnswer
-- ----------------------------
INSERT INTO `studentAnswer` VALUES (29, '1', 27, '第三章作业.docx', 4312542, '2020-06-01 15:24:11');
INSERT INTO `studentAnswer` VALUES (30, '2', 27, '第四章作业.docx', 3562106, '2020-06-01 15:25:21');
INSERT INTO `studentAnswer` VALUES (40, '1', 38, 'student.xlsx', 11462, '2020-06-06 18:16:59');
INSERT INTO `studentAnswer` VALUES (41, '2', 38, 'student.xlsx', 11462, '2020-06-06 18:15:27');
INSERT INTO `studentAnswer` VALUES (42, '3', 38, 'student.xlsx', 11462, '2020-06-06 18:15:27');
INSERT INTO `studentAnswer` VALUES (43, '4', 38, 'student.xlsx', 11462, '2020-06-06 18:15:27');
INSERT INTO `studentAnswer` VALUES (44, '5', 38, 'student.xlsx', 11462, '2020-06-06 18:15:28');
INSERT INTO `studentAnswer` VALUES (45, '6', 38, 'student.xlsx', 11462, '2020-06-06 18:15:27');
INSERT INTO `studentAnswer` VALUES (46, '8', 38, 'student.xlsx', 11462, '2020-06-02 13:56:27');
INSERT INTO `studentAnswer` VALUES (47, '11', 38, 'student.xlsx', 11462, '2020-06-06 18:15:29');
INSERT INTO `studentAnswer` VALUES (48, '18', 38, 'student.xlsx', 11462, '2020-06-06 18:15:38');
INSERT INTO `studentAnswer` VALUES (49, '7', 38, 'student.xlsx', 11462, '2020-06-06 18:15:28');
INSERT INTO `studentAnswer` VALUES (50, '14', 38, 'student.xlsx', 11462, '2020-06-06 18:15:33');
INSERT INTO `studentAnswer` VALUES (51, '10', 38, 'student.xlsx', 11462, '2020-06-06 18:15:27');
INSERT INTO `studentAnswer` VALUES (52, '9', 38, 'student.xlsx', 11462, '2020-06-06 18:15:28');
INSERT INTO `studentAnswer` VALUES (53, '20', 38, 'student.xlsx', 11462, '2020-06-06 18:15:31');
INSERT INTO `studentAnswer` VALUES (54, '12', 38, 'student.xlsx', 11462, '2020-06-06 18:15:32');
INSERT INTO `studentAnswer` VALUES (55, '16', 38, 'student.xlsx', 11462, '2020-06-06 18:15:39');
INSERT INTO `studentAnswer` VALUES (56, '15', 38, 'student.xlsx', 11462, '2020-06-06 18:15:41');
INSERT INTO `studentAnswer` VALUES (57, '13', 38, 'student.xlsx', 11462, '2020-06-06 18:15:34');
INSERT INTO `studentAnswer` VALUES (58, '17', 38, 'student.xlsx', 11462, '2020-06-06 18:15:32');
INSERT INTO `studentAnswer` VALUES (59, '19', 38, 'student.xlsx', 11462, '2020-06-06 18:15:31');
INSERT INTO `studentAnswer` VALUES (60, '104', 38, 'student.xlsx', 11462, '2020-06-02 13:08:57');
INSERT INTO `studentAnswer` VALUES (61, '41', 38, 'student.xlsx', 11462, '2020-06-06 18:15:34');
INSERT INTO `studentAnswer` VALUES (62, '111', 38, 'student.xlsx', 11462, '2020-06-02 13:08:57');
INSERT INTO `studentAnswer` VALUES (63, '39', 38, 'student.xlsx', 11462, '2020-06-06 18:15:32');
INSERT INTO `studentAnswer` VALUES (64, '183', 38, 'student.xlsx', 11462, '2020-06-02 13:08:58');
INSERT INTO `studentAnswer` VALUES (65, '127', 38, 'student.xlsx', 11462, '2020-06-02 13:08:58');
INSERT INTO `studentAnswer` VALUES (66, '101', 38, 'student.xlsx', 11462, '2020-06-02 13:08:58');
INSERT INTO `studentAnswer` VALUES (67, '155', 38, 'student.xlsx', 11462, '2020-06-02 13:09:00');
INSERT INTO `studentAnswer` VALUES (68, '56', 38, 'student.xlsx', 11462, '2020-06-02 13:09:00');
INSERT INTO `studentAnswer` VALUES (69, '38', 38, 'student.xlsx', 11462, '2020-06-06 18:15:37');
INSERT INTO `studentAnswer` VALUES (70, '26', 38, 'student.xlsx', 11462, '2020-06-06 18:15:35');
INSERT INTO `studentAnswer` VALUES (71, '166', 38, 'student.xlsx', 11462, '2020-06-02 13:09:01');
INSERT INTO `studentAnswer` VALUES (72, '165', 38, 'student.xlsx', 11462, '2020-06-02 13:09:02');
INSERT INTO `studentAnswer` VALUES (73, '107', 38, 'student.xlsx', 11462, '2020-06-02 13:09:02');
INSERT INTO `studentAnswer` VALUES (74, '161', 38, 'student.xlsx', 11462, '2020-06-02 13:09:03');
INSERT INTO `studentAnswer` VALUES (75, '106', 38, 'student.xlsx', 11462, '2020-06-02 13:09:03');
INSERT INTO `studentAnswer` VALUES (76, '178', 38, 'student.xlsx', 11462, '2020-06-02 13:09:03');
INSERT INTO `studentAnswer` VALUES (77, '37', 38, 'student.xlsx', 11462, '2020-06-06 18:15:35');
INSERT INTO `studentAnswer` VALUES (78, '21', 38, 'student.xlsx', 11462, '2020-06-06 18:15:36');
INSERT INTO `studentAnswer` VALUES (79, '61', 38, 'student.xlsx', 11462, '2020-06-02 13:09:04');
INSERT INTO `studentAnswer` VALUES (80, '188', 38, 'student.xlsx', 11462, '2020-06-02 13:09:05');
INSERT INTO `studentAnswer` VALUES (81, '59', 38, 'student.xlsx', 11462, '2020-06-02 13:09:05');
INSERT INTO `studentAnswer` VALUES (82, '142', 38, 'student.xlsx', 11462, '2020-06-02 13:09:06');
INSERT INTO `studentAnswer` VALUES (83, '109', 38, 'student.xlsx', 11462, '2020-06-02 13:09:06');
INSERT INTO `studentAnswer` VALUES (84, '96', 38, 'student.xlsx', 11462, '2020-06-02 13:09:06');
INSERT INTO `studentAnswer` VALUES (85, '180', 38, 'student.xlsx', 11462, '2020-06-02 13:09:06');
INSERT INTO `studentAnswer` VALUES (86, '163', 38, 'student.xlsx', 11462, '2020-06-02 13:09:06');
INSERT INTO `studentAnswer` VALUES (87, '66', 38, 'student.xlsx', 11462, '2020-06-02 13:09:06');
INSERT INTO `studentAnswer` VALUES (88, '24', 38, 'student.xlsx', 11462, '2020-06-06 18:15:37');
INSERT INTO `studentAnswer` VALUES (89, '35', 38, 'student.xlsx', 11462, '2020-06-06 18:15:33');
INSERT INTO `studentAnswer` VALUES (90, '84', 38, 'student.xlsx', 11462, '2020-06-02 13:09:07');
INSERT INTO `studentAnswer` VALUES (91, '154', 38, 'student.xlsx', 11462, '2020-06-02 13:09:07');
INSERT INTO `studentAnswer` VALUES (92, '100', 38, 'student.xlsx', 11462, '2020-06-02 13:09:07');
INSERT INTO `studentAnswer` VALUES (93, '22', 38, 'student.xlsx', 11462, '2020-06-06 18:15:36');
INSERT INTO `studentAnswer` VALUES (94, '192', 38, 'student.xlsx', 11462, '2020-06-02 13:09:08');
INSERT INTO `studentAnswer` VALUES (95, '123', 38, 'student.xlsx', 11462, '2020-06-02 13:09:08');
INSERT INTO `studentAnswer` VALUES (96, '119', 38, 'student.xlsx', 11462, '2020-06-02 13:09:09');
INSERT INTO `studentAnswer` VALUES (97, '25', 38, 'student.xlsx', 11462, '2020-06-06 18:15:35');
INSERT INTO `studentAnswer` VALUES (98, '44', 38, 'student.xlsx', 11462, '2020-06-06 18:15:32');
INSERT INTO `studentAnswer` VALUES (99, '81', 38, 'student.xlsx', 11462, '2020-06-02 13:09:09');
INSERT INTO `studentAnswer` VALUES (100, '97', 38, 'student.xlsx', 11462, '2020-06-02 13:09:10');
INSERT INTO `studentAnswer` VALUES (101, '148', 38, 'student.xlsx', 11462, '2020-06-02 13:09:10');
INSERT INTO `studentAnswer` VALUES (102, '23', 38, 'student.xlsx', 11462, '2020-06-06 18:15:35');
INSERT INTO `studentAnswer` VALUES (103, '53', 38, 'student.xlsx', 11462, '2020-06-02 13:09:11');
INSERT INTO `studentAnswer` VALUES (104, '134', 38, 'student.xlsx', 11462, '2020-06-02 13:09:11');
INSERT INTO `studentAnswer` VALUES (105, '179', 38, 'student.xlsx', 11462, '2020-06-02 13:09:11');
INSERT INTO `studentAnswer` VALUES (106, '168', 38, 'student.xlsx', 11462, '2020-06-02 13:09:12');
INSERT INTO `studentAnswer` VALUES (107, '132', 38, 'student.xlsx', 11462, '2020-06-02 13:09:12');
INSERT INTO `studentAnswer` VALUES (108, '28', 38, 'student.xlsx', 11462, '2020-06-06 18:15:35');
INSERT INTO `studentAnswer` VALUES (109, '108', 38, 'student.xlsx', 11462, '2020-06-02 13:09:12');
INSERT INTO `studentAnswer` VALUES (110, '58', 38, 'student.xlsx', 11462, '2020-06-02 13:09:11');
INSERT INTO `studentAnswer` VALUES (111, '99', 38, 'student.xlsx', 11462, '2020-06-02 13:09:12');
INSERT INTO `studentAnswer` VALUES (112, '174', 38, 'student.xlsx', 11462, '2020-06-02 13:09:12');
INSERT INTO `studentAnswer` VALUES (113, '133', 38, 'student.xlsx', 11462, '2020-06-02 13:09:12');
INSERT INTO `studentAnswer` VALUES (114, '43', 38, 'student.xlsx', 11462, '2020-06-06 18:15:36');
INSERT INTO `studentAnswer` VALUES (115, '77', 38, 'student.xlsx', 11462, '2020-06-02 13:09:13');
INSERT INTO `studentAnswer` VALUES (116, '195', 38, 'student.xlsx', 11462, '2020-06-02 13:09:13');
INSERT INTO `studentAnswer` VALUES (117, '49', 38, 'student.xlsx', 11462, '2020-06-06 18:15:32');
INSERT INTO `studentAnswer` VALUES (118, '92', 38, 'student.xlsx', 11462, '2020-06-02 13:09:14');
INSERT INTO `studentAnswer` VALUES (119, '182', 38, 'student.xlsx', 11462, '2020-06-02 13:09:15');
INSERT INTO `studentAnswer` VALUES (120, '137', 38, 'student.xlsx', 11462, '2020-06-02 13:09:15');
INSERT INTO `studentAnswer` VALUES (121, '79', 38, 'student.xlsx', 11462, '2020-06-02 13:09:15');
INSERT INTO `studentAnswer` VALUES (122, '167', 38, 'student.xlsx', 11462, '2020-06-02 13:09:15');
INSERT INTO `studentAnswer` VALUES (123, '126', 38, 'student.xlsx', 11462, '2020-06-02 13:09:15');
INSERT INTO `studentAnswer` VALUES (124, '135', 38, 'student.xlsx', 11462, '2020-06-02 13:09:16');
INSERT INTO `studentAnswer` VALUES (125, '86', 38, 'student.xlsx', 11462, '2020-06-02 13:09:16');
INSERT INTO `studentAnswer` VALUES (126, '162', 38, 'student.xlsx', 11462, '2020-06-02 13:09:16');
INSERT INTO `studentAnswer` VALUES (127, '193', 38, 'student.xlsx', 11462, '2020-06-02 13:09:16');
INSERT INTO `studentAnswer` VALUES (128, '90', 38, 'student.xlsx', 11462, '2020-06-02 13:09:17');
INSERT INTO `studentAnswer` VALUES (129, '145', 38, 'student.xlsx', 11462, '2020-06-02 13:09:17');
INSERT INTO `studentAnswer` VALUES (130, '75', 38, 'student.xlsx', 11462, '2020-06-02 13:09:17');
INSERT INTO `studentAnswer` VALUES (131, '63', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (132, '136', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (133, '71', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (134, '138', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (135, '198', 38, 'student.xlsx', 11462, '2020-06-06 18:54:14');
INSERT INTO `studentAnswer` VALUES (136, '73', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (137, '55', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (138, '124', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (139, '72', 38, 'student.xlsx', 11462, '2020-06-02 13:09:18');
INSERT INTO `studentAnswer` VALUES (140, '118', 38, 'student.xlsx', 11462, '2020-06-02 13:09:19');
INSERT INTO `studentAnswer` VALUES (141, '176', 38, 'student.xlsx', 11462, '2020-06-02 13:09:19');
INSERT INTO `studentAnswer` VALUES (142, '185', 38, 'student.xlsx', 11462, '2020-06-02 13:09:19');
INSERT INTO `studentAnswer` VALUES (143, '82', 38, 'student.xlsx', 11462, '2020-06-02 13:09:20');
INSERT INTO `studentAnswer` VALUES (144, '197', 38, 'student.xlsx', 11462, '2020-06-02 13:09:20');
INSERT INTO `studentAnswer` VALUES (145, '78', 38, 'student.xlsx', 11462, '2020-06-02 13:09:20');
INSERT INTO `studentAnswer` VALUES (146, '175', 38, 'student.xlsx', 11462, '2020-06-02 13:09:21');
INSERT INTO `studentAnswer` VALUES (147, '51', 38, 'student.xlsx', 11462, '2020-06-02 13:09:21');
INSERT INTO `studentAnswer` VALUES (148, '189', 38, 'student.xlsx', 11462, '2020-06-02 13:09:21');
INSERT INTO `studentAnswer` VALUES (149, '196', 38, 'student.xlsx', 11462, '2020-06-02 13:09:21');
INSERT INTO `studentAnswer` VALUES (150, '117', 38, 'student.xlsx', 11462, '2020-06-02 13:09:21');
INSERT INTO `studentAnswer` VALUES (151, '131', 38, 'student.xlsx', 11462, '2020-06-02 13:09:22');
INSERT INTO `studentAnswer` VALUES (152, '186', 38, 'student.xlsx', 11462, '2020-06-02 13:09:23');
INSERT INTO `studentAnswer` VALUES (153, '184', 38, 'student.xlsx', 11462, '2020-06-02 13:09:23');
INSERT INTO `studentAnswer` VALUES (154, '87', 38, 'student.xlsx', 11462, '2020-06-02 13:09:23');
INSERT INTO `studentAnswer` VALUES (155, '129', 38, 'student.xlsx', 11462, '2020-06-02 13:09:23');
INSERT INTO `studentAnswer` VALUES (156, '125', 38, 'student.xlsx', 11462, '2020-06-02 13:09:23');
INSERT INTO `studentAnswer` VALUES (157, '93', 38, 'student.xlsx', 11462, '2020-06-02 13:09:23');
INSERT INTO `studentAnswer` VALUES (158, '33', 38, 'student.xlsx', 11462, '2020-06-06 18:15:30');
INSERT INTO `studentAnswer` VALUES (159, '46', 38, 'student.xlsx', 11462, '2020-06-06 18:15:31');
INSERT INTO `studentAnswer` VALUES (160, '27', 38, 'student.xlsx', 11462, '2020-06-06 18:15:36');
INSERT INTO `studentAnswer` VALUES (161, '94', 38, 'student.xlsx', 11462, '2020-06-02 13:09:25');
INSERT INTO `studentAnswer` VALUES (162, '32', 38, 'student.xlsx', 11462, '2020-06-06 18:15:34');
INSERT INTO `studentAnswer` VALUES (163, '190', 38, 'student.xlsx', 11462, '2020-06-02 13:09:26');
INSERT INTO `studentAnswer` VALUES (164, '80', 38, 'student.xlsx', 11462, '2020-06-02 13:09:26');
INSERT INTO `studentAnswer` VALUES (165, '64', 38, 'student.xlsx', 11462, '2020-06-02 13:09:26');
INSERT INTO `studentAnswer` VALUES (166, '62', 38, 'student.xlsx', 11462, '2020-06-02 13:09:27');
INSERT INTO `studentAnswer` VALUES (167, '91', 38, 'student.xlsx', 11462, '2020-06-02 13:09:27');
INSERT INTO `studentAnswer` VALUES (168, '169', 38, 'student.xlsx', 11462, '2020-06-02 13:09:27');
INSERT INTO `studentAnswer` VALUES (169, '199', 38, 'student.xlsx', 11462, '2020-06-02 13:09:27');
INSERT INTO `studentAnswer` VALUES (170, '40', 38, 'student.xlsx', 11462, '2020-06-06 18:15:32');
INSERT INTO `studentAnswer` VALUES (171, '122', 38, 'student.xlsx', 11462, '2020-06-02 13:09:28');
INSERT INTO `studentAnswer` VALUES (172, '30', 38, 'student.xlsx', 11462, '2020-06-06 18:15:31');
INSERT INTO `studentAnswer` VALUES (173, '68', 38, 'student.xlsx', 11462, '2020-06-02 13:09:28');
INSERT INTO `studentAnswer` VALUES (174, '57', 38, 'student.xlsx', 11462, '2020-06-02 13:09:28');
INSERT INTO `studentAnswer` VALUES (175, '69', 38, 'student.xlsx', 11462, '2020-06-02 13:09:28');
INSERT INTO `studentAnswer` VALUES (176, '173', 38, 'student.xlsx', 11462, '2020-06-02 13:09:28');
INSERT INTO `studentAnswer` VALUES (177, '152', 38, 'student.xlsx', 11462, '2020-06-02 13:09:28');
INSERT INTO `studentAnswer` VALUES (178, '143', 38, 'student.xlsx', 11462, '2020-06-02 13:09:29');
INSERT INTO `studentAnswer` VALUES (179, '98', 38, 'student.xlsx', 11462, '2020-06-02 13:09:30');
INSERT INTO `studentAnswer` VALUES (180, '45', 38, 'student.xlsx', 11462, '2020-06-06 18:15:41');
INSERT INTO `studentAnswer` VALUES (181, '50', 38, 'student.xlsx', 11462, '2020-06-06 18:15:40');
INSERT INTO `studentAnswer` VALUES (182, '103', 38, 'student.xlsx', 11462, '2020-06-02 13:09:30');
INSERT INTO `studentAnswer` VALUES (183, '187', 38, 'student.xlsx', 11462, '2020-06-02 13:09:30');
INSERT INTO `studentAnswer` VALUES (184, '116', 38, 'student.xlsx', 11462, '2020-06-02 13:09:31');
INSERT INTO `studentAnswer` VALUES (185, '149', 38, 'student.xlsx', 11462, '2020-06-02 13:09:31');
INSERT INTO `studentAnswer` VALUES (186, '191', 38, 'student.xlsx', 11462, '2020-06-02 13:09:31');
INSERT INTO `studentAnswer` VALUES (187, '36', 38, 'student.xlsx', 11462, '2020-06-06 18:15:33');
INSERT INTO `studentAnswer` VALUES (188, '52', 38, 'student.xlsx', 11462, '2020-06-02 13:09:32');
INSERT INTO `studentAnswer` VALUES (189, '170', 38, 'student.xlsx', 11462, '2020-06-02 13:09:31');
INSERT INTO `studentAnswer` VALUES (190, '128', 38, 'student.xlsx', 11462, '2020-06-02 13:09:32');
INSERT INTO `studentAnswer` VALUES (191, '65', 38, 'student.xlsx', 11462, '2020-06-02 13:09:32');
INSERT INTO `studentAnswer` VALUES (192, '120', 38, 'student.xlsx', 11462, '2020-06-02 13:09:32');
INSERT INTO `studentAnswer` VALUES (193, '164', 38, 'student.xlsx', 11462, '2020-06-02 13:09:33');
INSERT INTO `studentAnswer` VALUES (194, '194', 38, 'student.xlsx', 11462, '2020-06-02 13:09:33');
INSERT INTO `studentAnswer` VALUES (195, '156', 38, 'student.xlsx', 11462, '2020-06-02 13:09:34');
INSERT INTO `studentAnswer` VALUES (196, '67', 38, 'student.xlsx', 11462, '2020-06-02 13:09:34');
INSERT INTO `studentAnswer` VALUES (197, '121', 38, 'student.xlsx', 11462, '2020-06-02 13:09:34');
INSERT INTO `studentAnswer` VALUES (198, '54', 38, 'student.xlsx', 11462, '2020-06-02 13:09:34');
INSERT INTO `studentAnswer` VALUES (199, '95', 38, 'student.xlsx', 11462, '2020-06-02 13:09:34');
INSERT INTO `studentAnswer` VALUES (200, '172', 38, 'student.xlsx', 11462, '2020-06-02 13:09:34');
INSERT INTO `studentAnswer` VALUES (201, '157', 38, 'student.xlsx', 11462, '2020-06-02 13:09:35');
INSERT INTO `studentAnswer` VALUES (202, '159', 38, 'student.xlsx', 11462, '2020-06-02 13:09:35');
INSERT INTO `studentAnswer` VALUES (203, '89', 38, 'student.xlsx', 11462, '2020-06-02 13:09:35');
INSERT INTO `studentAnswer` VALUES (204, '150', 38, 'student.xlsx', 11462, '2020-06-02 13:09:36');
INSERT INTO `studentAnswer` VALUES (205, '158', 38, 'student.xlsx', 11462, '2020-06-02 13:09:36');
INSERT INTO `studentAnswer` VALUES (206, '146', 38, 'student.xlsx', 11462, '2020-06-02 13:09:38');
INSERT INTO `studentAnswer` VALUES (207, '144', 38, 'student.xlsx', 11462, '2020-06-02 13:09:38');
INSERT INTO `studentAnswer` VALUES (208, '83', 38, 'student.xlsx', 11462, '2020-06-02 13:09:38');
INSERT INTO `studentAnswer` VALUES (209, '140', 38, 'student.xlsx', 11462, '2020-06-02 13:09:39');
INSERT INTO `studentAnswer` VALUES (210, '110', 38, 'student.xlsx', 11462, '2020-06-02 13:09:39');
INSERT INTO `studentAnswer` VALUES (211, '160', 38, 'student.xlsx', 11462, '2020-06-02 13:09:40');
INSERT INTO `studentAnswer` VALUES (212, '147', 38, 'student.xlsx', 11462, '2020-06-02 13:09:42');
INSERT INTO `studentAnswer` VALUES (213, '48', 38, 'student.xlsx', 11462, '2020-06-06 18:15:33');
INSERT INTO `studentAnswer` VALUES (214, '70', 38, 'student.xlsx', 11462, '2020-06-02 13:09:43');
INSERT INTO `studentAnswer` VALUES (215, '177', 38, 'student.xlsx', 11462, '2020-06-02 13:09:43');
INSERT INTO `studentAnswer` VALUES (216, '47', 38, 'student.xlsx', 11462, '2020-06-06 18:15:37');
INSERT INTO `studentAnswer` VALUES (217, '171', 38, 'student.xlsx', 11462, '2020-06-02 13:09:44');
INSERT INTO `studentAnswer` VALUES (218, '31', 38, 'student.xlsx', 11462, '2020-06-06 18:15:37');
INSERT INTO `studentAnswer` VALUES (219, '114', 38, 'student.xlsx', 11462, '2020-06-02 13:09:45');
INSERT INTO `studentAnswer` VALUES (220, '181', 38, 'student.xlsx', 11462, '2020-06-02 13:09:46');
INSERT INTO `studentAnswer` VALUES (221, '112', 38, 'student.xlsx', 11462, '2020-06-02 13:09:49');
INSERT INTO `studentAnswer` VALUES (222, '60', 38, 'student.xlsx', 11462, '2020-06-02 13:09:50');
INSERT INTO `studentAnswer` VALUES (223, '88', 38, 'student.xlsx', 11462, '2020-06-02 13:09:51');
INSERT INTO `studentAnswer` VALUES (224, '153', 38, 'student.xlsx', 11462, '2020-06-02 13:09:53');
INSERT INTO `studentAnswer` VALUES (225, '42', 38, 'student.xlsx', 11462, '2020-06-06 18:15:37');
INSERT INTO `studentAnswer` VALUES (226, '29', 38, 'student.xlsx', 11462, '2020-06-06 18:15:31');
INSERT INTO `studentAnswer` VALUES (227, '34', 38, 'student.xlsx', 11462, '2020-06-06 18:15:34');
INSERT INTO `studentAnswer` VALUES (228, '200', 38, '新建文本文档.txt', 2, '2020-06-02 16:27:06');
INSERT INTO `studentAnswer` VALUES (232, '1', 44, '第一章作业.docx', 1373355, '2020-06-05 14:46:47');
INSERT INTO `studentAnswer` VALUES (578, '1', 53, 'uploadAnswer.jmx', 26956, '2020-06-06 19:48:56');

-- ----------------------------
-- Table structure for studentExam
-- ----------------------------
DROP TABLE IF EXISTS `studentExam`;
CREATE TABLE `studentExam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `examId` int(11) NOT NULL,
  `login` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_studentExam_student_idx`(`studentId`) USING BTREE,
  INDEX `fk_studentExam_exam_idx`(`examId`) USING BTREE,
  CONSTRAINT `fk_studentExam_exam` FOREIGN KEY (`examId`) REFERENCES `exam` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_studentExam_student` FOREIGN KEY (`studentId`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1955 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of studentExam
-- ----------------------------
INSERT INTO `studentExam` VALUES (80, '1129', 24, NULL);
INSERT INTO `studentExam` VALUES (81, '1119', 24, 1);
INSERT INTO `studentExam` VALUES (82, '123', 25, NULL);
INSERT INTO `studentExam` VALUES (83, '124', 25, NULL);
INSERT INTO `studentExam` VALUES (84, '125', 25, NULL);
INSERT INTO `studentExam` VALUES (85, '126', 25, NULL);
INSERT INTO `studentExam` VALUES (86, '127', 25, NULL);
INSERT INTO `studentExam` VALUES (87, '128', 25, NULL);
INSERT INTO `studentExam` VALUES (88, '129', 25, NULL);
INSERT INTO `studentExam` VALUES (89, '130', 25, NULL);
INSERT INTO `studentExam` VALUES (99, '1', 27, 1);
INSERT INTO `studentExam` VALUES (108, '2', 27, 1);
INSERT INTO `studentExam` VALUES (109, '3', 27, NULL);
INSERT INTO `studentExam` VALUES (110, '4', 27, NULL);
INSERT INTO `studentExam` VALUES (112, '1', 30, 1);
INSERT INTO `studentExam` VALUES (113, '1', 30, NULL);
INSERT INTO `studentExam` VALUES (722, '1', 38, 1);
INSERT INTO `studentExam` VALUES (723, '2', 38, 1);
INSERT INTO `studentExam` VALUES (724, '3', 38, 1);
INSERT INTO `studentExam` VALUES (725, '4', 38, 1);
INSERT INTO `studentExam` VALUES (726, '5', 38, 1);
INSERT INTO `studentExam` VALUES (727, '6', 38, 1);
INSERT INTO `studentExam` VALUES (728, '7', 38, 1);
INSERT INTO `studentExam` VALUES (729, '8', 38, 1);
INSERT INTO `studentExam` VALUES (730, '9', 38, 1);
INSERT INTO `studentExam` VALUES (731, '10', 38, 1);
INSERT INTO `studentExam` VALUES (732, '11', 38, 1);
INSERT INTO `studentExam` VALUES (733, '12', 38, 1);
INSERT INTO `studentExam` VALUES (734, '13', 38, 1);
INSERT INTO `studentExam` VALUES (735, '14', 38, 1);
INSERT INTO `studentExam` VALUES (736, '15', 38, 1);
INSERT INTO `studentExam` VALUES (737, '16', 38, 1);
INSERT INTO `studentExam` VALUES (738, '17', 38, 1);
INSERT INTO `studentExam` VALUES (739, '18', 38, 1);
INSERT INTO `studentExam` VALUES (740, '19', 38, 1);
INSERT INTO `studentExam` VALUES (741, '20', 38, 1);
INSERT INTO `studentExam` VALUES (742, '21', 38, 1);
INSERT INTO `studentExam` VALUES (743, '22', 38, 1);
INSERT INTO `studentExam` VALUES (744, '23', 38, 1);
INSERT INTO `studentExam` VALUES (745, '24', 38, 1);
INSERT INTO `studentExam` VALUES (746, '25', 38, 1);
INSERT INTO `studentExam` VALUES (747, '26', 38, 1);
INSERT INTO `studentExam` VALUES (748, '27', 38, 1);
INSERT INTO `studentExam` VALUES (749, '28', 38, 1);
INSERT INTO `studentExam` VALUES (750, '29', 38, 1);
INSERT INTO `studentExam` VALUES (751, '30', 38, 1);
INSERT INTO `studentExam` VALUES (752, '31', 38, 1);
INSERT INTO `studentExam` VALUES (753, '32', 38, 1);
INSERT INTO `studentExam` VALUES (754, '33', 38, 1);
INSERT INTO `studentExam` VALUES (755, '34', 38, 1);
INSERT INTO `studentExam` VALUES (756, '35', 38, 1);
INSERT INTO `studentExam` VALUES (757, '36', 38, 1);
INSERT INTO `studentExam` VALUES (758, '37', 38, 1);
INSERT INTO `studentExam` VALUES (759, '38', 38, 1);
INSERT INTO `studentExam` VALUES (760, '39', 38, 1);
INSERT INTO `studentExam` VALUES (761, '40', 38, 1);
INSERT INTO `studentExam` VALUES (762, '41', 38, 1);
INSERT INTO `studentExam` VALUES (763, '42', 38, 1);
INSERT INTO `studentExam` VALUES (764, '43', 38, 1);
INSERT INTO `studentExam` VALUES (765, '44', 38, 1);
INSERT INTO `studentExam` VALUES (766, '45', 38, 1);
INSERT INTO `studentExam` VALUES (767, '46', 38, 1);
INSERT INTO `studentExam` VALUES (768, '47', 38, 1);
INSERT INTO `studentExam` VALUES (769, '48', 38, 1);
INSERT INTO `studentExam` VALUES (770, '49', 38, 1);
INSERT INTO `studentExam` VALUES (771, '50', 38, 1);
INSERT INTO `studentExam` VALUES (772, '51', 38, 1);
INSERT INTO `studentExam` VALUES (773, '52', 38, 1);
INSERT INTO `studentExam` VALUES (774, '53', 38, 1);
INSERT INTO `studentExam` VALUES (775, '54', 38, 1);
INSERT INTO `studentExam` VALUES (776, '55', 38, 1);
INSERT INTO `studentExam` VALUES (777, '56', 38, 1);
INSERT INTO `studentExam` VALUES (778, '57', 38, 1);
INSERT INTO `studentExam` VALUES (779, '58', 38, 1);
INSERT INTO `studentExam` VALUES (780, '59', 38, 1);
INSERT INTO `studentExam` VALUES (781, '60', 38, 1);
INSERT INTO `studentExam` VALUES (782, '61', 38, 1);
INSERT INTO `studentExam` VALUES (783, '62', 38, 1);
INSERT INTO `studentExam` VALUES (784, '63', 38, 1);
INSERT INTO `studentExam` VALUES (785, '64', 38, 1);
INSERT INTO `studentExam` VALUES (786, '65', 38, 1);
INSERT INTO `studentExam` VALUES (787, '66', 38, 1);
INSERT INTO `studentExam` VALUES (788, '67', 38, 1);
INSERT INTO `studentExam` VALUES (789, '68', 38, 1);
INSERT INTO `studentExam` VALUES (790, '69', 38, 1);
INSERT INTO `studentExam` VALUES (791, '70', 38, 1);
INSERT INTO `studentExam` VALUES (792, '71', 38, 1);
INSERT INTO `studentExam` VALUES (793, '72', 38, 1);
INSERT INTO `studentExam` VALUES (794, '73', 38, 1);
INSERT INTO `studentExam` VALUES (795, '74', 38, 1);
INSERT INTO `studentExam` VALUES (796, '75', 38, 1);
INSERT INTO `studentExam` VALUES (797, '76', 38, NULL);
INSERT INTO `studentExam` VALUES (798, '77', 38, 1);
INSERT INTO `studentExam` VALUES (799, '78', 38, 1);
INSERT INTO `studentExam` VALUES (800, '79', 38, 1);
INSERT INTO `studentExam` VALUES (801, '80', 38, 1);
INSERT INTO `studentExam` VALUES (802, '81', 38, 1);
INSERT INTO `studentExam` VALUES (803, '82', 38, 1);
INSERT INTO `studentExam` VALUES (804, '83', 38, 1);
INSERT INTO `studentExam` VALUES (805, '84', 38, 1);
INSERT INTO `studentExam` VALUES (806, '85', 38, NULL);
INSERT INTO `studentExam` VALUES (807, '86', 38, 1);
INSERT INTO `studentExam` VALUES (808, '87', 38, 1);
INSERT INTO `studentExam` VALUES (809, '88', 38, 1);
INSERT INTO `studentExam` VALUES (810, '89', 38, 1);
INSERT INTO `studentExam` VALUES (811, '90', 38, 1);
INSERT INTO `studentExam` VALUES (812, '91', 38, 1);
INSERT INTO `studentExam` VALUES (813, '92', 38, 1);
INSERT INTO `studentExam` VALUES (814, '93', 38, 1);
INSERT INTO `studentExam` VALUES (815, '94', 38, 1);
INSERT INTO `studentExam` VALUES (816, '95', 38, 1);
INSERT INTO `studentExam` VALUES (817, '96', 38, 1);
INSERT INTO `studentExam` VALUES (818, '97', 38, 1);
INSERT INTO `studentExam` VALUES (819, '98', 38, 1);
INSERT INTO `studentExam` VALUES (820, '99', 38, 1);
INSERT INTO `studentExam` VALUES (821, '100', 38, 1);
INSERT INTO `studentExam` VALUES (822, '101', 38, 1);
INSERT INTO `studentExam` VALUES (823, '102', 38, 1);
INSERT INTO `studentExam` VALUES (824, '103', 38, 1);
INSERT INTO `studentExam` VALUES (825, '104', 38, 1);
INSERT INTO `studentExam` VALUES (826, '105', 38, NULL);
INSERT INTO `studentExam` VALUES (827, '106', 38, 1);
INSERT INTO `studentExam` VALUES (828, '107', 38, 1);
INSERT INTO `studentExam` VALUES (829, '108', 38, 1);
INSERT INTO `studentExam` VALUES (830, '109', 38, 1);
INSERT INTO `studentExam` VALUES (831, '110', 38, 1);
INSERT INTO `studentExam` VALUES (832, '111', 38, 1);
INSERT INTO `studentExam` VALUES (833, '112', 38, 1);
INSERT INTO `studentExam` VALUES (834, '113', 38, NULL);
INSERT INTO `studentExam` VALUES (835, '114', 38, 1);
INSERT INTO `studentExam` VALUES (836, '115', 38, 1);
INSERT INTO `studentExam` VALUES (837, '116', 38, 1);
INSERT INTO `studentExam` VALUES (838, '117', 38, 1);
INSERT INTO `studentExam` VALUES (839, '118', 38, 1);
INSERT INTO `studentExam` VALUES (840, '119', 38, 1);
INSERT INTO `studentExam` VALUES (841, '120', 38, 1);
INSERT INTO `studentExam` VALUES (842, '121', 38, 1);
INSERT INTO `studentExam` VALUES (843, '122', 38, 1);
INSERT INTO `studentExam` VALUES (844, '123', 38, 1);
INSERT INTO `studentExam` VALUES (845, '124', 38, 1);
INSERT INTO `studentExam` VALUES (846, '125', 38, 1);
INSERT INTO `studentExam` VALUES (847, '126', 38, 1);
INSERT INTO `studentExam` VALUES (848, '127', 38, 1);
INSERT INTO `studentExam` VALUES (849, '128', 38, 1);
INSERT INTO `studentExam` VALUES (850, '129', 38, 1);
INSERT INTO `studentExam` VALUES (851, '130', 38, NULL);
INSERT INTO `studentExam` VALUES (852, '131', 38, 1);
INSERT INTO `studentExam` VALUES (853, '132', 38, 1);
INSERT INTO `studentExam` VALUES (854, '133', 38, 1);
INSERT INTO `studentExam` VALUES (855, '134', 38, 1);
INSERT INTO `studentExam` VALUES (856, '135', 38, 1);
INSERT INTO `studentExam` VALUES (857, '136', 38, 1);
INSERT INTO `studentExam` VALUES (858, '137', 38, 1);
INSERT INTO `studentExam` VALUES (859, '138', 38, 1);
INSERT INTO `studentExam` VALUES (860, '139', 38, NULL);
INSERT INTO `studentExam` VALUES (861, '140', 38, 1);
INSERT INTO `studentExam` VALUES (862, '141', 38, NULL);
INSERT INTO `studentExam` VALUES (863, '142', 38, 1);
INSERT INTO `studentExam` VALUES (864, '143', 38, 1);
INSERT INTO `studentExam` VALUES (865, '144', 38, 1);
INSERT INTO `studentExam` VALUES (866, '145', 38, 1);
INSERT INTO `studentExam` VALUES (867, '146', 38, 1);
INSERT INTO `studentExam` VALUES (868, '147', 38, 1);
INSERT INTO `studentExam` VALUES (869, '148', 38, 1);
INSERT INTO `studentExam` VALUES (870, '149', 38, 1);
INSERT INTO `studentExam` VALUES (871, '150', 38, 1);
INSERT INTO `studentExam` VALUES (872, '151', 38, 1);
INSERT INTO `studentExam` VALUES (873, '152', 38, 1);
INSERT INTO `studentExam` VALUES (874, '153', 38, 1);
INSERT INTO `studentExam` VALUES (875, '154', 38, 1);
INSERT INTO `studentExam` VALUES (876, '155', 38, 1);
INSERT INTO `studentExam` VALUES (877, '156', 38, 1);
INSERT INTO `studentExam` VALUES (878, '157', 38, 1);
INSERT INTO `studentExam` VALUES (879, '158', 38, 1);
INSERT INTO `studentExam` VALUES (880, '159', 38, 1);
INSERT INTO `studentExam` VALUES (881, '160', 38, 1);
INSERT INTO `studentExam` VALUES (882, '161', 38, 1);
INSERT INTO `studentExam` VALUES (883, '162', 38, 1);
INSERT INTO `studentExam` VALUES (884, '163', 38, 1);
INSERT INTO `studentExam` VALUES (885, '164', 38, 1);
INSERT INTO `studentExam` VALUES (886, '165', 38, 1);
INSERT INTO `studentExam` VALUES (887, '166', 38, 1);
INSERT INTO `studentExam` VALUES (888, '167', 38, 1);
INSERT INTO `studentExam` VALUES (889, '168', 38, 1);
INSERT INTO `studentExam` VALUES (890, '169', 38, 1);
INSERT INTO `studentExam` VALUES (891, '170', 38, 1);
INSERT INTO `studentExam` VALUES (892, '171', 38, 1);
INSERT INTO `studentExam` VALUES (893, '172', 38, 1);
INSERT INTO `studentExam` VALUES (894, '173', 38, 1);
INSERT INTO `studentExam` VALUES (895, '174', 38, 1);
INSERT INTO `studentExam` VALUES (896, '175', 38, 1);
INSERT INTO `studentExam` VALUES (897, '176', 38, 1);
INSERT INTO `studentExam` VALUES (898, '177', 38, 1);
INSERT INTO `studentExam` VALUES (899, '178', 38, 1);
INSERT INTO `studentExam` VALUES (900, '179', 38, 1);
INSERT INTO `studentExam` VALUES (901, '180', 38, 1);
INSERT INTO `studentExam` VALUES (902, '181', 38, 1);
INSERT INTO `studentExam` VALUES (903, '182', 38, 1);
INSERT INTO `studentExam` VALUES (904, '183', 38, 1);
INSERT INTO `studentExam` VALUES (905, '184', 38, 1);
INSERT INTO `studentExam` VALUES (906, '185', 38, 1);
INSERT INTO `studentExam` VALUES (907, '186', 38, 1);
INSERT INTO `studentExam` VALUES (908, '187', 38, 1);
INSERT INTO `studentExam` VALUES (909, '188', 38, 1);
INSERT INTO `studentExam` VALUES (910, '189', 38, 1);
INSERT INTO `studentExam` VALUES (911, '190', 38, 1);
INSERT INTO `studentExam` VALUES (912, '191', 38, 1);
INSERT INTO `studentExam` VALUES (913, '192', 38, 1);
INSERT INTO `studentExam` VALUES (914, '193', 38, 1);
INSERT INTO `studentExam` VALUES (915, '194', 38, 1);
INSERT INTO `studentExam` VALUES (916, '195', 38, 1);
INSERT INTO `studentExam` VALUES (917, '196', 38, 1);
INSERT INTO `studentExam` VALUES (918, '197', 38, 1);
INSERT INTO `studentExam` VALUES (919, '198', 38, 1);
INSERT INTO `studentExam` VALUES (920, '199', 38, 1);
INSERT INTO `studentExam` VALUES (921, '200', 38, 1);
INSERT INTO `studentExam` VALUES (926, '1', 44, 1);
INSERT INTO `studentExam` VALUES (927, '2', 44, 1);
INSERT INTO `studentExam` VALUES (1949, '1', 53, 1);
INSERT INTO `studentExam` VALUES (1950, '2', 53, 0);
INSERT INTO `studentExam` VALUES (1951, '1', 54, 1);
INSERT INTO `studentExam` VALUES (1952, '2', 54, 1);
INSERT INTO `studentExam` VALUES (1953, '1', 55, 1);
INSERT INTO `studentExam` VALUES (1954, '2', 55, 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fullName` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isManager` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (67, 'teacher', '111', '698d51a19d8a121ce581499d7b701668', 1);
INSERT INTO `teacher` VALUES (69, '101010', '冯洪海', '698d51a19d8a121ce581499d7b701668', 1);

SET FOREIGN_KEY_CHECKS = 1;
