package com.hq.chainStore.service.storeFile.bs;

import java.util.List;

import com.hq.chainStore.service.common.ReqResult;
import com.hq.chainStore.service.common.RespStatus;
import com.hq.chainStore.service.storeFile.apiData.FileOriginForm;
import com.hq.chainStore.service.storeFile.apiData.FileResp;
import com.hq.chainStore.service.storeFile.apiData.FileUploadApiForm;
import com.hq.common.log.LogModule;
import com.hq.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class FileHandler {
	
	public static FileHandler getInstance() {
		return HotSwap.getInstance().getSingleton(FileHandler.class);
	}
	
	public ReqResult<FileResp> saveFileWithOriginInfo(FileOriginForm originForm) {
		ReqResult<FileResp> result = ReqResult.newInstance(false, FileResp.class);
		FileResp imgResp = FileResp.newInstance();
		try {
			String path = FileMgr.getInstance().saveFileWithOriginInfo(originForm);
			imgResp.addPath(path);
			result.setTarget(imgResp);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Tmp, "FileHandler[saveFileWithOriginInfo]", "", e);
		}
		return result;
	}
	
	public ReqResult<FileResp> uploadFile(FileUploadApiForm uploadForm ) {
		ReqResult<FileResp> result = ReqResult.newInstance(false, FileResp.class);
		FileResp imgResp = FileResp.newInstance();
		try {
			List<String> list = FileMgr.getInstance().uploadFile(uploadForm);
			imgResp.setImgPathList(list);
			result.setTarget(imgResp);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Tmp, "FileHandler[uploadFile]", "", e);
		}
		return result;
	}
	
	public ReqResult<FileResp> uploadExcel(FileUploadApiForm uploadForm ) {
		ReqResult<FileResp> result = ReqResult.newInstance(false, FileResp.class);
		FileResp imgResp = FileResp.newInstance();
		try {
			List<String> list = FileMgr.getInstance().uploadExcel(uploadForm);
			imgResp.setImgPathList(list);
			result.setTarget(imgResp);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Tmp, "FileHandler[uploadFile]", "", e);
		}
		return result;
	}
	
	public ReqResult<FileResp> delete(String filePath) {
		
		ReqResult<FileResp> result = ReqResult.newInstance(false, FileResp.class);
		
		try {
			
			boolean success = FileMgr.getInstance().delete(filePath);
			if(success){
				result.setSuccess(true);
				result.setStatus(RespStatus.OK);
			}else{
				result.setSuccess(false);
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("文件不存在");
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Tmp, "FileHandler[delete]", "", e);
		}
		return result;
	}

	public ReqResult<FileResp> uploadCertFile(FileUploadApiForm uploadForm) {
		ReqResult<FileResp> result = ReqResult.newInstance(false, FileResp.class);
		FileResp imgResp = FileResp.newInstance();
		try {
			List<String> list = FileMgr.getInstance().uploadCertFile(uploadForm);
			imgResp.setImgPathList(list);
			result.setTarget(imgResp);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Tmp, "FileHandler[uploadFile]", "", e);
		}
		return result;
	}
	
	
}
