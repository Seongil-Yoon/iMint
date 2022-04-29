-- --------------------------------------------------------
-- 호스트:                          27.96.131.101
-- 서버 버전:                        8.0.28 - MySQL Community Server - GPL
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- imint_copy 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `imint_copy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `imint_copy`;

-- 테이블 imint_copy.admin 구조 내보내기
CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '관리자아이디',
  `admin_pw` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '관리자비밀번호',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.admin:~0 rows (대략적) 내보내기
DELETE FROM `admin`;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 테이블 imint_copy.block 구조 내보내기
CREATE TABLE IF NOT EXISTS `block` (
  `block_id` int NOT NULL AUTO_INCREMENT COMMENT '차단ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `mb_id2` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '차단회원ID',
  `block_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '차단일자',
  `block_isdelete` tinyint(1) DEFAULT NULL COMMENT '차단 취소 여부',
  PRIMARY KEY (`block_id`,`mb_id`) USING BTREE,
  KEY `FK_block_member` (`mb_id`),
  KEY `FK_block_member_2` (`mb_id2`),
  CONSTRAINT `FK_block_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_block_member_2` FOREIGN KEY (`mb_id2`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.block:~0 rows (대략적) 내보내기
DELETE FROM `block`;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
/*!40000 ALTER TABLE `block` ENABLE KEYS */;

-- 테이블 imint_copy.chatroom 구조 내보내기
CREATE TABLE IF NOT EXISTS `chatroom` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '채팅방 ID',
  `goods_id` int NOT NULL COMMENT '상품 ID',
  `buyer_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '구매희망자 ID',
  `resrv_date` datetime DEFAULT NULL COMMENT '상품 예약일시',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '채팅방 생성일시',
  `isdelete` tinyint NOT NULL DEFAULT '0' COMMENT '채팅방 삭제여부',
  PRIMARY KEY (`id`),
  KEY `chatroom_FK` (`goods_id`),
  KEY `chatroom_FK_1` (`buyer_id`),
  CONSTRAINT `chatroom_FK` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chatroom_FK_1` FOREIGN KEY (`buyer_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='채팅방 테이블';

-- 테이블 데이터 imint_copy.chatroom:~161 rows (대략적) 내보내기
DELETE FROM `chatroom`;
/*!40000 ALTER TABLE `chatroom` DISABLE KEYS */;
INSERT INTO `chatroom` (`id`, `goods_id`, `buyer_id`, `resrv_date`, `create_date`, `isdelete`) VALUES
	(3, 12, 'google_child12', NULL, '2022-04-28 16:24:01', 0),
	(4, 13, 'kakao_guard1', NULL, '2022-04-28 16:35:26', 0),
	(5, 14, 'google_child19', NULL, '2022-04-28 17:05:17', 0),
	(6, 12, 'google_child2', NULL, '2022-04-28 17:10:20', 0),
	(7, 17, 'google_child12', '2022-04-29 03:34:53', '2022-04-28 17:12:08', 0),
	(8, 17, 'google_child19', NULL, '2022-04-28 17:12:11', 0),
	(9, 15, 'google_child12', NULL, '2022-04-28 17:13:34', 0),
	(10, 20, 'google_child12', NULL, '2022-04-28 17:14:58', 0),
	(11, 21, 'google_child12', NULL, '2022-04-28 17:19:07', 0),
	(12, 13, 'google_child12', NULL, '2022-04-28 17:19:29', 0),
	(13, 22, 'google_child12', NULL, '2022-04-28 17:20:06', 0),
	(14, 23, 'google_child12', NULL, '2022-04-28 17:20:43', 0),
	(15, 26, 'google_child12', NULL, '2022-04-28 17:23:41', 0),
	(16, 25, 'google_child12', NULL, '2022-04-28 17:23:54', 0),
	(17, 26, 'google_child19', NULL, '2022-04-28 17:23:56', 0),
	(18, 35, 'google_child12', NULL, '2022-04-28 17:28:08', 0),
	(19, 32, 'google_child12', NULL, '2022-04-28 17:28:10', 0),
	(20, 33, 'google_child12', NULL, '2022-04-28 17:28:19', 0),
	(21, 35, 'google_child19', NULL, '2022-04-28 17:28:21', 0),
	(22, 33, 'google_child19', NULL, '2022-04-28 17:28:39', 0),
	(23, 15, 'google_child19', NULL, '2022-04-28 17:28:47', 0),
	(24, 29, 'google_child19', NULL, '2022-04-28 17:30:52', 0),
	(25, 24, 'google_child19', NULL, '2022-04-28 17:31:05', 0),
	(26, 34, 'google_child2', NULL, '2022-04-28 17:36:12', 0),
	(27, 18, 'google_child2', NULL, '2022-04-28 17:36:26', 0),
	(28, 31, 'google_child2', NULL, '2022-04-28 17:36:54', 0),
	(29, 33, 'google_child2', NULL, '2022-04-28 17:37:11', 0),
	(30, 36, 'google_child19', '2022-04-28 17:43:30', '2022-04-28 17:42:00', 0),
	(31, 36, 'google_child2', NULL, '2022-04-28 17:42:05', 0),
	(32, 36, 'google_child12', NULL, '2022-04-28 17:42:13', 0),
	(33, 34, 'google_child12', NULL, '2022-04-28 17:43:46', 0),
	(34, 27, 'naver_admin1', NULL, '2022-04-28 18:44:08', 0),
	(35, 30, 'google_child8', NULL, '2022-04-28 18:55:24', 0),
	(36, 32, 'google_child19', NULL, '2022-04-29 01:31:01', 0),
	(37, 16, 'google_child12', NULL, '2022-04-29 01:33:48', 0),
	(38, 27, 'google_child2', NULL, '2022-04-29 01:54:17', 0),
	(39, 29, 'google_child12', NULL, '2022-04-29 02:51:42', 0),
	(40, 28, 'google_child12', NULL, '2022-04-29 03:10:08', 0),
	(41, 27, 'kakao_guard1', NULL, '2022-04-29 03:21:38', 0),
	(42, 34, 'google_child8', NULL, '2022-04-29 03:22:58', 0),
	(43, 33, 'google_child8', NULL, '2022-04-29 03:23:20', 0),
	(44, 33, 'kakao_guard5', NULL, '2022-04-29 03:47:25', 0),
	(45, 29, 'kakao_guard5', NULL, '2022-04-29 04:18:16', 0),
	(46, 20, 'kakao_guard5', NULL, '2022-04-29 04:18:24', 0),
	(47, 27, 'kakao_guard5', NULL, '2022-04-29 04:18:33', 0),
	(48, 16, 'kakao_guard5', NULL, '2022-04-29 04:18:58', 0),
	(49, 12, 'kakao_guard5', NULL, '2022-04-29 04:19:02', 0),
	(50, 13, 'kakao_guard5', NULL, '2022-04-29 04:19:13', 0),
	(51, 34, 'kakao_guard5', NULL, '2022-04-29 04:19:23', 0),
	(52, 26, 'kakao_guard5', NULL, '2022-04-29 04:19:38', 0),
	(53, 25, 'kakao_guard5', NULL, '2022-04-29 04:19:45', 0),
	(54, 23, 'kakao_guard5', NULL, '2022-04-29 04:24:05', 0),
	(55, 38, 'kakao_guard5', NULL, '2022-04-29 04:49:16', 0),
	(56, 38, 'google_child19', NULL, '2022-04-29 05:17:32', 0),
	(57, 31, 'kakao_guard5', NULL, '2022-04-29 11:37:35', 0),
	(58, 32, 'kakao_guard5', NULL, '2022-04-29 11:39:31', 0),
	(59, 21, 'kakao_guard5', NULL, '2022-04-29 12:14:16', 0),
	(60, 42, 'naver_NXOiWYXGfEMB1C3kxD5QoQVUFC3Co28UaxwLAU-i7YU', NULL, '2022-04-29 13:34:09', 0),
	(61, 41, 'google_child19', NULL, '2022-04-29 13:37:03', 0),
	(62, 28, 'google_child19', NULL, '2022-04-29 13:37:27', 0),
	(63, 45, 'kakao_guard5', NULL, '2022-04-29 14:23:39', 0),
	(64, 45, 'google_child12', NULL, '2022-04-29 14:59:24', 0),
	(65, 41, 'google_child12', NULL, '2022-04-29 15:04:02', 0),
	(66, 45, 'google_child19', NULL, '2022-04-29 15:32:05', 0),
	(67, 45, 'google_child9', '2022-04-29 16:03:14', '2022-04-29 15:59:20', 0),
	(68, 45, 'google_child22', NULL, '2022-04-29 16:00:15', 0),
	(69, 44, 'google_child22', NULL, '2022-04-29 16:00:36', 0),
	(70, 43, 'google_child22', NULL, '2022-04-29 16:00:38', 0),
	(71, 26, 'google_child22', NULL, '2022-04-29 16:01:04', 0),
	(72, 45, 'google_child10', NULL, '2022-04-29 16:01:11', 0),
	(73, 38, 'google_child10', NULL, '2022-04-29 16:01:23', 0),
	(74, 44, 'google_child20', NULL, '2022-04-29 16:02:05', 0),
	(75, 46, 'google_child19', NULL, '2022-04-29 16:02:18', 0),
	(76, 46, 'google_child2', NULL, '2022-04-29 16:02:31', 0),
	(77, 38, 'google_child11', NULL, '2022-04-29 16:03:01', 0),
	(78, 13, 'google_child11', NULL, '2022-04-29 16:03:18', 0),
	(79, 46, 'google_child14', NULL, '2022-04-29 16:03:46', 0),
	(80, 47, 'google_child9', NULL, '2022-04-29 16:03:48', 0),
	(81, 47, 'google_child19', NULL, '2022-04-29 16:04:19', 0),
	(82, 48, 'google_child14', NULL, '2022-04-29 16:04:24', 0),
	(83, 48, 'google_child1', NULL, '2022-04-29 16:04:39', 0),
	(84, 48, 'google_child2', '2022-04-29 16:06:59', '2022-04-29 16:05:28', 0),
	(85, 49, 'google_child14', NULL, '2022-04-29 16:05:30', 0),
	(86, 49, 'google_child9', NULL, '2022-04-29 16:05:41', 0),
	(87, 49, 'google_child2', NULL, '2022-04-29 16:05:41', 0),
	(88, 48, 'google_child9', NULL, '2022-04-29 16:05:43', 0),
	(89, 48, 'google_child19', NULL, '2022-04-29 16:05:52', 0),
	(90, 46, 'google_child16', NULL, '2022-04-29 16:05:52', 0),
	(91, 46, 'google_child10', NULL, '2022-04-29 16:06:08', 0),
	(92, 47, 'google_child10', NULL, '2022-04-29 16:06:11', 0),
	(93, 49, 'google_child10', NULL, '2022-04-29 16:06:16', 0),
	(94, 49, 'google_child21', NULL, '2022-04-29 16:06:32', 0),
	(95, 46, 'google_child21', NULL, '2022-04-29 16:06:36', 0),
	(96, 46, 'google_child22', NULL, '2022-04-29 16:06:50', 0),
	(97, 21, 'google_child14', NULL, '2022-04-29 16:06:57', 0),
	(98, 39, 'google_child14', NULL, '2022-04-29 16:07:00', 0),
	(99, 41, 'google_child14', NULL, '2022-04-29 16:07:27', 0),
	(100, 26, 'google_child14', NULL, '2022-04-29 16:07:41', 0),
	(101, 47, 'google_child11', NULL, '2022-04-29 16:08:21', 0),
	(102, 50, 'google_child19', NULL, '2022-04-29 16:09:03', 0),
	(103, 52, 'google_child19', NULL, '2022-04-29 16:10:36', 0),
	(104, 52, 'google_child14', NULL, '2022-04-29 16:10:39', 0),
	(105, 51, 'google_child14', NULL, '2022-04-29 16:10:41', 0),
	(106, 50, 'google_child22', NULL, '2022-04-29 16:10:43', 0),
	(107, 52, 'google_child9', NULL, '2022-04-29 16:10:49', 0),
	(108, 50, 'google_child14', NULL, '2022-04-29 16:10:52', 0),
	(109, 52, 'google_child11', NULL, '2022-04-29 16:11:09', 0),
	(110, 49, 'google_child6', NULL, '2022-04-29 16:11:12', 0),
	(111, 50, 'google_child11', NULL, '2022-04-29 16:11:14', 0),
	(112, 51, 'google_child11', NULL, '2022-04-29 16:11:17', 0),
	(113, 46, 'google_child11', NULL, '2022-04-29 16:12:11', 0),
	(114, 53, 'google_child22', NULL, '2022-04-29 16:12:17', 0),
	(115, 53, 'google_child11', NULL, '2022-04-29 16:12:18', 0),
	(116, 52, 'google_child1', NULL, '2022-04-29 16:12:21', 0),
	(117, 53, 'google_child19', NULL, '2022-04-29 16:12:23', 0),
	(118, 50, 'google_child16', NULL, '2022-04-29 16:13:06', 0),
	(119, 53, 'google_child1', NULL, '2022-04-29 16:13:09', 0),
	(120, 34, 'google_child1', NULL, '2022-04-29 16:13:21', 0),
	(121, 54, 'google_child1', NULL, '2022-04-29 16:13:27', 0),
	(122, 54, 'google_child19', NULL, '2022-04-29 16:13:29', 0),
	(123, 53, 'google_child16', NULL, '2022-04-29 16:14:06', 0),
	(124, 54, 'google_child9', NULL, '2022-04-29 16:14:23', 0),
	(125, 49, 'google_child16', NULL, '2022-04-29 16:14:54', 0),
	(126, 46, 'google_child3', NULL, '2022-04-29 16:14:58', 0),
	(127, 54, 'google_child3', NULL, '2022-04-29 16:15:09', 0),
	(128, 54, 'google_child11', NULL, '2022-04-29 16:15:14', 0),
	(129, 53, 'google_child3', NULL, '2022-04-29 16:15:19', 0),
	(130, 52, 'google_child3', NULL, '2022-04-29 16:15:28', 0),
	(131, 50, 'google_child3', NULL, '2022-04-29 16:15:32', 0),
	(132, 47, 'google_child3', NULL, '2022-04-29 16:15:46', 0),
	(133, 49, 'google_child3', NULL, '2022-04-29 16:15:52', 0),
	(134, 53, 'google_child21', NULL, '2022-04-29 16:16:02', 0),
	(135, 34, 'google_child3', NULL, '2022-04-29 16:16:08', 0),
	(136, 54, 'google_child21', NULL, '2022-04-29 16:16:09', 0),
	(137, 52, 'google_child21', NULL, '2022-04-29 16:16:13', 0),
	(138, 54, 'google_child16', NULL, '2022-04-29 16:16:38', 0),
	(139, 53, 'google_child6', NULL, '2022-04-29 16:16:46', 0),
	(140, 52, 'google_child6', NULL, '2022-04-29 16:17:14', 0),
	(141, 52, 'google_child16', NULL, '2022-04-29 16:17:18', 0),
	(142, 47, 'google_child6', NULL, '2022-04-29 16:17:22', 0),
	(143, 38, 'google_child6', NULL, '2022-04-29 16:17:28', 0),
	(144, 29, 'google_child16', NULL, '2022-04-29 16:18:58', 0),
	(145, 55, 'google_child23', NULL, '2022-04-29 16:19:30', 0),
	(146, 53, 'google_child14', NULL, '2022-04-29 16:19:46', 0),
	(147, 54, 'google_child2', NULL, '2022-04-29 16:19:52', 0),
	(148, 50, 'google_child17', NULL, '2022-04-29 16:20:43', 0),
	(149, 47, 'google_child1', NULL, '2022-04-29 16:22:55', 0),
	(150, 38, 'google_child17', NULL, '2022-04-29 16:23:14', 0),
	(151, 49, 'google_child17', NULL, '2022-04-29 16:24:58', 0),
	(152, 42, 'google_child1', NULL, '2022-04-29 16:25:04', 0),
	(153, 50, 'google_child1', NULL, '2022-04-29 16:26:02', 0),
	(154, 52, 'kakao_guard5', NULL, '2022-04-29 16:26:51', 0),
	(155, 53, 'kakao_guard5', NULL, '2022-04-29 16:26:53', 0),
	(156, 49, 'google_child1', NULL, '2022-04-29 16:27:07', 0),
	(157, 46, 'kakao_guard5', NULL, '2022-04-29 16:27:57', 0),
	(158, 53, 'google_child10', NULL, '2022-04-29 16:38:58', 0),
	(159, 44, 'google_child17', NULL, '2022-04-29 16:40:45', 0),
	(160, 50, 'kakao_guard5', NULL, '2022-04-29 16:53:22', 0),
	(161, 49, 'kakao_guard5', NULL, '2022-04-29 16:58:10', 0),
	(162, 43, 'kakao_guard5', NULL, '2022-04-29 16:58:16', 0),
	(163, 39, 'kakao_guard5', NULL, '2022-04-29 16:58:28', 0);
/*!40000 ALTER TABLE `chatroom` ENABLE KEYS */;

-- 테이블 imint_copy.chat_messages 구조 내보내기
CREATE TABLE IF NOT EXISTS `chat_messages` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '메세지 ID',
  `chatroom_id` int NOT NULL COMMENT '채팅방 ID',
  `sender_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '메세지 전송 회원 ID',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '메세지 내용',
  `is_read` tinyint NOT NULL DEFAULT '0' COMMENT '메세지 읽음여부',
  `send_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '메세지 전송일시',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '메세지 삭제여부',
  PRIMARY KEY (`id`),
  KEY `chat_messages_FK` (`chatroom_id`),
  KEY `chat_messages_FK_1` (`sender_id`),
  CONSTRAINT `chat_messages_FK` FOREIGN KEY (`chatroom_id`) REFERENCES `chatroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chat_messages_FK_1` FOREIGN KEY (`sender_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci COMMENT='채팅 메세지 테이블';

-- 테이블 데이터 imint_copy.chat_messages:~166 rows (대략적) 내보내기
DELETE FROM `chat_messages`;
/*!40000 ALTER TABLE `chat_messages` DISABLE KEYS */;
INSERT INTO `chat_messages` (`id`, `chatroom_id`, `sender_id`, `message`, `is_read`, `send_date`, `is_deleted`) VALUES
	(14, 6, 'google_child19', '안녕하세요', 0, '2022-04-28 17:11:28', 0),
	(15, 3, 'google_child12', '.', 0, '2022-04-28 17:11:40', 0),
	(16, 3, 'google_child12', '듣고있나요', 0, '2022-04-28 17:11:50', 0),
	(17, 7, 'google_child12', '사고싶어요', 0, '2022-04-28 17:12:17', 0),
	(18, 7, 'google_child2', '', 0, '2022-04-28 17:12:25', 0),
	(19, 7, 'google_child2', '얼마에 구매하시나요?', 0, '2022-04-28 17:12:45', 0),
	(20, 3, 'google_child19', '네 듣고 있습니다', 0, '2022-04-28 17:29:12', 0),
	(21, 18, 'google_child12', '네고', 0, '2022-04-28 17:40:36', 0),
	(22, 18, 'google_child12', '네고', 0, '2022-04-28 17:41:22', 0),
	(23, 21, 'google_child19', '혹시 4만원에 되나요', 0, '2022-04-28 17:41:23', 0),
	(24, 18, 'google_child12', '네고', 0, '2022-04-28 17:41:25', 0),
	(25, 18, 'google_child12', '네고', 0, '2022-04-28 17:41:26', 0),
	(26, 30, 'google_child19', '사고 싶어요', 0, '2022-04-28 17:42:07', 0),
	(27, 31, 'google_child2', '', 0, '2022-04-28 17:42:12', 0),
	(28, 31, 'google_child2', '', 0, '2022-04-28 17:42:18', 0),
	(29, 31, 'google_child2', '', 0, '2022-04-28 17:42:22', 0),
	(30, 31, 'google_child2', '', 0, '2022-04-28 17:42:33', 0),
	(31, 31, 'google_child2', '500원이요', 0, '2022-04-28 17:42:40', 0),
	(32, 32, 'google_child12', '"500원', 0, '2022-04-28 17:42:43', 0),
	(33, 32, 'google_child12', '500', 0, '2022-04-28 17:42:50', 0),
	(34, 32, 'google_child12', '500', 0, '2022-04-28 17:43:02', 0),
	(35, 32, 'google_child12', '850', 0, '2022-04-28 17:43:04', 0),
	(36, 32, 'google_child12', '5661', 0, '2022-04-28 17:43:06', 0),
	(37, 32, 'google_child12', '123', 0, '2022-04-28 17:43:09', 0),
	(38, 32, 'google_child12', '14123', 0, '2022-04-28 17:43:10', 0),
	(39, 31, 'google_child2', '', 0, '2022-04-28 17:44:11', 0),
	(40, 31, 'google_child2', '저 예약할래요', 0, '2022-04-28 17:44:19', 0),
	(41, 31, 'google_child2', '저요', 0, '2022-04-28 17:44:24', 0),
	(42, 31, 'google_child5', '안돼요', 0, '2022-04-28 17:44:27', 0),
	(43, 25, 'google_child12', 'fdd', 0, '2022-04-28 18:15:53', 0),
	(44, 37, 'google_child12', '네고', 0, '2022-04-29 01:34:31', 0),
	(45, 38, 'google_child2', '저 사고 싶은데 15000원으로 거래 되나요?', 0, '2022-04-29 01:54:51', 0),
	(46, 38, 'google_child2', '내일 용돈받아서 바로 구매 가능해요~', 0, '2022-04-29 01:55:19', 0),
	(47, 27, 'google_child2', '저 꼭 구매하고 싶어요!!', 0, '2022-04-29 03:22:28', 0),
	(48, 21, 'google_child2', '4만원은 어렵습니다..', 0, '2022-04-29 03:22:43', 0),
	(49, 21, 'google_child2', '거의 새거라서요', 0, '2022-04-29 03:23:02', 0),
	(50, 19, 'google_child12', '안장만 빼가도 될까요?', 0, '2022-04-29 12:14:49', 0),
	(51, 37, 'google_child19', '?', 0, '2022-04-29 12:32:59', 0),
	(52, 37, 'google_child12', '.', 0, '2022-04-29 14:57:02', 0),
	(53, 37, 'google_child12', '.', 0, '2022-04-29 14:58:11', 0),
	(54, 37, 'google_child12', '.', 0, '2022-04-29 15:02:34', 0),
	(55, 37, 'google_child12', '.', 0, '2022-04-29 15:02:49', 0),
	(56, 21, 'google_child19', '아숩', 0, '2022-04-29 15:22:13', 0),
	(57, 21, 'google_child19', '그래도 살게요', 0, '2022-04-29 15:22:27', 0),
	(58, 38, 'google_child19', '18000원까지만 가능합니다', 0, '2022-04-29 15:23:00', 0),
	(59, 67, 'google_child9', '구매하고싶어요', 0, '2022-04-29 15:59:31', 0),
	(60, 68, 'google_child22', 'test', 0, '2022-04-29 16:00:29', 0),
	(61, 73, 'google_child10', '구매되나요?', 0, '2022-04-29 16:01:34', 0),
	(62, 75, 'google_child19', '개 맞나요?', 0, '2022-04-29 16:02:28', 0),
	(63, 76, 'google_child2', '가격 흥정 정말 안되나요?', 0, '2022-04-29 16:02:51', 0),
	(64, 67, 'google_child2', '예약해드렸습니다~', 0, '2022-04-29 16:03:11', 0),
	(65, 68, 'google_child2', '관심주셔서 감사합니다!', 0, '2022-04-29 16:03:28', 0),
	(66, 19, 'google_child2', '총 부품만 거래 가능합니다ㅠㅠ', 0, '2022-04-29 16:03:47', 0),
	(67, 84, 'google_child2', '구매원합니다!', 0, '2022-04-29 16:05:38', 0),
	(68, 89, 'google_child19', '톰캣 사고 싶어요', 0, '2022-04-29 16:06:00', 0),
	(69, 90, 'google_child16', '구매하고싶네요', 0, '2022-04-29 16:06:06', 0),
	(70, 90, 'google_child9', '사실래요?', 0, '2022-04-29 16:06:47', 0),
	(71, 76, 'google_child9', '네 안되요', 0, '2022-04-29 16:06:53', 0),
	(72, 96, 'google_child22', 'ytrytr', 0, '2022-04-29 16:06:59', 0),
	(73, 75, 'google_child9', '고양이에요 죄송', 0, '2022-04-29 16:07:01', 0),
	(74, 75, 'google_child19', '힝', 0, '2022-04-29 16:07:07', 0),
	(75, 84, 'google_child10', '예약해드렸습니다^^', 0, '2022-04-29 16:07:11', 0),
	(76, 75, 'google_child9', '사실거에요?', 0, '2022-04-29 16:07:14', 0),
	(77, 89, 'google_child10', '먼저 오신분이 계셔요 ㅠㅠ', 0, '2022-04-29 16:07:20', 0),
	(78, 75, 'google_child9', '흥정안되요', 0, '2022-04-29 16:07:22', 0),
	(79, 90, 'google_child16', 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', 0, '2022-04-29 16:07:22', 0),
	(80, 75, 'google_child19', '개 아니라서...', 0, '2022-04-29 16:07:24', 0),
	(81, 90, 'google_child16', '아진짴ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', 0, '2022-04-29 16:07:24', 0),
	(82, 75, 'google_child9', '아 개파시구나', 0, '2022-04-29 16:07:30', 0),
	(83, 75, 'google_child19', '넹 아숩네요 수고하세요!', 0, '2022-04-29 16:07:48', 0),
	(84, 75, 'google_child9', '네 수고하세요!', 0, '2022-04-29 16:07:53', 0),
	(85, 89, 'google_child19', '아고... 아숩네요 감사합ㄴ다', 0, '2022-04-29 16:08:02', 0),
	(86, 90, 'google_child9', '?', 0, '2022-04-29 16:08:03', 0),
	(87, 89, 'google_child19', '감사합니다 좋은 하루 되세요', 0, '2022-04-29 16:08:07', 0),
	(88, 90, 'google_child9', '안사실거면 차단합니다', 0, '2022-04-29 16:08:07', 0),
	(89, 84, 'google_child2', '감사합니다!!!', 0, '2022-04-29 16:08:18', 0),
	(90, 79, 'google_child14', '깍아줘요', 0, '2022-04-29 16:08:26', 0),
	(91, 79, 'google_child14', '깍아달라구요', 0, '2022-04-29 16:08:33', 0),
	(92, 79, 'google_child14', '지금당장깍아줏에ㅛ', 0, '2022-04-29 16:08:37', 0),
	(93, 90, 'google_child16', '가격제안 가능인데', 0, '2022-04-29 16:08:37', 0),
	(94, 79, 'google_child9', '싫어요', 0, '2022-04-29 16:08:39', 0),
	(95, 90, 'google_child16', '얼마까지 가능한가요', 0, '2022-04-29 16:08:40', 0),
	(96, 79, 'google_child9', '흥정안되요', 0, '2022-04-29 16:08:40', 0),
	(97, 79, 'google_child9', '흥정하실거면', 0, '2022-04-29 16:08:43', 0),
	(98, 79, 'google_child9', '차단합니다', 0, '2022-04-29 16:08:46', 0),
	(99, 79, 'google_child14', '그 가격에 안팔려요', 0, '2022-04-29 16:08:47', 0),
	(100, 79, 'google_child9', '팔려요', 0, '2022-04-29 16:08:50', 0),
	(101, 79, 'google_child14', '제가 사드릴테니까', 0, '2022-04-29 16:08:50', 0),
	(102, 79, 'google_child14', '깍아줘요', 0, '2022-04-29 16:08:51', 0),
	(103, 87, 'google_child2', '구매 말고 1일 대여는 안되나요?', 0, '2022-04-29 16:08:54', 0),
	(104, 79, 'google_child9', '아 그럼', 0, '2022-04-29 16:08:57', 0),
	(105, 79, 'google_child14', '근데 채팅치면 자동으로 밑으로 스크롤', 0, '2022-04-29 16:08:59', 0),
	(106, 79, 'google_child14', '안되는거', 0, '2022-04-29 16:09:00', 0),
	(107, 79, 'google_child9', '100원 깍아드릴게요', 0, '2022-04-29 16:09:00', 0),
	(108, 79, 'google_child14', '불편합니다', 0, '2022-04-29 16:09:01', 0),
	(109, 87, 'google_child11', '네 공교롭게도 안됩니다', 0, '2022-04-29 16:09:05', 0),
	(110, 79, 'google_child9', 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', 0, '2022-04-29 16:09:06', 0),
	(111, 79, 'google_child14', '어떻게 생각하시나요', 0, '2022-04-29 16:09:09', 0),
	(112, 79, 'google_child9', '불편합니다', 0, '2022-04-29 16:09:14', 0),
	(113, 90, 'google_child9', '아 그럼', 0, '2022-04-29 16:09:48', 0),
	(114, 85, 'google_child14', '깍아주세요', 0, '2022-04-29 16:09:50', 0),
	(115, 85, 'google_child14', '깍아달라고요', 0, '2022-04-29 16:09:52', 0),
	(116, 90, 'google_child9', '제가 특별히', 0, '2022-04-29 16:09:52', 0),
	(117, 85, 'google_child14', '빨리', 0, '2022-04-29 16:09:52', 0),
	(118, 85, 'google_child14', '당장', 0, '2022-04-29 16:09:53', 0),
	(119, 90, 'google_child9', '선생님한테만', 0, '2022-04-29 16:09:54', 0),
	(120, 85, 'google_child14', '깍으세요', 0, '2022-04-29 16:09:54', 0),
	(121, 90, 'google_child9', '90,000,000원에 드릴게요', 0, '2022-04-29 16:10:03', 0),
	(122, 90, 'google_child9', '이거 진짜', 0, '2022-04-29 16:10:04', 0),
	(123, 90, 'google_child9', '저한테 소중한거라서', 0, '2022-04-29 16:10:07', 0),
	(124, 90, 'google_child16', '아... 근데 막상구매했는데', 0, '2022-04-29 16:10:41', 0),
	(125, 90, 'google_child16', '저표정아니면', 0, '2022-04-29 16:10:43', 0),
	(126, 90, 'google_child16', '약간 곤란한데', 0, '2022-04-29 16:10:45', 0),
	(127, 105, 'google_child14', '안녕하세요', 0, '2022-04-29 16:10:49', 0),
	(128, 108, 'google_child14', '안녕하세요', 0, '2022-04-29 16:10:58', 0),
	(129, 104, 'google_child14', '안녕하세요', 0, '2022-04-29 16:11:03', 0),
	(130, 90, 'google_child9', '아 항상', 0, '2022-04-29 16:11:09', 0),
	(131, 90, 'google_child9', '저표정이에요', 0, '2022-04-29 16:11:10', 0),
	(132, 87, 'google_child2', '마음 바뀌시면 꼭 채팅주세요..', 0, '2022-04-29 16:11:11', 0),
	(133, 90, 'google_child9', '보장합니다', 0, '2022-04-29 16:11:15', 0),
	(134, 105, 'google_child1', '안녕하세요', 0, '2022-04-29 16:11:20', 0),
	(135, 90, 'google_child16', 'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ', 0, '2022-04-29 16:11:21', 0),
	(136, 90, 'google_child16', '어디서 거래가능하신가요', 0, '2022-04-29 16:11:24', 0),
	(137, 87, 'google_child11', '네 ㅠㅠ', 0, '2022-04-29 16:11:33', 0),
	(138, 85, 'google_child11', '나가세요', 0, '2022-04-29 16:11:40', 0),
	(139, 90, 'google_child9', '아 저 직거래 안하는데', 0, '2022-04-29 16:11:55', 0),
	(140, 90, 'google_child9', '주소좀 주세요', 0, '2022-04-29 16:12:00', 0),
	(141, 108, 'google_child21', '안녕하세요~', 0, '2022-04-29 16:12:30', 0),
	(142, 90, 'google_child16', '멀티캠퍼스 주소로 보내주시면됩니다', 0, '2022-04-29 16:12:31', 0),
	(143, 117, 'google_child19', '혹시 씰은 뭐 나오셨나요?', 0, '2022-04-29 16:12:59', 0),
	(144, 118, 'google_child16', '여긴 고양이 판매하시는 분들이 많네요', 0, '2022-04-29 16:13:19', 0),
	(145, 85, 'google_child14', '너무해 ㅜ', 0, '2022-04-29 16:13:45', 0),
	(146, 118, 'google_child21', '네 핫하죠~', 0, '2022-04-29 16:13:51', 0),
	(147, 118, 'google_child16', '100,000,000원짜리 고양이는 표정이 예술이던데', 0, '2022-04-29 16:14:33', 0),
	(148, 118, 'google_child16', '이고양이는', 0, '2022-04-29 16:14:37', 0),
	(149, 118, 'google_child16', '매력이 뭔가요', 0, '2022-04-29 16:14:40', 0),
	(150, 85, 'google_child14', '너무해', 0, '2022-04-29 16:15:17', 0),
	(151, 85, 'google_child14', '너무해', 0, '2022-04-29 16:15:18', 0),
	(152, 85, 'google_child14', '너무해', 0, '2022-04-29 16:15:18', 0),
	(153, 85, 'google_child11', '그댄 배추하세요', 0, '2022-04-29 16:15:42', 0),
	(154, 117, 'google_child17', '미개봉이라 확인불가능합니다...', 0, '2022-04-29 16:15:50', 0),
	(155, 85, 'google_child14', '오... 노잼입니다 ㅜ', 0, '2022-04-29 16:16:36', 0),
	(156, 118, 'google_child21', '음식 훔쳐먹는게 취미라 다이어트에 효과적입니다 ^~^', 0, '2022-04-29 16:16:44', 0),
	(157, 117, 'google_child19', '고양이가 먹었는데 미개봉', 0, '2022-04-29 16:16:45', 0),
	(158, 117, 'google_child19', '사기꾼이야!', 0, '2022-04-29 16:16:48', 0),
	(159, 129, 'google_child3', '스티커도 있나여??', 0, '2022-04-29 16:17:11', 0),
	(160, 85, 'google_child11', 'ㅎ..', 0, '2022-04-29 16:19:01', 0),
	(161, 117, 'google_child17', '잘안보여서 확인이어렵습니다...', 0, '2022-04-29 16:19:51', 0),
	(162, 129, 'google_child17', '씰은 잘안보여서 확인이 어렵습니다...', 0, '2022-04-29 16:20:15', 0),
	(163, 145, 'google_child23', '네고가능한가요', 0, '2022-04-29 16:20:26', 0),
	(164, 145, 'google_child4', '불가능합니다', 0, '2022-04-29 16:20:47', 0),
	(165, 145, 'google_child4', '제발', 0, '2022-04-29 16:20:53', 0),
	(166, 111, 'google_child11', '고양이 너무 귀여운데 가격이 좀 쎄요 ㅠㅡㅡㅡㅠ', 0, '2022-04-29 16:21:05', 0),
	(167, 145, 'google_child4', '9500원으로 제안합니다', 0, '2022-04-29 16:21:12', 0),
	(168, 145, 'google_child23', '넵 ', 0, '2022-04-29 16:21:15', 0),
	(169, 111, 'google_child21', '넘 커여운 고양이라 네고 어려운데.. 특별히 해드릴게여~!', 0, '2022-04-29 16:22:37', 0),
	(170, 149, 'google_child1', '웨이팅이 필수라는데 그 정도 가치가 있나요?', 0, '2022-04-29 16:24:00', 0),
	(171, 149, 'google_child14', '꼭 가보세요 낮술때렸습니다', 0, '2022-04-29 16:25:08', 0),
	(172, 111, 'google_child11', '앗 얼마까지 될까요', 0, '2022-04-29 16:25:18', 0),
	(173, 111, 'google_child21', '먼저 제안 해보시죳~~!', 0, '2022-04-29 16:30:48', 0),
	(174, 111, 'google_child11', '흠', 0, '2022-04-29 16:31:14', 0),
	(175, 111, 'google_child11', '깔끔하게 10만원 어떠신가요', 0, '2022-04-29 16:31:34', 0),
	(176, 111, 'google_child21', '너무하시는거 아닌가여~ 12만원 하시죠!', 0, '2022-04-29 16:35:47', 0),
	(177, 111, 'google_child11', 'ㅠㅠ', 0, '2022-04-29 16:44:23', 0),
	(178, 111, 'google_child11', '그럼', 0, '2022-04-29 16:44:28', 0),
	(179, 111, 'google_child11', '일단 훈련 장려금이 들어오면 생각해보고 다시 올게요 ..', 0, '2022-04-29 16:44:39', 0);
/*!40000 ALTER TABLE `chat_messages` ENABLE KEYS */;

-- 테이블 imint_copy.goods 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods` (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '상품ID',
  `seller_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '판매자ID',
  `seller_nick` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '닉네임',
  `goods_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '글제목',
  `goods_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci COMMENT '글내용',
  `goods_price` bigint NOT NULL COMMENT '가격',
  `goods_category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL DEFAULT '기타' COMMENT '카테고리',
  `goods_suggestible` tinyint(1) NOT NULL DEFAULT '0' COMMENT '흥정가능여부',
  `goods_location` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '거래지역',
  `goods_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '판매등록일자',
  `goods_status` enum('wait','resrv','comp') CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT 'wait' COMMENT '거래상태',
  `goods_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '상품 삭제 여부',
  PRIMARY KEY (`goods_id`,`seller_id`,`seller_nick`) USING BTREE,
  KEY `FK_goods_member` (`seller_id`,`seller_nick`),
  CONSTRAINT `FK_goods_member` FOREIGN KEY (`seller_id`, `seller_nick`) REFERENCES `member` (`mb_id`, `mb_nick`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.goods:~44 rows (대략적) 내보내기
DELETE FROM `goods`;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` (`goods_id`, `seller_id`, `seller_nick`, `goods_title`, `goods_content`, `goods_price`, `goods_category`, `goods_suggestible`, `goods_location`, `goods_create_date`, `goods_status`, `goods_isdelete`) VALUES
	(12, 'google_child19', 'GhostFairy', '탬버린 팝니다', '1달 쓰고 안 써서 팝니다', 2000, '교구', 0, '한국', '2022-04-28 16:18:51', 'comp', 0),
	(13, 'google_child19', 'GhostFairy', '피카츄 띠부띠부씰 팝니다', '필요 없어서 팜', 10000, '완구', 1, '한국', '2022-04-28 16:25:28', 'wait', 0),
	(14, 'google_child5', '해연실험1', '사진X 테스트', 'ㅌㅅㅌ', 1200, '문구', 0, '한국', '2022-04-28 16:44:25', 'wait', 1),
	(15, 'google_child5', '해연실험1', '엄청긴제목에대한테스트를하고있습니다.', 'ㅌㅅㅌ', 20000000, '문구', 0, '한국', '2022-04-28 17:08:27', 'wait', 1),
	(16, 'google_child19', 'GhostFairy', '청바지 팝니다', '150 사이즈에요\n\n무릎 안 까졌어요', 5000, '의류', 1, '한국', '2022-04-28 17:08:55', 'wait', 0),
	(17, 'google_child2', 'jj', '예쁜 풍선 팔아요', '개당 5천원, 묶음 구매시 3만원이요', 5000, '문구', 1, '한국', '2022-04-28 17:09:56', 'resrv', 0),
	(18, 'google_child12', '윤성2', '포켓몬빵 팔아요', '맛잇게드세요', 2520, '기타', 1, '한국', '2022-04-28 17:10:27', 'comp', 0),
	(19, 'google_child5', '해연실험1', '십만원이상테스트입니다', 'ㅇ', 200000, '문구', 0, '한국', '2022-04-28 17:10:54', 'wait', 1),
	(20, 'google_child19', 'GhostFairy', '스폰지밥 퍼즐 10000피스', '어려워서 팜', 10000, '완구', 1, '한국', '2022-04-28 17:11:16', 'wait', 0),
	(21, 'google_child2', 'jj', '초등 1학년 2학기 수학교과서', '작년에 썼던 교과서에요~ 낙서 없이 공부했습니다 ㅎㅎ ', 3500, '도서', 0, '한국', '2022-04-28 17:18:27', 'wait', 0),
	(22, 'google_child5', '해연실험1', '장식꽃 팔아요', '오늘안에 사시면 1000원', 2000, '기타', 1, '한국', '2022-04-28 17:19:14', 'wait', 0),
	(23, 'google_child19', 'GhostFairy', '무제 공책 10권 세트', '500원 짜리 공책 10권 세트', 3000, '문구', 0, '한국', '2022-04-28 17:20:37', 'wait', 0),
	(24, 'google_child12', '윤성2', '아이폰12', '아이폰12', 290000, '기타', 0, '한국', '2022-04-28 17:21:44', 'wait', 0),
	(25, 'google_child19', 'GhostFairy', '단소', '아무리 불어도 소리도 안 나고\n\n소리 안 난다고 맨날 남아서 연습하고\n\n화딱지 나서 팝니다', 1000, '교구', 0, '한국', '2022-04-28 17:21:51', 'wait', 0),
	(26, 'google_child5', '해연실험1', '닌텐도 팝니다', '이제 공부해야돼서 팔아용', 270000, '완구', 0, '한국', '2022-04-28 17:22:36', 'wait', 0),
	(27, 'google_child19', 'GhostFairy', '포켓몬 몬스터볼 백팩 팝니다', '얼마 안 썼고 깨끗하게 세탁했습니다\n\n흥정 가능', 20000, '문구', 1, '한국', '2022-04-28 17:23:47', 'wait', 0),
	(28, 'google_child5', '해연실험1', '젤다 야숨 칩', '차기작 나올것같아서 팝니다', 40000, '완구', 0, '한국', '2022-04-28 17:24:22', 'wait', 0),
	(29, 'google_child5', '해연실험1', '갬성 엽서', '1개당 1000원 네고 x', 1000, '문구', 0, '한국', '2022-04-28 17:25:19', 'wait', 0),
	(30, 'google_child12', '윤성2', '영어책', '영어책1', 3999, '교구', 1, '한국', '2022-04-28 17:25:35', 'wait', 0),
	(31, 'google_child19', 'GhostFairy', '나이키 조던 220', '깨끗해요', 13000, '문구', 0, '한국', '2022-04-28 17:25:54', 'wait', 0),
	(32, 'google_child2', 'jj', '자전거 부품 판매', '움직이지는 않은데 사실 분??', 30000, '기타', 1, '한국', '2022-04-28 17:26:02', 'wait', 0),
	(33, 'google_child5', '해연실험1', '다꾸세트', 'ㅍ', 10000, '문구', 0, '한국', '2022-04-28 17:26:53', 'wait', 0),
	(34, 'google_child19', 'GhostFairy', '시공주니어 독서레벨1 65권', '작년에 구입한 책이에요 너무 깨끗하고 1~65번 입니다. 그중에 2권은 분실해서 제가 따로 구입해 채워놨고 약간 바램있어요\n택배비포함입니다', 100000, '도서', 1, '한국', '2022-04-28 17:27:09', 'wait', 0),
	(35, 'google_child2', 'jj', '유치원때 썼던 콩순이컴', '초등학교 입학해서 팔아요', 45000, '완구', 1, '한국', '2022-04-28 17:28:03', 'comp', 0),
	(36, 'google_child5', '해연실험1', '액괴', '직접만든거에요', 1000, '완구', 0, '한국', '2022-04-28 17:30:53', 'resrv', 0),
	(37, 'google_child12', '윤성2', '테스트1', 'ㄴ', 12, '기타', 1, '한국', '2022-04-29 02:13:11', 'wait', 1),
	(38, 'google_child12', '윤성2', '안아줘요', '응애', 9999, '기타', 1, '한국', '2022-04-29 04:43:47', 'wait', 0),
	(39, 'google_child2', 'jj', '여아 신발 180mm', '1년정도 신었는데 예뻐요~', 21000, '가방,신발,잡화', 0, '한국', '2022-04-29 13:20:56', 'wait', 0),
	(40, 'google_child2', 'jj', '뽀로로 인형이요', '인형팔아요~', 1500, '완구', 0, '한국', '2022-04-29 13:21:58', 'wait', 0),
	(41, 'google_child2', 'jj', '미국 영어 교과서에요~ 영어공부하세요', '2권 총합해서 15000원이에요', 15000, '도서', 1, '한국', '2022-04-29 13:24:03', 'wait', 0),
	(42, 'google_child19', 'GhostFairy', '닌텐도 마법천자문', '닌텐도칩입니다.\n\n택비포함 8천원입니다.\n\n가지러 오시면 5천원입니다.', 7000, '완구', 0, '한국', '2022-04-29 13:24:16', 'wait', 0),
	(43, 'google_child19', 'GhostFairy', '88오락실 미니 게임기', '88오락실 미니 게임기\n사용가능하구요~\n다양한게임이 들어있어 좋아요.\n택포15000', 15000, '완구', 1, '한국', '2022-04-29 13:36:40', 'wait', 0),
	(44, 'google_child2', 'jj', '말랑이 거래', '팝입 푸시팝 여러개 팔아요 ', 20000, '완구', 1, '한국', '2022-04-29 13:45:32', 'wait', 0),
	(45, 'google_child2', 'jj', '퍼즐 판매', '500 피스짜리 퍼즐이에요', 2000, '완구', 0, '한국', '2022-04-29 13:48:14', 'resrv', 0),
	(46, 'google_child9', '한상민4062', '고양이', '팝니다.', 100000000, '문구', 0, '한국', '2022-04-29 16:02:06', 'wait', 0),
	(47, 'google_child14', '정재홍4119', '남영돈', '서울 남영돈 꼭 가보세요 맛있어요', 99999999, '기타', 0, '한국', '2022-04-29 16:03:23', 'wait', 1),
	(48, 'google_child10', 'Hs', '톰캣', 'ㅍㅍㅍ', 1, '기타', 1, '한국', '2022-04-29 16:04:00', 'resrv', 0),
	(49, 'google_child11', '모됴2989', '프로젝트하고 뻗은 로봇', '\n뻗어있는 친구를 데려가주세요 ', 30000, '기타', 1, '한국', '2022-04-29 16:05:27', 'wait', 0),
	(50, 'google_child21', '제리', '에?!', '커여운 고양이 분양합니다', 125000, '완구', 1, '한국', '2022-04-29 16:08:44', 'wait', 0),
	(51, 'google_child1', 'inacro', '저렴하게 와인을 팔아요. ', '당연히 미개봉 상태입니다.  제가 좋아하는 제품인데 한동안 술을 안 마시려고 사 놓은 것을 팔려고 합니다. \n', 10000, '기타', 1, '한국', '2022-04-29 16:09:19', 'wait', 1),
	(52, 'google_child22', 'pppp', '멍멍이', '멍멍이', 99999999, '기타', 1, '한국', '2022-04-29 16:09:22', 'wait', 0),
	(53, 'google_child17', 'martin1341', '포켓몬빵 팝니다', '고양이가 한입먹긴했는데... 10프로 네고 가능합니다...', 3000, '기타', 1, '한국', '2022-04-29 16:12:02', 'wait', 0),
	(54, 'google_child14', '정재홍4119', '코로나 자가 격리문자 팝니다', '코로나 자가 격리문자 팝니다', 500, '문구', 0, '한국', '2022-04-29 16:13:15', 'wait', 1),
	(55, 'google_child4', '윤성일_son1', '고라파덕', '고라파덕 귀여운 고라파덕', 9800, '기타', 1, '대구 중구', '2022-04-29 16:17:14', 'comp', 0);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

-- 테이블 imint_copy.goods_images 구조 내보내기
CREATE TABLE IF NOT EXISTS `goods_images` (
  `goods_images_id` int NOT NULL AUTO_INCREMENT COMMENT '상품이미지ID',
  `goods_id` int NOT NULL COMMENT '상품ID',
  `goods_images_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL DEFAULT '/static/images/noimage.png' COMMENT '이미지파일경로',
  `goods_images_thumbnail` tinyint(1) NOT NULL DEFAULT '0' COMMENT '대표이미지여부',
  `goods_images_originname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL DEFAULT 'noimage.png' COMMENT '파일원본이름',
  `goods_images_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '이미지 삭제여부',
  PRIMARY KEY (`goods_images_id`,`goods_id`) USING BTREE,
  KEY `FK_goods_images_goods` (`goods_id`),
  CONSTRAINT `FK_goods_images_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.goods_images:~60 rows (대략적) 내보내기
DELETE FROM `goods_images`;
/*!40000 ALTER TABLE `goods_images` DISABLE KEYS */;
INSERT INTO `goods_images` (`goods_images_id`, `goods_id`, `goods_images_path`, `goods_images_thumbnail`, `goods_images_originname`, `goods_images_isdelete`) VALUES
	(262, 12, '/iMintImage/goods/2022/4/28/12_2022-04-28_161851%286945a80f-3c7c-43e2-b1a4-b7bab5060ed4%29.jfif', 1, '99A62E485BB44A6218.jfif', 0),
	(263, 12, '/iMintImage/goods/2022/4/28/12_2022-04-28_161851%28757882fb-f2f7-40b0-9cb9-916090c2165d%29.jfif', 0, '99A62E485BB44A6218.jfif', 0),
	(264, 13, '/iMintImage/goods/2022/4/28/13_2022-04-28_162528%287d007a53-e381-4570-bb62-0efdb9698aaa%29.jpg', 1, '181591525_1_1646953016_w360.jpg', 0),
	(265, 15, '/iMintImage/goods/2022/4/28/15_2022-04-28_170827%28ebaec674-7eae-47b1-8527-4872eedce3c6%29.jpeg', 1, 'photo-1542676032-6e468ada2953.jpeg', 1),
	(266, 15, '/iMintImage/goods/2022/4/28/15_2022-04-28_170827%289e940dcb-7837-4533-afaa-0b574c2b8f31%29.jpeg', 0, 'photo-1542676032-6e468ada2953.jpeg', 1),
	(267, 15, '/iMintImage/goods/2022/4/28/15_2022-04-28_170827%286d25289d-39b8-48e0-b080-9fc2025fc6e0%29.jpeg', 0, 'photo-1499174549139-68d3f37243b4.jpeg', 1),
	(268, 16, '/iMintImage/goods/2022/4/28/16_2022-04-28_170909%28107c7ad0-7517-4bd7-b7da-c4c7137a6459%29.jpg', 1, 'istockphoto-1219372365-1024x1024.jpg', 0),
	(269, 16, '/iMintImage/goods/2022/4/28/16_2022-04-28_170909%28e04e4991-cfc1-4485-afd8-7118af6828f6%29.png', 0, 'noimage.png', 0),
	(270, 17, '/iMintImage/goods/2022/4/28/17_2022-04-28_170956%28e6085bfe-68ee-43fd-bdfc-468686e6ebb3%29.jpg', 1, 'balloonn.jpg', 0),
	(271, 18, '/iMintImage/goods/2022/4/28/18_2022-04-28_171027%2836459e20-0a59-46d8-9e9e-8a385267d337%29.jpeg', 1, '9de44be053c9a0acfe32d0c7717a7914ede6885acaaf89e3ea07a752c9091934.jpeg', 0),
	(272, 20, '/iMintImage/goods/2022/4/28/20_2022-04-28_171116%28166ffe0d-a5da-4a89-b05f-e4fbde90eabe%29.png', 1, 'image.png', 0),
	(273, 21, '/iMintImage/goods/2022/4/28/21_2022-04-28_171827%282efe84c0-63b2-4288-81a6-5dd93fdd48da%29.jpg', 1, '교과서.jpg', 0),
	(274, 21, '/iMintImage/goods/2022/4/28/21_2022-04-28_171827%28e030983d-fc8c-4fd6-970e-6da8859c8a9b%29.jpg', 0, '교과서.jpg', 0),
	(276, 23, '/iMintImage/goods/2022/4/28/23_2022-04-28_172037%28740f09bb-d7de-45b6-a0c9-0a575e883203%29.png', 1, 'image.png', 0),
	(277, 24, '/iMintImage/goods/2022/4/28/24_2022-04-28_172144%28287cc922-8a58-441e-bf79-87417b1c0b80%29.jpg', 1, 'B43CF1BEB28EB1238DD9F88E71212B02700325900AB80D48E18A5CFD9C2C0298.jpg', 0),
	(278, 25, '/iMintImage/goods/2022/4/28/25_2022-04-28_172151%28ad4af221-cfed-48cb-84c2-a6e81e14b50e%29.png', 1, 'image.png', 0),
	(279, 26, '/iMintImage/goods/2022/4/28/26_2022-04-28_172236%28fc30d9d2-5ca3-4811-bc0a-53935cc4b51e%29.jpeg', 1, '닌텐도.jpeg', 0),
	(280, 22, '/iMintImage/goods/2022/4/28/22_2022-04-28_172300%28464c1655-0902-47d7-b833-abdf9650772b%29.crdownload', 1, '22_2022-04-28_171914(ce4d1eab-60f7-4f44-9b9d-4c8b6304f31e).crdownload', 0),
	(281, 27, '/iMintImage/goods/2022/4/28/27_2022-04-28_172347%2875295194-9771-4bb6-b25b-e61247b43944%29.png', 1, 'image.png', 0),
	(282, 28, '/iMintImage/goods/2022/4/28/28_2022-04-28_172422%283f0ff535-3b96-4335-a659-3aa98eb62b47%29.jpeg', 1, '젤다.jpeg', 0),
	(283, 29, '/iMintImage/goods/2022/4/28/29_2022-04-28_172519%28096109b9-4a94-4634-a93c-3704d98ab938%29.webp', 1, '감성엽서.webp', 0),
	(284, 29, '/iMintImage/goods/2022/4/28/29_2022-04-28_172519%281e95cdc8-9554-4cc0-9ac0-caf40b7c7545%29.webp', 0, '감성엽서.webp', 0),
	(285, 29, '/iMintImage/goods/2022/4/28/29_2022-04-28_172519%28b8aea54a-f8c8-4fe2-b052-68d8fac716ee%29.webp', 0, '감성엽서.webp', 0),
	(288, 31, '/iMintImage/goods/2022/4/28/31_2022-04-28_172554%283405e38b-c1a0-4881-9846-e7f12ee840af%29.png', 1, 'image.png', 0),
	(290, 33, '/iMintImage/goods/2022/4/28/33_2022-04-28_172653%28de61f6df-cbe1-4356-92b3-2186366de9fd%29.jpeg', 1, '다꾸.jpeg', 0),
	(291, 34, '/iMintImage/goods/2022/4/28/34_2022-04-28_172709%28ca48c64a-a41a-49ed-bf1e-358c417774f1%29.png', 1, 'image.png', 0),
	(294, 32, '/iMintImage/goods/2022/4/28/32_2022-04-28_172955%282cb3ebcd-e37e-4974-b974-e2d2cc0db939%29.png', 1, '32_2022-04-28_172937(a0445e0d-a211-49bb-aeee-ff5afa520132).png', 0),
	(299, 35, '/iMintImage/goods/2022/4/28/35_2022-04-28_173023%283e66bd9b-345b-4ca3-bd14-d6113ee005a3%29.jpg', 1, '35_2022-04-28_173014(84a43ebc-fa9d-49b0-869b-969bb6401e2c).jpg', 0),
	(303, 36, '/iMintImage/goods/2022/4/28/36_2022-04-28_173053%289f3ef8ca-ce22-4877-b04e-9b1b17ca3b50%29.jpeg', 1, '액괴.jpeg', 0),
	(304, 30, '/iMintImage/goods/2022/4/28/30_2022-04-28_173452%28bf22010c-ddb9-4549-baa3-7ea26d160aff%29.jpg', 1, '30_2022-04-28_173031(222779b2-e89d-409e-bbae-2c0396f5a6c6).jpg', 0),
	(305, 30, '/iMintImage/goods/2022/4/28/30_2022-04-28_173452%2887e435fe-1a49-4a61-8f1d-39cf37637cf6%29.jpg', 0, '30_2022-04-28_173031(78549500-2fe8-4d42-a9b6-2eafb2645c49).jpg', 0),
	(306, 30, '/iMintImage/goods/2022/4/28/30_2022-04-28_173452%2880dd3f26-7401-4ae9-b586-2489b590d832%29.png', 0, '30_2022-04-28_173031(18b5c8c9-dd46-459f-9161-74b8cdf9270b).png', 0),
	(307, 30, '/iMintImage/goods/2022/4/28/30_2022-04-28_173452%28533c43a6-0f9c-4124-a036-cdc5eb208f4f%29.png', 0, '화면 캡처 2022-02-03 150644.png', 0),
	(310, 37, '/iMintImage/goods/2022/4/29/37_2022-04-29_021322%287ce78771-d5f0-431a-82a5-e2976d83715f%29.png', 1, '37_2022-04-29_021311(30ab658d-20a8-4f02-9d72-c4c6b1342981).png', 1),
	(311, 37, '/iMintImage/goods/2022/4/29/37_2022-04-29_021322%28c58944a9-828c-44c2-813d-b2b175d33bc5%29.png', 0, '37_2022-04-29_021311(fac00267-8c37-4f2e-bc1a-c22054721ea1).png', 1),
	(325, 38, '/iMintImage/goods/2022/4/29/38_2022-04-29_112349%28a7bc55f8-ee03-42f1-874c-6d4dd5873a89%29.gif', 1, '38_2022-04-29_110639(13f9db75-a801-4fd2-93cf-b458786fc665).gif', 0),
	(326, 38, '/iMintImage/goods/2022/4/29/38_2022-04-29_112349%2888fadcca-af43-458c-9d98-2f7b703df057%29.jpg', 0, '38_2022-04-29_110639(9e5d7b44-db78-4e1a-975a-989cf9533599).jpg', 0),
	(327, 38, '/iMintImage/goods/2022/4/29/38_2022-04-29_112349%2878e5a460-c35e-4cb6-bbfa-7b3c2a9b2780%29.jpg', 0, '38_2022-04-29_110639(86f1028b-01fd-4727-ab0d-fce186491c64).jpg', 0),
	(328, 38, '/iMintImage/goods/2022/4/29/38_2022-04-29_112349%2887d23c17-ddf8-4bf8-8522-13c926c48e69%29.jpg', 0, '38_2022-04-29_110639(4d7a16d2-f092-4a9d-a035-e944af6d8f55).jpg', 0),
	(329, 39, '/iMintImage/goods/2022/4/29/39_2022-04-29_132056%288798a847-bd05-4657-949b-3fba22ed001d%29.jpg', 1, '신발.jpg', 0),
	(330, 40, '/iMintImage/goods/2022/4/29/40_2022-04-29_132158%28d8056bd2-aa91-4bc0-b504-198d9df9c1fd%29.jpg', 1, '뽀로로.jpg', 0),
	(331, 41, '/iMintImage/goods/2022/4/29/41_2022-04-29_132403%280a68ac0c-ae64-45f7-9552-84a2085e809f%29.jpg', 1, '미국교과서.jpg', 0),
	(332, 42, '/iMintImage/goods/2022/4/29/42_2022-04-29_132416%28f4a2ece9-e054-4fee-99d4-8ef63fad2564%29.png', 1, 'image.png', 0),
	(333, 42, '/iMintImage/goods/2022/4/29/42_2022-04-29_132416%288ecf061f-53b3-4415-b1da-653f49954d10%29.png', 0, 'image.png', 0),
	(334, 43, '/iMintImage/goods/2022/4/29/43_2022-04-29_133640%28e6871cd0-fb70-4052-a353-1c19a8a238c5%29.png', 1, 'image.png', 0),
	(335, 43, '/iMintImage/goods/2022/4/29/43_2022-04-29_133640%2842ae8d80-8534-418d-ab00-ca8f0634b953%29.png', 0, 'image.png', 0),
	(336, 43, '/iMintImage/goods/2022/4/29/43_2022-04-29_133640%28c148b62f-b0fd-43e3-8a49-da63de45e352%29.png', 0, 'image.png', 0),
	(338, 44, '/iMintImage/goods/2022/4/29/44_2022-04-29_134637%28f68f9ec9-f875-42db-83ba-750e1f44dbfc%29.jpg', 1, '44_2022-04-29_134532(82a2107b-1a41-4d20-9658-ba17042159cb).jpg', 0),
	(339, 45, '/iMintImage/goods/2022/4/29/45_2022-04-29_134814%286533ec52-a8b2-4239-b7e6-c9843a75a8d2%29.jpg', 1, '퍼즐.jpg', 0),
	(342, 47, '/iMintImage/goods/2022/4/29/47_2022-04-29_160323%28f1f4c21c-29c5-4ff3-b304-1163a30138f1%29.jpg', 1, 'KakaoTalk_20220428_155543790.jpg', 1),
	(343, 48, '/iMintImage/goods/2022/4/29/48_2022-04-29_160400%2850b4f9b9-de87-4f34-bd68-7eab7baf73e1%29.png', 1, 'tomcat.png', 0),
	(344, 49, '/iMintImage/goods/2022/4/29/49_2022-04-29_160527%282c286e48-bdec-43d9-a543-675abcc867a5%29.png', 1, '해탈.png', 0),
	(345, 50, '/iMintImage/goods/2022/4/29/50_2022-04-29_160844%28e7ae2a7a-4349-4c95-b22f-46327b8d22c6%29.jpg', 1, 'cat1.jpg', 0),
	(347, 52, '/iMintImage/goods/2022/4/29/52_2022-04-29_160922%28fdce37c4-a3e3-4a28-8ff9-6132a65930e0%29.jfif', 1, 'dog1.jfif', 0),
	(350, 51, '/iMintImage/goods/2022/4/29/51_2022-04-29_161213%28bb35f5c3-e6e9-42f4-8123-210fa29364b9%29.jpg', 1, '51_2022-04-29_161033(bfdb8151-9afe-491e-a13e-d25e4c5651b8).jpg', 1),
	(351, 54, '/iMintImage/goods/2022/4/29/54_2022-04-29_161315%28e1623516-8dc1-44ec-bd77-155146320126%29.jpg', 1, 'KakaoTalk_20220418_163417436.jpg', 1),
	(352, 53, '/iMintImage/goods/2022/4/29/53_2022-04-29_161442%28a70b0ead-5f02-4bbc-bfa9-2266ab528067%29.jpg', 1, '53_2022-04-29_161202(474d20d7-309e-4e54-a084-20c9dc5c946f).jpg', 0),
	(357, 55, '/iMintImage/goods/2022/4/29/55_2022-04-29_162612%289a32ec57-1f22-4b54-9136-2bd5c72ecdea%29.jpg', 1, '55_2022-04-29_161819(28e27d3e-fa26-4038-ac8d-ed2362e3bb59).jpg', 0),
	(358, 55, '/iMintImage/goods/2022/4/29/55_2022-04-29_162612%289521093b-c89f-4f75-bda1-66e1ef66edea%29.jpg', 0, '55_2022-04-29_161819(de14bbf4-6e48-4e02-90b3-0516f0cbd7bb).jpg', 0),
	(359, 46, '/iMintImage/goods/2022/4/29/46_2022-04-29_162700%281c8e1f80-9c15-4e38-8776-626ab3910a78%29.png', 1, '46_2022-04-29_160258(916c0aeb-1359-4ee8-a674-ce7bcb0ab809).png', 0);
/*!40000 ALTER TABLE `goods_images` ENABLE KEYS */;

-- 테이블 imint_copy.member 구조 내보내기
CREATE TABLE IF NOT EXISTS `member` (
  `mb_no` int NOT NULL AUTO_INCREMENT COMMENT '등록순서',
  `mb_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `mb_provider` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT 'SNS사이트',
  `mb_guard` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '보호자',
  `mb_nick` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '닉네임',
  `mb_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '이메일',
  `mb_join_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일자',
  `mb_interest` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '관심사',
  `mb_location` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '내 동네',
  `mb_ratings_total` int DEFAULT '25' COMMENT '평가점수',
  `mb_pin` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '아이등록인증PIN',
  `mb_thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '프로필사진',
  `mb_isdelete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '탈퇴여부',
  `mb_role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '멤버권한',
  PRIMARY KEY (`mb_id`,`mb_nick`) USING BTREE,
  UNIQUE KEY `mb_no` (`mb_no`),
  UNIQUE KEY `mb_nick` (`mb_nick`),
  KEY `mb_guard` (`mb_guard`),
  CONSTRAINT `FK_member_member` FOREIGN KEY (`mb_guard`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.member:~31 rows (대략적) 내보내기
DELETE FROM `member`;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`mb_no`, `mb_id`, `mb_provider`, `mb_guard`, `mb_nick`, `mb_email`, `mb_join_date`, `mb_interest`, `mb_location`, `mb_ratings_total`, `mb_pin`, `mb_thumbnail`, `mb_isdelete`, `mb_role`) VALUES
	(125, 'google_child1', 'google', 'kakao_guard5', 'inacro', 'kjgyu24@gmail.com', '2022-04-29 16:03:07', '도서', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(114, 'google_child10', 'google', 'kakao_guard5', 'Hs', 'test202110071402@gmail.com', '2022-04-29 15:57:07', '완구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(116, 'google_child11', 'google', 'kakao_guard5', '모됴2989', 'rihyun101@gmail.com', '2022-04-29 15:58:05', '도서', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(60, 'google_child12', 'goole', 'kakao_guard5', '윤성2', 'dbs3346@gmail.com', '2022-04-28 15:49:03', '문구', '한국', 25, NULL, '/iMintImage/member/CHILD/goole/google_102592663151810141035.png', 0, 'CHILD'),
	(58, 'google_child13', 'goole', 'kakao_guard1', 'Haeyeon Jeon5955', 'haeyeon2727@gmail.com', '2022-04-28 15:48:17', NULL, NULL, 25, NULL, NULL, 0, 'CHILD'),
	(122, 'google_child14', 'google', 'kakao_guard5', '정재홍4119', 'jaehong2782@gmail.com', '2022-04-29 15:58:45', '문구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(121, 'google_child15', 'google', 'kakao_guard5', '써랑8672', 'terry940513@gmail.com', '2022-04-29 15:58:21', '도서', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(115, 'google_child16', 'google', 'kakao_guard5', '123456789', 'email@email.com', '2022-04-29 15:57:19', '문구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(126, 'google_child17', 'google', 'kakao_guard5', 'martin1341', 'martin1341@naver.com', '2022-04-29 16:09:34', '완구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(68, 'google_child18', 'google', 'kakao_guard5', 'temp22', 'temp22@gmail.com', '2022-04-29 10:29:58', '의류', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(53, 'google_child19', 'goole', 'naver_NXOiWYXGfEMB1C3kxD5QoQVUFC3Co28UaxwLAU-i7YU', 'GhostFairy', 'ghhjkk@hanyang.ac.kr', '2022-04-28 15:44:51', '문구', '한국', 25, NULL, '/iMintImage/member/CHILD/goole/google_105109371333645175867.jfif', 0, 'CHILD'),
	(55, 'google_child2', 'goole', 'kakao_guard1', 'jj', 'overult01@gmail.com', '2022-04-28 15:47:13', '문구', '한국', 25, NULL, '/iMintImage/member/CHILD/goole/google_117757611328703745272.jpg', 0, 'CHILD'),
	(118, 'google_child20', 'google', 'kakao_guard5', '7조화이팅', 'overult09@gmail.com', '2022-04-29 15:58:10', '교구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(120, 'google_child21', 'google', 'kakao_guard5', '제리', 'alfndp25@gmail.com', '2022-04-29 15:58:14', '완구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(117, 'google_child22', 'google', 'kakao_guard5', 'pppp', 'ppp@ppp.com', '2022-04-29 15:58:08', '문구', '한국', 25, NULL, '/iMintImage/member/CHILD/google/google_106900649026426205418.jfif', 0, 'CHILD'),
	(129, 'google_child23', 'google', 'kakao_guard2', '윤성일_son2', 'dbs3346b@gmail.com', '2022-04-29 16:18:57', '문구', '대구 중구', 25, NULL, NULL, 0, 'CHILD'),
	(124, 'google_child3', 'google', 'kakao_guard5', 'hee', 'iyou0101@gmail.com', '2022-04-29 16:01:45', '문구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(128, 'google_child4', 'google', 'kakao_guard2', '윤성일_son1', 'dbs3346a@gmail.com', '2022-04-29 16:14:48', '문구', '대구 중구', 25, NULL, NULL, 0, 'CHILD'),
	(57, 'google_child5', 'goole', 'kakao_guard4', '해연실험1', 'lillyine.hy@gmail.com', '2022-04-28 15:48:07', '문구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(112, 'google_child6', 'google', 'kakao_guard5', '문쥬빌레moonsjubilees8350', 'moons0927@gmail.com', '2022-04-29 15:55:33', '문구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(123, 'google_child7', 'google', 'kakao_guard5', '이강산4484', 'ghhjkk@gmail.com', '2022-04-29 15:58:54', '문구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(64, 'google_child8', 'goole', 'kakao_guard5', '윤성3', 'dbs1501189@gmail.com', '2022-04-28 17:56:34', '문구', '한국', 25, NULL, '/iMintImage/member/CHILD/goole/google_111407574750602381880.png', 0, 'CHILD'),
	(113, 'google_child9', 'google', 'kakao_guard5', '한상민4062', 'alstkdgks@gmail.com', '2022-04-29 15:55:33', '완구', '한국', 25, NULL, NULL, 0, 'CHILD'),
	(62, 'kakao_guard1', 'kakao', NULL, 'J7472', 'bebig@kakao.com', '2022-04-28 16:01:27', '문구', '한국', 25, 'STV4IAJ4', '/iMintImage/member/GUARD/kakao/kakao_2200195249.png', 0, 'GUARD'),
	(127, 'kakao_guard2', 'kakao', NULL, '윤성일_보호자', 'dbs1501189@gmail.com', '2022-04-29 16:12:40', NULL, '대구 중구', 25, 'R86JBCAP', NULL, 0, 'GUARD'),
	(65, 'kakao_guard3', 'naver', NULL, '전해연3782', 'lillyine@naver.com', '2022-04-28 18:23:49', NULL, NULL, 25, NULL, NULL, 0, 'UN_GUARD'),
	(59, 'kakao_guard4', 'kakao', NULL, '해연', 'lillyine@kakao.com', '2022-04-28 15:49:00', '문구', '한국', 25, 'UK5X7R3H', NULL, 0, 'GUARD'),
	(61, 'kakao_guard5', 'naver', NULL, '윤성일', 'dbs2671@naver.com', '2022-04-28 15:49:38', '문구', '한국', 25, 'AK6I7T3J', '/iMintImage/member/GUARD/naver/naver_BkthPZQVXx5zyZCwb2-G4kUDdwN4ve_4iuHnCiauZ8U.jpg', 0, 'GUARD'),
	(56, 'naver_admin1', 'naver', NULL, '양정민2591', 'wjdals0814@naver.com', '2022-04-28 15:47:48', '교구', '서울', 25, NULL, NULL, 1, 'ADMIN'),
	(67, 'naver_admin2', 'naver', NULL, '윤성일_어드민', 'dbs267@naver.com', '2022-04-29 10:28:51', NULL, '대구 중구', 25, '3NFXPJM3', NULL, 0, 'ADMIN'),
	(54, 'naver_NXOiWYXGfEMB1C3kxD5QoQVUFC3Co28UaxwLAU-i7YU', 'naver', NULL, '유령요정', 'ghhjkk@naver.com', '2022-04-28 15:45:53', '문구', '한국', 25, 'OU6I021M', NULL, 0, 'GUARD');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;

-- 테이블 imint_copy.rating 구조 내보내기
CREATE TABLE IF NOT EXISTS `rating` (
  `rating_id` int NOT NULL AUTO_INCREMENT COMMENT '평가ID',
  `trx_id` int NOT NULL COMMENT '거래ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `rating_score` int NOT NULL COMMENT '평가점수',
  `rating_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '평가등록일자',
  PRIMARY KEY (`rating_id`),
  KEY `FK_rating_transaction` (`trx_id`),
  KEY `FK_rating_member` (`mb_id`),
  CONSTRAINT `FK_rating_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rating_transaction` FOREIGN KEY (`trx_id`) REFERENCES `transaction` (`trx_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.rating:~0 rows (대략적) 내보내기
DELETE FROM `rating`;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;

-- 프로시저 imint_copy.test_data 구조 내보내기
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

-- 테이블 imint_copy.transaction 구조 내보내기
CREATE TABLE IF NOT EXISTS `transaction` (
  `trx_id` int NOT NULL AUTO_INCREMENT COMMENT '거래ID',
  `trx_isdelete` tinyint(1) DEFAULT '0' COMMENT '예약취소시간',
  `trx_complete_date` datetime DEFAULT NULL COMMENT '거래완료시간',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci DEFAULT NULL COMMENT '구매자ID',
  `goods_id` int NOT NULL COMMENT '상품ID',
  PRIMARY KEY (`trx_id`) USING BTREE,
  KEY `transaction_FK` (`mb_id`),
  KEY `transaction_FK_1` (`goods_id`),
  CONSTRAINT `transaction_FK` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transaction_FK_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.transaction:~4 rows (대략적) 내보내기
DELETE FROM `transaction`;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`trx_id`, `trx_isdelete`, `trx_complete_date`, `mb_id`, `goods_id`) VALUES
	(3, 0, '2022-04-28 17:47:21', 'google_child19', 35),
	(4, 0, '2022-04-29 03:49:15', 'google_child2', 18),
	(5, 0, '2022-04-29 05:20:06', 'google_child12', 12),
	(6, 0, '2022-04-29 16:21:32', 'google_child23', 55);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;

-- 테이블 imint_copy.wishlist 구조 내보내기
CREATE TABLE IF NOT EXISTS `wishlist` (
  `wishlist_id` int NOT NULL AUTO_INCREMENT COMMENT '관심ID',
  `mb_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_da_0900_ai_ci NOT NULL COMMENT '회원ID',
  `goods_id` int NOT NULL COMMENT '상품ID',
  `wishlist_create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '관심등록일자',
  `wishlist_isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '관심철회여부',
  PRIMARY KEY (`wishlist_id`),
  KEY `FK_wishlist_member` (`mb_id`),
  KEY `FK_wishlist_goods` (`goods_id`),
  CONSTRAINT `FK_wishlist_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_wishlist_member` FOREIGN KEY (`mb_id`) REFERENCES `member` (`mb_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_da_0900_ai_ci;

-- 테이블 데이터 imint_copy.wishlist:~37 rows (대략적) 내보내기
DELETE FROM `wishlist`;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` (`wishlist_id`, `mb_id`, `goods_id`, `wishlist_create_date`, `wishlist_isdelete`) VALUES
	(29, 'google_child19', 35, '2022-04-28 17:30:43', 0),
	(30, 'google_child19', 33, '2022-04-28 17:30:47', 0),
	(31, 'google_child19', 29, '2022-04-28 17:30:53', 0),
	(32, 'google_child19', 26, '2022-04-28 17:31:00', 0),
	(33, 'google_child19', 24, '2022-04-28 17:31:06', 0),
	(34, 'google_child2', 34, '2022-04-28 17:36:15', 0),
	(35, 'google_child2', 18, '2022-04-28 17:36:28', 0),
	(36, 'google_child2', 31, '2022-04-28 17:36:57', 1),
	(37, 'google_child2', 33, '2022-04-28 17:37:12', 0),
	(38, 'google_child2', 36, '2022-04-28 17:44:00', 0),
	(39, 'google_child12', 35, '2022-04-28 18:30:43', 0),
	(40, 'google_child12', 34, '2022-04-28 18:30:46', 0),
	(41, 'google_child12', 36, '2022-04-29 11:16:34', 1),
	(42, 'google_child12', 16, '2022-04-29 01:34:22', 0),
	(43, 'google_child19', 38, '2022-04-29 05:17:39', 0),
	(44, 'google_child12', 25, '2022-04-29 11:25:31', 0),
	(45, 'google_child19', 41, '2022-04-29 13:37:05', 0),
	(46, 'google_child19', 28, '2022-04-29 13:37:31', 0),
	(47, 'google_child22', 45, '2022-04-29 16:00:31', 0),
	(48, 'google_child22', 43, '2022-04-29 16:00:40', 0),
	(49, 'google_child11', 36, '2022-04-29 16:01:06', 1),
	(50, 'google_child10', 45, '2022-04-29 16:01:15', 0),
	(51, 'google_child20', 44, '2022-04-29 16:02:17', 0),
	(52, 'google_child2', 46, '2022-04-29 16:02:32', 0),
	(53, 'google_child10', 38, '2022-04-29 16:02:43', 0),
	(54, 'google_child19', 46, '2022-04-29 16:02:52', 0),
	(55, 'google_child2', 48, '2022-04-29 16:05:30', 0),
	(56, 'google_child2', 49, '2022-04-29 16:05:48', 0),
	(57, 'google_child19', 48, '2022-04-29 16:05:53', 0),
	(58, 'google_child19', 52, '2022-04-29 16:11:45', 0),
	(59, 'google_child1', 52, '2022-04-29 16:12:39', 0),
	(60, 'google_child19', 53, '2022-04-29 16:12:43', 0),
	(61, 'google_child3', 46, '2022-04-29 16:17:52', 0),
	(62, 'google_child2', 54, '2022-04-29 16:19:54', 0),
	(63, 'google_child12', 55, '2022-04-29 16:24:41', 0),
	(64, 'google_child23', 55, '2022-04-29 16:24:45', 0),
	(65, 'google_child17', 44, '2022-04-29 16:40:57', 0);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
