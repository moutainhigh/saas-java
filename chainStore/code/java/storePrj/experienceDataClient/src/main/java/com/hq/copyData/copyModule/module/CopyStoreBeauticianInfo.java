package com.hq.copyData.copyModule.module;

import java.util.Map;
import java.util.Set;

import com.hq.chainStore.service.common.EntityState;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.AddBeauticianInfoData;
import com.hq.chainStore.service.storeBeauticianInfo.bs.StoreBeauticianInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;

public class CopyStoreBeauticianInfo extends AbstractCopyModule{
	
	public static CopyStoreBeauticianInfo newInstance(){
		CopyStoreBeauticianInfo data = new CopyStoreBeauticianInfo();
		return data;
	}
	
	public void copy(){
		AccessTokenMgr.getInstance().setOpIdTL(getTargetBossId());
		StoreClerkInfo storeClerk = StoreClerkInfoMgr.getInstance().getByStoreId(getTargetStoreId());
		
		Map<Integer, StoreAdminRole> roleMap = storeClerk.getRoleMap();
		Map<Long, ClerkInfo> clerkInfoMap = storeClerk.getClerkInfoMap();
		for (ClerkInfo info : clerkInfoMap.values()) {
			if(info.getEntityState() == EntityState.Deleted.ordinal()) {
				continue;
			}
			if(isBeauticianRole(info.getRoleSet(), roleMap)){
				AddBeauticianInfoData addBeauticianInfoData = AddBeauticianInfoData.newInstance();
				addBeauticianInfoData.setBuserId(info.getBuserId());
				StoreBeauticianInfoMgr.getInstance().addBeauticianInfoData(getTargetStoreId(), addBeauticianInfoData);
			}
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
		System.out.println("copy store beautician info finish");
	}
	
	private boolean isBeauticianRole(Set<Integer> roleSet, Map<Integer, StoreAdminRole> roleMap) {
		for (Integer roleIndex : roleSet) {
			if("美容师".equals(roleMap.get(roleIndex).getName())){
				return true;
			}
		}
		return false;
	}
}
