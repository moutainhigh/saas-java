package com.hq.storeMS.service.sellItem.bs;

import java.util.List;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.sellItem.apiData.SellItemIdForm;
import com.hq.storeMS.service.sellItem.apiData.SellItemQueryForm;
import com.hq.storeMS.service.sellItem.data.SellItem;
import com.zenmind.common.hotSwap.HotSwap;

public class SellItemHandler {

	public static SellItemHandler getInstance() {
		return HotSwap.getInstance().getSingleton(SellItemHandler.class);
	}

	private final LogModule logModule = LogModule.SellItem;

	public ReqResult<SellItem> getSellItemList(SellItemQueryForm queryForm) {
		ReqResult<SellItem> result = ReqResult.newInstance(false, SellItem.class);
		try {
			List<SellItem> list = SellItemMgr.getInstance().getSellItemList(queryForm);
			result.setTargetList(list);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "SellItemHandler[getSellItemList]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<SellItem> getSellItem(long storeId, int sellItemType, String id) {
		ReqResult<SellItem> result = ReqResult.newInstance(false, SellItem.class);
		try {
			SellItem target = SellItemMgr.getInstance().getSellItemByIdForm(storeId, SellItemIdForm.newInstance(sellItemType, id));
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "SellItemHandler[getSellItem]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, sellItemType, id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
