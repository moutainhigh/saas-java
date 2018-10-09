package com.hq.chainMS.service.areaCode.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.service.areaCode.apiData.AreaCodeAddForm;
import com.hq.chainMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.chainMS.service.areaCode.data.AreaCode;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.ReqResult;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeHandler {

	public static AreaCodeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeHandler.class);
	}
	
	private final LogModule logModule = LogModule.AreaCode;
	
	public ReqResult<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		ReqResult<AreaCode> result = ReqResult.newInstance(false, AreaCode.class);
		try {
			List<AreaCode> areaCodes = AreaCodeMgr.getInstance().findByCond(queryForm);
			if(CollectionUtils.isEmpty(areaCodes)){
				areaCodes = new ArrayList<AreaCode>();
			}
			result.setTargetList(areaCodes);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AreaCodeHandler[findByCond]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<AreaCode> getAreaCode(long areaCodeId) {
		ReqResult<AreaCode> result = ReqResult.newInstance(false, AreaCode.class);
		try {
			AreaCode areaCode = AreaCodeMgr.getInstance().get(areaCodeId);
			result.setTarget(areaCode);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AreaCodeHandler[getAreaCode]";
			final String reason = LogHelper.getInstance().exceptionReason(areaCodeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<AreaCode> addAreaCode(AreaCodeAddForm inputForm) {
		ReqResult<AreaCode> result = ReqResult.newInstance(false, AreaCode.class);
		try {
			AreaCode target = inputForm.toAreaCode();
			AreaCodeMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "AreaCodeHandler[addAreaCode]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
