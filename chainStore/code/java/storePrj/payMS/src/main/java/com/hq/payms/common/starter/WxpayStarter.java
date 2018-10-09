package com.hq.payms.common.starter;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.payms.common.schedule.WxpayOrderQueryScheduler;
import com.hq.payms.common.schedule.WxpayPayCallbackEnsureScheduler;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.hq.payms.zenmind.zmWxpay.provider.ZmProviderWXPayMgr;
import com.hq.payms.zenmind.zmWxpay.safeNormal.ZmSafeNormalWXPayMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class WxpayStarter {
	public static WxpayStarter getInstance(){
		return HotSwap.getInstance().getSingleton(WxpayStarter.class);
	}

	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	public void init(ZmWXPayCfg zmWXPayCfg) {
		if(isInit.compareAndSet(false, true)){
			ZmSafeNormalWXPayMgr.getInstance().init(zmWXPayCfg);
			ZmProviderWXPayMgr.getInstance().init(zmWXPayCfg);
			WxpayOrderQueryScheduler.getInstance().init(zmWXPayCfg);
			WxpayPayCallbackEnsureScheduler.getInstance().init(zmWXPayCfg);
		}
	}

}
