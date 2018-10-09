package com.hq.storeMS.service.beauticianProduct.bs;

import com.hq.storeMS.service.beauticianProduct.apiData.AddForemost;
import com.hq.storeMS.service.beauticianProduct.apiData.BeauticianProductUpdateApiForm;
import com.hq.storeMS.service.beauticianProduct.apiData.BeauticianProductUpdateType;
import com.hq.storeMS.service.beauticianProduct.apiData.RemoveForemost;
import com.hq.storeMS.service.beauticianProduct.data.BeauticianProduct;
import com.hq.storeMS.service.beauticianProduct.data.BeauticianProductBeanHelper;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.zenmind.common.hotSwap.HotSwap;

public class BeauticianProductHandler {

	public static BeauticianProductHandler getInstance() {
		return HotSwap.getInstance().getSingleton(BeauticianProductHandler.class);
	}
	
	private final LogModule logModule = LogModule.BeauticianProduct;
	
	public ReqResult<BeauticianProduct> getBeauticianProduct(String id) {
		ReqResult<BeauticianProduct> result = ReqResult.newInstance(false, BeauticianProduct.class);
		try {
			BeauticianProduct beauticianProduct = BeauticianProductMgr.getInstance().get(id);
			result.setTarget(beauticianProduct);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "BeauticianProductHandler[getBeauticianProduct]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}

		return result;
	}
	
	public ReqResult<BeauticianProduct> updateBeauticianProduct(String beauticianProductId, BeauticianProductUpdateApiForm updateForm) {
		ReqResult<BeauticianProduct> result = ReqResult.newInstance(false, BeauticianProduct.class);
		try {
			BeauticianProductUpdateType updateType = BeauticianProductUpdateType.valueOf(updateForm.getUpdateType());
			boolean success = false;
			switch (updateType) {
			case AddForemost:
				success = addForemost(beauticianProductId, updateForm.getAddForemost());
				break;
			case RemoveForemost:
				success = removeForemost(beauticianProductId, updateForm.getRemoveForemost());
				break;
			default:
				break;
			}
			if (success) {
				result.setSuccess(true);
			} else {
				result.setTips(updateType.getMark() + "失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "BeauticianProductHandler[updateBeauticianProduct]";
			final String reason = LogHelper.getInstance().exceptionReason(beauticianProductId, updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}

		return result;
	}
	
	private boolean addForemost(String beauticianProductId, AddForemost data){
		boolean b = false;
		BeauticianProduct beauticianProduct = BeauticianProductMgr.getInstance().get(beauticianProductId);
		if(BeauticianProductBeanHelper.getInstance().addForemost(beauticianProduct, data)){
			BeauticianProductMgr.getInstance().update(beauticianProduct);
			b = true;
		}
		return b;
	}
	
	private boolean removeForemost(String beauticianProductId, RemoveForemost data){
		boolean b = false;
		BeauticianProduct beauticianProduct = BeauticianProductMgr.getInstance().get(beauticianProductId);
		if(BeauticianProductBeanHelper.getInstance().removeForemost(beauticianProduct, data)){
			BeauticianProductMgr.getInstance().update(beauticianProduct);
			b = true;
		}
		return b;
	}

}
