package com.hq.storeClient.service.pay.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.service.pay.apiData.BeScanApiForm;
import com.hq.storeClient.service.pay.apiData.MiniProgramApiForm;
import com.hq.storeClient.service.pay.apiData.PayCallbackForm;
import com.hq.storeClient.service.pay.apiData.ScanApiForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class PayDAO extends RestDao<PayResp> {
	
	public static PayDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PayDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	private final String table = "pay";
	
	public PayResp beScan(BeScanApiForm form){
		String path = "beScan";
		PayResp target = null;
		try {
			final String uriPattern = "{}/{}/{}";
		
			String uri = StringFormatUtil.format(uriPattern, getService(), table,path);
			target = RestProxy.getInstance().add(uri, form, PayResp.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.beScan()", table, path, e));
		}
		return target;
	}
	
	public PayResp scan(ScanApiForm form){
		String path = "scan";
		PayResp target = null;
		try {
			final String uriPattern = "{}/{}/{}";
		
			String uri = StringFormatUtil.format(uriPattern, getService(), table,path);
			target = RestProxy.getInstance().add(uri, form, PayResp.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.scan()", table, path, e));
		}
		return target;
	}
	
	public MiniProgramPayResp miniProgramPay(MiniProgramApiForm form){
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
	
	public void payCallback(PayCallbackForm form){
		String path = "payCallback";
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), table, path);
			RestProxy.getInstance().rawReq(uri, form);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.payCallback()", table, path, e));
		}
	}
	
}
