/*
SQLyog Community v13.2.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - median
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`median` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `median`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `loginId` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `admin` */

insert  into `admin`(`id`,`loginId`,`loginPw`,`name`) values 
(1,'admin','admin','김관리');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `body` text NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  `doctor_id` int(10) unsigned NOT NULL,
  `hit` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`title`,`body`,`patient_id`,`doctor_id`,`hit`) values 
(1,'2024-04-07 16:58:55','신고합니다','돌팔이에요',1,3,15),
(2,'2024-04-07 17:05:37','칭찬합니다','친절해요',2,4,1);

/*Table structure for table `articleReply` */

DROP TABLE IF EXISTS `articleReply`;

CREATE TABLE `articleReply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `body` char(100) NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  `article_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `articlereply_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `articleReply` */

insert  into `articleReply`(`id`,`regDate`,`body`,`patient_id`,`article_id`) values 
(1,'2024-04-07 17:07:09','댓글1',1,2);

/*Table structure for table `case` */

DROP TABLE IF EXISTS `case`;

CREATE TABLE `case` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` date NOT NULL,
  `doctor_id` int(10) unsigned NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  `medicalHistory` text NOT NULL,
  PRIMARY KEY (`id`,`doctor_id`,`patient_id`),
  KEY `doctor_id` (`doctor_id`),
  KEY `patient_id` (`patient_id`),
  CONSTRAINT `case_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `case_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `case` */

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(100) NOT NULL,
  `dpt_id` int(10) unsigned NOT NULL,
  `loginPw` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dpt_id` (`dpt_id`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`dpt_id`) REFERENCES `dpt` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `doctor` */

insert  into `doctor`(`id`,`name`,`dpt_id`,`loginPw`) values 
(0,'공용',0,'admin'),
(1,'이익준',1,'jo'),
(2,'채송화',2,'chae'),
(3,'양석형',3,'yang'),
(4,'김준완',4,'kim'),
(5,'안정원',5,'ahn'),
(6,'장겨울',1,'jang'),
(7,'용석민',2,'yong'),
(8,'추민하',3,'choo'),
(9,'도재학',4,'do'),
(10,'한현희',5,'han');

/*Table structure for table `doctor_time` */

DROP TABLE IF EXISTS `doctor_time`;

CREATE TABLE `doctor_time` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_id` int(10) unsigned NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `doctor_time_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `doctor_time` */

insert  into `doctor_time`(`id`,`doctor_id`,`time`) values 
(1,1,'09:10:00'),
(2,1,'10:10:00'),
(3,1,'11:10:00'),
(4,1,'13:10:00'),
(5,1,'14:10:00'),
(6,1,'15:10:00'),
(7,1,'16:10:00'),
(8,1,'17:10:00'),
(9,1,'18:10:00'),
(10,2,'09:10:00'),
(11,2,'10:10:00'),
(12,2,'11:10:00'),
(13,2,'13:10:00'),
(14,2,'14:10:00'),
(15,2,'15:10:00'),
(16,2,'16:10:00'),
(17,2,'17:10:00'),
(18,2,'18:10:00'),
(19,3,'09:10:00'),
(20,3,'10:10:00'),
(21,3,'11:10:00'),
(22,3,'13:10:00'),
(23,3,'14:10:00'),
(24,3,'15:10:00'),
(25,3,'16:10:00'),
(26,3,'17:10:00'),
(27,3,'18:10:00'),
(28,4,'09:10:00'),
(29,4,'10:10:00'),
(30,4,'11:10:00'),
(31,4,'13:10:00'),
(32,4,'14:10:00'),
(33,4,'15:10:00'),
(34,4,'16:10:00'),
(35,4,'17:10:00'),
(36,4,'18:10:00'),
(37,5,'09:10:00'),
(38,5,'10:10:00'),
(39,5,'11:10:00'),
(40,5,'13:10:00'),
(41,5,'14:10:00'),
(42,5,'15:10:00'),
(43,5,'16:10:00'),
(44,5,'17:10:00'),
(45,5,'18:10:00'),
(46,6,'09:10:00'),
(47,6,'10:10:00'),
(48,6,'11:10:00'),
(49,6,'13:10:00'),
(50,6,'14:10:00'),
(51,6,'15:10:00'),
(52,6,'16:10:00'),
(53,6,'17:10:00'),
(54,6,'18:10:00'),
(55,7,'09:10:00'),
(56,7,'10:10:00'),
(57,7,'11:10:00'),
(58,7,'13:10:00'),
(59,7,'14:10:00'),
(60,7,'15:10:00'),
(61,7,'16:10:00'),
(62,7,'17:10:00'),
(63,7,'18:10:00'),
(64,8,'09:10:00'),
(65,8,'10:10:00'),
(66,8,'11:10:00'),
(67,8,'13:10:00'),
(68,8,'14:10:00'),
(69,8,'15:10:00'),
(70,8,'16:10:00'),
(71,8,'17:10:00'),
(72,8,'18:10:00'),
(73,9,'09:10:00'),
(74,9,'10:10:00'),
(75,9,'11:10:00'),
(76,9,'13:10:00'),
(77,9,'14:10:00'),
(78,9,'15:10:00'),
(79,9,'16:10:00'),
(80,9,'17:10:00'),
(81,9,'18:10:00'),
(82,10,'09:10:00'),
(83,10,'10:10:00'),
(84,10,'11:10:00'),
(85,10,'13:10:00'),
(86,10,'14:10:00'),
(87,10,'15:10:00'),
(88,10,'16:10:00'),
(89,10,'17:10:00'),
(90,10,'18:10:00');

/*Table structure for table `dpt` */

DROP TABLE IF EXISTS `dpt`;

CREATE TABLE `dpt` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(100) NOT NULL,
  `phone` char(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `dpt` */

insert  into `dpt`(`id`,`name`,`phone`) values 
(0,'공용','0'),
(1,'간담췌외과','042-220-8840'),
(2,'신경외과','042-1899-6075'),
(3,'산부인과','042-586-0912'),
(4,'흉부외과','042-541-0586'),
(5,'소아외과','042-586-7676');

/*Table structure for table `medicalHistory` */

DROP TABLE IF EXISTS `medicalHistory`;

CREATE TABLE `medicalHistory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `medicalHistory` text NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `medicalHistory` (`medicalHistory`,`patient_id`) USING HASH,
  KEY `patient_id` (`patient_id`),
  CONSTRAINT `medicalhistory_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `medicalHistory` */

insert  into `medicalHistory`(`id`,`medicalHistory`,`patient_id`) values 
(1,'운동 열심히 하세요!!',1),
(2,'운동 열심히 하세요!!',2),
(3,'식단 관리가 필요합니다.',3),
(4,'조심히 다니세요',4);

/*Table structure for table `patient` */

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(100) NOT NULL,
  `regDate` date NOT NULL,
  `age` int(10) NOT NULL,
  `phone` char(100) NOT NULL,
  `rrn` char(100) NOT NULL,
  `height` decimal(6,2) DEFAULT NULL,
  `weight` decimal(6,2) DEFAULT NULL,
  `ud` char(100) NOT NULL,
  `loginId` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `patient` */

insert  into `patient`(`id`,`name`,`regDate`,`age`,`phone`,`rrn`,`height`,`weight`,`ud`,`loginId`,`loginPw`) values 
(1,'홍길동','2024-04-07',26,'010-1234-5678','991825-1507863',174.20,70.00,'폐렴','hong','gildong'),
(2,'홍길순','2024-04-07',24,'010-5678-1234','010725-2504835',162.20,50.00,'없음','hongg','ilsoon'),
(3,'박지성','2024-04-07',40,'010-1842-7640','850925-1104238',177.90,73.00,'골절','park','jisung'),
(4,'이우주','2024-04-07',7,'010-8820-7610','181217-1409635',120.50,23.50,'없음','lee','woojoo'),
(5,'김민섭','2024-04-08',26,'010-4809-7610','991217-1407412',174.50,62.20,'폐렴','bok','asd');

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `patient_id` int(10) unsigned NOT NULL,
  `rn` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rh` text NOT NULL,
  `doctor_id` int(10) unsigned NOT NULL,
  `name` char(100) NOT NULL,
  `dpt_id` int(10) unsigned NOT NULL,
  `doctor_time` time DEFAULT NULL,
  `regDate` datetime NOT NULL,
  PRIMARY KEY (`rn`),
  UNIQUE KEY `patient_id` (`patient_id`),
  KEY `fk_doctor_id` (`doctor_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `reservation` */

insert  into `reservation`(`patient_id`,`rn`,`rh`,`doctor_id`,`name`,`dpt_id`,`doctor_time`,`regDate`) values 
(5,14,'다리 골절',1,'김민섭',5,'14:10:00','2024-04-11 17:11:48'),
(1,15,'가슴이 답답해요',2,'홍길동',4,'18:10:00','2024-04-11 17:18:27'),
(3,18,'발목 부상',1,'박지성',2,'09:10:00','2024-04-11 22:31:52');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
