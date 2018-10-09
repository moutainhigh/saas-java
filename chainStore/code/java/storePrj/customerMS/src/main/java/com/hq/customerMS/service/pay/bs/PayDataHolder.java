package com.hq.customerMS.service.pay.bs;

import com.hq.customerMS.common.constants.ServerConstants;
import com.hq.customerMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.pay.apiData.BeScanApiForm;
import com.hq.storeClient.service.pay.apiData.MiniProgramApiForm;
import com.hq.storeClient.service.pay.apiData.ScanApiForm;
import com.hq.storeClient.service.pay.bs.PayClientMgr;
import com.hq.storeClient.service.pay.data.MiniProgramPayResp;
import com.hq.storeClient.service.pay.data.PayResp;
import com.zenmind.common.hotSwap.HotSwap;

public class PayDataHolder {

	public static PayDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(PayDataHolder.class);
	}
	
	public PayResp beScan(BeScanApiForm form){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PayResp data = PayClientMgr.getInstance().beScan(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public PayResp scan(ScanApiForm form){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PayResp data = PayClientMgr.getInstance().scan(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public MiniProgramPayResp miniProgramPay(MiniProgramApiForm form){
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MiniProgramPayResp data = PayClientMgr.getInstance().miniProgramPay(form);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
}
