package com.hq.storeMS.service.img.bs;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.hq.storeFileClient.service.file.apiData.InputStreamApiForm;
import com.hq.storeFileClient.service.file.bs.FileMgr;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeFileClient.service.file.data.UploadFileType;
import com.hq.storeMS.service.img.apiData.FileUploadApiForm;
import com.hq.storeMS.service.img.apiData.ImgResp;
import com.hq.storeMS.service.img.data.UploadModuleType;
import com.zenmind.common.hotSwap.HotSwap;

public class ImgMgr {
	
	public static ImgMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ImgMgr.class);
	}
	
	public ImgResp saveImg(FileUploadApiForm apiForm) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(apiForm.getImg().getInputStream(), apiForm.getImg().getOriginalFilename());
		fileApiForm.setFileType(apiForm.getFileType());
		fileApiForm.setModuleId(apiForm.getModuleId());
		fileApiForm.setModuleType(apiForm.getModuleType());
		return saveImgByInputStream(fileApiForm);
	}
	
	public ImgResp saveImg(InputStream is, String fileName, FileUploadApiForm apiForm) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(is, fileName);
		fileApiForm.setFileType(apiForm.getFileType());
		fileApiForm.setModuleId(apiForm.getModuleId());
		fileApiForm.setModuleType(apiForm.getModuleType());
		return saveImgByInputStream(fileApiForm);
	}
	
	private ImgResp saveImgByInputStream(InputStreamApiForm fileApiForm) throws Exception {
		ImgResp result = ImgResp.newInstance();
		FileResp fileResp = FileMgr.getInstance().saveFile(fileApiForm);
		if(fileResp!=null) {
			result.setImgPathList(fileResp.getImgPathList());
		}
		return result;
	}
	
	public ImgResp genJoinStoreQrCode(long storeId, InputStream is, String fileName) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(is, fileName);
		fileApiForm.setFileType(UploadFileType.IMG.getType());
		fileApiForm.setModuleId(String.valueOf(storeId));
		fileApiForm.setModuleType(UploadModuleType.Store.getType());
		return saveImgByInputStream(fileApiForm);
	}
	
	public ImgResp saveStorePayQrCode(long storeId, MultipartFile multipartFile) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		fileApiForm.setFileType(UploadFileType.IMG.getType());
		fileApiForm.setModuleId(String.valueOf(storeId));
		fileApiForm.setModuleType(UploadModuleType.Store.getType());
		return saveImgByInputStream(fileApiForm);
	}
	
	public ImgResp saveBuserImg(long buserId, MultipartFile multipartFile ) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		fileApiForm.setFileType(UploadFileType.IMG.getType());
		fileApiForm.setModuleId(String.valueOf(buserId));
		fileApiForm.setModuleType(UploadModuleType.Buser.getType());
		return saveImgByInputStream(fileApiForm);
	}

	public ImgResp saveStoreImg(long storeId, MultipartFile multipartFile ) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		fileApiForm.setFileType(UploadFileType.IMG.getType());
		fileApiForm.setModuleId(String.valueOf(storeId));
		fileApiForm.setModuleType(UploadModuleType.Store.getType());
		return saveImgByInputStream(fileApiForm);
	}
	
	public ImgResp saveProductImg(long storeId, String productId, MultipartFile multipartFile ) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		fileApiForm.setFileType(UploadFileType.IMG.getType());
		fileApiForm.setModuleId(String.valueOf(storeId));
		fileApiForm.setModuleType(UploadModuleType.Product.getType());
		return saveImgByInputStream(fileApiForm);
	}
	
	public ImgResp saveWxImg(String moduleId, MultipartFile multipartFile ) throws Exception{
		InputStreamApiForm fileApiForm = InputStreamApiForm.newInstance();
		fileApiForm.addFile(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		fileApiForm.setFileType(UploadFileType.IMG.getType());
		fileApiForm.setModuleId(moduleId);
		fileApiForm.setModuleType(UploadModuleType.WeChat.getType());
		return saveImgByInputStream(fileApiForm);
	}
	
}
