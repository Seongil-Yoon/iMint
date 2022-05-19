package multi.fclass.iMint.websocket.config;

import java.security.Principal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
@AllArgsConstructor
public class WebSocketPrincipal implements Principal {

	private final String name;

}
