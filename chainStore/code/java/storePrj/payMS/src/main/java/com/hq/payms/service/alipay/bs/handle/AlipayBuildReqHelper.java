package com.hq.payms.service.alipay.bs.handle;

import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.service.alipay.apiData.TradePagePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePayApiForm;
import com.hq.payms.service.alipay.apiData.TradePrecreateApiForm;
import com.hq.payms.service.alipay.apiData.TradeRefundApiForm;
import com.hq.payms.service.alipay.data.CommonReq;
import com.hq.payms.service.alipay.data.IntfNotifiableReq;
import com.hq.payms.service.alipay.data.TradePagePayReq;
import com.hq.payms.service.alipay.data.TradePayReq;
import com.hq.payms.service.alipay.data.TradePrecreateReq;
import com.hq.payms.service.alipay.data.TradeQueryReq;
import com.hq.payms.service.alipay.data.TradeRefundReq;
import com.hq.payms.service.bossPayInfo.bs.BossPayInfoMgr;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.common.EvnMarkEnum;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayConstants;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayBuildReqHelper {
	public static AlipayBuildReqHelper getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayBuildReqHelper.class);
	}
	
	public TradePayReq buildTradePayReq(TradePayApiForm form) {
		TradePayReq reqParam = form.toTradePayReq();
		reqParam.setSubject(ZmAlipayConstants.SUBJECT);
		String notifyUrl = PayMSCfgMgr.getProp().getAlipayNotifyUrl();
		fillNotifyUrl(reqParam, notifyUrl);
		fillCommonReqByStoreId(reqParam, form.getStoreId());
		return reqParam;
	}


	public TradePrecreateReq buildTradePrecreateReq(TradePrecreateApiForm form) {
		TradePrecreateReq reqParam = form.toTradePrecreateReq();
		reqParam.setSubject(ZmAlipayConstants.SUBJECT);
		String notifyUrl = PayMSCfgMgr.getProp().getAlipayNotifyUrl();
		fillNotifyUrl(reqParam, notifyUrl);
		fillCommonReqByStoreId(reqParam,form.getStoreId());
		return reqParam;
	}

	public TradePagePayReq buildTradePagePayReq(TradePagePayApiForm form) {
		TradePagePayReq reqParam = form.toTradePagePayReq();
		reqParam.setSubject(ZmAlipayConstants.SUBJECT);
		String notifyUrl = PayMSCfgMgr.getProp().getAlipayNotifyUrl();
		fillNotifyUrl(reqParam, notifyUrl);
		fillCommonReqByStoreId(reqParam,form.getStoreId());
		return reqParam;
	}
	
	public TradeQueryReq buildTradeQueryReq(long storeId, String outTradeNo) {
		TradeQueryReq reqParam = TradeQueryReq.newInstance();
		reqParam.setOutTradeNo(outTradeNo);
		fillCommonReqByStoreId(reqParam, storeId);
		return reqParam; 
	}
	
	public TradeRefundReq buildTradeRefundReq(TradeRefundApiForm form) {
		TradeRefundReq reqParam = form.toTradeRefundReq();
		fillCommonReqByStoreId(reqParam, form.getStoreId());
		return reqParam;
	}
	
	private void fillNotifyUrl(IntfNotifiableReq reqParam, String notifyUrl) {
		if (EvnMarkEnum.Prd.getMark().equals(PayMSCfgMgr.getProp().getEnvMark())) {// 生产环境
			reqParam.setNotifyUrl(notifyUrl);
		}else {
			reqParam.setNotifyUrl(notifyUrl + "/test"); //notify_url为必填参数，非生产环境填假url
		}
	}
	
	/**
	 * 填充商户信息
	 * @param commonReq
	 * @param storeId
	 */
	private void fillCommonReqByStoreId(CommonReq commonReq, long storeId) {
		BossPayInfo bossPayInfo = BossPayInfoMgr.getInstance().findByStoreId(storeId);
		commonReq.setAppId(bossPayInfo.getAlipayAppId());
		commonReq.setPrivateKey(bossPayInfo.getAlipayPrivateKey());
		commonReq.setAlipayPublicKey(bossPayInfo.getAlipayZfbPublicKey());
//		String pid = PayMSCfgMgr.getProp().getAlipayPid();
//		commonReq.setSysServiceProviderId(pid);
	}
}
