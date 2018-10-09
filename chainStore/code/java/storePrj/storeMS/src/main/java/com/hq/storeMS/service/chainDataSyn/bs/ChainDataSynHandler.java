package com.hq.storeMS.service.chainDataSyn.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.storeMS.service.chainDataSyn.data.GoodsSyn;
import com.hq.storeMS.service.chainDataSyn.data.MemberCardSyn;
import com.hq.storeMS.service.chainDataSyn.data.PackageProjectSyn;
import com.hq.storeMS.service.chainDataSyn.data.ProductCardSyn;
import com.hq.storeMS.service.chainDataSyn.data.ProductSyn;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainDataSynHandler {

	public static ChainDataSynHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainDataSynHandler.class);
	}
	
	private final LogModule logModule = LogModule.ChainDataSyn;
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findChainGoods(ChainDataQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<GoodsSyn> pageInfo = GoodsSynMgr.getInstance().findChainGoods(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChainDataSynHandler[findChainGoods]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findChainProduct(ChainDataQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<ProductSyn> pageInfo = ProductSynMgr.getInstance().findChainProduct(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChainDataSynHandler[findChainProduct]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findChainProductCard(ChainDataQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<ProductCardSyn> pageInfo = ProductCardSynMgr.getInstance().findChainProductCard(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChainDataSynHandler[findChainProductCard]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findChainPackageProject(ChainDataQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<PackageProjectSyn> pageInfo = PackageProjectSynMgr.getInstance().findChainPackageProject(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChainDataSynHandler[findChainProductCard]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findChainMemberCard(ChainDataQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<MemberCardSyn> pageInfo = MemberCardSynMgr.getInstance().findChainMemberCard(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChainDataSynHandler[findChainProductCard]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
