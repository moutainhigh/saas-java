package com.hq.chainStore.service.storeMaterialInfo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreMaterialInfoSynDataHolder extends AbsDataSynDataHolder<StoreMaterialInfo> {

	public static StoreMaterialInfoSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreMaterialInfoSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreMaterialInfo;

	protected Class<StoreMaterialInfo> getClazz() {
		return StoreMaterialInfo.class;
	}

	protected RestDao<StoreMaterialInfo> getDao() {
		return StoreMaterialInfoDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	public List<MaterialInfo> getMaterialInfos(String ownerId, Long storeId){
		StoreMaterialInfo storeMaterialInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<MaterialInfo>(storeMaterialInfo.getMaterialInfoMap().values());
	}
	
	public Map<String, MaterialInfo> getMaterialInfoMap(String ownerId, Long storeId){
		StoreMaterialInfo storeMaterialInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeMaterialInfo.getMaterialInfoMap();
	}

}
