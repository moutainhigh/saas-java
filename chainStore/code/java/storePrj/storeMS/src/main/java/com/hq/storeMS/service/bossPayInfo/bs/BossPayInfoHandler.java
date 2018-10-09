package com.hq.storeMS.service.bossPayInfo.bs;

import org.springframework.web.multipart.MultipartFile;

import com.hq.payRestClient.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payRestClient.service.bossPayInfo.data.BossPayInfo;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.bossPayInfo.apiData.CertFileUpLoadForm;
import com.hq.storeMS.service.common.EvnMarkEnum;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class BossPayInfoHandler {
	
	private final LogModule logModule = LogModule.BossPayInfo;
	
	public static BossPayInfoHandler getInstance(){
		return HotSwap.getInstance().getSingleton(BossPayInfoHandler.class);
	}
	
	public ReqResult<BossPayInfo> add(BossPayInfoAddApiForm form) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			BossPayInfo target = BossPayInfoMgr.getInstance().add(form);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BossPayInfoHandler[add]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<BossPayInfo> update(long id, BossPayInfoAddApiForm updateForm) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			BossPayInfo target = BossPayInfoMgr.getInstance().update(id, updateForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BossPayInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<BossPayInfo> get(long id) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			BossPayInfo target = BossPayInfoMgr.getInstance().get(id);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BossPayInfoHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<BossPayInfo> findByStoreId(long storeId) {
		ReqResult<BossPayInfo> result = ReqResult.newInstance(false, BossPayInfo.class);
		try {
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			BossPayInfo target = BossPayInfoMgr.getInstance().findByStoreId(storeId);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BossPayInfoHandler[findByStoreId]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<FileResp> uploadCertFile(CertFileUpLoadForm upForm) {
		ReqResult<FileResp> result = ReqResult.newInstance(false, FileResp.class);
		try {
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			MultipartFile file = upForm.getFile();
			if(file == null){
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("上传的文件为空");
			}else{
				FileResp fileResp = BossPayInfoMgr.getInstance().uploadCertFile(upForm);
				result.setTarget(fileResp);
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Img, "BossPayInfoHandler[saveCertFile]", "", e);
		}
		return result;
	}

}
