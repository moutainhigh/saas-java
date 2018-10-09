package com.hq.payRestClient.service.pay.data;

import com.hq.payRestClient.service.common.PayClientCfg;
import com.hq.payRestClient.service.pay.apiData.BeScanPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayApiForm;
import com.hq.payRestClient.service.pay.apiData.MiniProgramPayResp;
import com.hq.payRestClient.service.pay.apiData.PayQueryApiForm;
import com.hq.payRestClient.service.pay.apiData.PayQueryResp;
import com.hq.payRestClient.service.pay.apiData.ScanPayApiForm;
import com.hq.payRestClient.service.qrcode.apiData.QrCodeResp;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class PayRestDAO extends RestDao<QrCodeResp> {
	
	public static PayRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PayRestDAO.class);
	}

	private final String table = "pay";
	
	@Override
	public String getService() {
		return PayClientCfg.getPayServer();
	}
	
	public QrCodeResp beScan(BeScanPayApiForm form){
		String path = "beScan";
		QrCodeResp target = null;
		try {
			final String uriPattern = "{}/{}/{}";
		
			String uri = StringFormatUtil.format(uriPattern, getService(), table,path);
			target = RestProxy.getInstance().add(uri, form, QrCodeResp.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.beScan()", table, path, e));
		}
		return target;
	}
	
	public void scan(ScanPayApiForm form){
		String path = "scan";
		try {
			final String uriPattern = "{}/{}/{}";
		
			String uri = StringFormatUtil.format(uriPattern, getService(), table,path);
			//未抛异常则表示预下单成功
			RestProxy.getInstance().add(uri, form, Void.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.scan()", table, path, e));
		}
	}
	
	public MiniProgramPayResp miniProgramPay(MiniProgramPayApiForm form){
		String path = "miniProgramPay";
		MiniProgramPayResp target = null;
		try {
			final String uriPattern = "{}/{}/{}";
		
			String uri = StringFormatUtil.format(uriPattern, getService(), table,path);
			target = RestProxy.getInstance().add(uri, form, MiniProgramPayResp.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.miniProgramPay()", table, path, e));
		}
		return target;
	}
	
	public PayQueryResp query(PayQueryApiForm form) {
		String path = "query";
		PayQueryResp target  = null;
		try {
			ReqMap reqMap = new ReqMap();
			reqMap.add("apiType", form.getApiType());
			reqMap.add("outTradeNo", form.getOutTradeNo());
			String reqParam = reqMap.toReqParam();
			//http://{sesrviceId}/{table}/{uriPath}?{param}
			final String uriPattern = "{}/{}/{}?{}";
			
			String uri = StringFormatUtil.format(uriPattern, getService(), table, path, reqParam);
			RestResp restResp = RestProxy.getInstance().rawGetReq(uri);
			target = JsonUtil.getInstance().fromJson(restResp.gettJson(), PayQueryResp.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.query()", table, path, e));
		}
		return target;
	}
	

}
