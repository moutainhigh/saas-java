package com.hq.storeManagerMS.service.img.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.common.log.MainLog;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.hq.storeManagerMS.service.img.apiData.FileUploadApiForm;
import com.hq.storeManagerMS.service.img.apiData.FilesUploadApiForm;
import com.hq.storeManagerMS.service.img.apiData.ImgResp;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ImgHandler {
	
	public static ImgHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ImgHandler.class);
	}
	
	public ReqResult<ImgResp> saveImg(FileUploadApiForm apiForm) {
		ReqResult<ImgResp> result = ReqResult.newInstance(false, ImgResp.class);
		try {
			ImgResp imgResp = ImgMgr.getInstance().saveImg(apiForm);
			result.setTarget(imgResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Img, "ImgHandler[saveImg]", "", e);
		}
		return result;
	}
	
	public ReqResult<ImgResp> saveImgs(FilesUploadApiForm apiForm) {
		ReqResult<ImgResp> result = ReqResult.newInstance(false, ImgResp.class);
		try {
			MultipartFile[] imgs = apiForm.getImgs();
			if(imgs == null || imgs.length == 0){
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("上传的图片为空");
			}else{
				ImgResp imgResp = ImgResp.newInstance();
				List<String> imgPathList = new ArrayList<String>();
				for (MultipartFile img : imgs) {
					FileUploadApiForm addForm = FileUploadApiForm.newInstance();
					FastBeanCopyer.getInstance().copy(apiForm, addForm);
					addForm.setImg(img);
					ImgResp temp = ImgMgr.getInstance().saveImg(addForm);
					imgPathList.addAll(temp.getImgPathList());
				}
				imgResp.setImgPathList(imgPathList);
				result.setTarget(imgResp);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Img, "ImgHandler[saveImgs]", "", e);
		}
		return result;
	}
}
