package multi.fclass.iMint.goods.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.common.code.ErrorCode;
import multi.fclass.iMint.common.exception.HandlableException;
import multi.fclass.iMint.common.exception.hadler.ForbiddenException;
import multi.fclass.iMint.common.exception.hadler.NotFoundException;
import multi.fclass.iMint.common.exception.hadler.UnauthorizedException;
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

	@Autowired
	HttpSession httpSession;

	@Value("${root}")
	String root;

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
	public int goodsWrite(String mbId, GoodsDTO goodsDto, List<MultipartFile> files) {
//		String sellerId = (String) httpSession.getAttribute("mbId");
//		String sellerNick = (String) httpSession.getAttribute("mbNick");
		if (!mbId.equals(goodsDto.getSellerId())) {
			throw new ForbiddenException(ErrorCode.FORBIDDEN);
		}
		goodsDAO.goodsInsert(goodsDto);
		int goodsId = goodsDto.getGoodsId();

		if (files != null && !files.isEmpty()) {
			// 상품글의 파일들을 올릴 경로("C:/iMint/goods/yyyy/MM/dd")를 배열로 반환
			List<String> paths = utilService.createGoodsPaths(goodsDAO.goodsDate(goodsId));
			fileService.mkDir(paths); // 폴더 생성
			// 경로에 파일업로드 and DB insert
			int goodsImagesId = fileService.uploadGoodsImageFiles(paths, goodsDto.getGoodsId(), files);
			if (goodsImagesId == -1) {
				return goodsImagesId;
//			throw new 
			}
		}

		return goodsId;
	}

	@Override
	public int goodsModify(String mbId, GoodsDTO goodsDto, List<MultipartFile> files) {
		System.out.println(mbId + "," + goodsDto.getSellerId());
//		if (!mbId.equals(goodsDto.getSellerId())) {
//			throw new ForbiddenException(ErrorCode.FORBIDDEN);
//		}
		int updateRows = 0;
		int goodsId = -1;
		goodsId = goodsDAO.goods(goodsDto.getGoodsId()).getGoodsId();
		if (goodsId == -1) {
			// 수정할 게시글이 없으므로 not found
			throw new NotFoundException(ErrorCode.NOT_FOUND);
		}
		goodsDAO.goodsUpdate(goodsDto);

		List<GoodsImagesDTO> images = goodsDAO.goodsImageList(goodsId);
		List<String> imagesPath = new ArrayList<String>();
		try {
			for (GoodsImagesDTO imageDTO : images) {
				imagesPath.add(URLDecoder.decode(imageDTO.getGoodsImagesPath(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		fileService.rmFiles(imagesPath);
		updateRows = goodsDAO.goodsImagesDelete(goodsId);

		if (files != null && !files.isEmpty()) {
			// 상품글의 파일들을 올릴 경로("C:/iMint/goods/yyyy/MM/dd")를 배열로 반환
			List<String> paths = utilService.createGoodsPaths(goodsDAO.goodsDate(goodsId));
			fileService.mkDir(paths); // 폴더 생성
			// 경로에 파일업로드 and DB insert
			int goodsImagesId = fileService.uploadGoodsImageFiles(paths, goodsDto.getGoodsId(), files);
			if (goodsImagesId == -1) {
				return goodsImagesId;
//			throw new 
			}
		}
		return updateRows;
	}

	@Override
	public int goodsDelete(int goodsId, String mbId) {
//		mbId = Authentication auth
		GoodsDTO goodsDTO = goodsDAO.goods(goodsId);
		int result = 0;

		if (goodsDTO == null) {
			throw new NotFoundException(ErrorCode.NOT_FOUND);
		}
		if (mbId.isEmpty() || goodsDTO.getSellerId().equals(mbId) == false) {
			// 로그인한 아이디와 작성자 아이디가 달라서 권한없음 오류보냄
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		} else {
			// 로그인 아이디 와 작성자 아이디 가 같아서 글삭제

			// 실제파일은 삭제하지 않고, DB의 isdelete값만 1로 변경
			System.out.println("상품삭제 : " + goodsId + ", " + mbId);
			goodsDAO.goodsIsdelete(goodsId, mbId);
			goodsDAO.goodsImagesIsdelete(goodsId);
			result = 1;

		}
		return result;
	}

}
