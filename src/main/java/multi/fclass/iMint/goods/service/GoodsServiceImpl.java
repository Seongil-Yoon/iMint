package multi.fclass.iMint.goods.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.common.service.IFileService;
import multi.fclass.iMint.common.service.IUtilService;
import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.goods.dto.GoodsImagesDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
	@Autowired
	IGoodsDAO goodsDAO;
	
	@Autowired
	IUtilService utilService;
	
	@Autowired
	IFileService fileService;

	@Override
	public GoodsDTO goods(int goodsId) {
		return goodsDAO.goods(goodsId);
	}

	@Override
	public List<GoodsImagesDTO> goodsImageList(int goodsId) {
		List<GoodsImagesDTO> goodsImages = goodsDAO.goodsImageList(goodsId);
		if (goodsImages.isEmpty()) {
			goodsImages.add(new GoodsImagesDTO(null, goodsId, "/static/images/noimage.png", true, "noimage.png", null));
		}
		return goodsImages;
	}

	@Override
	public String goodsWrite(GoodsDTO goodsDto, List<MultipartFile> files) {
		List<String> paths = utilService.createGoodsPaths(goodsDto.getGoodsCreateDate());
		fileService.mkDir(paths); //폴더 생성
		String path = fileService.uploadGoodsImageFiles(paths, goodsDto.getGoodsId(), files); //경로에 파일업로드

		return path;
	}

}
