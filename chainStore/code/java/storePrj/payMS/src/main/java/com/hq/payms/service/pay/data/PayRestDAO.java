package com.hq.payms.service.pay.data;

import com.hq.payms.common.config.PayMSCfgMgr;
import com.hq.payms.service.pay.apiData.callback.PayCallbackForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class PayRestDAO{
	public static PayRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PayRestDAO.class);
	}

	private final String table = "pay";

	private String getService() {
		return PayMSCfgMgr.getProp().getStoreMSHost();
	}
	
	public void payCallback(PayCallbackForm form){
		String path = "payCallback";
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), table, path);
			//未抛异常则表示回调成功
			RestProxy.getInstance().add(uri, form, Void.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayRestDAO.payCallback()", table, path, e));
		}
	}

}
