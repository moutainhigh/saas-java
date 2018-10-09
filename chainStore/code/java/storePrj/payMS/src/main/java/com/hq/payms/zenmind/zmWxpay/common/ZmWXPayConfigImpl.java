package com.hq.payms.zenmind.zmWxpay.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.zenmind.common.hotSwap.HotSwap;

public class ZmWXPayConfigImpl extends WXPayConfig {
	
	public static ZmWXPayConfigImpl getInstance(){
		return HotSwap.getInstance().getSingleton(ZmWXPayConfigImpl.class);
	}
	
	private byte[] certData; // 微信支付接口中，涉及资金回滚的接口会使用到商户证书，包括退款、撤销接口。
	
	private ZmWXPayCfg zmWXPayCfg;

	public void init(ZmWXPayCfg zmWXPayCfgP) throws Exception {
		zmWXPayCfg = zmWXPayCfgP;
		String certPath = zmWXPayCfg.getWxpayCertPath();
		initCertData(certPath);
	}
	
	private void initCertData(String certPath) throws Exception {
		if(StringUtils.isBlank(certPath)) return;
		InputStream certStream = null;
		try {
			//本地测试的服务商商户证书
//			certPath = "E:/honkonDoc/honkon/cert/provider/apiclient_cert.p12";
			
			File file = new File(certPath);
			certStream = new FileInputStream(file);
			certData = new byte[(int) file.length()];
			certStream.read(certData);
		}finally {
			if(certStream != null) {
				try {
					certStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public String getAppID() {
		return zmWXPayCfg.getWxpayAppId();
	}

	@Override
	public String getMchID() {
		return zmWXPayCfg.getWxpayMchId();
	}

	@Override
	public String getKey() {
		return zmWXPayCfg.getWxpayKey();
	}

	@Override
	public InputStream getCertStream() {
		ByteArrayInputStream certBis;
		certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	public int getHttpConnectTimeoutMs() {
		return zmWXPayCfg.getWxpayHttpConnectTimeoutMs();
	}

	public int getHttpReadTimeoutMs() {
		return zmWXPayCfg.getWxpayHttpReadTimeoutMs();
	}

	@Override
	public IWXPayDomain getWXPayDomain() {
		return ZmWXPayDomainSimpleImpl.instance();
	}

	public String getPrimaryDomain() {
		return WXPayConstants.DOMAIN_API;
	}

	public String getAlternateDomain() {
		return WXPayConstants.DOMAIN_API2;
	}
	
	@Override
	public boolean shouldAutoReport() {
        return false; //不必报告
    }

	@Override
	public int getReportWorkerNum() {
		return 1;
	}

	@Override
	public int getReportBatchSize() {
		return 2;
	}
}
