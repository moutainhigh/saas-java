package com.hq.payms.service.pay.bs;

import com.hq.payms.common.log.ConsoleLog;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.service.pay.apiData.callback.PayCallbackForm;
import com.zenmind.common.hotSwap.HotSwap;

public class PayMgr {
	public static PayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(PayMgr.class);
	}
	
	/**
	 * 回调通知调用方微服务
	 * @param param
	 * @return
	 */
	public boolean payCallback(PayCallbackForm param){
		boolean callbackSuccess = false;
		try {
			PayDataHolder.getInstance().payCallback(param);
			callbackSuccess = true;
			ConsoleLog.info(LogModule.Tmp, "PayMgr[payCallback]成功", param.toString());
		} catch (Exception e) {
			callbackSuccess = false;
			MainLog.error(LogModule.Tmp, "PayMgr[payCallback]失败", param.toString(), e);
		}
		return callbackSuccess;
	}
	
}
