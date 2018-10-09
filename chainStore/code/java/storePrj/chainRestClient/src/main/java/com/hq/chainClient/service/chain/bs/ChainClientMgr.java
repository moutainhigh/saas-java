package com.hq.chainClient.service.chain.bs;

import java.util.List;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chain.apiData.ApplyChainBatchDoForm;
import com.hq.chainClient.service.chain.apiData.ApplyChainDoForm;
import com.hq.chainClient.service.chain.apiData.ApplyChainForm;
import com.hq.chainClient.service.chain.apiData.ChainAddForm;
import com.hq.chainClient.service.chain.apiData.ChainQueryForm;
import com.hq.chainClient.service.chain.apiData.ChainUpdateForm;
import com.hq.chainClient.service.chain.apiData.ChainUpdateInfoForm;
import com.hq.chainClient.service.chain.apiData.ChainUpdateType;
import com.hq.chainClient.service.chain.apiData.RelieveStoreForm;
import com.hq.chainClient.service.chain.data.Chain;
import com.hq.chainClient.service.chain.data.ChainDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class ChainClientMgr {

	public static ChainClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainClientMgr.class);
	}
	
	public Chain addChain(ChainAddForm addForm) {
		return ChainDAO.getInstance().add(addForm);
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
	
	public List<Chain> findByUser(long userId) {
		final String findPath = "findByUser";
		ReqMap reqMap = ReqMap.newInstance().add("userId", userId);
		return ChainDAO.getInstance().findWithReqParam(findPath, reqMap, 1, 1);
	}
	
	public void relieveStore(long chainId, RelieveStoreForm inputForm) {
		ChainUpdateForm chainUpdateApiForm = ChainUpdateForm.newInstance();
		chainUpdateApiForm.setUpdateType(ChainUpdateType.RelieveStore.ordinal());
		chainUpdateApiForm.setRelieveStoreForm(inputForm);
		update(chainId, chainUpdateApiForm);
	}
	
	public void updateChainInfo(long chainId, ChainUpdateInfoForm inputForm) {
		ChainUpdateForm chainUpdateApiForm = ChainUpdateForm.newInstance();
		chainUpdateApiForm.setUpdateType(ChainUpdateType.UpdateChainInfo.ordinal());
		chainUpdateApiForm.setChainUpdateInfoForm(inputForm);
		update(chainId, chainUpdateApiForm);
	}
	
	public void applyChain(long chainId, ApplyChainForm inputForm) {
		ChainUpdateForm chainUpdateApiForm = ChainUpdateForm.newInstance();
		chainUpdateApiForm.setUpdateType(ChainUpdateType.ApplyChain.ordinal());
		chainUpdateApiForm.setApplyChainForm(inputForm);
		update(chainId, chainUpdateApiForm);
	}
	
	public void handleApplyChain(long chainId, ApplyChainDoForm inputForm) {
		ChainUpdateForm chainUpdateApiForm = ChainUpdateForm.newInstance();
		chainUpdateApiForm.setUpdateType(ChainUpdateType.HandleApplyChain.ordinal());
		chainUpdateApiForm.setApplyChainDoForm(inputForm);
		update(chainId, chainUpdateApiForm);
	}
	
	public void batchHandleApplyChain(long chainId, ApplyChainBatchDoForm inputForm) {
		ChainUpdateForm chainUpdateApiForm = ChainUpdateForm.newInstance();
		chainUpdateApiForm.setUpdateType(ChainUpdateType.BatchHandleApplyChain.ordinal());
		chainUpdateApiForm.setApplyChainBatchDoForm(inputForm);
		update(chainId, chainUpdateApiForm);
	}
	
	private void update(long chainId, ChainUpdateForm updateForm) {
		ChainDAO.getInstance().update(chainId, updateForm);
	}
}
