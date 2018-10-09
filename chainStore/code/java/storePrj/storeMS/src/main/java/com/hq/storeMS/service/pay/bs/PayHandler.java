package com.hq.storeMS.service.pay.bs;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.payRestClient.service.pay.apiData.BeScanPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayResp;
import com.hq.payRestClient.service.pay.apiData.OrderOriginTypeEnum;
import com.hq.payRestClient.service.pay.apiData.ScanPayApiForm;
import com.hq.payRestClient.service.qrcode.apiData.QrCodeResp;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.buser.apiData.BUserVipRechargeData;
import com.hq.storeMS.service.buser.bs.handler.BUserVipMgr;
import com.hq.storeMS.service.common.EvnMarkEnum;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.pay.apiData.BeScanApiForm;
import com.hq.storeMS.service.pay.apiData.MiniProgramApiForm;
import com.hq.storeMS.service.pay.apiData.PayCallbackForm;
import com.hq.storeMS.service.pay.apiData.ScanApiForm;
import com.hq.storeMS.service.pay.data.PayResp;
import com.hq.storeMS.service.wxOpenId.bs.WxOpenIdDataHolder;
import com.hq.storeMS.service.wxOpenId.data.WxOpenId;
import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class PayHandler {
	
	private final LogModule logModule = LogModule.Pay;
	
	public static PayHandler getInstance(){
		return HotSwap.getInstance().getSingleton(PayHandler.class);
	}
	
	public ReqResult<PayResp> beScan(BeScanApiForm form) {
		ReqResult<PayResp> result = ReqResult.newInstance(false, PayResp.class);
		try {
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			BeScanPayApiForm beScanPayApiForm = form.toBeScanPayApiForm();
			String outTradeNo = generateOutTradeNo();
			beScanPayApiForm.setOutTradeNo(outTradeNo);
			QrCodeResp qrCodeResp = PayMgr.getInstance().beScan(beScanPayApiForm);
			
			PayResp payResp = PayResp.newInstance();
			payResp.setImgUrl(qrCodeResp.getImgUrl());
			payResp.setOutTradeNo(outTradeNo);
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
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			ScanPayApiForm scanPayApiForm = new ScanPayApiForm();
			FastBeanCopyer.getInstance().copy(form, scanPayApiForm);
			String outTradeNo = generateOutTradeNo();
			scanPayApiForm.setOutTradeNo(outTradeNo);
			scanPayApiForm.setOrderOriginType(OrderOriginTypeEnum.STOREMS_ORDER.ordinal());
			PayMgr.getInstance().scan(scanPayApiForm);
			
			PayResp payResp = PayResp.newInstance();
			payResp.setOutTradeNo(outTradeNo);
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
			if(EvnMarkEnum.Test.getMark().equals(StoreMSCfgMgr.getProp().getEnvMark())){//体验仿真环境
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("体验环境不支持在线支付");
				return result;
			}
			MiniProgramPayApiForm miniProgramPayApiForm = MiniProgramPayApiForm.newInstance();
			FastBeanCopyer.getInstance().copy(form, miniProgramPayApiForm);
			
//			setOpenIdToMiniProgramPayApiFormIfNeed(form, miniProgramPayApiForm);
			//生成交易号
			String outTradeNo = generateOutTradeNo();
			miniProgramPayApiForm.setOutTradeNo(outTradeNo);
			miniProgramPayApiForm.setOrderOriginType(OrderOriginTypeEnum.STOREMS_ORDER.ordinal());
			
			MiniProgramPayResp miniProgramPayResp = PayMgr.getInstance().miniProgramPay(miniProgramPayApiForm);
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

	/**
	 * 根据jsCode获取openId，jsCode有效期为5分钟，此时获取可能迟了
	 * @param form
	 * @param miniProgramPayApiForm
	 */
	@SuppressWarnings("unused")
	private void setOpenIdToMiniProgramPayApiFormIfNeed(MiniProgramApiForm form, MiniProgramPayApiForm miniProgramPayApiForm) {
		if(StringUtils.isBlank(form.getOpenid()) && StringUtils.isNotBlank(form.getJsCode())) {
			WxOpenId wxOpenId = WxOpenIdDataHolder.getInstance().findByKey(form.getAppid(), form.getJsCode());
			if(wxOpenId!=null && StringUtils.isNotBlank(wxOpenId.getOpenId())){
				miniProgramPayApiForm.setOpenid(wxOpenId.getOpenId());
			}
		}
	}
	
	public ReqResult<Void> payCallback(PayCallbackForm form) {
		ReqResult<Void> result = ReqResult.newInstance(false, Void.class);
		try {
			OrderOriginTypeEnum orderOriginTypeEnum = OrderOriginTypeEnum.valueOf(form.getOrderOriginType());
			switch (orderOriginTypeEnum) {
			case STOREMS_ORDER:
				PayMgr.getInstance().orderCallback(form);
				break;
			case STOREMNGMS_CHARGE:
				Charge charge = PayMgr.getInstance().chargeCallback(form);
				updateBuserVipInfo(charge);
				break;
			default:
				break;
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "PayHandler[payCallback]";
			final String reason = LogHelper.getInstance().exceptionReason(form);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private void updateBuserVipInfo(Charge charge) {
		if(charge == null) {
			return;
		}
		BUserVipRechargeData vipRechargeData = BUserVipRechargeData.newInstance();
		vipRechargeData.setBuserId(charge.getBuserId());
		vipRechargeData.setExpiredTime(charge.getExpiredTime());
		vipRechargeData.setVipType(charge.getVipLevelId());
		BUserVipMgr.getInstance().vipRecharge(vipRechargeData);
	}

	private String generateOutTradeNo() {
	    StringBuffer orderSNBuffer = new StringBuffer();
	    orderSNBuffer.append(System.currentTimeMillis());
	    orderSNBuffer.append(RandomUtils.nextInt(10000, 99999));
	    return orderSNBuffer.toString();
	}

}
