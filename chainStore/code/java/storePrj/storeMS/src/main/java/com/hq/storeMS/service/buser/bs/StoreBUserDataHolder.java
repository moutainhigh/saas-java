package com.hq.storeMS.service.buser.bs;

import java.util.List;
import java.util.Set;

import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.StoreBUser;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoDataHolder;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * 店铺的用户
 */
public class StoreBUserDataHolder {
	
	public static StoreBUserDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(StoreBUserDataHolder.class);
	}
	
	public StoreBUser get(long storeId) {
		String id = StoreClerkInfo.getIdByStoreId(storeId);
		StoreClerkInfo clerkInfo = StoreClerkInfoDataHolder.getInstance().get(id);
		Set<Long> buserIdSet = clerkInfo.getClerkInfoMap().keySet();
		BUserCommQueryForm queryForm=BUserCommQueryForm.newInstance();
		queryForm.setBuserIds(buserIdSet).setPageItemCount(buserIdSet.size()).setPageNo(1);
		List<BUser> busers = BUserDataHolder.getInstance().findListByCond(queryForm);
		StoreBUser target = StoreBUser.newInstance(storeId);
		target.addAllBUsers(busers);
		return target;
	}
}
