package com.hq.payms.service.wxpay.bs.handle;

import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.service.bossPayInfo.bs.BossPayInfoMgr;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.bossPayInfo.data.WxpayModelEnum;
import com.hq.payms.service.common.EvnMarkEnum;
import com.hq.payms.service.wxpay.apiData.MicroPayApiForm;
import com.hq.payms.service.wxpay.apiData.RefundApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderApiForm;
import com.hq.payms.service.wxpay.apiData.UnifiedOrderForMiniProgramApiForm;
import com.hq.payms.service.wxpay.data.CommonReq;
import com.hq.payms.service.wxpay.data.DownloadBillReq;
import com.hq.payms.service.wxpay.data.IntfNotifiableReq;
import com.hq.payms.service.wxpay.data.MicroPayReq;
import com.hq.payms.service.wxpay.data.OrderQueryOrCloseReq;
import com.hq.payms.service.wxpay.data.RefundReq;
import com.hq.payms.service.wxpay.data.UnifiedOrderReq;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayConstants;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayBuildReqHelper {
	public static WxpayBuildReqHelper getInstance() {
		return HotSwap.getInstance().getSingleton(WxpayBuildReqHelper.class);
	}
	
	public UnifiedOrderReq buildUnifiedOrderReq(UnifiedOrderApiForm form, String remoteAddr) {
		UnifiedOrderReq param = form.toUnifiedOrderReq();
		param.setSpbill_create_ip(remoteAddr);
		param.setTrade_type(ZmWXPayConstants.TRADE_TYPE_NATIVE);
		param.setBody(ZmWXPayConstants.SUBJECT);
		param.setProduct_id(ZmWXPayConstants.PRODUCT_ID);
		String notifyUrl = PayMSCfgMgr.getProp().getWxpayNotifyUrl();
		fillNotifyUrl(param, notifyUrl);
		fillCommonReqByStoreId(param, form.getStoreId());
		return param;
	}
	
	public UnifiedOrderReq buildUnifiedOrderReqForMiniProgram(UnifiedOrderForMiniProgramApiForm form, String remoteAddr) {
		UnifiedOrderReq param = form.toUnifiedOrderReq();
		param.setSpbill_create_ip(remoteAddr);
		param.setTrade_type(ZmWXPayConstants.TRADE_TYPE_JSAPI);
		param.setBody(ZmWXPayConstants.SUBJECT_MINI_PROGROM);
		String notifyUrl = PayMSCfgMgr.getProp().getWxpayNotifyUrl();
		fillNotifyUrl(param, notifyUrl);
		fillOtherReqForMiniProgram(param, form);
		return param;
	}
	
	public MicroPayReq buildMicroPayReq(MicroPayApiForm form, String remoteAddr) {
		MicroPayReq param = form.toMicroPayReq();
		param.setSpbill_create_ip(remoteAddr);
		param.setBody(ZmWXPayConstants.SUBJECT);
		fillCommonReqByStoreId(param, form.getStoreId());
		return param;
	}
	
	public OrderQueryOrCloseReq buildOrderQueryOrCloseReq(long storeId, String out_trade_no) {
		OrderQueryOrCloseReq param = OrderQueryOrCloseReq.newInstance();
		param.setOut_trade_no(out_trade_no);
		fillCommonReqByStoreId(param, storeId);
		return param;
	}
	
	public RefundReq buildRefundReq(RefundApiForm form) {
		RefundReq param = form.toRefundReq();
		WxpayRecord payRecord = WxpayRecordMgr.getInstance().findByOutTradeNo(form.getOut_trade_no());
		param.setTransaction_id(payRecord.getTransaction_id());
		String notifyUrl = PayMSCfgMgr.getProp().getWxpayRefundNotifyUrl();
		fillNotifyUrl(param, notifyUrl);
		fillCommonReqByStoreId(param, form.getStoreId());
		return param;
	}

	private void fillNotifyUrl(IntfNotifiableReq reqParam, String notifyUrl) {
		if (EvnMarkEnum.Prd.getMark().equals(PayMSCfgMgr.getProp().getEnvMark())) {// 生产环境
			reqParam.setNotify_url(notifyUrl);
		}else {
			reqParam.setNotify_url(notifyUrl + "/test"); //notify_url为必填参数，非生产环境填假url
		}
	}
	
	private void fillCommonReqByStoreId(CommonReq commonReq, long storeId) {
		BossPayInfo bossPayInfo = BossPayInfoMgr.getInstance().findByStoreId(storeId);
		commonReq.setWxpayModel(bossPayInfo.getWxpayModel()); //设置支付模式
		WxpayModelEnum wxpayModelEnum = WxpayModelEnum.valueOf(bossPayInfo.getWxpayModel());
		switch(wxpayModelEnum) {
		case NORMAL:
			fillCommonReqForNormalModel(commonReq, bossPayInfo);
			break;
		case PROVIDER:
			fillCommonReqForProviderModel(commonReq, bossPayInfo);
			break;
		default:
			break;
		}
	}
	
	private void fillOtherReqForMiniProgram(UnifiedOrderReq reqParam, UnifiedOrderForMiniProgramApiForm form) {
		BossPayInfo bossPayInfo = BossPayInfoMgr.getInstance().findByStoreId(form.getStoreId());
		reqParam.setWxpayModel(bossPayInfo.getWxpayModel()); //设置支付模式
		WxpayModelEnum wxpayModelEnum = WxpayModelEnum.valueOf(bossPayInfo.getWxpayModel());
		switch(wxpayModelEnum) {
		case NORMAL:
			reqParam.setAppid(form.getAppid()); //不再使用主体公众号的appid,而是使用小程序的appid
			reqParam.setMch_id(bossPayInfo.getWxpayMchId());
			reqParam.setKey(bossPayInfo.getWxpayKey());
			reqParam.setCert_path(bossPayInfo.getWxpayCertPath());
			break;
		case PROVIDER:
			reqParam.setSub_appid(form.getAppid()); //当前调起支付的小程序APPID
			reqParam.setSub_openid(form.getOpenid()); //当前调起支付的小程序APPID下的用户openId
			reqParam.setOpenid(""); //传了sub_openid，就不要传openid
			reqParam.setSub_mch_id(bossPayInfo.getWxpaySubMchId()); //微信支付分配的子商户号
			break;
		default:
			break;
		}
	}
	
	private void fillCommonReqForNormalModel(CommonReq commonReq, BossPayInfo bossPayInfo) {
		commonReq.setAppid(bossPayInfo.getWxpayAppId());
		commonReq.setMch_id(bossPayInfo.getWxpayMchId());
		commonReq.setKey(bossPayInfo.getWxpayKey());
		commonReq.setCert_path(bossPayInfo.getWxpayCertPath());
	}
	
	private void fillCommonReqForProviderModel(CommonReq commonReq, BossPayInfo bossPayInfo) {
//		commonReq.setSub_appid(bossPayInfo.getWxpaySubAppId()); //非必填
		commonReq.setSub_mch_id(bossPayInfo.getWxpaySubMchId());
	}
	
	public DownloadBillReq buildDownloadBillReq(String bill_date, String bill_type) {
		DownloadBillReq reqParam = DownloadBillReq.newInstance();
		reqParam.setBill_date(bill_date);
		reqParam.setBill_type(bill_type);
//		fillCommonReqByStoreId(reqParam, storeId); //还要做处理
		return reqParam;
	}

}
