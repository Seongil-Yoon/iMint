package multi.fclass.iMint.mail;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class MailHandler {
    private JavaMailSender sender;
    private MimeMessage message;
    private MimeMessageHelper msgHelper;

    public MailHandler(JavaMailSender sender) throws MessagingException {
        this.sender = sender;
        message = sender.createMimeMessage();
        msgHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setFrom(String fromAddress) throws MessagingException {
        msgHelper.setFrom(fromAddress);
    }

    public void setTo(String email) throws MessagingException {
        msgHelper.setTo(email);
    }

    public void setSubject(String subject) throws MessagingException {
        msgHelper.setSubject(subject);
    }

    public void setText(String text, boolean useHtml) throws MessagingException {
        msgHelper.setText(text, useHtml);
    }

    public void setAttach(String displayFileName, MultipartFile file) throws MessagingException {
        msgHelper.addAttachment(displayFileName, file);
    }

    public void setInline(String contentId, MultipartFile file) throws MessagingException, IOException {
        msgHelper.addInline(contentId, new ByteArrayResource(file.getBytes()), "image/jpeg");
    }

    public void send() {
        try {
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
