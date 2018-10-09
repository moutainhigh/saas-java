package com.hq.payms.service.pay.bs;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.alipay.apiData.TradePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePrecreateApiForm;
import com.hq.payms.service.alipay.bs.AlipayHandler;
import com.hq.payms.service.alipayRecord.bs.AlipayRecordMgr;
import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RespStatus;
import com.hq.payms.service.pay.apiData.ApiTypeEnum;
import com.hq.payms.service.pay.apiData.BeScanPayApiForm;
import com.hq.payms.service.pay.apiData.MiniProgramPayApiForm;
import com.hq.payms.service.pay.apiData.PayQueryApiForm;
import com.hq.payms.service.pay.apiData.PayQueryResp;
import com.hq.payms.service.pay.apiData.ScanPayApiForm;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.hq.payms.service.wxpay.apiData.MicroPayApiForm;
import com.hq.payms.service.wxpay.apiData.MiniProgramPayResp;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderApiForm;
import com.hq.payms.service.wxpay.bs.WxpayHandler;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class PayHandler {
	private final LogModule logModule = LogModule.Tmp;

	public static PayHandler getInstance() {
		return HotSwap.getInstance().getSingleton(PayHandler.class);
	}
	
	public ReqResult<QrCodeResp> beScan(BeScanPayApiForm form, String remoteAddr) {
		ReqResult<QrCodeResp> result = ReqResult.newInstance(false, QrCodeResp.class);
		try {
			if(!form.isValid()) {
				result.setTips("必填参数未填写");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			ApiTypeEnum apiType = ApiTypeEnum.valueOf(form.getApiType());
			switch(apiType) {
			case WXPAY:
				UnifiedOrderApiForm unifiedOrderApiForm = form.toUnifiedOrderApiForm();
				result = WxpayHandler.getInstance().doUnifiedOrder(unifiedOrderApiForm, remoteAddr);
				break;
			case ALIPAY:
				TradePrecreateApiForm tradePrecreateApiForm = form.toTradePrecreateApiForm();
				result = AlipayHandler.getInstance().doTradePrecreate(tradePrecreateApiForm);
				break;
			default:
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("未知的支付方式");
				break;
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "PayHandler[beScan]", "", e);
		}
		return result;
	}

	public ReqResult<Void> scan(ScanPayApiForm form, String remoteAddr) {
		ReqResult<Void> result = ReqResult.newInstance(false, Void.class);
		try {
			if(!form.isValid()) {
				result.setTips("必填参数未填写");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			ApiTypeEnum apiType = ApiTypeEnum.valueOf(form.getApiType());
			switch(apiType) {
			case WXPAY:
				MicroPayApiForm microPayApiForm = form.toMicroPayApiForm();
				result = WxpayHandler.getInstance().doMicroPay(microPayApiForm, remoteAddr);
				break;
			case ALIPAY:
				TradePayApiForm tradePayApiForm = form.toTradePayApiForm();
				result = AlipayHandler.getInstance().doTradePay(tradePayApiForm);
				break;
			default:
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("未知的支付方式");
				break;
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "PayHandler[scan]", "", e);
		}
		return result;
	}
	
	public ReqResult<MiniProgramPayResp> miniProgramPay(MiniProgramPayApiForm form, String remoteAddr) {
		ReqResult<MiniProgramPayResp> result = ReqResult.newInstance(false, MiniProgramPayResp.class);
		try {
			if(!form.isValid()) {
				result.setTips("必填参数未填写");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			result = WxpayHandler.getInstance().doUnifiedOrderForMiniProgram(form.toUnifiedOrderForMiniProgramApiForm(), remoteAddr);
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "PayHandler[beScan]", "", e);
		}
		return result;
	}

	public ReqResult<PayQueryResp> query(PayQueryApiForm form) {
		ReqResult<PayQueryResp> result = ReqResult.newInstance(false, PayQueryResp.class);
		try {
			if(!form.isValid()) {
				result.setTips("必填参数未填写");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			ApiTypeEnum apiType = ApiTypeEnum.valueOf(form.getApiType());
			switch(apiType) {
			case WXPAY:
				WxpayRecord wxpayRecord = WxpayRecordMgr.getInstance().findByOutTradeNo(form.getOutTradeNo());
				if(wxpayRecord == null) {
					result.setStatus(RespStatus.NOT_FOUND);
					result.setTips("未查询到支付记录");
					return result;
				}
				if(wxpayRecord.getPaySuccessTime() > 0L) {
					PayQueryResp queryResp = PayQueryResp.newInstance(wxpayRecord.getTransaction_id());
					result.setTarget(queryResp);
					result.setSuccess(true);
				}else {
					result.setStatus(RespStatus.INVALID_REQUEST);
					result.setTips("支付未完成");
				}
				break;
			case ALIPAY:
				AlipayRecord alipayRecord = AlipayRecordMgr.getInstance().findByOutTradeNo(form.getOutTradeNo());
				if(alipayRecord == null) {
					result.setStatus(RespStatus.NOT_FOUND);
					result.setTips("未查询到支付记录");
					return result;
				}
				if(alipayRecord.getPaySuccessTime() > 0L) {
					PayQueryResp queryResp = PayQueryResp.newInstance(alipayRecord.getTradeNo());
					result.setTarget(queryResp);
					result.setSuccess(true);
				}else {
					result.setStatus(RespStatus.INVALID_REQUEST);
					result.setTips("支付未完成");
				}
				break;
			default:
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("未知的支付方式");
				break;
				
			}
		} catch (Exception e) {
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "PayHandler[query]", "", e);
		}
		return result;
	}

}
