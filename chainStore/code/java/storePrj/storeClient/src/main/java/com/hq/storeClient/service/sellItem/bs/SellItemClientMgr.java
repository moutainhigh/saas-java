package com.hq.storeClient.service.sellItem.bs;

import java.util.List;

import com.hq.storeClient.service.sellItem.apiData.SellItemQueryForm;
import com.hq.storeClient.service.sellItem.data.SellItem;
import com.hq.storeClient.service.sellItem.data.SellItemDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class SellItemClientMgr {
	public static SellItemClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(SellItemClientMgr.class);
	}
	
	public SellItem get(long storeId, int sellItemType, String id) {
		final String uriPath = "getSellItem";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", storeId).add("sellItemType", sellItemType).add("id", id);
		return SellItemDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
	}
	
	public List<SellItem> getSellItemList(SellItemQueryForm queryForm) {
		final String actionName = "getSellItemList";
		return SellItemDAO.getInstance().getSellItemList(actionName, queryForm);
	}
}
