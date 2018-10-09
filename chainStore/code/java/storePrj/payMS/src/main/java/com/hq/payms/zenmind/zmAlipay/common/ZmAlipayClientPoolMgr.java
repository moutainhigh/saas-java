package com.hq.payms.zenmind.zmAlipay.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;

import com.alipay.api.AlipayClient;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;
import com.hq.payms.common.utils.CachedMapFactory;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * AlipayClient的mgr类，使用对象池的方式管理各个商户对应的AlipayClient<br>
 * 经测试，每个AlipayClient对象大小10k左右，缓存1024个可以接受
 * 
 * @see https://openclub.alipay.com/read.php?tid=2123&fid=56<br>
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmAlipayClientPoolMgr implements IntfZmAlipayClientMgr{
	
	public static ZmAlipayClientPoolMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ZmAlipayClientPoolMgr.class);
	}
	
//	private ConcurrentMap<String, AlipayClient> clientPool = new ConcurrentHashMap<String, AlipayClient>();
	
	private Map<String, AlipayClient> clientPool = CachedMapFactory.newMap(1024);
	
	private ClientBuilder clientBuilder =  new ClientBuilder();
	
	@Override
	public AlipayClient getClient(ZmAlipayAppParam appParam){
		if(!appParam.isVaild()) {
			throw new IllegalArgumentException("ZmAlipayAppParam对象的属性不能为空!");
		}
		String appId = appParam.getAppId();
		AlipayClient client = clientPool.get(appId);
		if(client == null){
//			synchronized(this) {
//				client = clientPool.get(appId);
//				if(client == null) {
					client = clientBuilder.setAppid(appId)
							.setPrivateKey(appParam.getPrivateKey())
							.setAlipayPublicKey(appParam.getAlipayPublicKey())
							.build();
			        clientPool.putIfAbsent(appId, client);
//				}
//			}
		}
//		return client; 
		return clientPool.get(appId);
	}
	
	public void remove(String appId) {
		clientPool.remove(appId);
	}
	
	public static class ClientBuilder {
        private String gatewayUrl;
        private String appid;
        private String privateKey;
        private String format;
        private String charset;
        private String alipayPublicKey;
        private String signType;

        public ZmAlipayClient build() {
            if (StringUtils.isEmpty(gatewayUrl)) {
                gatewayUrl = Configs.getOpenApiDomain(); // 与mcloudmonitor网关地址不同
            }
            if (StringUtils.isEmpty(appid)) {
//                appid = Configs.getAppid();
            	throw new IllegalArgumentException("appid不能为空!");
            }
            if (StringUtils.isEmpty(privateKey)) {
//                privateKey = Configs.getPrivateKey();
            	throw new IllegalArgumentException("privateKey不能为空!");
            }
            if (StringUtils.isEmpty(format)) {
                format = "json";
            }
            if (StringUtils.isEmpty(charset)) {
                charset = "utf-8";
            }
            if (StringUtils.isEmpty(alipayPublicKey)) {
//                alipayPublicKey = Configs.getAlipayPublicKey();
            	throw new IllegalArgumentException("alipayPublicKey不能为空!");
            }
            if (StringUtils.isEmpty(signType)) {
                signType = Configs.getSignType();
            }
            
            ZmAlipayClient client = new ZmAlipayClient(this.getGatewayUrl(), this.getAppid(), this.getPrivateKey(),
            		this.getFormat(), this.getCharset(), this.getAlipayPublicKey(), this.getSignType());
            client.setConnectTimeout(Configs.getHttpConnectTimeoutMs());
            client.setReadTimeout(Configs.getHttpReadTimeoutMs());
            return client;
        }

        public ClientBuilder setAlipayPublicKey(String alipayPublicKey) {
            this.alipayPublicKey = alipayPublicKey;
            return this;
        }

        public ClientBuilder setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public ClientBuilder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        public ClientBuilder setFormat(String format) {
            this.format = format;
            return this;
        }

        public ClientBuilder setGatewayUrl(String gatewayUrl) {
            this.gatewayUrl = gatewayUrl;
            return this;
        }

        public ClientBuilder setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }
        
        public ClientBuilder setSignType(String signType) {
            this.signType = signType;
            return this;
        }
        
        public String getAlipayPublicKey() {
            return alipayPublicKey;
        }
        
        public String getSignType() {
            return signType;
        }

        public String getAppid() {
            return appid;
        }

        public String getCharset() {
            return charset;
        }

        public String getFormat() {
            return format;
        }

        public String getGatewayUrl() {
            return gatewayUrl;
        }

        public String getPrivateKey() {
            return privateKey;
        }
    }

}
