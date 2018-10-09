package com.hq.storeMS.service.homePage.bs.mgr;

import com.hq.storeMS.service.homePage.apiData.QueryHomePageForm;
import com.hq.storeMS.service.homePage.data.HomePage;

public interface IHomePageMgr {
	public void attachInfo(HomePage homePage, QueryHomePageForm queryForm);
}
