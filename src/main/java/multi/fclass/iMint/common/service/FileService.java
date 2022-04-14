package multi.fclass.iMint.common.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

@Service
public class FileService implements IFileService {
	@Autowired
	IUtilService utilService;

	@Autowired
	IGoodsDAO goodsDAO; // 파일업로드 후 DB insert

	@Autowired
	GoodsImagesDTO goodsImagesDTO;

	@Value("${route}")
	String route;

	// 폴더 생성
	@Override
	public String mkDir(List<String> paths) {
		String completePath = "";
		for (String path : paths) {
			File dirPath = new File(completePath += path + route);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
		return completePath;
	}

	// 파일업로드
	@Override
	public String uploadGoodsImageFiles(List<String> paths, int goodsId,List<MultipartFile> files) {
//    	String path = "C:\\iMint\\goods\\YYYY\\MM";
		String path = utilService.completePath(paths);
		String fileName = null;
		try {
			for (MultipartFile file : files) {
				UUID uuid = UUID.randomUUID();
				SimpleDateFormat foramt1 = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
				String formatToday = foramt1.format(new Date());
				fileName = file.getOriginalFilename() + "_" + formatToday + "(" + uuid + ")" + ".png";
				byte[] fileData = file.getBytes();
				File target = new File(path, fileName);

				FileCopyUtils.copy(fileData, target);
				
				
//				
//				WebMvcConfigurer에다가 별칭경로/파일명
//				goodsImagesDTO.builder()
//					.goodsId(goodsId)
//					.goodsImagesPath(path)
//				goodsDAO.goodsImagesInsert(null)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path.concat(route).concat(fileName);
	}
}
