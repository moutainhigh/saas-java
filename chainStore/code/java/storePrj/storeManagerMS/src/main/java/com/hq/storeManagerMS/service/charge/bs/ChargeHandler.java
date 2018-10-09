package com.hq.storeManagerMS.service.charge.bs;

import org.apache.commons.lang3.RandomUtils;

import com.hq.storeManagerMS.common.log.LogHelper;
import com.hq.storeManagerMS.common.log.LogModule;
import com.hq.storeManagerMS.service.charge.apiData.ChargeAddForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerMS.service.charge.apiData.ChargeUpdateApiForm;
import com.hq.storeManagerMS.service.charge.bs.updateHandler.ChargeHandleHelper;
import com.hq.storeManagerMS.service.charge.data.Charge;
import com.hq.storeManagerMS.service.charge.data.ChargeTypeEnum;
import com.hq.storeManagerMS.service.common.ExceptionInfo;
import com.hq.storeManagerMS.service.common.HandlerHelper;
import com.hq.storeManagerMS.service.common.OperateTips;
import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RespStatus;
import com.hq.storeManagerMS.service.vipLevel.bs.VipLevelMgr;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevel;
import com.zenmind.common.hotSwap.HotSwap;

public class ChargeHandler {

    public static ChargeHandler getInstance() {
        return HotSwap.getInstance().getSingleton(ChargeHandler.class);
    }

    private final LogModule logModule = LogModule.Charge;

    @SuppressWarnings("rawtypes")
    public ReqResult<PageResp> findChargePageInfo(ChargeQueryForm queryForm) {
        ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
        try {
            PageResp<Charge> pageResp = ChargeMgr.getInstance().findChargePageInfo(queryForm);
            result.setTarget(pageResp);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "ChargeHandler[findByCond]";
            final String reason = LogHelper.getInstance().exceptionReason(queryForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    public ReqResult<Charge> getCharge(long chargeId) {
        ReqResult<Charge> result = ReqResult.newInstance(false, Charge.class);
        try {
            Charge charge = ChargeMgr.getInstance().get(chargeId);
            result.setTarget(charge);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "ChargeHandler[getCharge]";
            final String reason = LogHelper.getInstance().exceptionReason(chargeId);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }

    public ReqResult<Charge> addCharge(ChargeAddForm inputForm) {
        ReqResult<Charge> result = ReqResult.newInstance(false, Charge.class);
        try {
            Charge target = inputForm.toCharge();
            target.setNumber(generateChargeNumber());
            VipLevel vipLevel = VipLevelMgr.getInstance().get(target.getVipLevelId());
            if(target.getChargeType() == ChargeTypeEnum.RENEW.ordinal()) {//续费的金额
            	target.setVipAmount(vipLevel.getRenewCharge());
            }else {//普通、升级的金额
            	target.setVipAmount(vipLevel.getOpenCharge());
            }
            ChargeMgr.getInstance().addAndReturnId(target);
            result.setTarget(target);
            result.setSuccess(true);
        } catch (Exception e) {
            final String logId = "ChargeHandler[addCharge]";
            final String reason = LogHelper.getInstance().exceptionReason(inputForm);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }
    
    private String generateChargeNumber() {
	    StringBuffer orderSNBuffer = new StringBuffer();
	    orderSNBuffer.append(System.currentTimeMillis());
	    orderSNBuffer.append(RandomUtils.nextInt(10000, 99999));
	    return orderSNBuffer.toString();
	}

    public ReqResult<Charge> updateCharge(long chargeId, ChargeUpdateApiForm updateInfo) {
        ReqResult<Charge> result = ReqResult.newInstance(false, Charge.class);
        try {
            OperateTips operateTips = ChargeHandleHelper.getInstance().update(updateInfo);
			if (operateTips.isSuccess()) {
				Charge target = ChargeMgr.getInstance().get(chargeId);
				result.setTarget(target);
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
        } catch (Exception e) {
            final String logId = "ChargeHandler[updateCharge]";
            final String reason = LogHelper.getInstance().exceptionReason(updateInfo);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }
    
    public ReqResult<Charge> deleteCharge(long chargeId) {
        ReqResult<Charge> result = ReqResult.newInstance(false, Charge.class);
        try {
            Charge charge = ChargeMgr.getInstance().get(chargeId);
            if (charge != null) {
                ChargeMgr.getInstance().delete(charge);
                result.setSuccess(true);
            } else {
                result.setTips("删除的收费记录不存在");
                result.setStatus(RespStatus.INVALID_REQUEST);
            }
        } catch (Exception e) {
            final String logId = "ChargeHandler[deleteCharge]";
            final String reason = LogHelper.getInstance().exceptionReason(chargeId);
            ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
                    .withLogId(logId).withReason(reason).withResult(result);
            HandlerHelper.getInstance().handleException(exceptionInfo, e);
        }
        return result;
    }
}
