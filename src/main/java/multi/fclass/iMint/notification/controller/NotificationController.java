package multi.fclass.iMint.notification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.jose.shaded.json.JSONObject;

import multi.fclass.iMint.notification.dto.NotificationDTO;
import multi.fclass.iMint.notification.service.INotificationService;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author GhostFairy
 *
 */

@Controller
public class NotificationController {

	@Autowired
	INotificationService notifyService;

	@Autowired
	ParseMbId parseService;

	// REST API: 알림 목록 불러오기
	@GetMapping("/ws/notify/getnotifications")
	@ResponseBody
	public List<NotificationDTO> getNotifications(Authentication auth) {
		String myId = parseService.parseMbId(auth);

		return notifyService.getNotifications(myId);
	}

	// REST API: 알림 읽음 표시
	@PostMapping("/ws/notify/read")
	@ResponseBody
	public String markAsRead(Authentication auth, Integer notificationId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", notifyService.markAsRead(myId, notificationId));

		return out.toJSONString();
	}

	// REST API: 모든 알림 읽음 표시
	@PostMapping("/ws/notify/readall")
	@ResponseBody
	public String markAsReadAll(Authentication auth) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", notifyService.markAsReadAll(myId));

		return out.toJSONString();
	}

	// REST API: 알림 삭제
	@PostMapping("/ws/notify/delete")
	@ResponseBody
	public String deleteNotification(Authentication auth, Integer notificationId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", notifyService.deleteNotification(myId, notificationId));

		return out.toJSONString();
	}

	// REST API: 모든 알림 삭제
	@PostMapping("/ws/notify/deleteall")
	@ResponseBody
	public String deleteAllNotifications(Authentication auth) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", notifyService.deleteAllNotifications(myId));

		return out.toJSONString();
	}

}