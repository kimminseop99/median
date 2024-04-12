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
drop table dpt;

CREATE TABLE doctor(
  id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` CHAR(100) NOT NULL,
  dpt_id INT(10) UNSIGNED NOT NULL,
  loginPw CHAR(100) NOT NULL,
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
  time TIME NOT NULL,
  FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

INSERT INTO doctor_time (doctor_id, time) VALUES
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


select * from doctor_time;





CREATE TABLE patient(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
`name` CHAR(100) NOT NULL,
regDate DATE NOT NULL,
age INT(10) NOT NULL,
phone CHAR(100) NOT NULL,
rrn CHAR(100) NOT NULL,
height DECIMAL(6,2),
weight DECIMAL(6,2),
ud CHAR(100) NOT NULL,
loginId CHAR(100) NOT NULL UNIQUE,
loginPw CHAR(100) NOT NULL,
);


INSERT INTO patient
 SET `name` = '김민섭',
regDate = NOW(),
age = '26',
phone = '010-4809-7610',
rrn = '991217-1407412',
height = '174.500000',
weight = '62.200000',
ud = '폐렴',

loginId = 'bok',
loginPw = 'asd';

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
DROP TABLE patient;

CREATE TABLE `case`(
id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
regDate DATE NOT NULL,
doctor_id INT(10) UNSIGNED NOT NULL,
patient_id INT(10) UNSIGNED NOT NULL,
medicalHistory TEXT NOT NULL,
PRIMARY KEY(id, doctor_id, patient_id),
FOREIGN KEY(doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
FOREIGN KEY(patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

INSERT INTO `case`
SET doctor_id = 1,
patient_id = 1,
medicalHistory = '운동 열심히 하세요!!';

INSERT INTO `case`
SET doctor_id = 2,
patient_id = 2,
medicalHistory = '운동 열심히 하세요!!';

INSERT INTO `case`
SET doctor_id = 2,
patient_id = 3,
medicalHistory = '식단 관리가 필요합니다.';

INSERT INTO `case`
SET doctor_id = 5,
patient_id = 4,
medicalHistory = '조심히 다니세요';


SELECT * FROM `case`;
DROP TABLE `case`;

CREATE TABLE medicalHistory (
id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
medicalHistory TEXT NOT NULL,
patient_id INT(10) UNSIGNED NOT NULL,
FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
UNIQUE KEY (medicalHistory, patient_id)
);

INSERT INTO medicalHistory
SET
patient_id = 1,
medicalHistory = '운동 열심히 하세요!!';

INSERT INTO medicalHistory
SET
patient_id = 2,
medicalHistory = '운동 열심히 하세요!!';

INSERT INTO medicalHistory
SET
patient_id = 3,
medicalHistory = '식단 관리가 필요합니다.';

INSERT INTO medicalHistory
SET
patient_id = 4,
medicalHistory = '조심히 다니세요';


SELECT * FROM medicalHistory
DROP TABLE medicalHistory

CREATE TABLE `admin`(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
loginId CHAR(100) NOT NULL UNIQUE,
loginPw CHAR(100) NOT NULL,
`name` CHAR(100) NOT NULL
);

INSERT INTO `admin`
SET loginId = 'admin',
loginPw = 'admin',
`name` = '김관리';



SELECT * FROM `admin`

CREATE TABLE reservation (
    patient_id INT(10) UNSIGNED NOT NULL,
    rn INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    regDate DATE NOT NULL,
    rh TEXT NOT NULL,
    doctor_id INT(10) UNSIGNED NOT NULL,
    PRIMARY KEY (rn),

    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

ALTER TABLE reservation ADD COLUMN regDate datetime not null;
alter table reservation drop column regDate;
ALTER TABLE reservation ADD COLUMN `name` CHAR(100) NOT NULL;
ALTER TABLE reservation ADD COLUMN dpt_id INT(10) UNSIGNED NOT NULL;


INSERT INTO reservation ( rh, `name`)
VALUES ( '가슴이 아파요',  '홍길동');

select * from reservation
where `time` = 1;


SELECT * FROM reservation;
DROP TABLE article;

ALTER TABLE reservation
CHANGE COLUMN `time` doctor_time TIME;



CREATE TABLE article (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` TEXT NOT NULL,
	patient_id INT(10) UNSIGNED NOT NULL,
	doctor_id INT(10) UNSIGNED NOT NULL,
	hit INT(10) UNSIGNED NOT NULL,
	INDEX doctor_id(`doctor_id`),
	FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE
);





INSERT INTO article
SET regDate = NOW(),
title = '신고합니다',
`body` = '돌팔이에요',
patient_id = 1,
doctor_id = 3,
hit = 15;

INSERT INTO article
SET regDate = NOW(),
title = '칭찬합니다',
`body` = '친절해요',
patient_id = 2,
doctor_id = 4,
hit = 1;

SELECT * FROM article;

 articleReply;

CREATE TABLE articleReply (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	`body` CHAR(100) NOT NULL,
	patient_id INT(10) UNSIGNED NOT NULL,
	article_id INT(10) UNSIGNED NOT NULL,
	INDEX article_id(`article_id`),
	FOREIGN KEY (article_id) REFERENCES article(id) ON DELETE CASCADE
);

SELECT * FROM articleReply;

INSERT INTO articleReply
SET regDate = NOW(),
`body` = '댓글1',
patient_id = 1,
article_id = 2;

select * from reservation;
select * from doctor;

