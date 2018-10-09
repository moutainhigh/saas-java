package com.hq.chainStore.service.storeClerkInfo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class StoreClerkInfoSynDataHolder extends AbsDataSynDataHolder<StoreClerkInfo> {

	public static StoreClerkInfoSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreClerkInfoSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.StoreClerkInfo;

	protected Class<StoreClerkInfo> getClazz() {
		return StoreClerkInfo.class;
	}
	
	public StoreClerkInfo getData(Long ownerId,String targetId){
		return super.getData(String.valueOf(ownerId), targetId);
	}


	protected RestDao<StoreClerkInfo> getDao() {
		return StoreClerkInfoDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	public List<StoreAdminRole> getStoreAdminRoles(String ownerId, Long storeId){
		StoreClerkInfo storeClerkInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<StoreAdminRole>(storeClerkInfo.getRoleMap().values());
	}
	
	public Map<Integer, StoreAdminRole> getRoleMap(String ownerId, Long storeId){
		StoreClerkInfo storeClerkInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeClerkInfo.getRoleMap();
	}
	
	public List<ApplyClerkInfo> getApplyClerkInfos(String ownerId, Long storeId){
		StoreClerkInfo storeClerkInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<ApplyClerkInfo>(storeClerkInfo.getApplyClerkInfoMap().values());
	}
	
	public Map<Long, ApplyClerkInfo> getApplyClerkInfoMap(String ownerId, Long storeId){
		StoreClerkInfo storeClerkInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeClerkInfo.getApplyClerkInfoMap();
	}
	
	public List<ClerkInfo> getClerkInfos(String ownerId, Long storeId){
		StoreClerkInfo storeClerkInfo = super.getData(ownerId, String.valueOf(storeId));
		return new ArrayList<ClerkInfo>(storeClerkInfo.getClerkInfoMap().values());
	}
	
	public Map<Long, ClerkInfo> getClerkInfoMap(String ownerId, Long storeId){
		StoreClerkInfo storeClerkInfo = super.getData(ownerId, String.valueOf(storeId));
		return storeClerkInfo.getClerkInfoMap();
	}

}
