package com.hq.chainStore.service.chainDataSyn.bs;

import com.hq.chainStore.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.chainStore.service.chainDataSyn.data.ChainDataSynDAO;
import com.hq.chainStore.service.chainDataSyn.data.GoodsSyn;
import com.hq.chainStore.service.chainDataSyn.data.MemberCardSyn;
import com.hq.chainStore.service.chainDataSyn.data.PackageProjectSyn;
import com.hq.chainStore.service.chainDataSyn.data.ProductCardSyn;
import com.hq.chainStore.service.chainDataSyn.data.ProductSyn;
import com.hq.chainStore.service.common.PageResp;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainDataSynMgr {

	public static ChainDataSynMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ChainDataSynMgr.class);
	}

	public PageResp<GoodsSyn> findChainGoods(ChainDataQueryForm queryForm) {
		return ChainDataSynDAO.getInstance().findChainGoods(queryForm);
	}

	public PageResp<MemberCardSyn> findChainMemberCard(ChainDataQueryForm queryForm) {
		return ChainDataSynDAO.getInstance().findChainMemberCard(queryForm);
	}

	public PageResp<PackageProjectSyn> findChainPackageProject(ChainDataQueryForm queryForm) {
		return ChainDataSynDAO.getInstance().findChainPackageProject(queryForm);
	}

	public PageResp<ProductSyn> findChainProduct(ChainDataQueryForm queryForm) {
		return ChainDataSynDAO.getInstance().findChainProduct(queryForm);
	}

	public PageResp<ProductCardSyn> findChainProductCard(ChainDataQueryForm queryForm) {
		return ChainDataSynDAO.getInstance().findChainProductCard(queryForm);
	}
}
