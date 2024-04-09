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
  `time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dpt_id` (`dpt_id`),
  CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`dpt_id`) REFERENCES `dpt` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `doctor` */

insert  into `doctor`(`id`,`name`,`dpt_id`,`loginPw`,`time`) values 
(0,'공용',0,'admin',0),
(1,'이익준',1,'jo',0),
(2,'채송화',2,'chae',0),
(3,'양석형',3,'yang',0),
(4,'김준완',4,'kim',0),
(5,'안정원',5,'ahn',0),
(6,'장겨울',1,'jang',0),
(7,'용석민',2,'yong',0),
(8,'추민하',3,'choo',0),
(9,'도재학',4,'do',0),
(10,'한현희',5,'han',0);

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
  `medicalHistory` text NOT NULL,
  `doctor_id` int(10) unsigned NOT NULL,
  `loginId` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `patient` */

insert  into `patient`(`id`,`name`,`regDate`,`age`,`phone`,`rrn`,`height`,`weight`,`ud`,`medicalHistory`,`doctor_id`,`loginId`,`loginPw`) values 
(1,'홍길동','2024-04-07',26,'010-1234-5678','991825-1507863',174.20,70.00,'폐렴','보류',1,'hong','gildong'),
(2,'홍길순','2024-04-07',24,'010-5678-1234','010725-2504835',162.20,50.00,'없음','보류',2,'hongg','ilsoon'),
(3,'박지성','2024-04-07',40,'010-1842-7640','850925-1104238',177.90,73.00,'골절','이상없음',2,'park','jisung'),
(4,'이우주','2024-04-07',7,'010-8820-7610','181217-1409635',120.50,23.50,'없음','보류',5,'lee','woojoo'),
(5,'김민섭','2024-04-08',26,'010-4809-7610','991217-1407412',174.50,62.20,'폐렴','null',0,'bok','asd');

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `patient_id` int(10) unsigned NOT NULL,
  `rn` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` date NOT NULL,
  `rh` text NOT NULL,
  `doctor_id` int(10) unsigned NOT NULL,
  `name` char(100) NOT NULL,
  `time` int(10) unsigned NOT NULL,
  PRIMARY KEY (`rn`),
  UNIQUE KEY `patient_id` (`patient_id`),
  UNIQUE KEY `time` (`time`),
  KEY `fk_doctor_id` (`doctor_id`),
  CONSTRAINT `fk_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `reservation` */

insert  into `reservation`(`patient_id`,`rn`,`regDate`,`rh`,`doctor_id`,`name`,`time`) values 
(1,1,'2024-05-01','가슴이 아파요',1,'홍길동',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
