package com.hq.payms.service.pay.bs;

import com.hq.payms.service.pay.apiData.callback.PayCallbackForm;
import com.hq.payms.service.pay.data.PayRestDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class PayDataHolder {
	public static PayDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(PayDataHolder.class);
	}
	
	public void payCallback(PayCallbackForm param){
		PayRestDAO.getInstance().payCallback(param);
	}
	
}
