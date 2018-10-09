package com.hq.chainStore.service.chainGoods.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainGoodsDAO extends RestDao<ChainGoods> {

	public static ChainGoodsDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainGoodsDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public GoodsDetail findGoodsDetail(String goodsId, long chainId) {
		final String findPath = "findGoodsDetail";
		ReqMap reqMap = ReqMap.newInstance().add("goodsId", goodsId).add("chainId", chainId);
		RestResp restResp = super.rawGetReq(findPath, reqMap, 1, 1);
		if (restResp != null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), GoodsDetail.class);
		}
		return null;
	}
}
