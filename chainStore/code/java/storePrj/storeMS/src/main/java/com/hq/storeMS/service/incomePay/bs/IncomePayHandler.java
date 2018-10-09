package com.hq.storeMS.service.incomePay.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.incomePay.apiData.IncomeAddPayForm;
import com.hq.storeMS.service.incomePay.apiData.IncomePayQueryForm;
import com.hq.storeMS.service.incomePay.apiData.IncomePayUpdateApiForm;
import com.hq.storeMS.service.incomePay.apiData.IncomePayUpdateType;
import com.hq.storeMS.service.incomePay.data.IncomePay;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class IncomePayHandler {

    public static IncomePayHandler getInstance() {
        return HotSwap.getInstance().getSingleton(IncomePayHandler.class);
    }

    private final LogModule logModule = LogModule.IncomePay;

    @SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findIncomePayPageInfo(IncomePayQueryForm queryForm) {
        ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
        try {
            PageResp<IncomePay> pageResp = IncomePayMgr.getInstance().findIncomePayPageInfo(queryForm);
            result.setTarget(pageResp);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "IncomePayHandler[findByCond]";
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    public ReqResult<IncomePay> getIncomePay(long storeId, long incomePayId) {
        ReqResult<IncomePay> result = ReqResult.newInstance(false, IncomePay.class);
        try {
            IncomePay incomePay = IncomePayMgr.getInstance().get(storeId, incomePayId);
            result.setTarget(incomePay);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "IncomePayHandler[getIncomePay]";
            final String reason = LogHelper.getInstance().exceptionReason(incomePayId);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    public ReqResult<IncomePay> addIncomePay(IncomeAddPayForm inputForm) {
        ReqResult<IncomePay> result = ReqResult.newInstance(false, IncomePay.class);
        try {
            BUserAuthUtils.getInstance().checkPermission(inputForm.getStoreId(), StoreAdminPermEnum.INCOME_PAY_ADMIN);
            IncomePay target = IncomePay.newInstance();
            FastBeanCopyer.getInstance().copy(inputForm, target);
            IncomePayMgr.getInstance().addAndReturnId(target);
            result.setTarget(target);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "IncomePayHandler[addIncomePay]";
            final String reason = LogHelper.getInstance().exceptionReason(inputForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    public ReqResult<IncomePay> updateIncomePay(long storeId, long incomePayId, IncomePayUpdateApiForm updateInfo) {
        ReqResult<IncomePay> result = ReqResult.newInstance(false, IncomePay.class);
        try {
            BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.INCOME_PAY_ADMIN);
            IncomePay target = IncomePayMgr.getInstance().get(storeId, incomePayId);
            if(target == null) {
            	result.setTips("修改的收支记录不存在");
                result.setStatus(RespStatus.INVALID_REQUEST);
                return result;
            }
            IncomePayUpdateType updateTypeEnum = updateInfo.getUpdateTypeEnum();
            switch (updateTypeEnum) {
			case UpdateInfo:
				updateInfo.getIncomePayUpdateInfoForm().updateIncomePay(target);
	            IncomePayMgr.getInstance().updateIncomePay(target);
				break;
			default:
				break;
			}
            result.setTarget(target);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "IncomePayHandler[updateIncomePay]";
            final String reason = LogHelper.getInstance().exceptionReason(updateInfo);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }

        return result;
    }

    public ReqResult<IncomePay> deleteIncomePay(long storeId, long incomePayId) {
        ReqResult<IncomePay> result = ReqResult.newInstance(false, IncomePay.class);
        try {
            BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.INCOME_PAY_ADMIN);
            IncomePay incomePay = IncomePayMgr.getInstance().get(storeId, incomePayId);
            if (incomePay != null) {
                IncomePayMgr.getInstance().delete(incomePay);
                result.setSuccess(true);
            } else {
                result.setTips("删除的收支记录不存在");
                result.setStatus(RespStatus.INVALID_REQUEST);
            }
        } catch (Exception e) {
            final String logId = "IncomePayHandler[deleteIncomePay]";
            final String reason = LogHelper.getInstance().exceptionReason(storeId, incomePayId);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }
}
