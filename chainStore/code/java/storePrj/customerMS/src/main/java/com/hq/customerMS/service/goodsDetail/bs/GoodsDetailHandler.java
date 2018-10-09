package com.hq.customerMS.service.goodsDetail.bs;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeClient.service.goodsDetail.data.GoodsDetail;
import com.zenmind.common.hotSwap.HotSwap;

public class GoodsDetailHandler {

	public static GoodsDetailHandler getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailHandler.class);
	}

	private final LogModule logModule = LogModule.GoodsDetail;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> getGoodsDetailPageInfo(GoodsDetailQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<GoodsDetail> pageResp = GoodsDetailMgr.getInstance().getGoodsDetailPageInfo(queryForm);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "GoodsDetailHandler[getGoodsDetailPageInfo]", reason, e);
		}
		return result;
	}
	
	public ReqResult<GoodsDetail> getGoodsDetail(long storeId, String id) {
		ReqResult<GoodsDetail> result = ReqResult.newInstance(false, GoodsDetail.class);
		try {
			GoodsDetail goodsDetail = GoodsDetailMgr.getInstance().get(storeId, id);
			result.setTarget(goodsDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "GoodsDetailHandler[getGoodsDetail]", reason, e);
		}
		return result;
	}
}
