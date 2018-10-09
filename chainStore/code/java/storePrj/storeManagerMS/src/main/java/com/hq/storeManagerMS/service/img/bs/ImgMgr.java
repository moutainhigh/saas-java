package com.hq.storeManagerMS.service.img.bs;

import java.io.InputStream;

import com.hq.storeFileClient.service.file.apiData.InputStreamApiForm;
import com.hq.storeFileClient.service.file.bs.FileMgr;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeManagerMS.service.img.apiData.FileUploadApiForm;
import com.hq.storeManagerMS.service.img.apiData.ImgResp;
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
	
}
