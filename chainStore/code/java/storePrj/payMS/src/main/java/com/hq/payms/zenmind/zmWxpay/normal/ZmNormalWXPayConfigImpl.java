package com.hq.payms.zenmind.zmWxpay.normal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayAppParam;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayCfg;
import com.hq.payms.zenmind.zmWxpay.common.ZmWXPayDomainSimpleImpl;
import com.zenmind.common.hotSwap.HotSwap;

public class ZmNormalWXPayConfigImpl extends WXPayConfig {
	
	public static ZmNormalWXPayConfigImpl getInstance(){
		return HotSwap.getInstance().getSingleton(ZmNormalWXPayConfigImpl.class);
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
			//本地测试的商户证书
//			certPath = "E:/honkonDoc/honkon/cert/normal/apiclient_cert.p12";
			
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
	
	/**
	 * 读取本地证书文件
	 * @param certPath
	 * @return
	 */
	private byte[] readCertData(String certPath){
		if(StringUtils.isBlank(certPath)) return null;
		InputStream certStream = null;
		try {
			File file = new File(certPath);
			certStream = new FileInputStream(file);
			byte[] byteData = new byte[(int) file.length()];
			certStream.read(byteData);
			return byteData;
		}catch (Exception ie) {
			ie.printStackTrace();
		} finally {
			if(certStream != null) {
				try {
					certStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 读取远程证书文件
	 * @param certPath
	 * @return
	 */
	private byte[] readCertDataFromNet(String netPath) {
		if(StringUtils.isBlank(netPath)) return null;
		InputStream inStream = null;
		ByteArrayOutputStream swapStream = null;
		try {
			URL url = new URL(netPath);
			inStream = url.openStream();
			swapStream = new ByteArrayOutputStream(); 
			final int len = 8;
			byte[] buff = new byte[len]; //buff用于存放循环读取的临时数据 
			int readLen = 0; 
			while ((readLen = inStream.read(buff, 0, len)) > 0) { 
				swapStream.write(buff, 0, readLen); 
			} 
			return swapStream.toByteArray();
		} catch (Exception ie) {
			ie.printStackTrace();
		} finally {
			try {
				if (swapStream != null) {
					swapStream.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
     * 动态切换配置,主要是为了切换ZmWXPayAppParam的信息
     * @param configP
     */
	public void setAppParam(ZmWXPayAppParam appParam) throws Exception{
		zmWXPayCfg.setWxpayAppId(appParam.getAppId());
		zmWXPayCfg.setWxpayMchId(appParam.getMchId());
		zmWXPayCfg.setWxpayKey(appParam.getKey());
		zmWXPayCfg.setWxpayCertPath(appParam.getCertPath());
		this.init(zmWXPayCfg);
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
