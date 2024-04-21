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
  `loginId` varchar(100) NOT NULL,
  `loginPw` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `regDate` date NOT NULL DEFAULT curdate(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `admin` */

insert  into `admin`(`id`,`loginId`,`loginPw`,`name`,`regDate`) values 
(1,'admin','admin','관리자','2024-04-19'),
(2,'admin2','admin2','부관리자','2024-04-19');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `body` text NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  `boardId` int(10) unsigned NOT NULL,
  `hit` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `boardId` (`boardId`),
  CONSTRAINT `fk_boardId` FOREIGN KEY (`boardId`) REFERENCES `board` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `article` */

insert  into `article`(`id`,`regDate`,`updateDate`,`title`,`body`,`patient_id`,`boardId`,`hit`) values 
(1,'2024-04-17 01:32:51','2024-04-18 14:38:53','고지혈증에 좋은 음식','아보카도, 곡물, 등푸른 생선, 마늘, 올리브 오일',1,1,9),
(6,'2024-04-18 01:23:37','2024-04-18 01:23:37','칭찬합니다','의사선생님이 친절하게 설명해주셨습니다.',3,6,2),
(9,'2024-04-18 14:36:39','2024-04-18 14:36:39','콜레스테롤 낮추는 음식','와인, 녹차, 아몬드, 연어, 브로콜리, 콩류',1,1,3),
(10,'2024-04-18 14:42:13','2024-04-18 14:42:13','자원봉사자를 모집합니다.','병원 자원 봉사자를 희망하시는 분들은 042-1234-5678로 문의 주세요',0,2,0),
(11,'2024-04-18 16:50:47','2024-04-18 16:52:34','눈에 좋은 음식','당근, 시금치, 생선, 계란, 오렌지, 아몬드',2,1,10),
(14,'2024-04-19 16:22:51','2024-04-19 16:22:51','개인정보처리방침 공고','변경 사항 : 개인정보보호법, 정보통신망법 개정',0,2,0);

/*Table structure for table `articleReply` */

DROP TABLE IF EXISTS `articleReply`;

CREATE TABLE `articleReply` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `body` text NOT NULL,
  `patient_id` int(10) unsigned DEFAULT NULL,
  `doctor_id` int(10) unsigned DEFAULT NULL,
  `admin_id` int(10) unsigned DEFAULT NULL,
  `articleId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `articleId` (`articleId`),
  KEY `patient_id` (`patient_id`),
  KEY `doctor_id` (`doctor_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `articlereply_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
  CONSTRAINT `articlereply_ibfk_2` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE,
  CONSTRAINT `articlereply_ibfk_3` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE,
  CONSTRAINT `articlereply_ibfk_4` FOREIGN KEY (`articleId`) REFERENCES `article` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `articleReply` */

insert  into `articleReply`(`id`,`regDate`,`updateDate`,`body`,`patient_id`,`doctor_id`,`admin_id`,`articleId`) values 
(2,'2024-04-19 16:36:42','2024-04-19 16:36:42','좋은 정보입니다.',NULL,NULL,1,1),
(7,'2024-04-19 17:54:07','2024-04-19 17:54:07','정보 감사합니다.',NULL,NULL,1,1),
(8,'2024-04-19 18:13:10','2024-04-19 18:13:10','아보카도 추가합니다',1,NULL,NULL,9),
(9,'2024-04-19 18:15:49','2024-04-19 18:15:49','블루베리도 추천합니다',NULL,1,NULL,11),
(10,'2024-04-19 18:28:03','2024-04-19 18:28:03','정보 감사합니다',1,NULL,NULL,11),
(11,'2024-04-19 18:33:36','2024-04-19 18:33:36','좋은 글이네요^^',NULL,NULL,1,11);

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `code` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `board` */

insert  into `board`(`id`,`regDate`,`updateDate`,`code`,`name`) values 
(1,'2024-04-17 00:18:28','2024-04-17 00:18:28','free','자유'),
(2,'2024-04-17 00:18:28','2024-04-17 00:18:28','notice','공지'),
(3,'2024-04-17 00:18:28','2024-04-17 00:18:28','HEP','간담췌외과'),
(4,'2024-04-17 00:18:28','2024-04-17 00:18:28','NEU','신경외과'),
(5,'2024-04-17 00:18:28','2024-04-17 00:18:28','OBG','산부인과'),
(6,'2024-04-17 00:18:28','2024-04-17 00:18:28','THO','흉부외과'),
(7,'2024-04-17 00:18:28','2024-04-17 00:18:28','PED','소아외과');

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(100) NOT NULL,
  `dpt_id` int(10) unsigned NOT NULL,
  `loginPw` char(100) NOT NULL,
  `regDate` date NOT NULL DEFAULT curdate(),
  PRIMARY KEY (`id`),
  KEY `dpt_id` (`dpt_id`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`dpt_id`) REFERENCES `dpt` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `doctor` */

insert  into `doctor`(`id`,`name`,`dpt_id`,`loginPw`,`regDate`) values 
(1,'이익준',1,'jo','2024-04-19'),
(2,'장겨울',1,'jang','2024-04-19'),
(3,'채송화',2,'chae','2024-04-19'),
(4,'용석민',2,'yong','2024-04-19'),
(5,'양석형',3,'yang','2024-04-19'),
(6,'추민하',3,'choo','2024-04-19'),
(7,'김준완',4,'kim','2024-04-19'),
(8,'도재학',4,'do','2024-04-19'),
(9,'안정원',5,'ahn','2024-04-19'),
(10,'한현희',5,'han','2024-04-19');

/*Table structure for table `doctor_time` */

DROP TABLE IF EXISTS `doctor_time`;

CREATE TABLE `doctor_time` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `doctor_id` int(10) unsigned NOT NULL,
  `TIME` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `doctor_time_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `doctor_time` */

insert  into `doctor_time`(`id`,`doctor_id`,`TIME`) values 
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `dpt` */

insert  into `dpt`(`id`,`name`,`phone`) values 
(1,'간담췌외과','042-220-8840'),
(2,'신경외과','042-1899-6075'),
(3,'산부인과','042-586-0912'),
(4,'흉부외과','042-541-0586'),
(5,'소아외과','042-586-7676');

/*Table structure for table `patient` */

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `regDate` date NOT NULL,
  `age` int(11) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `rrn` char(100) NOT NULL,
  `height` decimal(6,2) DEFAULT NULL,
  `weight` decimal(6,2) DEFAULT NULL,
  `ud` varchar(100) NOT NULL,
  `loginId` varchar(100) NOT NULL,
  `loginPw` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `patient` */

insert  into `patient`(`id`,`name`,`regDate`,`age`,`phone`,`rrn`,`height`,`weight`,`ud`,`loginId`,`loginPw`) values 
(1,'대한','2024-04-17',26,'010-4809-7610','991217-1407412',174.50,62.20,'폐렴','bok','asd'),
(2,'홍길동','2024-04-17',26,'010-1234-5678','991825-1507863',174.20,70.00,'폐렴','hong','gildong'),
(3,'박효신','2024-04-17',42,'010-5678-1234','810901-1408215',178.00,78.20,'인후두염','parkhyo','shin'),
(4,'박지성','2024-04-17',40,'010-1842-7640','850925-1104238',177.90,73.00,'골절','park','jisung'),
(5,'이우주','2024-04-17',7,'010-8820-7610','181217-1409635',120.50,23.50,'없음','lee','woojoo');

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `patient_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rh` text NOT NULL,
  `doctor_id` int(10) unsigned NOT NULL,
  `name` char(100) NOT NULL,
  `dpt_id` int(10) unsigned NOT NULL,
  `doctor_time` time DEFAULT NULL,
  `regDate` datetime NOT NULL,
  `doctor_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_doctor_id` (`doctor_id`),
  KEY `fk_patient_id` (`patient_id`),
  KEY `fk_dpt_id` (`dpt_id`),
  CONSTRAINT `fk_dpt_id` FOREIGN KEY (`dpt_id`) REFERENCES `dpt` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `reservation` */

insert  into `reservation`(`patient_id`,`id`,`rh`,`doctor_id`,`name`,`dpt_id`,`doctor_time`,`regDate`,`doctor_date`) values 
(1,10,'흉부 통증',1,'대한',1,'09:10:00','2024-04-21 22:55:42','2024-04-20'),
(2,11,'두통',3,'홍길동',2,'10:10:00','2024-04-21 22:55:42','2024-04-21'),
(3,12,'발목이 아파요',5,'박효신',3,'11:10:00','2024-04-21 22:55:42','2024-04-22'),
(4,13,'열이 나요',7,'박지성',4,'13:10:00','2024-04-21 22:55:42','2024-04-23');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
