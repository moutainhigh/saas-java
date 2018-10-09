package com.hq.storeMS.service.storeClerkInfo.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.storeClerkInfo.apiData.AddClerkInfoData;
import com.hq.storeMS.service.storeClerkInfo.apiData.StoreClerkInfoUpdateForm;
import com.hq.storeMS.service.storeClerkInfo.bs.update.ClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.bs.update.ClerkInfoHandlerHelper;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoHandler {

	public static StoreClerkInfoHandler getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoHandler.class);
	}

	public ReqResult<StoreClerkInfo> get(String id) {
		ReqResult<StoreClerkInfo> result = ReqResult.newInstance(false, StoreClerkInfo.class);
		try {
			StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().get(id);
			if (storeClerkInfo != null) {
				result.setTarget(storeClerkInfo);
				result.setSuccess(true);
			} else {
				result.setStatus(RespStatus.NOT_FOUND);
				result.setTips("店铺不存在");
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.StoreClerkInfo;
			final String logId = "StoreClerkInfoHandler[get]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
			
		}
		return result;
	}

	public ReqResult<StoreClerkInfo> update(StoreClerkInfoUpdateForm formInfo) {
		ReqResult<StoreClerkInfo> result = ReqResult.newInstance(false, StoreClerkInfo.class);
		try {
			OperateTips operateTips = ClerkInfoHandlerHelper.getInstance().update(formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.StoreClerkInfo;
			final String logId = "StoreClerkInfoHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	/**
	 * 
	 * addClerk:(扫描二维码加入店铺,不需要权限验证). <br/>   
	 *  
	 * @author kevin
	 * @param addClerkInfoApiForm
	 * @return  
	 * @since JDK 1.6
	 */
	public ReqResult<StoreClerkInfo> addClerk(Long storeId, AddClerkInfoData addClerkInfoApiForm) {
		ReqResult<StoreClerkInfo> result = ReqResult.newInstance(false, StoreClerkInfo.class);
		try {
			OperateTips operateTips = ClerkInfoMgr.getInstance().addClerk(addClerkInfoApiForm);
			if(operateTips.isSuccess()){
				result.setSuccess(true);
			}else{
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.StoreClerkInfo;
			final String logId = "StoreClerkInfoHandler[addClerk]";
			final String reason = LogHelper.getInstance().exceptionReason(storeId, addClerkInfoApiForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		
		return result;
	}
}
