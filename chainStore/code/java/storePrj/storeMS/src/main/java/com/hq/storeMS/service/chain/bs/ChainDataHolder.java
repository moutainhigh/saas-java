package com.hq.storeMS.service.chain.bs;

import org.apache.commons.lang3.math.NumberUtils;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chain.apiData.ApplyChainForm;
import com.hq.chainClient.service.chain.apiData.ChainQueryForm;
import com.hq.chainClient.service.chain.apiData.RelieveStoreForm;
import com.hq.chainClient.service.chain.bs.ChainClientMgr;
import com.hq.chainClient.service.chain.data.Chain;
import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.datasyn.IntfDataHolder;
import com.hq.storeMS.common.datasyn.info.DataSynItem;
import com.hq.storeMS.common.datasyn.info.DataSynType;
import com.hq.storeMS.common.datasyn.info.DataSynVer;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.AppIdThreadLocal;
import com.hq.storeMS.service.chain.data.ChainCacheDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dataSyn.DataSynMgr;

public class ChainDataHolder implements IntfDataHolder{
	
	public static ChainDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(ChainDataHolder.class);
	}
	
	final private DataSynType synType = DataSynType.Chain;
	
	public Chain get(long id) {
		Chain data = ChainCacheDAO.getInstance().get(id);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ChainClientMgr.getInstance().getChain(id);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public Chain findByNumber(String number) {
		Chain data = ChainCacheDAO.getInstance().getOne(number);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = ChainClientMgr.getInstance().findByNumber(number);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public PageResp<Chain> findChainByCond(ChainQueryForm queryForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		PageResp<Chain> page = ChainClientMgr.getInstance().findChainByCond(queryForm);
		AppIdThreadLocal.getInstance().set(null);
		return page;
	}
	
	public void applyChain(long chainId, ApplyChainForm inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		ChainClientMgr.getInstance().applyChain(chainId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public void relieveStore(long chainId, RelieveStoreForm inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		ChainClientMgr.getInstance().relieveStore(chainId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public DataSynType getSynType() {
		return synType;
	}

	public DataSynItem getSynItem(DataSynVer clientSynVer){
		DataSynItem item = null;
		String id = clientSynVer.getId();
		if(NumberUtils.isNumber(id)){
			Chain target = this.get(Long.valueOf(id));			
			if(target != null){
				long newVer = target.getVer();
				if(clientSynVer.getVer() < newVer){
					String data = DataSynMgr.getInstance().toClientData(target);
					item = DataSynItem.newInstance(clientSynVer, newVer, data );
				}
			}else{
				MainLog.info(LogModule.Chain, "ChainDataHolder[getSynItem]", "获取详情对象为空[id="+id+"]");
			}
		}else{
			MainLog.info(LogModule.Chain, "ChainDataHolder[getSynItem]", "数据同步失败[id="+id+"]");
		}
		return item;
	}
}
