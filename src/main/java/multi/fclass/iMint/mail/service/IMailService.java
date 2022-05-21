package multi.fclass.iMint.mail.service;

import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.mail.dto.MailDTO;

/**
 * @author Seongil, Yoon
 *
 */
public interface IMailService {
	public void mailSend(MailDTO mailDto);
	public void mailSend2(MailDTO mailDto);

	public void fileMailSend(MailDTO mailDto, String htmlContent);
	public void fileMailSend2(MailDTO mailDto, String htmlContent);

	public void notiMailSend(String address, String title, String targetNick, GoodsDTO goodsDto, String goodsThumbnail);
}
