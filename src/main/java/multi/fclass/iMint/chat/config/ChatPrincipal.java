package multi.fclass.iMint.chat.config;

import java.security.Principal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
@AllArgsConstructor
public class ChatPrincipal implements Principal {

	private final String name;
	private final String nick;

}
