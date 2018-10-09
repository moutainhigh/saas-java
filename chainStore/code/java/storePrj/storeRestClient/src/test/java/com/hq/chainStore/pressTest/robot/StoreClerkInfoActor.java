package com.hq.chainStore.pressTest.robot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.common.ClientConstants;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreAdminRoleInfo4Add;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoActor {
	
	public static StoreClerkInfoActor getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoActor.class);
	}
	
	/**
	 * 查询
	 * @param storeId
	 * @return
	 */
	public StoreClerkInfo get(long storeId){
		return StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
	}
	
	/**
	 * 获取员工列表
	 * @param storeId
	 * @return
	 */
	public List<ClerkInfo> getClerkList(long storeId){
		return StoreClerkInfoMgr.getInstance().getClerkList(ClientConstants.STORE_CLERKINFO_ID_SUFFFIX+storeId);
	}
	
	/**
	 * 添加岗位
	 * @param storeId
	 */
	public void addStoreAdminRole(long storeId){
		StoreClerkInfo storeClerkInfo = get(storeId);
		StoreAdminRoleInfo4Add addRoleInfo=StoreAdminRoleInfo4Add.newInstance()
				.setDescript("店长"+RandomUtils.nextInt(0,30000))
				.setName("店长"+RandomUtils.nextInt(0,1000))
				.setStoreClerkInfoId(storeClerkInfo.getId())
				.setStoreId(storeId)
				.setRoleIdIndex(storeClerkInfo.getRoleIdIndex()+1);
		
		Set<Integer> permSet = new HashSet<Integer>();
		for (int i = 1; i < StoreAdminPermEnum.values().length; i++) {
			if(i==StoreAdminPermEnum.MATERIAL_ADMIN.ordinal() || i==StoreAdminPermEnum.SALARY_ADMIN.ordinal()){
				continue;
			}
			permSet.add(i);
		}
		addRoleInfo.setPermSet(permSet);
		StoreClerkInfoMgr.getInstance().addStoreAdminRole(addRoleInfo);	
	}

}
