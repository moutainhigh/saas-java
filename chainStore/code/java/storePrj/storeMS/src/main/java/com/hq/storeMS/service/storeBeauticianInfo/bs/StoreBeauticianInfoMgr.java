package com.hq.storeMS.service.storeBeauticianInfo.bs;

import com.hq.storeMS.service.storeBeauticianInfo.apiData.AddBeauticianInfoData;
import com.hq.storeMS.service.storeBeauticianInfo.apiData.RemoveBeauticianInfoData;
import com.hq.storeMS.service.storeBeauticianInfo.apiData.UpdateBeauticianInfoData;
import com.hq.storeMS.service.storeBeauticianInfo.data.BeauticianInfo;
import com.hq.storeMS.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreBeauticianInfoMgr {

	public static StoreBeauticianInfoMgr getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoMgr.class);
	}

	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * 
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addWithId(StoreBeauticianInfo target) {
		StoreBeauticianInfoDataHolder.getInstance().addWithId(target);
	}

	public void delete(StoreBeauticianInfo target) {
		StoreBeauticianInfoDataHolder.getInstance().delete(target);
	}

	public StoreBeauticianInfo getByStoreId(long storeId) {
		StoreBeauticianInfo storeBeauticianInfo = StoreBeauticianInfoDataHolder.getInstance().get(storeId);
		//不存在则创建
		if(storeBeauticianInfo == null){
			storeBeauticianInfo = StoreBeauticianInfo.newInstance(storeId);
			addWithId(storeBeauticianInfo);
		}
		return storeBeauticianInfo;
	}
	
	public void update(StoreBeauticianInfo target) {
		StoreBeauticianInfoDataHolder.getInstance().update(target);
	}

	public boolean addBeauticianInfo(long storeId,AddBeauticianInfoData inputData) {
		boolean success = false;
		long buserId = inputData.getBuserId();
		
		StoreBeauticianInfo storeBeauticianInfo = getByStoreId(storeId);

		if (storeBeauticianInfo != null) {
			BeauticianInfo beauticianInfo = BeauticianInfo.newInstance(buserId);
			success = storeBeauticianInfo.addBeauticianInfo(beauticianInfo);
			if (success) {
				StoreBeauticianInfoDataHolder.getInstance().update(storeBeauticianInfo);
			}
		}

		return success;
	}

	public boolean updateBeauticianInfo(long storeId,UpdateBeauticianInfoData inputData) {
		boolean success = false;
		StoreBeauticianInfo storeBeauticianInfo = getByStoreId(storeId);
		if (storeBeauticianInfo != null) {
			BeauticianInfo beauticianInfo = BeauticianInfo.newInstance(inputData.getBuserId());
			beauticianInfo.updateInfo(inputData);
			success = storeBeauticianInfo.updateBeauticianInfo(beauticianInfo);
			if (success) {
				StoreBeauticianInfoDataHolder.getInstance().update(storeBeauticianInfo);
			}
		}
		return success;
	}
	
	public boolean removeBeauticianInfo(long storeId,RemoveBeauticianInfoData inputData) {
		boolean success = false;
		StoreBeauticianInfo storeBeauticianInfo = getByStoreId(storeId);
		if (storeBeauticianInfo != null) {
			success = storeBeauticianInfo.removeBeauticianInfo(inputData.getBuserId());
			if (success) {
				StoreBeauticianInfoDataHolder.getInstance().update(storeBeauticianInfo);
			}
		}
		return success;
	}
	

}
