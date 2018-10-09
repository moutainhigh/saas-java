package com.hq.payms.common.starter;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.payms.common.schedule.AlipayOrderQueryScheduler;
import com.hq.payms.common.schedule.AlipayPayCallbackEnsureScheduler;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayCfg;
import com.hq.payms.zenmind.zmAlipay.safeNormal.ZmSafeNoramlAlipayMgr;
import com.zenmind.common.hotSwap.HotSwap;

public class AlipayStarter {
	public static AlipayStarter getInstance(){
		return HotSwap.getInstance().getSingleton(AlipayStarter.class);
	}

	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	public void init(ZmAlipayCfg zmAlipayCfg) {
		if(isInit.compareAndSet(false, true)){
			ZmSafeNoramlAlipayMgr.getInstance().init(zmAlipayCfg);
			AlipayOrderQueryScheduler.getInstance().init(zmAlipayCfg);
			AlipayPayCallbackEnsureScheduler.getInstance().init(zmAlipayCfg);
		}
	}
}
