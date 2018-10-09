package com.hq.payms.service.alipay.bs.handle;

import org.apache.commons.lang3.StringUtils;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.alipay.apiData.IntfAlipayApiForm;
import com.hq.payms.service.alipay.apiData.TradeRefundApiForm;
import com.hq.payms.service.alipayRecord.bs.AlipayRecordMgr;
import com.hq.payms.service.alipayRecord.data.AlipayRecord;
import com.hq.payms.service.bossPayInfo.bs.BossPayInfoMgr;
import com.hq.payms.service.bossPayInfo.data.BossPayInfo;
import com.hq.payms.service.common.OperateTips;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayCheckFormHelper {
	public static AlipayCheckFormHelper getInstance() {
		return HotSwap.getInstance().getSingleton(AlipayCheckFormHelper.class);
	}
	
	public OperateTips check(IntfAlipayApiForm form) {
		OperateTips tips = OperateTips.newInstance(false);
		tips = checkStoreId(form.getStoreId());
		if(!tips.isSuccess()) {
			return tips;
		}
		tips = checkTotalAmount(form.getTotalAmount(), form.getOutTradeNo());
		if(!tips.isSuccess()) {
			return tips;
		}
		tips = checkOutTradeNo(form.getOutTradeNo());
		return tips;
	}
	
	public OperateTips checkTradeRefundApiForm(TradeRefundApiForm form) {
		OperateTips tips = OperateTips.newInstance(false);
		AlipayRecord payRecord = AlipayRecordMgr.getInstance().findByOutTradeNo(form.getOutTradeNo());
		if (Float.parseFloat(form.getRefundAmount()) <= 0
				|| Float.parseFloat(form.getRefundAmount()) > Float.parseFloat(payRecord.getTotalAmount())) { // 防止抓包修改交易金额造成损失
			MainLog.warn(LogModule.Alipay, "AlipayCheckFormHelper[checkTradeRefundApiForm]", "退款交易号" + form.getOutTradeNo() + "金额" + form.getRefundAmount() + "元, 交易金额无效！");
			tips.setTips("交易金额无效");
			tips.setSuccess(false);
        }else {
        	tips.setSuccess(true);
        }
		return tips;
	}
	
	private OperateTips checkTotalAmount(String totalAmount, String outTradeNo) {
		OperateTips tips = OperateTips.newInstance(false);
		if (Float.parseFloat(totalAmount) <= 0) { // 防止抓包修改交易金额造成损失
			tips.setTips("交易金额无效");
			MainLog.warn(LogModule.Alipay, "AlipayCheckFormHelper[checkTotalAmount]", "交易号" + outTradeNo + "金额" + totalAmount + "元, 交易金额无效！");
		}else {
			tips.setSuccess(true);
		}
		return tips;
	}
	
	private OperateTips checkOutTradeNo(String outTradeNo) {
		OperateTips tips = OperateTips.newInstance(false);
		AlipayRecord alipayRecord = AlipayRecordMgr.getInstance().findByOutTradeNo(outTradeNo);
		if (alipayRecord != null) {
			tips.setTips("交易号重复");
		}else {
			tips.setSuccess(true);
		}
		return tips;
	} 
	
	private OperateTips checkStoreId(long storeId) {
		OperateTips tips = OperateTips.newInstance(false);
		BossPayInfo bossPayInfo = BossPayInfoMgr.getInstance().findByStoreId(storeId);
		if (bossPayInfo == null || StringUtils.isBlank(bossPayInfo.getAlipayAppId())) {
			tips.setTips("商户未开通支付宝支付");
		}else {
			tips.setSuccess(true);
		}
		return tips;
	} 
}
