CREATE DATABASE IF NOT EXISTS RRS_DB;
USE RRS_DB;

CREATE TABLE `USERS` (
	`USER_ID` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '유저ID 기본키',
	`NAME` VARCHAR(50) NOT NULL COMMENT '유저 이름',
	`USERNAME` VARCHAR(50) UNIQUE NOT NULL COMMENT '유저 로그인 아이디(중복안됨)',
	`PASSWORD` VARCHAR(255) NOT NULL COMMENT '유저 로그인 비밀번호',
	`NICKNAME` VARCHAR(50) UNIQUE NOT NULL COMMENT '유저 닉네임(중복안됨)',
	`PHONE` VARCHAR(20) UNIQUE NOT NULL COMMENT '휴대폰 번호(중복안됨)',
	`ADDRESS` VARCHAR(255) NOT NULL COMMENT '주소',
	`ADDRESS_DETAIL` VARCHAR(255) NOT NULL COMMENT '주소 상세',
	`EMAIL` VARCHAR(255) UNIQUE NOT NULL COMMENT '유저 이메일 (중복안됨)',
	`PROFILE_IMAGE_URL` VARCHAR(255) COMMENT '프로필 사진 주소',
    `ROLES` VARCHAR(255) NOT NULL COMMENT 'ROLE_USER, ROLE_PROVIDER',
    `JOIN_PATH` VARCHAR(5) NOT NULL COMMENT '가입 경로 (HOME, KAKAO, NAVER)',
    `SNS_ID` VARCHAR(255) DEFAULT NULL COMMENT 'OAUTH2 사용자 아이디',
    `PROVIDER_INTRODUCTION` TEXT
);

CREATE TABLE `AVAILABLE_DATE_OF_WEEK` (
	`AVAILABLE_DATE_OF_WEEK_ID` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '댕시터 근무일',
    `PROVIDER_ID` BIGINT NOT NULL COMMENT 'USER_ID /  ROLE_PROVIDER',
    `AVAILABLE_DATE` DATE NOT NULL COMMENT '댕시터가 가능한 날짜',
    FOREIGN KEY (`PROVIDER_ID`) REFERENCES `USERS` (`USER_ID`)
);

CREATE TABLE `RESERVATIONS` (
	`RESERVATION_ID` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '예약목록ID 기본키',
	`USER_ID` BIGINT NOT NULL COMMENT '유저ID 기본키',
	`PROVIDER_ID` BIGINT NOT NULL COMMENT 'USER_ID /  ROLE_PROVIDER',
	`RESERVATION_START_DATE` DATE NOT NULL COMMENT '이용 시작일',
	`RESERVATION_END_DATE` DATE NOT NULL COMMENT '이용 종료일',
	`RESERVATION_STATUS` ENUM ('PENDING', 'IN_PROGRESS' , 'REJECTED', 'CANCELLED', 'COMPLETED') DEFAULT 'PENDING' ,
    `RESERVATION_MEMO` TEXT COMMENT '예약 전달 메모',
	FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`USER_ID`),
	FOREIGN KEY (`PROVIDER_ID`) REFERENCES `USERS` (`USER_ID`)
);
# 예약을 할 때마다 하나씩 데이터가 생성이됨
# 1/1~1/3 >> 1/1, 1/2, 1/3 데이터가 다 만들어져서 유저랑 연결됨
# 시작날짜부터 끝날짜의 리스트를 만들어서 찾아감 (고객은 첫날이랑 끝날만 선택하니까 그 범위에 있는 날짜를 다 가져옴)