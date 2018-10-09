package com.hq.chainStore.service.leaguerRecord.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class LeaguerRecordDAO extends RestDao<LeaguerRecord> {

	public static LeaguerRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerRecordDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public PageResp<LeaguerRecord> getLeaguerRecordPageInfo(String findPath, LeaguerRecordQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), LeaguerRecord.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("LeaguerRecordDAO.getLeaguerRecordPageInfo()", "LeaguerRecord", "", e));
		}
	}
	
}
