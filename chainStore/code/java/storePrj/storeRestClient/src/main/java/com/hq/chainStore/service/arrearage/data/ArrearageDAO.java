package com.hq.chainStore.service.arrearage.data;

import java.util.List;

import com.hq.chainStore.service.arrearage.apiData.ArrearageQueryForm;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class ArrearageDAO extends RestDao<Arrearage> {

	public static ArrearageDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ArrearageDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<Arrearage> getArrearagePageInfo(String findPath, ArrearageQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), Arrearage.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("ArrearageDAO.getArrearagePageInfo()", "arrearage", "", e));
		}
	}
	
	public PageResp<ArrearageGroup> getArrearageGroupPageInfo(String findPath, ArrearageQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ArrearageGroup.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("ArrearageDAO.getArrearageGroupPageInfo()", "arrearage", "", e));
		}
	}
	
	public List<ArrearageGroup> findArrearageGroupList(String findPath, ArrearageQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil.getInstance().parseList(restResp.gettListJson(), ArrearageGroup.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("ArrearageDAO.findArrearageGroupList()", "arrearage", "", e));
		}
	}
	
}
