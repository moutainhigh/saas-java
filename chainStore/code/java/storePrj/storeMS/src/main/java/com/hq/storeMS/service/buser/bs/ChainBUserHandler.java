package com.hq.storeMS.service.buser.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeMS.service.buser.apiData.BUserChainQueryForm;
import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.bs.handler.BUserAddMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainBUserHandler {

	public static ChainBUserHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainBUserHandler.class);
	}

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findByChain(BUserChainQueryForm params) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
			FastBeanCopyer.getInstance().copy(params, queryForm);
			PageResp<BUser> pageInfo = BUserQueryMgr.getInstance().findPageByCond(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[findByChain]", reason, e);
		}
		return result;
	}

	public ReqResult<BUser> regByChainForm(BUserAddByChainForm formInfo) {
		ReqResult<BUser> result = ReqResult.newInstance(false, BUser.class);
		try {
			OperateTips operateTips = BUserAddMgr.getInstance().regByChainForm(formInfo);
			if (operateTips.isSuccess()) {
				result.setTarget(BUserQueryMgr.getInstance().findByPhone(formInfo.getPhone()));
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.BUser, "BUserHandler[regByChainForm]", reason, e);
		}
		return result;
	}
}
