package multi.fclass.iMint.notification.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GhostFairy
 *
 */
@Getter
@Setter
public class NotificationDTO {

	private int id; // 알림 ID
	private String category; // 알림 분류
	private String mbId; // 알림 수신 회원 ID
	private String message; // 알림 내용
	private boolean isRead; // 알림 읽음여부
	private boolean isDeleted; // 알림 삭제여부
	private LocalDateTime sendDate; // 알림 전송일시

}
