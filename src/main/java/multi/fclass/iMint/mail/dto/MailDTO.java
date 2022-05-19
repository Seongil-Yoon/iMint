package multi.fclass.iMint.mail.dto;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MailDTO {
	@NonNull
	private String address;
	private String title;
	private HashMap<String, String> message;
	@Nullable
	private MultipartFile file;

//	@Builder
//	public MailDTO(String address, String title, HashMap<String, String> message) {
//		this.address = address;
//		this.title = title;
//		this.message = message;
//	}

}
