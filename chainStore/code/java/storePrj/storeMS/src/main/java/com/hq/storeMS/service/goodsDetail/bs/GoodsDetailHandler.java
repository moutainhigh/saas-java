package com.hq.storeMS.service.goodsDetail.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;
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
	
	public ReqResult<GoodsDetail> getStoreGoodsDetail(long storeId, String id) {
		ReqResult<GoodsDetail> result = ReqResult.newInstance(false, GoodsDetail.class);
		try {
			if(storeId==0) {
				storeId=getStoreId();
			}
			GoodsDetail goodsDetail = GoodsDetailMgr.getInstance().get(storeId, id);
			result.setTarget(goodsDetail);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "GoodsDetailHandler[getStoreGoodsDetail]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
}
