package com.hq.storeMS.service.specialData.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.specialData.apiData.UpdateSpecialDataApiForm;
import com.hq.storeMS.service.specialData.apiData.UpdateSpecialDataType;
import com.hq.storeMS.service.specialData.data.SpecialData;
import com.hq.storeMS.service.specialData.data.SpecialDataBeanHelper;
import com.zenmind.common.hotSwap.HotSwap;

public class SpecialDataHandler {

	public static SpecialDataHandler getInstance() {
		return HotSwap.getInstance().getSingleton(SpecialDataHandler.class);
	}
	
	private final LogModule logModule = LogModule.SpecialData;

	public ReqResult<SpecialData> getSpecialData(String id) {
		ReqResult<SpecialData> result = ReqResult.newInstance(false, SpecialData.class);
		try {
			SpecialData specialData = SpecialDataMgr.getInstance().get(id);
			result.setTarget(specialData);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(id);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "SpecialDataHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<SpecialData> updateSpecialData(String specialDataId, UpdateSpecialDataApiForm updateForm) {
		ReqResult<SpecialData> result = ReqResult.newInstance(false, SpecialData.class);
		try {
			UpdateSpecialDataType updateType = UpdateSpecialDataType.valueOf(updateForm.getUpdateType());
			SpecialData specialData = SpecialDataMgr.getInstance().get(specialDataId);
			boolean success = false;
			switch (updateType) {
				case AddBeauticianSpecialData:
					success = SpecialDataBeanHelper.getInstance().addBeauticianSpecialData(specialData, updateForm.getBeauticianSpecialData());
					break;
				case AddProductSpecialData:
					success = SpecialDataBeanHelper.getInstance().addProductSpecialData(specialData, updateForm.getProductSpecialData());
					break;
				case AddCUserSpecialData:
					success = SpecialDataBeanHelper.getInstance().addCUserSpecialData(specialData, updateForm.getCuserSpecialData());
					break;
				case DelBeauticianSpecialData:
					success = SpecialDataBeanHelper.getInstance().delBeauticianSpecialData(specialData, updateForm.getBeauticianSpecialData());
					break;
				case DelProductSpecialData:
					success = SpecialDataBeanHelper.getInstance().delProductSpecialData(specialData, updateForm.getProductSpecialData());
					break;
				case DelCUserSpecialData:
					success = SpecialDataBeanHelper.getInstance().delCUserSpecialData(specialData, updateForm.getCuserSpecialData());
					break;
				default:
					break;
			}
			if (success) {
				SpecialDataMgr.getInstance().update(specialData);
				result.setSuccess(true);
			} else {
				result.setTips(updateType.getMark()+"失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "SpecialDataHandler[updateSpecialData]";
			final String reason = LogHelper.getInstance().exceptionReason(specialDataId, updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
						.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
}
