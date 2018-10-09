package com.hq.storeMS.service.buser.data;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.zenmind.common.hotSwap.HotSwap;

public class BUserBeanHelper {

	public static BUserBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(BUserBeanHelper.class);
	}
	
	public void removeStoreId4BUser(BUser buser, long storeId) {
		buser.removeStoreId(storeId);
		//用户没有加入店铺  则更新用户的角色为未分配
		if(CollectionUtils.isEmpty(buser.getStoreIdSet())){
			Set<Integer> roleSet = new HashSet<Integer>();
			roleSet.add(BuserRoleEnum.INIT.ordinal());
			buser.setRoleSet(roleSet);
			buser.setExpiredTime(0L);
		}
	}
}
