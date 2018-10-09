package com.hq.chainStore.service.img.bs;

import java.io.File;
import java.util.List;

import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.img.apiData.ImgResp;
import com.hq.chainStore.service.img.data.ImgDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ImgMgr {
	
	public static ImgMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ImgMgr.class);
	}

	/**
	 * 
	 * @Deprecated 已经被saveImg(FileUploadApiForm apiForm, File file)替代
	 *
	 */
	@Deprecated
	public ImgResp saveImg(Object postParam) {
		return ImgDAO.getInstance().saveImg(postParam);
	}
	
	public ImgResp saveImg(FileUploadApiForm apiForm, File file) {
		return ImgDAO.getInstance().saveImg(apiForm, file);
	}
	
	public ImgResp saveImgs(FileUploadApiForm apiForm, List<File> files) {
		return ImgDAO.getInstance().saveImgs(apiForm, files);
	}
	
	public ImgResp saveImg(String fileType, String moduleType, String moduleId, File file) {
		FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
		apiForm.setFileType(fileType);
		apiForm.setModuleType(moduleType);
		apiForm.setModuleId(moduleId);
		return saveImg(apiForm, file);
	}
	
	public ImgResp saveBUserImg(long buserId, List<File> files) {
		return ImgDAO.getInstance().saveBUserImg(buserId, files);
	}
	
	public ImgResp saveStoreImg(long storeId, List<File> files) {
		return ImgDAO.getInstance().saveStoreImg(storeId, files);
	}
	
	/**
	 * 
	 * @Deprecated 已经被saveStorePayQrCode(long storeId, Integer payType, File file)替代
	 *
	 */
	@Deprecated
	public ImgResp saveStorePayQrCode(long storeId, Object postParam) {
		return ImgDAO.getInstance().saveStorePayQrCode(storeId, postParam);
	}
	
	public ImgResp saveStorePayQrCode(long storeId, Integer payType, File file) {
		return ImgDAO.getInstance().saveStorePayQrCode(storeId, payType, file);
	}
	
	public ImgResp saveProductImg(long storeId, String productId, List<File> files) {
		return ImgDAO.getInstance().saveProductImg(storeId, productId, files);
	}
	
}
