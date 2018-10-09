package com.hq.chainStore.service.pay.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.pay.apiData.BeScanApiForm;
import com.hq.chainStore.service.pay.apiData.ScanApiForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class PayDAO extends RestDao<PayResp> {

	private final String table = "pay";
	
	public static PayDAO getInstance(){
		return HotSwap.getInstance().getSingleton(PayDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public PayResp beScan(String path, BeScanApiForm form){
		try {
			RestResp restResp = super.rawReq(path, form);
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), PayResp.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayDAO.beScan()", this.table, path, e));
		}
	}
	
	public PayResp scan(String path, ScanApiForm form){
		try {
			RestResp restResp = super.rawReq(path, form);
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), PayResp.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PayDAO.scan()", this.table, path, e));
		}
	}
	
}
