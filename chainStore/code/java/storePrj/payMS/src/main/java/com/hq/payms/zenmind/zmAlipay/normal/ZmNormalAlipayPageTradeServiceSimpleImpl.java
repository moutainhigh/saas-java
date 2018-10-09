package com.hq.payms.zenmind.zmAlipay.normal;

import org.apache.commons.lang.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.demo.trade.config.Configs;

/**
 *  电脑网站支付服务<br>
 *  仅支持单商户，不支持多商户<br>
 *  一定要在创建AlipayPageTradeService之前调用Configs.init("xxxxxx");设置参数
 */
public class ZmNormalAlipayPageTradeServiceSimpleImpl implements IntfZmNoramlAlipayPageTradeService{
	
	protected AlipayClient client;

    public static class ClientBuilder {
        private String gatewayUrl;
        private String appid;
        private String privateKey;
        private String format;
        private String charset;
        private String alipayPublicKey;
        private String signType;

        public ZmNormalAlipayPageTradeServiceSimpleImpl build() {
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

            return new ZmNormalAlipayPageTradeServiceSimpleImpl(this);
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

    public ZmNormalAlipayPageTradeServiceSimpleImpl(ClientBuilder builder) {
        if (StringUtils.isEmpty(builder.getGatewayUrl())) {
            throw new NullPointerException("gatewayUrl should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getAppid())) {
            throw new NullPointerException("appid should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getPrivateKey())) {
            throw new NullPointerException("privateKey should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getFormat())) {
            throw new NullPointerException("format should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getCharset())) {
            throw new NullPointerException("charset should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getAlipayPublicKey())) {
            throw new NullPointerException("alipayPublicKey should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getSignType())) {
            throw new NullPointerException("signType should not be NULL!");
        }
        
        client = new DefaultAlipayClient(builder.getGatewayUrl(), builder.getAppid(), builder.getPrivateKey(),
                builder.getFormat(), builder.getCharset(), builder.getAlipayPublicKey(), builder.getSignType());
    }
    
    public ZmNormalAlipayPageTradeServiceSimpleImpl() {
    	
    }

    @Override
    public String tradePagePay(AlipayTradePagePayRequest alipayRequest) {
    	try {
        	String formHtml = client.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        	return formHtml;
        } catch (AlipayApiException e) {
        	throw new RuntimeException(e);
        }
    }

}