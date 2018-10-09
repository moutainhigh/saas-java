package com.hq.payms.zenmind.zmWxpay.safeNormal;

import java.io.InputStream;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayDomainSimpleImpl;
import com.zenmind.common.hotSwap.HotSwap;

public class ZmSafeNormalWXPayConfigImpl extends WXPayConfig {
	
	public static ZmSafeNormalWXPayConfigImpl getInstance(){
		return HotSwap.getInstance().getSingleton(ZmSafeNormalWXPayConfigImpl.class);
	}
	
	private ZmWXPayCfg zmWXPayCfg;

	public void init(ZmWXPayCfg zmWXPayCfgP) throws Exception {
		zmWXPayCfg = zmWXPayCfgP;
	}

	@Override
	@Deprecated
	public String getAppID() {
		throw new RuntimeException("调用了废弃的方法");
	}

	@Override
	@Deprecated
	public String getMchID() {
		throw new RuntimeException("调用了废弃的方法");
	}

	@Override
	@Deprecated
	public String getKey() {
		throw new RuntimeException("调用了废弃的方法");
	}

	@Override
	@Deprecated
	public InputStream getCertStream() {
		throw new RuntimeException("调用了废弃的方法");
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
