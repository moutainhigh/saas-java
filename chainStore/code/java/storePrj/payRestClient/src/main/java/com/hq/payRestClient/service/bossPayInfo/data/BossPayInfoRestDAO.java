package com.hq.payRestClient.service.bossPayInfo.data;

import com.hq.payRestClient.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payRestClient.service.common.PayClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class BossPayInfoRestDAO extends RestDao<BossPayInfo> {

	
	public static BossPayInfoRestDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BossPayInfoRestDAO.class);
	}

	private final String table = "bossPayInfo";
	
	@Override
	public String getService() {
		return PayClientCfg.getPayServer();
	}

	
	public BossPayInfo add(BossPayInfoAddApiForm form){
		String path = "";
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), table,path);
			BossPayInfo target = RestProxy.getInstance().add(uri, form, BossPayInfo.class);
			return target;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("BossPayInfoRestDAO.add()", table, path, e));
		}
	}
	
	public BossPayInfo update(long id, BossPayInfoAddApiForm updateForm){
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), table,id);
			BossPayInfo target = RestProxy.getInstance().update(uri, updateForm, BossPayInfo.class);
			return target;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				//如果是RestProxyException直接往上抛
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("BossPayInfoRestDAO.update()", table, id+"", e));
		}
		
	}

	public BossPayInfo get(long id) {
		try {
			final String uriPattern = "{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), table,id);
			BossPayInfo target = RestProxy.getInstance().get(uri, BossPayInfo.class);
			return target;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("BossPayInfoRestDAO.get()", table, id+"", e));
		}
		
	}

	public BossPayInfo findByStoreId(long storeId){
		String path = "/findByStoreId";
		ReqMap reqMap = new ReqMap();
		reqMap.add("storeId", storeId);
		try {
			final String uriPattern = "{}/{}/{}?{}";
			String reqParam = reqMap.toReqParam();
			String uri = StringFormatUtil.format(uriPattern, getService(), table, path, reqParam);
			BossPayInfo target = RestProxy.getInstance().get(uri, BossPayInfo.class);
			return target;
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("BossPayInfoRestDAO.findByStoreId()",table, path, e));
		}
	}
}
