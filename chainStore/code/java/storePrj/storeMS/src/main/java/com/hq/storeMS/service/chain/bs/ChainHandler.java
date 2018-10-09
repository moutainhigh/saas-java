package com.hq.storeMS.service.chain.bs;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chain.apiData.ChainQueryForm;
import com.hq.chainClient.service.chain.apiData.ChainUpdateForm;
import com.hq.chainClient.service.chain.apiData.ChainUpdateType;
import com.hq.chainClient.service.chain.apiData.RelieveStoreForm;
import com.hq.chainClient.service.chain.data.Chain;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.store.apiData.StoreUpdateChainData;
import com.hq.storeMS.service.store.bs.update.StoreUpdateMgr;
import com.hq.storeMS.service.store.data.StoreOperateEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainHandler {

	public static ChainHandler getInstance() {
		return HotSwap.getInstance().getSingleton(ChainHandler.class);
	}
	
	private final LogModule logModule = LogModule.Chain;

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
			MainLog.error(logModule, "ChainHandler[findChainByCond]", reason, e);
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
			MainLog.error(logModule, "ChainHandler[findByNumber]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Chain> getChain(long chainId) {
		ReqResult<Chain> result = ReqResult.newInstance(false, Chain.class);
		try {
			Chain chain = ChainMgr.getInstance().getChain(chainId);
			result.setTarget(chain);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainHandler[getChain]", reason, e);
		}
		return result;
	}
	
	public ReqResult<Chain> updateChain(ChainUpdateForm chainForm) {
		ReqResult<Chain> result = ReqResult.newInstance(false, Chain.class);
		try {
			ChainUpdateType chainUpdateType = chainForm.getChainUpdateType();
			switch (chainUpdateType) {
			case ApplyChain:
				ChainMgr.getInstance().applyChain(chainForm.getApplyChainForm());
				break;
			case RelieveStore:
				RelieveStoreForm form = chainForm.getRelieveStoreForm();
				StoreUpdateChainData inputForm = StoreUpdateChainData.newInstance(form.getStoreId(), form.getChainId(), StoreOperateEnum.RelieveStore);
				StoreUpdateMgr.getInstance().updateStoreChainData(inputForm);
				ChainMgr.getInstance().relieveStore(form);
				break;
			default:
				break;
			}
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "ChainHandler[updateChain]", reason, e);
		}
		return result;
	}

}
