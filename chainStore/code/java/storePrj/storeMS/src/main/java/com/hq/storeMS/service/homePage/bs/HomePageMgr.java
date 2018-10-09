package com.hq.storeMS.service.homePage.bs;

import com.hq.storeMS.service.homePage.apiData.QueryHomePageForm;
import com.hq.storeMS.service.homePage.bs.mgr.HomePageMgrHelper;
import com.hq.storeMS.service.homePage.data.HomePage;
import com.zenmind.common.hotSwap.HotSwap;

public class HomePageMgr {

	public static HomePageMgr getInstance(){
		return HotSwap.getInstance().getSingleton(HomePageMgr.class);
	}
	
	public HomePage getHomePageData(QueryHomePageForm queryForm) {
		HomePage homePage = HomePage.newInstance();
		HomePageMgrHelper.getInstance().attachInfo(homePage, queryForm);
		return homePage;
	}
}
