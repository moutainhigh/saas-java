package com.hq.chainStore.service.chainDataSyn.data;

import com.hq.chainStore.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.common.JsonUtil4Client;
import com.hq.common.StringUtils4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

public class ChainDataSynDAO extends RestDao<ChainDataSyn> {

	public static ChainDataSynDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ChainDataSynDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<GoodsSyn> findChainGoods(ChainDataQueryForm queryForm) {
		final String findPath = "findChainGoods";
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), GoodsSyn.class);
		}
		return null;
	}

	public PageResp<MemberCardSyn> findChainMemberCard(ChainDataQueryForm queryForm) {
		final String findPath = "findChainMemberCard";
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), MemberCardSyn.class);
		}
		return null;
	}

	public PageResp<PackageProjectSyn> findChainPackageProject(ChainDataQueryForm queryForm) {
		final String findPath = "findChainPackageProject";
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), PackageProjectSyn.class);
		}
		return null;
	}

	public PageResp<ProductSyn> findChainProduct(ChainDataQueryForm queryForm) {
		final String findPath = "findChainProduct";
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ProductSyn.class);
		}
		return null;
	}

	public PageResp<ProductCardSyn> findChainProductCard(ChainDataQueryForm queryForm) {
		final String findPath = "findChainProductCard";
		RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), 1, 1);
		if(restResp!=null && StringUtils4Client.isNotBlank(restResp.gettJson())) {
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), ProductCardSyn.class);
		}
		return null;
	}
}
