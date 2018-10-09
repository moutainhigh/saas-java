package com.hq.customerMS.service.pay.bs;

import com.hq.storeClient.service.pay.apiData.BeScanApiForm;
import com.hq.storeClient.service.pay.apiData.MiniProgramApiForm;
import com.hq.storeClient.service.pay.apiData.ScanApiForm;
import com.hq.storeClient.service.pay.data.MiniProgramPayResp;
import com.hq.storeClient.service.pay.data.PayResp;
import com.zenmind.common.hotSwap.HotSwap;

public class PayMgr {

	public static PayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(PayMgr.class);
	}
	
	public PayResp beScan(BeScanApiForm form){
		return PayDataHolder.getInstance().beScan(form);
	}
	
	public PayResp scan(ScanApiForm form){
		return PayDataHolder.getInstance().scan(form);
	}
	
	public MiniProgramPayResp miniProgramPay(MiniProgramApiForm form){
		return PayDataHolder.getInstance().miniProgramPay(form);
	}
	
}
