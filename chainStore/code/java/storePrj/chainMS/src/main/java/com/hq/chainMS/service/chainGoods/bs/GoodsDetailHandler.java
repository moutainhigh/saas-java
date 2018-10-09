package com.hq.chainMS.service.chainGoods.bs;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.chainGoods.apiData.GoodsDetailQueryForm;
import com.hq.chainMS.service.chainGoods.data.GoodsDetail;
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
			final String logId = "GoodsDetailHandler[getGoodsDetailPageInfo]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<GoodsDetail> getGoodsDetail(long chainId, String id) {
		ReqResult<GoodsDetail> result = ReqResult.newInstance(false, GoodsDetail.class);
		try {
			GoodsDetail goodsDetail = GoodsDetailMgr.getInstance().get(chainId, id);
			result.setTarget(goodsDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "GoodsDetailHandler[getGoodsDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
