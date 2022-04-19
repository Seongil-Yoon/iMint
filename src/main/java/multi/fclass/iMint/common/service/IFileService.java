package multi.fclass.iMint.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	public String mkDir(List<String> paths);

	public int uploadGoodsImageFiles(List<String> paths, int goodsId, List<MultipartFile> files);

	public int rmFiles(List<String> imagesPath);
}
