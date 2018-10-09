package com.hq.storeClient.service.pay.bs;
import com.hq.storeClient.service.pay.apiData.BeScanApiForm;
import com.hq.storeClient.service.pay.apiData.MiniProgramApiForm;
import com.hq.storeClient.service.pay.apiData.PayCallbackForm;
import com.hq.storeClient.service.pay.apiData.ScanApiForm;
import com.hq.storeClient.service.pay.data.MiniProgramPayResp;
import com.hq.storeClient.service.pay.data.PayDAO;
import com.hq.storeClient.service.pay.data.PayResp;
import com.zenmind.common.hotSwap.HotSwap;

public class PayClientMgr {

	public static PayClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(PayClientMgr.class);
	}
	
	public PayResp beScan(BeScanApiForm form){
		return PayDAO.getInstance().beScan(form);
	}
	
	public PayResp scan(ScanApiForm form){
		return PayDAO.getInstance().scan(form);
	}
	
	public MiniProgramPayResp miniProgramPay(MiniProgramApiForm form){
		return PayDAO.getInstance().miniProgramPay(form);
	}
	
	public void payCallback(PayCallbackForm form){
		PayDAO.getInstance().payCallback(form);
	}
	
}
