package com.hq.storeClient.service.sellItem.data;

import java.util.List;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.service.sellItem.apiData.SellItemQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class SellItemDAO extends RestDao<SellItem> {

	public static SellItemDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SellItemDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	// 由于参数过于复杂，使用post方式发送数据 获取产品列表
	public List<SellItem> getSellItemList(String actionName, SellItemQueryForm queryForm) {
		RestResp restResp = super.rawReq(actionName, queryForm);
		return JsonUtil.getInstance().parseList(restResp.gettListJson(), SellItem.class);
	}

}
