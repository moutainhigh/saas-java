package com.hq.customerRestClient.service.img.bs;

import java.io.File;
import java.util.List;

import com.hq.customerRestClient.service.img.apiData.FileUploadApiForm;
import com.hq.customerRestClient.service.img.apiData.ImgResp;
import com.hq.customerRestClient.service.img.data.ImgDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class ImgClientMgr {
	
	public static ImgClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ImgClientMgr.class);
	}

	public ImgResp saveImg(FileUploadApiForm apiForm, File file) {
		return ImgDAO.getInstance().saveImg(apiForm, file);
	}
	
	public ImgResp saveImgs(FileUploadApiForm apiForm, List<File> files) {
		return ImgDAO.getInstance().saveImgs(apiForm, files);
	}
	
}
