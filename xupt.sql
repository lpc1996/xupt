/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : xupt

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 12/02/2020 21:45:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_info
-- ----------------------------
DROP TABLE IF EXISTS `base_info`;
CREATE TABLE `base_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动序号',
  `user_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学工号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `formar_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '曾用名',
  `sex` enum('男','女','保密') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '保密' COMMENT '性别',
  `age` int(2) NOT NULL DEFAULT 0 COMMENT '年龄',
  `native_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '籍贯',
  `IDCARD_type` enum('居民身份证','中华人民共和国护照') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '证件类型',
  `IDCARD_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '证件号码',
  `user_type` enum('student','teacher','admin') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户类型',
  `tel` char(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id_2`(`user_id`) USING BTREE,
  UNIQUE INDEX `user_id_3`(`user_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户基本信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院名称',
  `presidentId` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '院长职工号',
  `vicePresidentId` char(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '副院长职工号',
  `information` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '学院简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学院信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程名称',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院ID',
  `department_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部ID',
  `type` enum('必修课','选修课') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '课程类型',
  `credit` double(1, 0) NOT NULL COMMENT '学分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '课程信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系/部代号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系/部名称',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院代号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `department-college`(`college_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系/部信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户昵称',
  `limit` enum('1','2','3','4','5','6','7','8','9') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '权限等级',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业名称',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院ID',
  `department_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `major_colleege`(`college_id`) USING BTREE,
  INDEX `major_department`(`department_id`) USING BTREE,
  CONSTRAINT `major-college` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `major-department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '专业信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for offering_courses
-- ----------------------------
DROP TABLE IF EXISTS `offering_courses`;
CREATE TABLE `offering_courses`  (
  `id` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '开课班级序号',
  `course_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程编号',
  `teacher_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '授课教师',
  `begin` date NOT NULL COMMENT '开始时间',
  `school_year_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '开课学年',
  `school_trem_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '开课学期',
  `semester_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '授课年级',
  `max_num` int(3) NOT NULL COMMENT '班级人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '开课信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`  (
  `id` int(10) NOT NULL COMMENT '自动序号',
  `student_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `course_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所选课程班',
  `result` double(3, 2) NOT NULL COMMENT '成绩',
  `time` datetime(0) NOT NULL COMMENT '录入时间',
  `teacher_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '录入教师',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '成绩表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_trem
-- ----------------------------
DROP TABLE IF EXISTS `school_trem`;
CREATE TABLE `school_trem`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学期编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学期名称',
  `school_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学年',
  `begin` date NOT NULL COMMENT '开始时间',
  `end` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学期' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_year
-- ----------------------------
DROP TABLE IF EXISTS `school_year`;
CREATE TABLE `school_year`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学年编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学年名称',
  `begin` date NOT NULL COMMENT '开始时间',
  `end` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学年' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '年级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学号',
  `year` date NOT NULL COMMENT '入学年份',
  `college` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `department` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部',
  `major` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属专业',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属年级',
  `class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属班级',
  `culture_level` enum('专科','本科','硕士','博士') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '培养层次',
  `student_type` enum('普通专科生','普通本科生','硕士研究生','博士生') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生类别',
  `Education` enum('小学','初中','高中','本科','专科','硕士','博士') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学历',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student-team`(`class`) USING BTREE,
  INDEX `student-college`(`college`) USING BTREE,
  CONSTRAINT `student-college` FOREIGN KEY (`college`) REFERENCES `college` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student-team` FOREIGN KEY (`class`) REFERENCES `team` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生学籍信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动序号',
  `student_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `course_id` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '授课班',
  `school_year_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '选课学年',
  `school_trem_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '选课学期',
  `time` date NOT NULL COMMENT '选课时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生选课信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '工号',
  `college` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `department` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部',
  `level` enum('助教','讲师','副教授','教授') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '职称',
  `Education` enum('专科','本科','硕士','博士') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学历',
  `year` date NOT NULL COMMENT '入职时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '教师信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '班级编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '班级名称',
  `number` int(2) NOT NULL COMMENT '班级人数',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `department_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部',
  `major_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属专业',
  `semester` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属年级',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `team-college`(`college_id`) USING BTREE,
  INDEX `team-department`(`department_id`) USING BTREE,
  INDEX `team-semester`(`semester`) USING BTREE,
  INDEX `team-major`(`major_id`) USING BTREE,
  CONSTRAINT `team-college` FOREIGN KEY (`college_id`) REFERENCES `college` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team-department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team-major` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team-semester` FOREIGN KEY (`semester`) REFERENCES `semester` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '班级信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for base_student
-- ----------------------------
DROP VIEW IF EXISTS `base_student`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `base_student` AS select `base_info`.`user_id` AS `user_id`,`base_info`.`name` AS `name`,
	`base_info`.`formar_name` AS `formar_name`,`base_info`.`sex` AS `sex`,`base_info`.`age` AS `age`,`base_info`.`native_place` AS `native_place`,
	`base_info`.`IDCARD_type` AS `IDCARD_type`,`base_info`.`IDCARD_NUM` AS `IDCARD_NUM`,`base_info`.`user_type` AS `user_type`,`base_info`.`tel` AS `tel`,
	`student`.`year` AS `year`,`student`.`college` AS `college`,`student`.`department` AS `department`,`student`.`major` AS `major`,`student`.`grade` AS `grade`,
	`student`.`class` AS `class`,`student`.`culture_level` AS `culture_level`,`student`.`student_type` AS `student_type`,`student`.`Education` AS `Education` 
from (`base_info` join `student`) 
where (`base_info`.`user_id` = `student`.`id`);

-- ----------------------------
-- View structure for base_teacher
-- ----------------------------
DROP VIEW IF EXISTS `base_teacher`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `base_teacher` AS select `base_info`.`user_id` AS `user_id`,`base_info`.`name` AS `name`,
	`base_info`.`formar_name` AS `formar_name`,`base_info`.`sex` AS `sex`,`base_info`.`age` AS `age`,`base_info`.`native_place` AS `native_place`,
	`base_info`.`IDCARD_type` AS `IDCARD_type`,`base_info`.`IDCARD_NUM` AS `IDCARD_NUM`,`base_info`.`user_type` AS `user_type`,`base_info`.`tel` AS `tel`,
	`teacher`.`college` AS `college`,`teacher`.`department` AS `department`,`teacher`.`level` AS `level`,`teacher`.`Education` AS `Education`,
	`teacher`.`year` AS `year` 
from (`base_info` join `teacher`) 
where (`base_info`.`user_id` = `teacher`.`id`);

SET FOREIGN_KEY_CHECKS = 1;
