package com.hq.chainMS.service.chain.bs;

import java.util.List;
import java.util.Set;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthUtils;
import com.hq.chainMS.service.chain.apiData.ChainAddForm;
import com.hq.chainMS.service.chain.apiData.ChainQueryForm;
import com.hq.chainMS.service.chain.apiData.ChainUpdateForm;
import com.hq.chainMS.service.chain.bs.updateHandle.ChainHandleHelper;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainUser.bs.ChainUserMgr;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.chainUser.data.ChainUserBeanHelper;
import com.hq.chainMS.service.common.ExceptionInfo;
import com.hq.chainMS.service.common.HandlerHelper;
import com.hq.chainMS.service.common.OperateTips;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RespStatus;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainHandler {

	public static ChainHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainHandler.class);
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findChainByCond(ChainQueryForm queryForm) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Chain> pageInfo = ChainMgr.getInstance().findChainByCond(queryForm);
			result.setTarget(pageInfo);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Chain, "ChainHandler[findChainByCond]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Chain> findByNumber(String number) {
		ReqResult<Chain> result = ReqResult.newInstance(false, Chain.class);
		try {
			Chain chain = ChainMgr.getInstance().findByNumber(number);
			result.setTarget(chain);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(number);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Chain, "ChainHandler[findByNumber]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Chain> get(long chainId) {
		ReqResult<Chain> result = ReqResult.newInstance(false, Chain.class);
		try {
			Chain chain = ChainMgr.getInstance().get(chainId);
			result.setTarget(chain);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Chain, "ChainHandler[get]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Chain> findUserChains(long chainUserId) {
		ReqResult<Chain> result = ReqResult.newInstance(false, Chain.class);
		try {
			ChainUser ChainUser = ChainUserMgr.getInstance().get(chainUserId);
			Set<Long> chainIds = ChainUser.takeChainIds();
			List<Chain> chainList = ChainMgr.getInstance().findByIdList(chainIds);
			result.setTargetList(chainList);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainUserId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Chain, "ChainHandler[findChainUserChains]", reason, e);
		}
		return result;
	}
	

	public ReqResult<Chain> add(ChainAddForm formInfo) {
		ReqResult<Chain> result = ReqResult.newInstance(false, Chain.class);
		try {
			Long bossId = ChainUserAuthUtils.getInstance().getSessionChainUserId();
			Chain chain = formInfo.toChain(bossId);
			ChainMgr.getInstance().addAndReturnId(chain);
			ChainUser boss = ChainUserMgr.getInstance().get(chain.getBossId());
			ChainUserBeanHelper.getInstance().addChainBoss(boss, chain.getId());
			ChainUserMgr.getInstance().update(boss);
			result.setTarget(chain);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试");
			MainLog.error(LogModule.Chain, "ChainHandler[add]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Chain> update(ChainUpdateForm formInfo) {
		ReqResult<Chain> result = ReqResult.newInstance(false, Chain.class);
		try {
			OperateTips operateTips = ChainHandleHelper.getInstance().update(formInfo);
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final LogModule logModule = LogModule.Chain;
			final String logId = "ChainHandler[update]";
			final String reason = LogHelper.getInstance().exceptionReason(formInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule)
					.withLogId(logId).withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
}
