package com.hq.payms.zenmind.zmAlipay.safeNormal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.alipay.demo.trade.config.Configs;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayCfg;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 支付宝服务的mgr类(普通商户模式/自研开发者)<br>
 * 使用前要先初始化
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmSafeNoramlAlipayMgr {

	public static ZmSafeNoramlAlipayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ZmSafeNoramlAlipayMgr.class);
	}

	private static final LogModule logModule = LogModule.Alipay;

    
    private ZmSafeNormalAlipayTradeServiceImplAdapter tradeService;
	
	private AtomicBoolean isInit = new AtomicBoolean(false);
	
	public void init(ZmAlipayCfg zmAlipayCfgP) {
		if(isInit.compareAndSet(false, true)){
			try {
				initConfigs(zmAlipayCfgP);
				initServices();
			} catch (Exception e) {
				MainLog.error(logModule, "ZmAlipayMgr[init]", "初始化ZmAlipayMgr失败", e);
			}
		}
	}
	
    private void initConfigs(ZmAlipayCfg zmAlipayCfgP) {
    	
        Map<String, Object> cfgMap = new HashMap<String, Object>(); 
        cfgMap.put("open_api_domain", zmAlipayCfgP.getAlipayOpenApiDomain());
        cfgMap.put("mcloud_api_domain", zmAlipayCfgP.getAlipayMcloudApiDomain());
        cfgMap.put("pid", zmAlipayCfgP.getAlipayPid());
        cfgMap.put("appid", zmAlipayCfgP.getAlipayAppid());
        cfgMap.put("private_key", zmAlipayCfgP.getAlipayPrivateKey());
        cfgMap.put("public_key", zmAlipayCfgP.getAlipayDevPublicKey());
        cfgMap.put("alipay_public_key", zmAlipayCfgP.getAlipayZfbPublicKey());
        cfgMap.put("sign_type", zmAlipayCfgP.getAlipaySignType());
        cfgMap.put("max_query_retry", zmAlipayCfgP.getAlipayMaxQueryRetry());
        cfgMap.put("query_duration", zmAlipayCfgP.getAlipayQueryDuration());
        cfgMap.put("max_cancel_retry", zmAlipayCfgP.getAlipayMaxCancelRetry());
        cfgMap.put("cancel_duration", zmAlipayCfgP.getAlipayCancelDuration());
        cfgMap.put("heartbeat_delay", zmAlipayCfgP.getAlipayHeartbeatDelay());
        cfgMap.put("heartbeat_duration", zmAlipayCfgP.getAlipayHeartbeatDuration());
        cfgMap.put("http_connect_timeout_ms", zmAlipayCfgP.getAlipayHttpConnectTimeoutMs());
        cfgMap.put("http_read_timeout_ms", zmAlipayCfgP.getAlipayHttpReadTimeoutMs());
        
        Configs.init(cfgMap);
       
    }
    
    private void initServices() {
    	
    	/****************************** 以下服务支持用方案一向多商户付款 **************************************/
    	
        tradeService = new ZmSafeNormalAlipayTradeServiceImplAdapter();
        
    }
	public ZmSafeNormalAlipayTradeServiceImplAdapter getTradeService() {
		return tradeService;
	}

}
