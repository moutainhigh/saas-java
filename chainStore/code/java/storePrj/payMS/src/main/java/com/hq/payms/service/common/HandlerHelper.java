package com.hq.payms.service.common;

import org.apache.shiro.authz.AuthorizationException;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.zenmind.common.hotSwap.HotSwap;

public class HandlerHelper {

	public static HandlerHelper getInstance() {
		return HotSwap.getInstance().getSingleton(HandlerHelper.class);
	}
	
	public <T> void handleException(ExceptionInfo exceptionInfo, Exception e) {
		
		ReqResult<?> result = exceptionInfo.getResult();
		LogModule logModule = exceptionInfo.getLogModule();
		String logId = exceptionInfo.getLogId();
		String reason = exceptionInfo.getReason();
		
		if(e instanceof AuthorizationException){
			result.setStatus(RespStatus.UN_AUTHORIZED);
			result.setTips("用户请求未授权");
			MainLog.error(logModule, logId, reason, e);
		}else{
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(logModule, logId, reason, e);
		}
	}
	
	// 根据operateTips 生成ReqResult
	public void handleReqResult(ReqResult<?> result, OperateTips operateTips) {
		result.setSuccess(operateTips.isSuccess());
		result.setStatus(RespStatus.INVALID_REQUEST);
		result.setTips(operateTips.getTips());
	}
	
}
