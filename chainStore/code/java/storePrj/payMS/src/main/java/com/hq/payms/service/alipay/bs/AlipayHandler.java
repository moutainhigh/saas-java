package com.hq.payms.service.alipay.bs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.alipay.apiData.TradePagePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePrecreateApiForm;
import com.hq.payms.service.alipay.apiData.TradeQueryApiForm;
import com.hq.payms.service.alipay.apiData.TradeRefundApiForm;
import com.hq.payms.service.alipay.bs.handle.AlipayBuildReqHelper;
import com.hq.payms.service.alipay.bs.handle.AlipayCheckFormHelper;
import com.hq.payms.service.alipay.bs.handle.AlipayRespHandle;
import com.hq.payms.service.alipay.data.TradePagePayReq;
import com.hq.payms.service.alipay.data.TradePayReq;
import com.hq.payms.service.alipay.data.TradePrecreateReq;
import com.hq.payms.service.alipay.data.TradeQueryReq;
import com.hq.payms.service.alipay.data.TradeRefundReq;
import com.hq.payms.service.common.HandlerHelper;
import com.hq.payms.service.common.OperateTips;
import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RespStatus;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayHandler {
	private final LogModule logModule = LogModule.Alipay;

	public static AlipayHandler getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayHandler.class);
	}
	
	private final String reason = "支付宝后台处理失败，请检查支付配置";
	
	public ReqResult<QrCodeResp> doTradePrecreate(TradePrecreateApiForm form) {
		ReqResult<QrCodeResp> result = ReqResult.newInstance(false, QrCodeResp.class);
		try {
			OperateTips checkFormTips = AlipayCheckFormHelper.getInstance().check(form);
			if(!checkFormTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, checkFormTips);
				return result;
			}
			TradePrecreateReq reqParam = AlipayBuildReqHelper.getInstance().buildTradePrecreateReq(form);
			AlipayF2FPrecreateResult alipayF2FPrecreateResult = AlipayMgr.getInstance().doTradePrecreate(reqParam);
			QrCodeResp qrCodeResp = AlipayRespHandle.getInstance().handleAlipayF2FPrecreateResult(alipayF2FPrecreateResult, reqParam, form);
			if(qrCodeResp != null) {
				result.setTarget(qrCodeResp);
				result.setSuccess(true);
			} else {
				if(alipayF2FPrecreateResult.getResponse() != null) {
					result.setTips(alipayF2FPrecreateResult.getResponse().getSubMsg());
				}else {
					result.setTips(reason);
				}
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "AlipayHandler[doTradePrecreate]", "", e);
		}
		return result;
	}
	
	
	public ReqResult<Void> doTradePay(TradePayApiForm form) {
		ReqResult<Void> result = ReqResult.newInstance(false, Void.class);
		try {
			OperateTips checkFormTips = AlipayCheckFormHelper.getInstance().check(form);
			if(!checkFormTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, checkFormTips);
				return result;
			}
			TradePayReq tradePayReq = AlipayBuildReqHelper.getInstance().buildTradePayReq(form);
			AlipayF2FPayResult alipayF2FPayResult = AlipayMgr.getInstance().doTradePay(tradePayReq);
			OperateTips operateTips = AlipayRespHandle.getInstance().handleAlipayF2FPayResult(alipayF2FPayResult, tradePayReq, form);
			if(operateTips.isSuccess()) {
				result.setSuccess(true);
			}else {
				if(alipayF2FPayResult.getResponse() != null) {
					result.setTips(alipayF2FPayResult.getResponse().getSubMsg());
				}else {
					result.setTips(reason);
				}
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "AlipayHandler[doTradePay]", "", e);
		}
		return result;
	}
	
	
	public void receiveNotify(HttpServletRequest request, HttpServletResponse response) {
		try {
			AlipayRespHandle.getInstance().handleNotify(request, response);
		} catch (Exception e) {
			MainLog.error(logModule, "WxpayHandler[receiveNotify]", "", e);
		}
	}

	public ReqResult<AlipayF2FQueryResult> doTradeQuery(TradeQueryApiForm form) {
		ReqResult<AlipayF2FQueryResult> result = ReqResult.newInstance(false, AlipayF2FQueryResult.class);
		try {
			TradeQueryReq reqParam = AlipayBuildReqHelper.getInstance().buildTradeQueryReq(form.getStoreId(), form.getOutTradeNo());
			AlipayF2FQueryResult alipayF2FQueryResult = AlipayMgr.getInstance().doTradeQuery(reqParam);
			if(alipayF2FQueryResult.isTradeSuccess()) {
				result.setTarget(alipayF2FQueryResult);
				result.setSuccess(true);
			}else {
				result.setTips(reason);
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "WxpayHandler[doTradeQuery]", "", e);
		}
		return result;
	}

	
	public ReqResult<AlipayF2FRefundResult> doTradeRefund(TradeRefundApiForm form) {
		ReqResult<AlipayF2FRefundResult> result = ReqResult.newInstance(false, AlipayF2FRefundResult.class);
		try {
			OperateTips checkFormTips = AlipayCheckFormHelper.getInstance().checkTradeRefundApiForm(form);
			if(!checkFormTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, checkFormTips);
				return result;
			}
			TradeRefundReq tradeRefundReq = AlipayBuildReqHelper.getInstance().buildTradeRefundReq(form);
			AlipayF2FRefundResult alipayF2FRefundResult = AlipayMgr.getInstance().doTradeRefund(tradeRefundReq);
			OperateTips operateTips = AlipayRespHandle.getInstance().handleAlipayF2FRefundResult(alipayF2FRefundResult, tradeRefundReq);
			if(operateTips.isSuccess()) {
				result.setTarget(alipayF2FRefundResult);
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "AlipayHandler[doTradeRefund]", "", e);
		}
		return result;
	}
	
	public ReqResult<String> doTradePagePay(TradePagePayApiForm form) {
		ReqResult<String> result = ReqResult.newInstance(false, String.class);
		try {
			OperateTips checkFormTips = AlipayCheckFormHelper.getInstance().check(form);
			if(!checkFormTips.isSuccess()){
				HandlerHelper.getInstance().handleReqResult(result, checkFormTips);
				return result;
			}
			TradePagePayReq tradePagePayReq = AlipayBuildReqHelper.getInstance().buildTradePagePayReq(form);
			String formHtml = AlipayMgr.getInstance().doTradePagePay(tradePagePayReq);
			result.setTarget(formHtml);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "AlipayHandler[doTradePay]", "", e);
		}
		return result;
	}

	
}
