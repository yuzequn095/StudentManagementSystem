/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19 : Database - db_student_swing
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_student_swing` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_student_swing`;

/*Table structure for table `t_school_class` */

DROP TABLE IF EXISTS `t_school_class`;

CREATE TABLE `t_school_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(20) DEFAULT NULL,
  `classDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_school_class` */

insert  into `t_school_class`(`id`,`className`,`classDesc`) values (12,'Information system','Information system, an integrated set of components for collecting, storing, and processing data and for providing information, knowledge, and digital'),(14,'Computer science','Computer Science is the study of computers and computational systems. Unlike electrical and computer engineers,  scientists deal mostly with software and software  includes their theory, design, development, and application.');

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `dept` varchar(128) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert  into `t_student`(`id`,`name`,`sn`,`sex`,`dept`,`classId`,`address`) values (5,'Julian','11111','female','Business',12,'2751 Mission St. San francisco, CA 94110'),(6,'Tom','11112','male','Medical',12,'411 Hayes St. San francisco, CA 941'),(7,'Bob','11113','male','Engineering',14,'2530 Bancroft Way Berkeley, CA 947'),(11,'Wuyu','888','male','Business',12,'Fu');

/*Table structure for table `t_teacher` */

DROP TABLE IF EXISTS `t_teacher`;

CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `dept` varchar(128) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `address` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_teacher` */

insert  into `t_teacher`(`id`,`name`,`sn`,`sex`,`dept`,`classId`,`address`) values (1,'John','10000','male','Business',14,'1600 Pennsylvania Ave 156'),(2,'James','10001','male','Medical',12,'1305 Van Ness Ave'),(3,'Christian','10002','female','Engineering',12,'71 Tamai Vista Bl'),(9,'Teacher P','434','male','Medical',12,'Doc 167 Street'),(11,'New T','4545','male','Business',14,'China');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`) values (1,'admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
