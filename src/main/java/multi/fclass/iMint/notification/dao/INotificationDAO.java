package multi.fclass.iMint.notification.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.notification.dto.NotificationDTO;

/**
 * @author GhostFairy
 *
 */

@Mapper
public interface INotificationDAO {

	public Integer sendNotification(NotificationDTO notification);

	public List<NotificationDTO> getNotifications(String mbId);

	public int markAsRead(String mbId, int id);

	public int markAsReadAll(String mbId);

	public int deleteNotification(String mbId, int id);

	public int deleteAllNotifications(String mbId);

}
