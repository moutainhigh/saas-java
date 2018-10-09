package com.hq.storeManagerRestClient.service.img.bs;

import java.io.File;

import com.hq.storeManagerRestClient.service.img.apiData.FileUploadApiForm;
import com.hq.storeManagerRestClient.service.img.apiData.ImgResp;
import com.hq.storeManagerRestClient.service.img.data.ImgDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ImgMgr {
	
	public static ImgMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ImgMgr.class);
	}

	public ImgResp saveImg(FileUploadApiForm apiForm, File file) {
		return ImgDAO.getInstance().saveImg(apiForm, file);
	}
	
	public ImgResp saveImg(String fileType, String moduleType, String moduleId, File file) {
		FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
		apiForm.setFileType(fileType);
		apiForm.setModuleType(moduleType);
		apiForm.setModuleId(moduleId);
		return saveImg(apiForm, file);
	}
	
}
