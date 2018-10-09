package com.hq.chainStore.service.homePage.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.homePage.apiData.QueryHomePageForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class HomePageDAO {
	
	public static HomePageDAO getInstance(){
		return HotSwap.getInstance().getSingleton(HomePageDAO.class);
	}
	
	public HomePage getHomePageData(QueryHomePageForm queryForm){
		final String uriPattern = "{}/{}/{}?{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "homePage", "getHomePageData", queryForm.toReqMap().toReqParam());
		RestResp restResp = RestProxy.getInstance().rawGetReq(uri);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), HomePage.class);
	}
}
