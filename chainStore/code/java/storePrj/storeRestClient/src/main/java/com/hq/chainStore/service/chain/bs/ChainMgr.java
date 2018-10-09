package com.hq.chainStore.service.chain.bs;

import com.hq.chainStore.service.chain.apiData.ApplyChainForm;
import com.hq.chainStore.service.chain.apiData.ChainQueryForm;
import com.hq.chainStore.service.chain.apiData.ChainUpdateForm;
import com.hq.chainStore.service.chain.apiData.ChainUpdateType;
import com.hq.chainStore.service.chain.apiData.RelieveStoreForm;
import com.hq.chainStore.service.chain.data.Chain;
import com.hq.chainStore.service.chain.data.ChainDAO;
import com.hq.chainStore.service.common.PageResp;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class ChainMgr {

	public static ChainMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainMgr.class);
	}
	
	public Chain getChain(long chainId) {
		return ChainDAO.getInstance().get(chainId);
	}
	
	public Chain findByNumber(String number) {
		final String findPath = "findByNumber";
		ReqMap reqMap = ReqMap.newInstance().add("number", number);
		return ChainDAO.getInstance().findOneWithReqParam(findPath, reqMap);
	}
	
	public PageResp<Chain> findChainByCond(ChainQueryForm queryForm) {
		return ChainDAO.getInstance().findChainByCond(queryForm);
	}
	
	public void relieveStore(long chainId, RelieveStoreForm inputForm) {
		ChainUpdateForm chainUpdateApiForm = ChainUpdateForm.newInstance();
		chainUpdateApiForm.setUpdateType(ChainUpdateType.RelieveStore.ordinal());
		chainUpdateApiForm.setRelieveStoreForm(inputForm);
		update(chainId, chainUpdateApiForm);
	}
	
	public void applyChain(long chainId, ApplyChainForm inputForm) {
		ChainUpdateForm chainUpdateApiForm = ChainUpdateForm.newInstance();
		chainUpdateApiForm.setUpdateType(ChainUpdateType.ApplyChain.ordinal());
		chainUpdateApiForm.setApplyChainForm(inputForm);
		update(chainId, chainUpdateApiForm);
	}
	
	private void update(long chainId, ChainUpdateForm updateForm) {
		ChainDAO.getInstance().update(chainId, updateForm);
	}
}
