package multi.fclass.iMint.mail.service;

import multi.fclass.iMint.mail.dto.MailDTO;

/**
 * @author Seongil, Yoon
 *
 */
public interface IMailService {
	public void mailSend(MailDTO mailDto);
}
