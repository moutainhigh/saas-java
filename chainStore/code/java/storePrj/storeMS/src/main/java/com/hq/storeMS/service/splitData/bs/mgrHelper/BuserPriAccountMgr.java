package com.hq.storeMS.service.splitData.bs.mgrHelper;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.buser.bs.BUserDataHolder;
import com.hq.storeMS.service.buser.data.BUser;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserPriAccountMgr {
	
	public static BuserPriAccountMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BuserPriAccountMgr.class);
	}
	
	public void updateData() {
		List<BUser> list = getBUserDatas();
		if(CollectionUtils.isNotEmpty(list)) {
			for (BUser user : list) {
				update(user);
			}
		}
	}
	
	private void update(BUser user) {
		user.setPriAccountNum(user.getPhone()+"");
		BUserDataHolder.getInstance().updpate(user);
	}
	
	private List<BUser> getBUserDatas(){
		return BUserDataHolder.getInstance().findPriAccountNumNotExists();
	}
}
