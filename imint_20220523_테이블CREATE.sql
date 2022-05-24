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


-- imint_back 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `imint_back` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `imint_back`;

-- 테이블 imint_back.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '관리자아이디',
  `admin_pw` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '관리자비밀번호',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='관리자';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.block 구조 내보내기
CREATE TABLE IF NOT EXISTS `block` (
  `block_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '차단ID',
  `mb_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `mb_id2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '차단회원ID',
  `block_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '차단일자',
  `block_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '차단 취소 여부',
  PRIMARY KEY (`block_id`),
  KEY `FK_block_member` (`mb_id`),
  KEY `FK_block_member_2` (`mb_id2`),
  CONSTRAINT `block_FK` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `block_FK_1` FOREIGN KEY (`mb_id2`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='차단';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.chatroom 구조 내보내기
CREATE TABLE IF NOT EXISTS `chatroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '채팅방 ID',
  `goods_id` int(11) NOT NULL COMMENT '상품 ID',
  `buyer_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '구매희망자 ID',
  `resrv_date` datetime DEFAULT NULL COMMENT '상품 예약일시',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '채팅방 생성일시',
  `isdelete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '채팅방 삭제여부',
  PRIMARY KEY (`id`),
  KEY `chatroom_FK` (`goods_id`),
  KEY `chatroom_FK_1` (`buyer_id`),
  CONSTRAINT `chatroom_FK` FOREIGN KEY (`buyer_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chatroom_FK_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='채팅방';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.chat_messages 구조 내보내기
CREATE TABLE IF NOT EXISTS `chat_messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '채팅 메세지 ID',
  `chatroom_id` int(11) NOT NULL COMMENT '채팅방 ID',
  `sender_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '채팅 메세지 전송 회원 ID',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '채팅 메세지 내용',
  `is_read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '채팅 메세지 읽음여부',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '채팅 메세지 삭제여부',
  `send_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '채팅 메세지 전송일시',
  PRIMARY KEY (`id`),
  KEY `chat_messages_FK` (`chatroom_id`),
  KEY `chat_messages_FK_1` (`sender_id`),
  CONSTRAINT `chat_messages_FK` FOREIGN KEY (`chatroom_id`) REFERENCES `chatroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chat_messages_FK_1` FOREIGN KEY (`sender_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=396 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='채팅 메세지';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.goods 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '상품ID',
  `seller_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '판매자ID',
  `seller_nick` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '닉네임',
  `goods_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '글제목',
  `goods_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci COMMENT '글내용',
  `goods_price` bigint(20) NOT NULL COMMENT '가격',
  `goods_category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL DEFAULT '기타' COMMENT '카테고리',
  `goods_suggestible` tinyint(1) NOT NULL DEFAULT '0' COMMENT '흥정가능여부',
  `goods_location` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '거래지역',
  `goods_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '판매등록일자',
  `goods_status` enum('wait','resrv','comp') CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT 'wait' COMMENT '거래상태',
  `goods_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '상품 삭제 여부',
  PRIMARY KEY (`goods_id`),
  KEY `FK_goods_member` (`seller_id`,`seller_nick`),
  KEY `goods_FK_1` (`seller_nick`),
  CONSTRAINT `goods_FK` FOREIGN KEY (`seller_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goods_FK_1` FOREIGN KEY (`seller_nick`) REFERENCES `member` (`mb_nick`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='상품';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.goods_images 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods_images` (
  `goods_images_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '상품이미지ID',
  `goods_id` int(11) NOT NULL COMMENT '상품ID',
  `goods_images_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL DEFAULT '/static/images/noimage.png' COMMENT '이미지파일경로',
  `goods_images_thumbnail` tinyint(1) NOT NULL DEFAULT '0' COMMENT '대표이미지여부',
  `goods_images_originname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL DEFAULT 'noimage.png' COMMENT '파일원본이름',
  `goods_images_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '이미지 삭제여부',
  PRIMARY KEY (`goods_images_id`),
  KEY `FK_goods_images_goods` (`goods_id`),
  CONSTRAINT `goods_images_FK` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=453 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='상품 사진';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `mb_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '등록순서',
  `mb_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `mb_provider` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT 'SNS사이트',
  `mb_guard` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '보호자',
  `mb_nick` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '닉네임',
  `mb_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '이메일',
  `mb_join_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일자',
  `mb_interest` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '관심사',
  `mb_location` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '내 동네',
  `mb_ratings_total` int(11) DEFAULT '10' COMMENT '평가점수',
  `mb_pin` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '아이등록인증PIN',
  `mb_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '프로필사진',
  `mb_isdelete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '탈퇴여부',
  `mb_role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '멤버권한',
  PRIMARY KEY (`mb_id`),
  UNIQUE KEY `member_UN` (`mb_no`),
  UNIQUE KEY `member_UN_1` (`mb_nick`),
  KEY `mb_guard` (`mb_guard`),
  CONSTRAINT `member_FK` FOREIGN KEY (`mb_guard`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='회원';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.notification 구조 내보내기
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '알림 ID',
  `category` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '알림 분류',
  `mb_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '알림 수신 회원 ID',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '알림 내용',
  `is_read` tinyint(4) NOT NULL DEFAULT '0' COMMENT '알림 읽음여부',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '알림 삭제여부',
  `send_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '알림 전송일시',
  PRIMARY KEY (`id`),
  KEY `notification_FK` (`mb_id`),
  CONSTRAINT `notification_FK` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='알림 메세지';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.rating 구조 내보내기
CREATE TABLE IF NOT EXISTS `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '평가ID',
  `trx_id` int(11) NOT NULL COMMENT '거래ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `rating_score` int(11) NOT NULL COMMENT '평가점수',
  `rating_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '평가등록일자',
  PRIMARY KEY (`rating_id`),
  KEY `FK_rating_transaction` (`trx_id`),
  KEY `FK_rating_member` (`mb_id`),
  CONSTRAINT `rating_FK` FOREIGN KEY (`trx_id`) REFERENCES `transaction` (`trx_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rating_FK_1` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='거래평가';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 프로시저 imint_back.test_data 구조 내보내기
DELIMITER //
CREATE PROCEDURE `test_data`()
BEGIN
	DECLARE i INT DEFAULT 4;
	
	while(i < 40) DO 
	 INSERT INTO `imint`.`member` (`mb_id`, `mb_provider`, `mb_guard`, `mb_nick`, `mb_email`, `mb_join_date`, `mb_interest`, `mb_location`, `mb_thumbnail`, `mb_role`) 
	 VALUES (i, 'goole', 'naver_BkthPZQVXx5zyZCwb2-G4kUDdwN4ve_4iuHnCiauZ8U', i, 'dbs3346@gmail.com', '2022-04-28 15:49:03', '문구', '한국', '/iMintImage/member/CHILD/goole/google_102592663151810141035.png', 'CHILD');
	  
	   SET i=i+1;
	END while;
END//
DELIMITER ;

-- 테이블 imint_back.transaction 구조 내보내기
CREATE TABLE IF NOT EXISTS `transaction` (
  `trx_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '거래ID',
  `trx_isdelete` tinyint(1) DEFAULT '0' COMMENT '예약취소시간',
  `trx_complete_date` datetime DEFAULT NULL COMMENT '거래완료시간',
  `mb_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '구매자ID',
  `goods_id` int(11) NOT NULL COMMENT '상품ID',
  PRIMARY KEY (`trx_id`) USING BTREE,
  KEY `transaction_FK` (`mb_id`),
  KEY `transaction_FK_1` (`goods_id`),
  CONSTRAINT `transaction_FK` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaction_FK_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='거래';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 imint_back.wishlist 구조 내보내기
CREATE TABLE IF NOT EXISTS `wishlist` (
  `wishlist_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '관심ID',
  `mb_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `goods_id` int(11) NOT NULL COMMENT '상품ID',
  `wishlist_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '관심등록일자',
  `wishlist_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '관심철회여부',
  PRIMARY KEY (`wishlist_id`),
  KEY `FK_wishlist_member` (`mb_id`),
  KEY `FK_wishlist_goods` (`goods_id`),
  CONSTRAINT `wishlist_FK` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `wishlist_FK_1` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='관심';

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
