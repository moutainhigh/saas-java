package com.hq.customerMS.service.pay.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.service.common.ExceptionInfo;
import com.hq.customerMS.service.common.HandlerHelper;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.storeClient.service.pay.apiData.BeScanApiForm;
import com.hq.storeClient.service.pay.apiData.MiniProgramApiForm;
import com.hq.storeClient.service.pay.apiData.ScanApiForm;
import com.hq.storeClient.service.pay.data.MiniProgramPayResp;
import com.hq.storeClient.service.pay.data.PayResp;
import com.zenmind.common.hotSwap.HotSwap;

public class PayHandler {
	
	private final LogModule logModule = LogModule.Pay;
	
	public static PayHandler getInstance(){
		return HotSwap.getInstance().getSingleton(PayHandler.class);
	}
	
	public ReqResult<PayResp> beScan(BeScanApiForm form) {
		ReqResult<PayResp> result = ReqResult.newInstance(false, PayResp.class);
		try {
			PayResp payResp = PayMgr.getInstance().beScan(form);
			result.setTarget(payResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "PayHandler[beScan]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<PayResp> scan(ScanApiForm form) {
		ReqResult<PayResp> result = ReqResult.newInstance(false, PayResp.class);
		try {
			PayResp payResp = PayMgr.getInstance().scan(form);
			result.setTarget(payResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "PayHandler[scan]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<MiniProgramPayResp> miniProgramPay(MiniProgramApiForm form) {
		ReqResult<MiniProgramPayResp> result = ReqResult.newInstance(false, MiniProgramPayResp.class);
		try {
			MiniProgramPayResp miniProgramPayResp = PayMgr.getInstance().miniProgramPay(form);
			result.setTarget(miniProgramPayResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "PayHandler[miniProgramPay]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}
