-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.17 - MySQL Community Server - GPL
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- babycarrot 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `babycarrot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `babycarrot`;

-- 테이블 babycarrot.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '관리자아이디',
  `admin_pw` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '관리자비밀번호',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.block 구조 내보내기
CREATE TABLE IF NOT EXISTS `block` (
  `block_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '차단ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `mb_id2` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '차단회원ID',
  `block_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '차단일자',
  `block_isdelete` tinyint(1) DEFAULT NULL COMMENT '차단 취소 여부',
  PRIMARY KEY (`block_id`,`mb_id`) USING BTREE,
  KEY `FK_block_member` (`mb_id`),
  KEY `FK_block_member_2` (`mb_id2`),
  CONSTRAINT `FK_block_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_block_member_2` FOREIGN KEY (`mb_id2`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.chat_message 구조 내보내기
CREATE TABLE IF NOT EXISTS `chat_message` (
  `chat_message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '채팅 메세지ID',
  `chat_room_id` int(11) NOT NULL COMMENT '채팅방ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '메세지 전송 회원ID',
  `chat_message_create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '메세지 전송 시간',
  `chat_message_content` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '채팅 메세지',
  `chat_message_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '채팅 메세지 삭제 여부',
  PRIMARY KEY (`chat_message_id`),
  KEY `FK_chat_message_chat_room` (`chat_room_id`),
  KEY `FK_chat_message_member` (`mb_id`),
  CONSTRAINT `FK_chat_message_chat_room` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`chat_room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_chat_message_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.chat_room 구조 내보내기
CREATE TABLE IF NOT EXISTS `chat_room` (
  `chat_room_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '채팅방ID',
  `goods_id` int(11) NOT NULL COMMENT '상품ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '구매희망회원ID',
  `chat_room_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '채팅시작시간',
  `chat_room_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '채팅방삭제여부',
  PRIMARY KEY (`chat_room_id`,`goods_id`) USING BTREE,
  KEY `FK_chat_room_goods` (`goods_id`),
  KEY `FK_chat_room_member` (`mb_id`),
  CONSTRAINT `FK_chat_room_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_chat_room_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.goods 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '상품ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '판매자ID',
  `goods_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '글제목',
  `goods_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci COMMENT '글내용',
  `goods_price` bigint(20) NOT NULL COMMENT '가격',
  `goods_category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL DEFAULT '기타' COMMENT '카테고리',
  `goods_suggestible` tinyint(1) NOT NULL DEFAULT '0' COMMENT '흥정가능여부',
  `goods_location` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '거래지역',
  `goods_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '판매등록일자',
  `goods_status` enum('wait','resrv','comp') CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '거래상태',
  `goods_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '상품 삭제 여부',
  PRIMARY KEY (`goods_id`,`mb_id`) USING BTREE,
  KEY `FK_goods_member` (`mb_id`),
  CONSTRAINT `FK_goods_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.goods_images 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods_images` (
  `goods_images_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '상품이미지ID',
  `goods_id` int(11) NOT NULL COMMENT '상품ID',
  `goods_images_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '이미지파일경로',
  PRIMARY KEY (`goods_images_id`,`goods_id`) USING BTREE,
  KEY `FK_goods_images_goods` (`goods_id`),
  CONSTRAINT `FK_goods_images_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `mb_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '등록순서',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `mb_guard` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '보호자',
  `mb_nick` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '닉네임',
  `mb_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '이메일',
  `mb_join_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일자',
  `mb_interest` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '관심사',
  `mb_location` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '내 동네',
  `mb_ratings_total` int(11) DEFAULT '25' COMMENT '평가점수',
  `mb_pin` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '아이등록인증PIN',
  `mb_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '프로필사진',
  `mb_isdelete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '탈퇴여부',
  PRIMARY KEY (`mb_id`) USING BTREE,
  UNIQUE KEY `mb_no` (`mb_no`),
  UNIQUE KEY `mb_email` (`mb_email`),
  UNIQUE KEY `mb_nick` (`mb_nick`),
  KEY `FK_member_member` (`mb_guard`),
  CONSTRAINT `FK_member_member` FOREIGN KEY (`mb_guard`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.rating 구조 내보내기
CREATE TABLE IF NOT EXISTS `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '평가ID',
  `trx_id` int(11) NOT NULL COMMENT '거래ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `rating_score` int(11) NOT NULL COMMENT '평가점수',
  `rating_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '평가등록일자',
  PRIMARY KEY (`rating_id`),
  KEY `FK_rating_transaction` (`trx_id`),
  KEY `FK_rating_member` (`mb_id`),
  CONSTRAINT `FK_rating_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rating_transaction` FOREIGN KEY (`trx_id`) REFERENCES `transaction` (`trx_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.transaction 구조 내보내기
CREATE TABLE IF NOT EXISTS `transaction` (
  `trx_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '거래ID',
  `chat_room_id` int(11) NOT NULL COMMENT '채팅방ID',
  `trx_resrv_date` datetime DEFAULT NULL COMMENT '예약완료시간',
  `trx_isdelete` tinyint(1) DEFAULT '0' COMMENT '예약취소시간',
  `trx_complete_date` datetime DEFAULT NULL COMMENT '거래완료시간',
  PRIMARY KEY (`trx_id`,`chat_room_id`) USING BTREE,
  KEY `FK_transaction_chat_room` (`chat_room_id`),
  CONSTRAINT `FK_transaction_chat_room` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`chat_room_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 babycarrot.wishlist 구조 내보내기
CREATE TABLE IF NOT EXISTS `wishlist` (
  `wishlist_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '관심ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `goods_id` int(11) NOT NULL COMMENT '상품ID',
  `wishlist_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '관심등록일자',
  `wishlist_withdraw` tinyint(1) NOT NULL DEFAULT '0' COMMENT '관심철회여부',
  PRIMARY KEY (`wishlist_id`),
  KEY `FK_wishlist_member` (`mb_id`),
  KEY `FK_wishlist_goods` (`goods_id`),
  CONSTRAINT `FK_wishlist_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_wishlist_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
