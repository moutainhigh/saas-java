package com.hq.chainStore.service.homePage.bs;

import com.hq.chainStore.service.homePage.apiData.QueryHomePageForm;
import com.hq.chainStore.service.homePage.data.HomePage;
import com.hq.chainStore.service.homePage.data.HomePageDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class HomePageMgr {

	public static HomePageMgr getInstance(){
		return HotSwap.getInstance().getSingleton(HomePageMgr.class);
	}
	
	public HomePage getHomePageData(QueryHomePageForm queryForm) {
		return HomePageDAO.getInstance().getHomePageData(queryForm);
	}
}
