package com.hq.payms.zenmind.zmAlipay.normal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.hq.payms.common.log.LogModule;
import com.hq.payms.common.log.MainLog;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayCfg;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 支付宝服务的mgr类<br>
 * 使用前要先初始化<br>
 * 
 * replaced by <code>ZmSafeNoramlAlipayMgr</code>
 * 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmNoramlAlipayMgr {

	public static ZmNoramlAlipayMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ZmNoramlAlipayMgr.class);
	}

	private static final LogModule logModule = LogModule.Alipay;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
//    private AlipayTradeService tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用
//    private AlipayMonitorService monitorService;
    
	// 支付宝当面付2.0服务
    private AlipayTradeService tradeService;
    //电脑网站支付服务
    private ZmNormalAlipayPageTradeServiceSimpleImpl pageTradeService;
	
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
    	
    	/** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
//        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
//        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
//        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
//            .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
//            .setFormat("json").build();
        
        //电脑网站支付服务
//        pageTradeService = new ZmAlipayPageTradeSimpleService.ClientBuilder().build();
        
    	/****************************** 以下服务支持用方案一向多商户付款 **************************************/
        //当面付服务
        tradeService = new ZmNormalAlipayTradeServiceImpl();
        
        //电脑网站支付服务
        pageTradeService = new ZmNormalAlipayPageTradeServiceImpl();
       
    }

	public AlipayTradeService getTradeService() {
		return tradeService;
	}

	public ZmNormalAlipayPageTradeServiceSimpleImpl getPageTradeService() {
		return pageTradeService;
	}
	
//	public AlipayTradeService getTradeWithHBService() {
//		return tradeWithHBService;
//	}
//
//	public AlipayMonitorService getMonitorService() {
//		return monitorService;
//	}

}
