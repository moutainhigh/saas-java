package com.hq.payms.service.wxpay.bs;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.common.HandlerHelper;
import com.hq.payms.service.common.OperateTips;
import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RespStatus;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.hq.payms.service.wxpay.apiData.MicroPayApiForm;
import com.hq.payms.service.wxpay.apiData.MiniProgramPayResp;
import com.hq.payms.service.wxpay.apiData.RefundApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderForMiniProgramApiForm;
import com.hq.payms.service.wxpay.bs.handle.WxpayBuildReqHelper;
import com.hq.payms.service.wxpay.bs.handle.WxpayCheckFormHelper;
import com.hq.payms.service.wxpay.bs.handle.WxpayRespHandle;
import com.hq.payms.service.wxpay.data.CommonResp;
import com.hq.payms.service.wxpay.data.DownloadBillReq;
import com.hq.payms.service.wxpay.data.MicroPayReq;
import com.hq.payms.service.wxpay.data.MicroPayResp;
import com.hq.payms.service.wxpay.data.OrderQueryOrCloseReq;
import com.hq.payms.service.wxpay.data.OrderQueryResp;
import com.hq.payms.service.wxpay.data.RefundReq;
import com.hq.payms.service.wxpay.data.UnifiedOrderReq;
import com.hq.payms.service.wxpay.data.UnifiedOrderResp;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class WxpayHandler {
	private final LogModule logModule = LogModule.Wxpay;

	public static WxpayHandler getInstance() {
		return HotSwap.getInstance().getSingleton(WxpayHandler.class);
	}
	
	public ReqResult<QrCodeResp> doUnifiedOrder(UnifiedOrderApiForm form, String remoteAddr) {
		ReqResult<QrCodeResp> result = ReqResult.newInstance(false, QrCodeResp.class);
		try {
			OperateTips checkFormTips = WxpayCheckFormHelper.getInstance().check(form);
			if(!checkFormTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, checkFormTips);
				return result;
			}
			UnifiedOrderReq reqParam = WxpayBuildReqHelper.getInstance().buildUnifiedOrderReq(form, remoteAddr);
			UnifiedOrderResp unifiedOrderResp = WxpayMgr.getInstance().doUnifiedOrder(reqParam);
			QrCodeResp qrCodeResp = WxpayRespHandle.getInstance().handleUnifiedOrderResp(unifiedOrderResp, reqParam, form);
			if(qrCodeResp != null) {
				result.setTarget(qrCodeResp);
				result.setSuccess(true);
			}else {
				transportErrMsgToReqResult(result, unifiedOrderResp);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doUnifiedOrder]", "", e);
		}
		return result;
	}
	
	public ReqResult<MiniProgramPayResp> doUnifiedOrderForMiniProgram(UnifiedOrderForMiniProgramApiForm form, String remoteAddr) {
		ReqResult<MiniProgramPayResp> result = ReqResult.newInstance(false, MiniProgramPayResp.class);
		try {
			OperateTips checkFormTips = WxpayCheckFormHelper.getInstance().check(form);
			if(!checkFormTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, checkFormTips);
				return result;
			}
			UnifiedOrderReq reqParam = WxpayBuildReqHelper.getInstance().buildUnifiedOrderReqForMiniProgram(form, remoteAddr);
			UnifiedOrderResp unifiedOrderResp = WxpayMgr.getInstance().doUnifiedOrder(reqParam);
			MiniProgramPayResp miniProgramPayResp = WxpayRespHandle.getInstance().handleUnifiedOrderRespForMiniProgram(unifiedOrderResp, reqParam, form);
			if(miniProgramPayResp != null) {
				result.setTarget(miniProgramPayResp);
				result.setSuccess(true);
			}else {
				transportErrMsgToReqResult(result, unifiedOrderResp);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doUnifiedOrderForMiniProgram]", "", e);
		}
		return result;
	}
	
	public ReqResult<Void> doMicroPay(MicroPayApiForm form, String remoteAddr) {
		ReqResult<Void> result = ReqResult.newInstance(false, Void.class);
		try {
			OperateTips checkFormTips = WxpayCheckFormHelper.getInstance().check(form);
			if(!checkFormTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, checkFormTips);
				return result;
			}
			MicroPayReq microPayReq = WxpayBuildReqHelper.getInstance().buildMicroPayReq(form, remoteAddr);
			MicroPayResp microPayResp = WxpayMgr.getInstance().doMicroPay(microPayReq);
			OperateTips operateTips = WxpayRespHandle.getInstance().handleMicroPayResp(microPayResp, microPayReq, form);
			if(operateTips.isSuccess()) {
				result.setSuccess(true);
			}else {
				transportErrMsgToReqResult(result, microPayResp);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doMicroPay]", "", e);
		}
		return result;
	}
	
	public void receiveNotify(HttpServletRequest request, HttpServletResponse response) {
		try {
			WxpayRespHandle.getInstance().handleNotify(request, response);
		} catch (Exception e) {
			MainLog.error(logModule, "WxpayHandler[receiveNotify]", "", e);
		}
	}

	public ReqResult<OrderQueryResp> doOrderQuery(long storeId, String out_trade_no) {
		ReqResult<OrderQueryResp> result = ReqResult.newInstance(false, OrderQueryResp.class);
		try {
			OrderQueryOrCloseReq reqParam = WxpayBuildReqHelper.getInstance().buildOrderQueryOrCloseReq(storeId, out_trade_no);
			OrderQueryResp orderQueryResp = WxpayMgr.getInstance().doOrderQuery(reqParam);
			result.setTarget(orderQueryResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doOrderQuery]", "", e);
		}
		return result;
	}

	public ReqResult<CommonResp> doOrderClose(long storeId, String out_trade_no) {
		ReqResult<CommonResp> result = ReqResult.newInstance(false, CommonResp.class);
		try {
			OrderQueryOrCloseReq reqParam = WxpayBuildReqHelper.getInstance().buildOrderQueryOrCloseReq(storeId, out_trade_no);
			CommonResp commonResp = WxpayMgr.getInstance().doOrderClose(reqParam);
			result.setTarget(commonResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doOrderClose]", "", e);
		}
		return result;
	}

	public ReqResult<CommonResp> doOrderReverse(long storeId, String out_trade_no) {
		ReqResult<CommonResp> result = ReqResult.newInstance(false, CommonResp.class);
		try {
			OrderQueryOrCloseReq reqParam = WxpayBuildReqHelper.getInstance().buildOrderQueryOrCloseReq(storeId, out_trade_no);
			CommonResp commonResp = WxpayMgr.getInstance().doOrderReverse(reqParam);
			result.setTarget(commonResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doOrderReverse]", "", e);
		}
		return result;
	}
	
	public ReqResult<CommonResp> doRefund(RefundApiForm form) {
		ReqResult<CommonResp> result = ReqResult.newInstance(false, CommonResp.class);
		try {
			RefundReq reqParam = WxpayBuildReqHelper.getInstance().buildRefundReq(form);
			CommonResp commonResp = WxpayMgr.getInstance().doRefund(reqParam);
			//TODO 是否需要对 CommonResp做进一步处理
			if(commonResp.isResultSuccess()) {
				WxpayRecord payRecord = WxpayRecordMgr.getInstance().findByOutTradeNo(form.getOut_trade_no());
				payRecord.setRefund_fee(form.getRefund_fee());
				payRecord.setOut_refund_no(form.getOut_refund_no());
				WxpayRecordMgr.getInstance().update(payRecord);
			}
			result.setTarget(commonResp);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doRefund]", "", e);
		}
		return result;
	}
	
	public void receiveRefundNotify(HttpServletRequest request, HttpServletResponse response) {
		try {
			WxpayRespHandle.getInstance().handleRefundNotify(request, response);
		} catch (Exception e) {
			MainLog.error(logModule, "WxpayHandler[receiveRefundNotify]", "", e);
		}
	}
	
	public ReqResult<String> doDownloadBill(String bill_date, String bill_type) {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		try {
			DownloadBillReq reqParam = WxpayBuildReqHelper.getInstance().buildDownloadBillReq(bill_date, bill_type);
			Map<String, String> respMap = WxpayMgr.getInstance().doDownloadBill(reqParam);
			WxpayRespHandle.getInstance().handleDownloadBillResp(respMap);
			String respJson = JsonUtil.getInstance().toJson(respMap);
			result.setTarget(respJson);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doDownloadBill]", "", e);
		}
		return result;
	}
	
	
	private void transportErrMsgToReqResult(ReqResult<?> result, CommonResp commonResp) {
		if(commonResp.isReturnMsgNotOk()) {
			result.setTips(commonResp.getReturn_msg());
		}else {
			result.setTips(commonResp.getErr_code_des());
		}
		result.setStatus(RespStatus.INVALID_REQUEST);
		result.setSuccess(false);
	}
	
}
