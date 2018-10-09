package com.hq.chainStore.service.chainProduct.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainProductDAO extends RestDao<ChainProduct> {

	public static ChainProductDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainProductDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public ProductDetail findProductDetail(String productId, long chainId) {
		final String findPath = "findProductDetail";
		ReqMap reqMap = ReqMap.newInstance().add("productId", productId).add("chainId", chainId);
		RestResp restResp = super.rawGetReq(findPath, reqMap, 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), ProductDetail.class);
		}
		return null;
	}
}
