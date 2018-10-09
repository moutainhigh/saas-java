package com.hq.chainStore.service.chainCard.data;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainCardDAO extends RestDao<ChainCard> {

	public static ChainCardDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainCardDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public ProductCardDetail findProductCardDetail(String productCardId, long chainId) {
		final String findPath = "findProductCardDetail";
		ReqMap reqMap = ReqMap.newInstance().add("productCardId", productCardId).add("chainId", chainId);
		RestResp restResp = super.rawGetReq(findPath, reqMap, 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), ProductCardDetail.class);
		}
		return null;
	}

	public MembershipCardDetail findMemberCardDetail(String memberCardId, long chainId) {
		final String findPath = "findMemberCardDetail";
		ReqMap reqMap = ReqMap.newInstance().add("memberCardId", memberCardId).add("chainId", chainId);
		RestResp restResp = super.rawGetReq(findPath, reqMap, 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil.getInstance().fromJson(restResp.gettJson(), MembershipCardDetail.class);
		}
		return null;
	}
}
