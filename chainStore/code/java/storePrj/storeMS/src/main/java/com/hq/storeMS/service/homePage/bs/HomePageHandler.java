package com.hq.storeMS.service.homePage.bs;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.homePage.apiData.QueryHomePageForm;
import com.hq.storeMS.service.homePage.data.HomePage;
import com.zenmind.common.hotSwap.HotSwap;

public class HomePageHandler {

	public static HomePageHandler getInstance() {
		return HotSwap.getInstance().getSingleton(HomePageHandler.class);
	}
	
	private final LogModule logModule = LogModule.HomePage;
	
	public ReqResult<HomePage> getHomePageData(QueryHomePageForm queryForm, String items) {
		ReqResult<HomePage> result = ReqResult.newInstance(false, HomePage.class);
		try {
			queryForm.setItems(getItemsEnumIndexs(items));
			HomePage target = HomePageMgr.getInstance().getHomePageData(queryForm);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "HomePageHandler[getHomePageData]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private Set<Integer> getItemsEnumIndexs(String items){
		Set<Integer> itemsEnumIndexs = new HashSet<Integer>();
		if(StringUtils.isNoneBlank(items)){
			String[] itemArray = items.split(",");
			for (String itm : itemArray) {
				itemsEnumIndexs.add(Integer.valueOf(itm));
			}
		}
		return itemsEnumIndexs;
	}
}
