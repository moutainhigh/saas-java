package com.hq.storeMS.service.pay.bs;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdatePayItemApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.orderRestClient.service.order.data.PayTypeEnum;
import com.hq.payRestClient.service.pay.apiData.BeScanPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayResp;
import com.hq.payRestClient.service.pay.apiData.PayQueryApiForm;
import com.hq.payRestClient.service.pay.apiData.PayQueryResp;
import com.hq.payRestClient.service.pay.apiData.ScanPayApiForm;
import com.hq.payRestClient.service.qrcode.apiData.QrCodeResp;
import com.hq.storeMS.service.charge.bs.ChargeMgr;
import com.hq.storeMS.service.order.bs.OrderMgr;
import com.hq.storeMS.service.pay.apiData.PayCallbackForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdatePayInfoForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateType;
import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.hq.storeManagerRestClient.service.charge.data.ChargeChannelEnum;
import com.hq.storeManagerRestClient.service.charge.data.ChargePayItem;
import com.hq.storeManagerRestClient.service.charge.data.ChargeStatusEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class PayMgr {

	public static PayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(PayMgr.class);
	}
	
	public QrCodeResp beScan(BeScanPayApiForm form){
		return PayDataHolder.getInstance().beScan(form);
	}
	
	public void scan(ScanPayApiForm form){
		PayDataHolder.getInstance().scan(form);
	}
	
	public MiniProgramPayResp miniProgramPay(MiniProgramPayApiForm form){
		return PayDataHolder.getInstance().miniProgramPay(form);
	}
	
	public PayQueryResp query(PayQueryApiForm form) {
		return PayDataHolder.getInstance().query(form);
	}
	
	//智美通订单回调
	public void orderCallback(PayCallbackForm form) {
		Order order = OrderMgr.getInstance().get(form.getStoreId(), form.getOrderId());
		List<PayItem> payItems = order.getPayItems();
		if(!checkHasPayItem(form.getOutTradeNo(), payItems)){
			PayItem payItem = PayItem.newInstance();
			payItem.setPayType(form.getPayType());
			payItem.setCost(form.getAmount());
			payItem.setOutTradeNo(form.getOutTradeNo());
			payItem.setTradeNo(form.getTradeNo());
			payItems.add(payItem);
		}
		
		OrderUpdatePayItemApiForm updatePayItemApiForm = OrderUpdatePayItemApiForm.newInstance();
		updatePayItemApiForm.setPayItems(payItems);
		OrderUpdateApiForm updateApiForm = OrderUpdateApiForm.newInstance();
		updateApiForm.setStoreId(order.getStoreId());
		updateApiForm.setUpdatePayItemApiForm(updatePayItemApiForm);
		updateApiForm.setUpdateType(OrderUpdateType.UpdatePayItem.ordinal());
		OrderMgr.getInstance().update(order.getId(), updateApiForm);
	}
	
	private boolean checkHasPayItem(String outTradeNo, List<PayItem> payItems) {
		boolean has = false;
		for (PayItem payItem : payItems) {
			if(StringUtils.equals(payItem.getOutTradeNo(), outTradeNo)){
				has = true;
				break;
			}
		}
		return has;
	}
	
	//智美通平台 商户缴费回调
	public Charge chargeCallback(PayCallbackForm form) {
		long chargeId = form.getOrderId();
		Charge oldCharge = ChargeMgr.getInstance().getCharge(chargeId);
		List<ChargePayItem> payItems = oldCharge.getPayItems();
		ChargePayItem item = ChargePayItem.newInstance();
		PayTypeEnum payType = PayTypeEnum.valueOf(form.getPayType());
		if(payType == PayTypeEnum.ALIPAY) {
			item.setPayType(ChargeChannelEnum.ALIPAY.ordinal());
		}else if(payType == PayTypeEnum.WECHAT) {
			item.setPayType(ChargeChannelEnum.WECHAT.ordinal());
		}
		item.setCost(new BigDecimal(form.getAmount()*100).longValue());
		item.setCreatedTime(System.currentTimeMillis());
		item.setTradeNo(form.getTradeNo());
		item.setOutTradeNo(form.getOutTradeNo());
		payItems.add(item);
		
		ChargeUpdatePayInfoForm infoForm = ChargeUpdatePayInfoForm.newInstance();
		infoForm.setId(chargeId);
		infoForm.setStatus(ChargeStatusEnum.HAS_PAY.ordinal());
		infoForm.setPayItems(payItems);
		ChargeUpdateApiForm updateForm = ChargeUpdateApiForm.newInstance();
		updateForm.setChargeUpdatePayInfoForm(infoForm);
		updateForm.setUpdateType(ChargeUpdateType.UpdatePayInfo.ordinal());
		Charge charge = ChargeMgr.getInstance().updateCharge(chargeId, updateForm);
		return charge;
	}
	
}
