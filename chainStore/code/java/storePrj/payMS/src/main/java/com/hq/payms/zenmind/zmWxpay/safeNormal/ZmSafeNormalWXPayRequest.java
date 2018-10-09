package com.hq.payms.zenmind.zmWxpay.safeNormal;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayAppParam;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCertFileHelper;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 在<code>com.github.wxpay.sdk.WXPayRequest</code>基础上做了修改<br>
 * 1, 改成单例<br>
 * 2, 使<code>ZmWXPayAppParam<code>可作为参数传入
 * 
 * 关于安全性：<br>
 * 每次调支付方法都将ZmWXPayAppParam作为参数传入，而不是切换共享的WXPayConfig对象属性值，是线程安全的
 * 
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
public class ZmSafeNormalWXPayRequest {
	
	public static ZmSafeNormalWXPayRequest getInstance(){
		return HotSwap.getInstance().getSingleton(ZmSafeNormalWXPayRequest.class);
	}
	
    private WXPayConfig config;
    
    public void init(WXPayConfig config) throws Exception{
        this.config = config;
    }

    /**
     * 请求，只请求一次，不做重试
     * @param domain
     * @param urlSuffix
     * @param uuid
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @param useCert 是否使用证书，针对退款、撤销等操作
     * @return
     * @throws Exception
     */
    private String requestOnce(ZmWXPayAppParam appParam, final String domain, String urlSuffix, String uuid, String data, int connectTimeoutMs, int readTimeoutMs, boolean useCert) throws Exception {
        BasicHttpClientConnectionManager connManager;
        if (useCert) {
            // 证书
            char[] password = appParam.getMchId().toCharArray();
            // 证书如果加载速度慢，证书数据后续可能需要缓存起来
            InputStream certStream = ZmWXPayCertFileHelper.getInstance().getCertStreamByFileName(false, appParam.getCertPath());
            if(certStream == null) {
            	throw new RuntimeException("商户证书加载失败!" + "appId:" + appParam.getAppId());
            }
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(certStream, password);

            // 实例化密钥库 & 初始化密钥工厂
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, password);

            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());

            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslConnectionSocketFactory)
                            .build(),
                    null,
                    null,
                    null
            );
        }
        else {
            connManager = new BasicHttpClientConnectionManager(
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build(),
                    null,
                    null,
                    null
            );
        }

        HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connManager)
                .build();

        String url = "https://" + domain + urlSuffix;
        HttpPost httpPost = new HttpPost(url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeoutMs).setConnectTimeout(connectTimeoutMs).build();
        httpPost.setConfig(requestConfig);

        StringEntity postEntity = new StringEntity(data, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 " + appParam.getMchId());  // TODO: 很重要，用来检测 sdk 的使用情况，要不要加上商户信息？
        httpPost.setEntity(postEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");

    }


    private String request(ZmWXPayAppParam appParam, String urlSuffix, String uuid, String data, int connectTimeoutMs, int readTimeoutMs, boolean useCert, boolean autoReport) throws Exception {
        IWXPayDomain.DomainInfo domainInfo = config.getWXPayDomain().getDomain(config);
        if(domainInfo == null){
            throw new Exception("WXPayConfig.getWXPayDomain().getDomain() is empty or null");
        }
        try {
            String result = requestOnce(appParam, domainInfo.domain, urlSuffix, uuid, data, connectTimeoutMs, readTimeoutMs, useCert);
            return result;
        }
        catch (Exception ex) {
        	throw ex;
        }
        
    }


    /**
     * 可重试的，非双向认证的请求
     * @param urlSuffix
     * @param uuid
     * @param data
     * @return
     */
    public String requestWithoutCert(ZmWXPayAppParam appParam, String urlSuffix, String uuid, String data, boolean autoReport) throws Exception {
        return this.request(appParam, urlSuffix, uuid, data, config.getHttpConnectTimeoutMs(), config.getHttpReadTimeoutMs(), false, autoReport);
    }

    /**
     * 可重试的，非双向认证的请求
     * @param urlSuffix
     * @param uuid
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @return
     */
    public String requestWithoutCert(ZmWXPayAppParam appParam, String urlSuffix, String uuid, String data, int connectTimeoutMs, int readTimeoutMs,  boolean autoReport) throws Exception {
        return this.request(appParam, urlSuffix, uuid, data, connectTimeoutMs, readTimeoutMs, false, autoReport);
    }

    /**
     * 可重试的，双向认证的请求
     * @param urlSuffix
     * @param uuid
     * @param data
     * @return
     */
    public String requestWithCert(ZmWXPayAppParam appParam, String urlSuffix, String uuid, String data, boolean autoReport) throws Exception {
        return this.request(appParam, urlSuffix, uuid, data, config.getHttpConnectTimeoutMs(), config.getHttpReadTimeoutMs(), true, autoReport);
    }

    /**
     * 可重试的，双向认证的请求
     * @param urlSuffix
     * @param uuid
     * @param data
     * @param connectTimeoutMs
     * @param readTimeoutMs
     * @return
     */
    public String requestWithCert(ZmWXPayAppParam appParam, String urlSuffix, String uuid, String data, int connectTimeoutMs, int readTimeoutMs, boolean autoReport) throws Exception {
        return this.request(appParam, urlSuffix, uuid, data, connectTimeoutMs, readTimeoutMs, true, autoReport);
    }
}
