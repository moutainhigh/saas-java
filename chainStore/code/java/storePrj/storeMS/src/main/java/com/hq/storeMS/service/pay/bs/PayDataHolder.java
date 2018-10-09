package com.hq.storeMS.service.pay.bs;

import com.hq.payRestClient.service.pay.apiData.BeScanPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayResp;
import com.hq.payRestClient.service.pay.apiData.PayQueryApiForm;
import com.hq.payRestClient.service.pay.apiData.PayQueryResp;
import com.hq.payRestClient.service.pay.apiData.ScanPayApiForm;
import com.hq.payRestClient.service.pay.data.PayRestDAO;
import com.hq.payRestClient.service.qrcode.apiData.QrCodeResp;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.common.validate.info.ValidateInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class PayDataHolder {

	public static PayDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(PayDataHolder.class);
	}
	
	public QrCodeResp beScan(BeScanPayApiForm form){
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		QrCodeResp data = PayRestDAO.getInstance().beScan(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public void scan(ScanPayApiForm form){
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		PayRestDAO.getInstance().scan(form);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public MiniProgramPayResp miniProgramPay(MiniProgramPayApiForm form){
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		MiniProgramPayResp data = PayRestDAO.getInstance().miniProgramPay(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public PayQueryResp query(PayQueryApiForm form) {
		AppIdThreadLocal.getInstance().set(getValidateInfo());
		PayQueryResp data = PayRestDAO.getInstance().query(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	private ValidateInfo getValidateInfo() {
		ValidateInfo info = ValidateInfo.newInstance();
		info.setAppId(ServerConstants.appId);
		info.setBossId(ValidateInfoThreadLocal.getInstance().getBossId());
		return info;
	}
	
}
