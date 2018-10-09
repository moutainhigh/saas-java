package com.hq.payms.zenmind.zmWxpay.normal;

import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayAppParam;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayConfigImpl;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 微信支付服务(普通商户模式)的mgr类<br>
 * 使用前要先初始化<br>
 * 
 * 存在线程安全性问题<br>
 * replaced by <code>ZmSafeNormalWXPayMgr</code>
 * 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmNormalWXPayMgr {

	public static ZmNormalWXPayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ZmNormalWXPayMgr.class);
	}

	private static final LogModule logModule = LogModule.Wxpay;
	
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	private ZmNormalWXPay wxpay;
	
	public void init(ZmWXPayCfg zmWXPayCfg) {
		if(isInit.compareAndSet(false, true)){
			try {
				ZmNormalWXPayConfigImpl config = ZmNormalWXPayConfigImpl.getInstance();
				config.init(zmWXPayCfg);
				ZmNormalWXPay.getInstance().init(config, false, zmWXPayCfg.isWxpayUseSandbox());
			    wxpay = ZmNormalWXPay.getInstance();
			} catch (Exception e) {
				MainLog.error(logModule, "ZmWXPayNormalMgr[init]", "初始化ZmWXPayNormalMgr失败", e);
			}
		}
	}
	
	/**
	 * 动态切换商户appId等信息
	 * @param appParam
	 */
	public void setAppParam(ZmWXPayAppParam appParam) {
		try {
			ZmNormalWXPayConfigImpl config = ZmNormalWXPayConfigImpl.getInstance();
			config.setAppParam(appParam);
			ZmNormalWXPay.getInstance().setConfig(config);
		} catch (Exception e) {
			throw new RuntimeException("ZmWXPayNormalMgr动态切换商户appId等信息失败", e);
		}
		
	}
	
	public ZmNormalWXPay getWXPay() {
		return wxpay;
	}
}
