package com.hq.payms.zenmind.zmAlipay.normal;

import org.apache.commons.lang.StringUtils;

import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.zm.ZmAlipayAppParam;
import com.hq.payms.zenmind.zmAlipay.common.ZmAlipayClient;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * AlipayClient的mgr类，管理各个商户对应的AlipayClient<br>
 * 
 * 使用单个client对象动态设置属性的方式<br>
 * 这种方式中client是多线程共享，可能存在线程安全问题
 * 
 * @see https://openclub.alipay.com/read.php?tid=2123&fid=56<br>
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmNormalAlipayClientSingleMgr {
	
	public static ZmNormalAlipayClientSingleMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ZmNormalAlipayClientSingleMgr.class);
	}
	
	private ZmAlipayClient client;
	
	public ZmNormalAlipayClientSingleMgr(){
		client = new ClientBuilder().build();
	}
	
	public ZmAlipayClient getClient(ZmAlipayAppParam appParam){
		if(!appParam.isVaild()) {
			throw new IllegalArgumentException("ZmAlipayAppParam mustn't be empty!");
		}
		//切换商户信息
		client.setAppId(appParam.getAppId());
		client.setPrivateKey(appParam.getPrivateKey());
		client.setAlipayPublicKey(appParam.getAlipayPublicKey());
		return client; 
	}

	public static class ClientBuilder {
        private String gatewayUrl;
        private String appid;
        private String privateKey;
        private String format;
        private String charset;
        private String alipayPublicKey;
        private String signType;
        
        private int httpConnectTimeoutMs;
    	private int httpReadTimeoutMs;

        public ZmAlipayClient build() {
            if (StringUtils.isEmpty(gatewayUrl)) {
                gatewayUrl = Configs.getOpenApiDomain(); // 与mcloudmonitor网关地址不同
            }
            if (StringUtils.isEmpty(appid)) {
                appid = Configs.getAppid();
            }
            if (StringUtils.isEmpty(privateKey)) {
                privateKey = Configs.getPrivateKey();
            }
            if (StringUtils.isEmpty(format)) {
                format = "json";
            }
            if (StringUtils.isEmpty(charset)) {
                charset = "utf-8";
            }
            if (StringUtils.isEmpty(alipayPublicKey)) {
                alipayPublicKey = Configs.getAlipayPublicKey();
            }
            if (StringUtils.isEmpty(signType)) {
                signType = Configs.getSignType();
            }
            
            httpConnectTimeoutMs = Configs.getHttpConnectTimeoutMs();
            httpReadTimeoutMs = Configs.getHttpReadTimeoutMs();
            
            ZmAlipayClient client = new ZmAlipayClient(this.getGatewayUrl(), this.getAppid(), this.getPrivateKey(),
            							this.getFormat(), this.getCharset(), this.getAlipayPublicKey(), this.getSignType());
            client.setConnectTimeout(this.getHttpConnectTimeoutMs());
            client.setReadTimeout(this.getHttpReadTimeoutMs());
            
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

		public int getHttpConnectTimeoutMs() {
			return httpConnectTimeoutMs;
		}

		public void setHttpConnectTimeoutMs(int httpConnectTimeoutMs) {
			this.httpConnectTimeoutMs = httpConnectTimeoutMs;
		}

		public int getHttpReadTimeoutMs() {
			return httpReadTimeoutMs;
		}

		public void setHttpReadTimeoutMs(int httpReadTimeoutMs) {
			this.httpReadTimeoutMs = httpReadTimeoutMs;
		}

        
    }

}
