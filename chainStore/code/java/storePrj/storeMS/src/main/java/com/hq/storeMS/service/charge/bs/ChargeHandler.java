package com.hq.storeMS.service.charge.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerRestClient.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerRestClient.service.charge.data.Charge;
import com.hq.storeManagerRestClient.service.charge.data.ChargeOriginEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeHandler {

	public static ChargeHandler getInstance(){
		return HotSwap.getInstance().getSingleton(ChargeHandler.class);
	}
	
	final LogModule logModule = LogModule.Charge;
	
	@SuppressWarnings("rawtypes")
    public ReqResult<PageResp> findChargePageInfo(ChargeQueryForm queryForm) {
        ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
        try {
            PageResp<Charge> pageResp = ChargeMgr.getInstance().findChargePageInfo(queryForm);
            result.setTarget(pageResp);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "ChargeHandler[findChargePageInfo]";
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }
	
	public ReqResult<Charge> updateCharge(long chargeId, ChargeUpdateApiForm updateForm) {
		ReqResult<Charge> result = ReqResult.newInstance(false, Charge.class);
		try {
			Charge charge = ChargeMgr.getInstance().updateCharge(chargeId, updateForm);
			result.setTarget(charge);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChargeHandler[updateCharge]";
            final String reason = LogHelper.getInstance().exceptionReason(chargeId, updateForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Charge> getCharge(long id) {
		ReqResult<Charge> result = ReqResult.newInstance(false, Charge.class);
		try {
			Charge charge = ChargeMgr.getInstance().getCharge(id);
			result.setTarget(charge);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChargeHandler[getCharge]";
            final String reason = LogHelper.getInstance().exceptionReason(id);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Charge> addCharge(ChargeAddForm formInfo) {
		ReqResult<Charge> result = ReqResult.newInstance(false, Charge.class);
		try {
			formInfo.setOrigin(ChargeOriginEnum.StoreMS.ordinal());
			Charge charge = ChargeMgr.getInstance().addCharge(formInfo);
			result.setTarget(charge);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "ChargeHandler[addCharge]";
            final String reason = LogHelper.getInstance().exceptionReason(formInfo);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}
