package com.hq.storeMS.service.buser.bs.handler;

import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BuserRoleEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserRoleHelper {

	public static BUserRoleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(BUserRoleHelper.class);
	}
	
	public boolean isBossRole(BUser buser){
		if(buser != null && buser.getRoleSet().contains(BuserRoleEnum.BOSS.ordinal())){
			return true;
		}
		return false;
	}
	
	public boolean isClerkRole(BUser buser){
		if(buser != null && buser.getRoleSet().contains(BuserRoleEnum.CLERK.ordinal())){
			return true;
		}
		return false;
	}
	
	public boolean isInitRole(BUser buser){
		if(buser != null && buser.getRoleSet().contains(BuserRoleEnum.INIT.ordinal())){
			return true;
		}
		return false;
	}

}
