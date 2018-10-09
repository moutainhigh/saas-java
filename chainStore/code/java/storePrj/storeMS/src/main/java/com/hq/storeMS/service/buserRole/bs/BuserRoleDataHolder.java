package com.hq.storeMS.service.buserRole.bs;

import com.hq.storeMS.service.buserRole.data.BuserRole;
import com.hq.storeMS.service.buserRole.data.BuserRoleCacheDAO;
import com.hq.storeMS.service.buserRole.data.BuserRoleDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class BuserRoleDataHolder {
	
	public static BuserRoleDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(BuserRoleDataHolder.class);
	}
	
	public void addWithId(BuserRole target) {
		BuserRoleDAO.getInstance().addWithId(target);
		BuserRoleCacheDAO.getInstance().delete(target);
	}

	public void updpate(BuserRole target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		BuserRoleDAO.getInstance().updpate(target);
		BuserRoleCacheDAO.getInstance().delete(target);
	}
	
	public void delete(BuserRole target) {
		BuserRoleDAO.getInstance().delete(target.getId());
		BuserRoleCacheDAO.getInstance().delete(target);
	}
	
	public BuserRole get(long id) {
		BuserRole target = BuserRoleCacheDAO.getInstance().get(id);
		if(target == null){
			target = BuserRoleDAO.getInstance().get(id);
			if(target != null){
				BuserRoleCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
}
