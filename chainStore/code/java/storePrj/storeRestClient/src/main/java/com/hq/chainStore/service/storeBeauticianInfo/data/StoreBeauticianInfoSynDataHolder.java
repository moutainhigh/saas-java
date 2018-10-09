package com.hq.chainStore.service.storeBeauticianInfo.data;

import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.buser.data.BUserSynDataHolder;
import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreBeauticianInfoSynDataHolder extends AbsDataSynDataHolder<StoreBeauticianInfo> {

	public static StoreBeauticianInfoSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreBeauticianInfoSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreBeauticianInfo;

	protected Class<StoreBeauticianInfo> getClazz() {
		return StoreBeauticianInfo.class;
	}

	protected RestDao<StoreBeauticianInfo> getDao() {
		return StoreBeauticianInfoDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	public List<BUser> getBUsers(String ownerId, Long storeId){
		StoreBeauticianInfo storeBeauticianInfo = super.getData(ownerId, String.valueOf(storeId));
		Map<Long, BeauticianInfo> beauticianInfoMap = storeBeauticianInfo.getBeauticianInfoMap();
		return BUserSynDataHolder.getInstance().findByMultitId(beauticianInfoMap.keySet());
	}
	
	public Map<Long, BeauticianInfo> getBeauticianInfoMap(String ownerId, Long storeId){
		StoreBeauticianInfo storeBeauticianInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeBeauticianInfo.getBeauticianInfoMap();
	}

}
