package com.hq.payms.zenmind.zmWxpay.provider;

import java.util.concurrent.atomic.AtomicBoolean;

import com.github.wxpay.sdk.WXPay;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayConfigImpl;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 微信支付服务(服务商模式)的mgr类<br>
 * 使用前要先初始化
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmProviderWXPayMgr {

	public static ZmProviderWXPayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ZmProviderWXPayMgr.class);
	}

	private static final LogModule logModule = LogModule.Wxpay;
	
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	private WXPay wxpay;
	
	public void init(ZmWXPayCfg zmWXPayCfg) {
		if(isInit.compareAndSet(false, true)){
			try {
				ZmWXPayConfigImpl config = ZmWXPayConfigImpl.getInstance();
				config.init(zmWXPayCfg);
				boolean useSandbox = zmWXPayCfg.isWxpayUseSandbox();
			    wxpay = new WXPay(config, false, useSandbox);
			} catch (Exception e) {
				MainLog.error(logModule, "ZmProviderWXPayMgr[init]", "初始化ZmProviderWXPayMgr失败", e);
			}
		}
	}
	
	public WXPay getWXPay() {
		return wxpay;
	}
}
