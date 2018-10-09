package com.hq.chainStore.service.bonus.data;

import com.hq.chainStore.service.bonus.apiData.BonusRecordQueryForm;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class BonusRecordDAO extends RestDao<BonusRecord> {

	public static BonusRecordDAO getInstance(){
		return HotSwap.getInstance().getSingleton(BonusRecordDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<BonusRecord> findBonusRecordPageInfo(String findPath, BonusRecordQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), BonusRecord.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("BonusRecordDAO.findBonusRecordPageInfo()", "bonusRecord", "", e));
		}
	}
	
	public PageResp<BonusRecordGroup> findBonusRecordGroupPageInfo(String findPath, BonusRecordQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), BonusRecordGroup.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("BonusRecordDAO.findBonusRecordGroupPageInfo()", "bonusRecord", "", e));
		}
	}
}
