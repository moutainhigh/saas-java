package com.hq.storeMS.service.buserRole.data;

import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleBeanHelper {
	
	public static BuserRoleBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(BuserRoleBeanHelper.class);
	}
	
}
