package com.hq.chainMS.service.storeCardInfo.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.chainMS.service.storeCardInfo.data.StoreCardInfoCacheDAO;
import com.hq.storeClient.service.storeCardInfo.apiData.BatchUpdMemberCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.BatchUpdProductCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdMemberCardStateData;
import com.hq.storeClient.service.storeCardInfo.apiData.UpdProductCardStateData;
import com.hq.storeClient.service.storeCardInfo.bs.StoreCardInfoClientMgr;
import com.hq.storeClient.service.storeCardInfo.data.StoreCardInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreCardInfoDataHolder {

	public static StoreCardInfoDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreCardInfoDataHolder.class);
	}

	public StoreCardInfo getStoreCardInfo(long storeId) {
		StoreCardInfo data = StoreCardInfoCacheDAO.getInstance().get(storeId);
		if(data!=null) {
			return data;
		}
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		data = StoreCardInfoClientMgr.getInstance().get(storeId);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
	
	public void batchUpdMemberCardState(long storeId, BatchUpdMemberCardStateData inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreCardInfoClientMgr.getInstance().batchUpdMemberCardState(storeId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public void batchUpdProductCardState(long storeId, BatchUpdProductCardStateData inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreCardInfoClientMgr.getInstance().batchUpdProductCardState(storeId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public void updMemberCardState(long storeId, UpdMemberCardStateData inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreCardInfoClientMgr.getInstance().updMemberCardState(storeId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
	public void updProductCardState(long storeId, UpdProductCardStateData inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		StoreCardInfoClientMgr.getInstance().updProductCardState(storeId, inputForm);
		AppIdThreadLocal.getInstance().set(null);
	}
	
}
