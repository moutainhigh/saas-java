package com.hq.storeMS.service.activateCode.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.activateCode.apiData.ActivateCodeGenApiForm;
import com.hq.storeMS.service.activateCode.apiData.AddActivateCodeForm;
import com.hq.storeMS.service.activateCode.apiData.QueryActivateCodeForm;
import com.hq.storeMS.service.activateCode.data.ActivateCode;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ActivateCodeHandler {

	public static ActivateCodeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ActivateCodeHandler.class);
	}
	
	private final LogModule logModule = LogModule.ActivateCode;
	
	public ReqResult<ActivateCode> findByCond(QueryActivateCodeForm queryForm) {
		ReqResult<ActivateCode> result = ReqResult.newInstance(false, ActivateCode.class);
		try {
			List<ActivateCode> activateCodes = ActivateCodeMgr.getInstance().findByCond(queryForm);
			if(CollectionUtils.isEmpty(activateCodes)){
				activateCodes = new ArrayList<ActivateCode>();
			}
			result.setTargetList(activateCodes);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ActivateCodeHandler[findByCond]";
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<ActivateCode> getActivateCode(long activateCodeId) {
		ReqResult<ActivateCode> result = ReqResult.newInstance(false, ActivateCode.class);
		try {
			ActivateCode activateCode = ActivateCodeMgr.getInstance().get(activateCodeId);
			result.setTarget(activateCode);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ActivateCodeHandler[getActivateCode]";
			final String reason = LogHelper.getInstance().exceptionReason(activateCodeId);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<ActivateCode> addActivateCode(AddActivateCodeForm inputForm) {
		ReqResult<ActivateCode> result = ReqResult.newInstance(false, ActivateCode.class);
		try {
			ActivateCode target = ActivateCode.newInstance();
			FastBeanCopyer.getInstance().copy(inputForm, target);
			ActivateCodeMgr.getInstance().addAndReturnId(target);
			result.setTarget(target);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ActivateCodeHandler[addActivateCode]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<ActivateCode> genActivateCodes(ActivateCodeGenApiForm inputForm) {
		ReqResult<ActivateCode> result = ReqResult.newInstance(false, ActivateCode.class);
		try {
			QueryActivateCodeForm queryForm = QueryActivateCodeForm.newInstance();
			queryForm.setPageItemCount(ServerConstants.PAGE_ITEM_COUNT);
			queryForm.setPageNo(1);
			List<ActivateCode> activateCodes = ActivateCodeMgr.getInstance().findByCond(queryForm);
			Set<String> codes = new HashSet<String>();
			for (ActivateCode activateCode : activateCodes) {
				codes.add(activateCode.getActivateCode());
			}
			Set<String> newCodes = genRandomCode(inputForm, codes);
			
			for (String code : newCodes) {
				ActivateCode target = ActivateCode.newInstance();
				target.setActivateCode(code);
				ActivateCodeMgr.getInstance().addAndReturnId(target);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ActivateCodeHandler[genActivateCodes]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private Set<String> genRandomCode(ActivateCodeGenApiForm inputForm, Set<String> activateCodes){
		int count = inputForm.getCount();
		int len = inputForm.getLen();
		String source = inputForm.getSource();
		Set<String> codes = new HashSet<String>();
		int index = 0;
		while(index < count){
			String val = RandomStringUtils.random(len, source);
			if(!codes.contains(val) && !activateCodes.contains(val)){
				codes.add(val);
				index++;
			}
		}
		return codes;
	}
}
