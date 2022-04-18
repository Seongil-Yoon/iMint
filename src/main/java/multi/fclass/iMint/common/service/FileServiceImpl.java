package multi.fclass.iMint.common.service;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

@Service
public class FileServiceImpl implements IFileService {
	@Autowired
	IUtilService utilService;

	@Autowired
	IGoodsDAO goodsDAO; // 파일업로드 후 DB insert

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
	public int uploadGoodsImageFiles(List<String> paths, int goodsId, List<MultipartFile> files) {
//    	String path = "C:\\iMint\\goods\\YYYY\\MM\\";
		String realPath = utilService.completePath(paths);
		String mappingPath = utilService.completePath(paths, 1);
		String fileName = null;
		String fileOriginName = null;
		String fileOriginNameExt = null;
		GoodsImagesDTO goodsImagesDTO = null;
		int goodsImagesId = 0;
		try {
			int index = 0;
			Boolean isThumbnail = false;
			if (!files.isEmpty()) {
				for (MultipartFile file : files) {
					isThumbnail = false;
					if (index == 0) {
						isThumbnail = true;
					}
					UUID uuid = UUID.randomUUID();
					SimpleDateFormat foramt1 = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
					String formatToday = foramt1.format(new Date());
					String originalFilename = URLEncoder.encode(file.getOriginalFilename(), "UTF-8");
					fileOriginName =  FilenameUtils.getBaseName(originalFilename);
					fileOriginNameExt = originalFilename.substring(originalFilename.lastIndexOf("."));
					// ex) .png, .jpg, .gif
					fileName = goodsId + "_" + fileOriginName + "_" + formatToday + "(" + uuid + ")" + fileOriginNameExt;
					byte[] fileData = file.getBytes();
					File target = new File(realPath, fileName);

					FileCopyUtils.copy(fileData, target);
					goodsImagesDTO = GoodsImagesDTO.builder()
							.goodsId(goodsId)
							.goodsImagesPath("/iMintImage/" + mappingPath + fileName)
							.goodsImagesThumbnail(isThumbnail)
							.goodsImagesOriginname(file.getOriginalFilename())
						.build();

					goodsDAO.goodsImagesInsert(goodsImagesDTO);
					goodsImagesId = goodsImagesDTO.getGoodsImagesId();

					index++;
				}
			} else {
				goodsImagesId = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			in.close();
//			out.close();
		}
		return goodsImagesId;
	}
}
