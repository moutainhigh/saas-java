package com.hq.chainStore.service.leaguerDetail.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class LeaguerDetailDAO extends RestDao<LeaguerDetail> {

	public static LeaguerDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<LeaguerDetail> getLeaguerDetailPageInfo(String findPath, LeaguerDetailQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), LeaguerDetail.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("LeaguerDetailDAO.getLeaguerDetailPageInfo()", "leaguerDetail", "", e));
		}
	}
	
	public MemberCardExist checkMemberCardExist(String findPath, String number, long storeId) {
		try {
			final String uriPattern = "{}/{}/{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, getService(), "leaguerDetail", findPath, storeId, number);
			RestResp restResp = RestProxy.getInstance().rawGetReq(uri);
			return JsonUtil4Client.getInstance().fromJson(restResp.gettJson(), MemberCardExist.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("LeaguerDetailDAO.checkMemberCardExist()", "leaguerDetail", "", e));
		}
	}
	
}
