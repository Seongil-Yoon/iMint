package multi.fclass.iMint.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import multi.fclass.iMint.chat.service.IChatService;

/**
 * @author GhostFairy
 *
 */
public class ChatInterceptor implements ChannelInterceptor {

	@Autowired
	IChatService chatService;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor sha = StompHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

		if (sha.getCommand().equals(StompCommand.CONNECT)) {
			String userName = sha.getFirstNativeHeader("user-name");
			String userNick = chatService.getNick(userName);
			if (userName.equals("") || userNick == null) {
				System.out.println("STOMP CONNECT: UNAUTHORIZED USER");
				return null;
			}
			sha.setUser(new ChatPrincipal(userName, userNick));
			System.out.println("STOMP CONN: " + sha.getUser().getName());
		} else if (sha.getCommand().equals(StompCommand.SUBSCRIBE) || sha.getCommand().equals(StompCommand.SEND)) {
			String userName = sha.getUser().getName();
			String destination = sha.getDestination();
			int chatroomId = Integer.parseInt(destination.substring(destination.lastIndexOf("/") + 1));
			if (!chatService.isChatroomJoinable(userName, chatroomId)) {
				return null;
			}
			System.out.println("STOMP SUB/SEND: " + sha.getUser().getName() + " to chatroom " + chatroomId);
		}

		return message;
	}
}
