package multi.fclass.iMint.member.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jungmin, Yang
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ThumbnailDTO {

	String mbId;
	String Provider;
	Role mbRole;
	MultipartFile mbThumbnail;
	
}
