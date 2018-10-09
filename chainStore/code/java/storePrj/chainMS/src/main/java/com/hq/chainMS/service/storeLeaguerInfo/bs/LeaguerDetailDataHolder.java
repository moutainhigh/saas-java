package com.hq.chainMS.service.storeLeaguerInfo.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storeLeaguerInfo.data.LeaguerDetailCacheDAO;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeClient.service.leaguerDetail.bs.LeaguerDetailClientMgr;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerDetailDataHolder{
	
	public static LeaguerDetailDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerDetailDataHolder.class);
	}
	
	public PageResp<LeaguerDetail> getLeaguerDetailPageInfo(LeaguerDetailQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<LeaguerDetail> page = LeaguerDetailClientMgr.getInstance().getLeaguerDetailPageInfo(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return page;
	}
	
	public LeaguerDetail get(long storeId, String leaguerDetailId) {
		LeaguerDetail data = LeaguerDetailCacheDAO.getInstance().get(storeId, leaguerDetailId);
		if(data != null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = LeaguerDetailClientMgr.getInstance().get(storeId,leaguerDetailId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
