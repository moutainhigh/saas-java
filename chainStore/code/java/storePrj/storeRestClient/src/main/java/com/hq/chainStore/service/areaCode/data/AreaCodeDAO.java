package com.hq.chainStore.service.areaCode.data;

import java.util.List;

import com.hq.chainStore.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;

public class AreaCodeDAO extends RestDao<AreaCode> {

	public static AreaCodeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public List<AreaCode> findByCondUnAuth(String findPath, AreaCodeQueryForm queryForm){
		try {
			final String uriPattern = "{}/{}/{}/{}?{}pageItemCount={}&pageNo={}";
			String uri = StringFormatUtil.format(uriPattern, getService(), "unAuth", "areaCode", findPath, queryForm.toReqMap().toReqParam(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return (List<AreaCode>) RestProxy.getInstance().list(uri, AreaCode.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("AreaCodeDAO.findByCondUnAuth()", "AreaCode", findPath, e));
		}
	}
	
}
