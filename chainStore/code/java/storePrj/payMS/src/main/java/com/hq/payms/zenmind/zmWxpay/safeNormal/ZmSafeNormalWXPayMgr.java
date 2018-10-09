package com.hq.payms.zenmind.zmWxpay.safeNormal;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 微信支付服务(普通商户模式)的mgr类<br>
 * 使用前要先初始化<br>
 * 
 * 关于安全性：<br>
 * 每次调支付方法都将ZmWXPayAppParam作为参数传入，而不是切换共享的WXPayConfig对象属性值，是线程安全的
 * 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmSafeNormalWXPayMgr {

	public static ZmSafeNormalWXPayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ZmSafeNormalWXPayMgr.class);
	}

	private static final LogModule logModule = LogModule.Wxpay;
	
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	private ZmSafeNormalWXPay wxpay;
	
	public void init(ZmWXPayCfg zmWXPayCfg) {
		if(isInit.compareAndSet(false, true)){
			try {
				ZmSafeNormalWXPayConfigImpl config = ZmSafeNormalWXPayConfigImpl.getInstance();
				config.init(zmWXPayCfg);
				ZmSafeNormalWXPay.getInstance().init(config, false, zmWXPayCfg.isWxpayUseSandbox());
			    wxpay = ZmSafeNormalWXPay.getInstance();
			} catch (Exception e) {
				MainLog.error(logModule, "ZmSafeNormalWXPayMgr[init]", "初始化ZmSafeNormalWXPayMgr失败", e);
			}
		}
	}
	
	public ZmSafeNormalWXPay getWXPay() {
		return wxpay;
	}
}
