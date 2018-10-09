package com.hq.storeClient.service.storeCardInfo.bs;

import com.hq.storeClient.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.StoreCardInfoUpdateApiForm;
import com.hq.storeClient.service.storeCardInfo.apiData.StoreCardInfoUpdateType;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfoDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoClientMgr {

	public static StoreCardInfoClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoClientMgr.class);
	}

	public StoreCardInfo get(long storeId) {
		return StoreCardInfoDAO.getInstance().get(storeId);
	}

	public void updateStoreCardInfo(long storeId, StoreCardInfoUpdateApiForm apiForm) {
		StoreCardInfoDAO.getInstance().update(storeId, apiForm);
	}

	public void updMemberCardState(long storeId, UpdMemberCardStateData updateMemberCardStateData) {
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setUpdateMemberCardStateData(updateMemberCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdMemberCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}

	public void batchUpdMemberCardState(long storeId, BatchUpdMemberCardStateData batchUpdateMemberCardStateData) {
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setBatchUpdateMemberCardStateData(batchUpdateMemberCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.BatchUpdMemberCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}

	public void updProductCardState(long storeId, UpdProductCardStateData updateProductCardStateData) {
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setUpdateProductCardStateData(updateProductCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.UpdProductCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}

	public void batchUpdProductCardState(long storeId, BatchUpdProductCardStateData batchUpdateProductCardStateData) {
		StoreCardInfoUpdateApiForm updateForm = StoreCardInfoUpdateApiForm.newInstance();
		updateForm.setBatchUpdateProductCardStateData(batchUpdateProductCardStateData);
		updateForm.setUpdateType(StoreCardInfoUpdateType.BatchUpdProductCardState.ordinal());
		updateStoreCardInfo(storeId, updateForm);
	}

}
