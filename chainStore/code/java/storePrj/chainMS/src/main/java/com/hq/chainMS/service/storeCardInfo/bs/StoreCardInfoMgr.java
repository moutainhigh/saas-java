package com.hq.chainMS.service.storeCardInfo.bs;

import java.util.Set;

import com.hq.storeClient.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoMgr {

	public static StoreCardInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoMgr.class);
	}
	
	public StoreCardInfo getStoreCardInfo(long storeId) {
		return StoreCardInfoDataHolder.getInstance().getStoreCardInfo(storeId);
	}
	
	public void updMemberCardState(Set<Long> applyStoreIds, String id, int state) {
		for (Long storeId : applyStoreIds) {
			UpdMemberCardStateData data = UpdMemberCardStateData.newInstance();
			data.setStoreId(storeId);
			data.setId(id);
			data.setState(state);
			updMemberCardState(storeId, data);
		}
	}
	
	public void updProductCardState(Set<Long> applyStoreIds, String id, int state) {
		for (Long storeId : applyStoreIds) {
			UpdProductCardStateData data = UpdProductCardStateData.newInstance();
			data.setStoreId(storeId);
			data.setId(id);
			data.setState(state);
			updProductCardState(storeId, data);
		}
	}
	
	public void batchUpdMemberCardState(long storeId, BatchUpdMemberCardStateData inputForm) {
		StoreCardInfoDataHolder.getInstance().batchUpdMemberCardState(storeId, inputForm);
	}
	
	public void batchUpdProductCardState(long storeId, BatchUpdProductCardStateData inputForm) {
		StoreCardInfoDataHolder.getInstance().batchUpdProductCardState(storeId, inputForm);
	}
	
	public void updMemberCardState(long storeId, UpdMemberCardStateData inputForm) {
		StoreCardInfoDataHolder.getInstance().updMemberCardState(storeId, inputForm);
	}
	
	public void updProductCardState(long storeId, UpdProductCardStateData inputForm) {
		StoreCardInfoDataHolder.getInstance().updProductCardState(storeId, inputForm);
	}
}
