package com.hq.payms.service.wxpay.bs.handle;

import org.apache.commons.lang3.StringUtils;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.bossPayInfo.bs.BossPayInfoMgr;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.bossPayInfo.data.WxpayModelEnum;
import com.hq.payms.service.common.OperateTips;
import com.hq.payms.service.wxpay.apiData.IntfWxpayApiForm;
import com.hq.payms.service.wxpayRecord.bs.WxpayRecordMgr;
import com.hq.payms.service.wxpayRecord.data.WxpayRecord;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayCheckFormHelper {
	public static WxpayCheckFormHelper getInstance() {
		return HotSwap.getInstance().getSingleton(WxpayCheckFormHelper.class);
	}
	
	public OperateTips check(IntfWxpayApiForm form) {
		OperateTips tips = OperateTips.newInstance(false);
		tips = checkStoreId(form.getStoreId());
		if(!tips.isSuccess()) {
			return tips;
		}
		tips = checkTotalAmount(form.getTotal_fee(), form.getOut_trade_no());
		if(!tips.isSuccess()) {
			return tips;
		}
		tips = checkOutTradeNo(form.getOut_trade_no());
		return tips;
	}
	
	private OperateTips checkTotalAmount(String totalAmount, String outTradeNo) {
		OperateTips tips = OperateTips.newInstance(false);
		if (Integer.parseInt(totalAmount) <= 0) { // 防止抓包修改交易金额造成损失
			tips.setTips("交易金额无效");
			MainLog.warn(LogModule.Wxpay, "AlipayHandler[checkTotalAmount]", "交易号" + outTradeNo + "金额" + totalAmount + "分, 交易金额无效！");
		}else {
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private OperateTips checkOutTradeNo(String outTradeNo) {
		OperateTips tips = OperateTips.newInstance(false);
		WxpayRecord wxpayRecord = WxpayRecordMgr.getInstance().findByOutTradeNo(outTradeNo);
		if (wxpayRecord != null) {
			tips.setTips("交易号重复");
		}else {
			tips.setSuccess(true);
		}
		return tips;
	} 
	
	private OperateTips checkStoreId(long storeId) {
		OperateTips tips = OperateTips.newInstance(false);
		BossPayInfo bossPayInfo = BossPayInfoMgr.getInstance().findByStoreId(storeId);
		if (isBlankWxpay(bossPayInfo)) {
			tips.setTips("商户未开通微信支付");
		}else {
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private boolean isBlankWxpay(BossPayInfo bossPayInfo) {
		return bossPayInfo == null || WxpayModelEnum.valueOf(bossPayInfo.getWxpayModel()) == WxpayModelEnum.NONE ||
				(StringUtils.isBlank(bossPayInfo.getWxpayMchId()) && StringUtils.isBlank(bossPayInfo.getWxpaySubMchId()));
	}
}
