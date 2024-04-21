DROP DATABASE IF EXISTS `median`;
CREATE DATABASE `median`;
USE `median`;

SHOW DATABASES;

CREATE TABLE dpt(
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` CHAR(100) NOT NULL,
  phone CHAR(100) NOT NULL
);

INSERT INTO dpt
SET `name` = '공용',
phone = '0';

INSERT INTO dpt
SET `name` = '간담췌외과',
phone = '042-220-8840';

INSERT INTO dpt
SET `name` = '신경외과',
phone = '042-1899-6075';

INSERT INTO dpt
SET `name` = '산부인과',
phone = '042-586-0912';

INSERT INTO dpt
SET `name` = '흉부외과',
phone = '042-541-0586';

INSERT INTO dpt
SET `name` = '소아외과',
phone = '042-586-7676';


SELECT * FROM dpt;
DROP TABLE dpt;

CREATE TABLE doctor(
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` CHAR(100) NOT NULL,
  dpt_id INT(10) UNSIGNED NOT NULL,
  loginPw CHAR(100) NOT NULL,
  regDate DATE NOT NULL,
  FOREIGN KEY(dpt_id) REFERENCES dpt(id)
);



INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('공용', 0, 'admin');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('이익준', 1, 'jo');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('장겨울', 1, 'jang');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('채송화', 2, 'chae');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('용석민', 2, 'yong');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('양석형', 3, 'yang');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('추민하', 3, 'choo');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('김준완', 4, 'kim');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('도재학', 4, 'do');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('안정원', 5, 'ahn');

INSERT INTO doctor (`name`, dpt_id, loginPw)
VALUES ('한현희', 5, 'han');

SELECT * FROM doctor;




CREATE TABLE doctor_time (
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  doctor_id INT(10) UNSIGNED NOT NULL,
  TIME TIME NOT NULL,
  FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

INSERT INTO doctor_time (doctor_id, TIME) VALUES
(1, '09:10'),(1, '10:10'),(1, '11:10'),(1, '13:10'),(1, '14:10'),(1, '15:10'),(1, '16:10'),(1, '17:10'),(1, '18:10'),
(2, '09:10'),(2, '10:10'),(2, '11:10'),(2, '13:10'),(2, '14:10'),(2, '15:10'),(2, '16:10'),(2, '17:10'),(2, '18:10'),
(3, '09:10'),(3, '10:10'),(3, '11:10'),(3, '13:10'),(3, '14:10'),(3, '15:10'),(3, '16:10'),(3, '17:10'),(3, '18:10'),
(4, '09:10'),(4, '10:10'),(4, '11:10'),(4, '13:10'),(4, '14:10'),(4, '15:10'),(4, '16:10'),(4, '17:10'),(4, '18:10'),
(5, '09:10'),(5, '10:10'),(5, '11:10'),(5, '13:10'),(5, '14:10'),(5, '15:10'),(5, '16:10'),(5, '17:10'),(5, '18:10'),
(6, '09:10'),(6, '10:10'),(6, '11:10'),(6, '13:10'),(6, '14:10'),(6, '15:10'),(6, '16:10'),(6, '17:10'),(6, '18:10'),
(7, '09:10'),(7, '10:10'),(7, '11:10'),(7, '13:10'),(7, '14:10'),(7, '15:10'),(7, '16:10'),(7, '17:10'),(7, '18:10'),
(8, '09:10'),(8, '10:10'),(8, '11:10'),(8, '13:10'),(8, '14:10'),(8, '15:10'),(8, '16:10'),(8, '17:10'),(8, '18:10'),
(9, '09:10'),(9, '10:10'),(9, '11:10'),(9, '13:10'),(9, '14:10'),(9, '15:10'),(9, '16:10'),(9, '17:10'),(9, '18:10'),
(10, '09:10'),(10, '10:10'),(10, '11:10'),(10, '13:10'),(10, '14:10'),(10, '15:10'),(10, '16:10'),(10, '17:10'),(10, '18:10');


SELECT * FROM doctor_time;


CREATE TABLE patient(
 id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    regDate DATE NOT NULL,
    age INT NOT NULL,
    phone VARCHAR(100) NOT NULL,
    rrn CHAR(100) NOT NULL,
    height DECIMAL(6,2),
    weight DECIMAL(6,2),
    ud VARCHAR(100) NOT NULL,
    loginId VARCHAR(100) NOT NULL UNIQUE,
    loginPw VARCHAR(100) NOT NULL
);

INSERT INTO patient
SET `name` = '홍길동',
regDate = NOW(),
age = 26,
phone = '010-1234-5678',
rrn = '991825-1507863',
height = 174.2, weight = 70,
ud = '폐렴',
loginId = 'hong', loginPw = 'gildong';

INSERT INTO patient
SET `name` = '홍길순',
regDate = NOW(),
age = 24,
phone = '010-5678-1234',
rrn = '010725-2504835',
height = 162.2, weight = 50,
ud = '없음',
loginId = 'hongg', loginPw = 'ilsoon';

INSERT INTO patient
SET `name` = '박지성',
regDate = NOW(),
age = 40,
phone = '010-1842-7640',
rrn = '850925-1104238',
height = 177.9, weight = 73,
ud = '골절',
loginId = 'park', loginPw = 'jisung';

INSERT INTO patient
SET `name` = '이우주',
regDate = NOW(),
age = 7,
phone = '010-8820-7610',
rrn = '181217-1409635',
height = 120.5, weight = 23.5,
ud = '없음',
loginId = 'lee', loginPw = 'woojoo';



SELECT * FROM patient;




CREATE TABLE `admin`(
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    loginId VARCHAR(100) NOT NULL UNIQUE,
    loginPw VARCHAR(255) NOT NULL,
    `name` varchar(100) not null,
    regDate DATE NOT NULL
);



INSERT INTO `admin`
SET
loginId = 'admin',
loginPw = 'admin',
`name` = '김관리';


SELECT * FROM `admin`;





CREATE TABLE reservation (
  `patient_id` INT(10) UNSIGNED NOT NULL,
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `rh` TEXT NOT NULL,
  `doctor_id` INT(10) UNSIGNED NOT NULL,
  `name` CHAR(100) NOT NULL,
  `dpt_id` INT(10) UNSIGNED NOT NULL,
  `doctor_time` TIME DEFAULT NULL,
  `regDate` DATETIME NOT NULL,
  `doctor_date` DATE,
  PRIMARY KEY (`id`),
  KEY `fk_doctor_id` (`doctor_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE,
  CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
  CONSTRAINT fk_dpt_id FOREIGN KEY (dpt_id) REFERENCES dpt(id) ON DELETE CASCADE
);

INSERT INTO reservation (patient_id, rh, doctor_id, `name`, dpt_id, doctor_time, regDate, doctor_date)
VALUES
(1, '흉부 통증', 1, '대한', 1, '09:10', NOW(), '2024-04-20'),
(2, '두통', 3, '홍길동', 2, '10:10', NOW(), '2024-04-21'),
(3, '발목이 아파요', 5, '박효신', 3, '11:10', NOW(), '2024-04-22'),
(4, '열이 나요', 7, '박지성', 4, '13:10', NOW(), '2024-04-23');


SELECT * FROM reservation;
DROP TABLE article;


CREATE TABLE article (
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title CHAR(100) NOT NULL,
`body` TEXT NOT NULL,
patient_id INT(10) UNSIGNED NOT NULL,
boardId INT(10) UNSIGNED NOT NULL,
INDEX boardId(`boardId`),
hit INT(10) UNSIGNED NOT NULL,
CONSTRAINT fk_boardId FOREIGN KEY (boardId) REFERENCES board(id) ON DELETE CASCADE
);



INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1',
patient_id = 1,
boardId = 1,
hit = 1;;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
patient_id = 2,
boardId = 1,
hit = 3;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
patient_id = 2,
boardId = 2,
hit = 4;

SELECT * FROM article;



CREATE TABLE articleReply (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `body` TEXT NOT NULL,
    patient_id INT(10) UNSIGNED ,
    doctor_id INT(10) UNSIGNED ,
    admin_id INT(10) UNSIGNED ,
    articleId INT(10) UNSIGNED NOT NULL,
    INDEX articleId(`articleId`),
    FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`articleId`) REFERENCES `article` (`id`) ON DELETE CASCADE
);


INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글1',
patient_id = 1,
articleId = 1;

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글2',
patient_id = 2,
articleId = 3;

INSERT INTO articleReply
 SET regDate = NOW(),
 updateDate = NOW(),
 `body` = '좋은 정보입니다.',
  admin_id = 1,
  articleId = 1;

SELECT * FROM articleReply;



CREATE TABLE board (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	`code` CHAR(100) NOT NULL UNIQUE, # free/notice
	`name` CHAR(100) NOT NULL # 자유/공지
);

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유';

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지';

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'HEP',
`name` = '간담췌외과';

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'NEU',
`name` = '신경외과';

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'OBG',
`name` = '산부인과';

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'THO',
`name` = '흉부외과';

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'PED',
`name` = '소아외과';

SELECT * FROM board;
SELECT * FROM reservation;
SELECT * FROM doctor;
SELECT * FROM patient;
SELECT * FROM article;
SELECT * FROM articleReply;
select * from `admin`;

