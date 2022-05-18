package multi.fclass.iMint.websocket.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import multi.fclass.iMint.block.service.IBlockService;
import multi.fclass.iMint.chat.service.IChatService;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.websocket.config.WebSocketPrincipal;

/**
 * @author GhostFairy
 *
 */
public class WebSocketInterceptor implements ChannelInterceptor {

	@Autowired
	IChatService chatService;

	@Autowired
	IBlockService blockService;

	@Autowired
	ParseMbId parseService;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor sha = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

		if (sha.getCommand().equals(StompCommand.CONNECT)) {
			String userName = sha.getFirstNativeHeader("user-name");
			if (userName == null || userName.equals("")) {
				return null;
			}
			sha.setUser(new WebSocketPrincipal(userName));
		} else if (sha.getCommand().equals(StompCommand.SUBSCRIBE)) {
			String destination = sha.getDestination();
			destination = destination.substring(destination.lastIndexOf("/") + 1);

			if (!destination.equals("notify")) {
				String userName = sha.getUser().getName();
				int chatroomId = Integer.parseInt(destination.substring(destination.lastIndexOf("/") + 1));
				if (!chatService.isChatroomJoinable(userName, chatroomId)) {
					return null;
				}
			}
		} else if (sha.getCommand().equals(StompCommand.SEND)) {
			String userName = sha.getUser().getName();
			String destination = sha.getDestination();
			int chatroomId = Integer.parseInt(destination.substring(destination.lastIndexOf("/") + 1));
			if (parseService.getRoleMbId(userName) != Role.CHILD
					|| !chatService.isChatroomJoinable(userName, chatroomId)) {
				return null;
			}
		}

		return message;
	}
}
