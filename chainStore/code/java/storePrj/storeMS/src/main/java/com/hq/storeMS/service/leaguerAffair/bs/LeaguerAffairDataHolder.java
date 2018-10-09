package com.hq.storeMS.service.leaguerAffair.bs;

import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffair;
import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffairDAO;
import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffairRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerAffairDataHolder {
	
	public static LeaguerAffairDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(LeaguerAffairDataHolder.class);
	}
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addWithId(LeaguerAffair target) {
		LeaguerAffairDAO.getInstance().addWithId(target);
	}
	

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void update(LeaguerAffair target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		LeaguerAffairDAO.getInstance().updpate(target);
		LeaguerAffairRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(String id) {
		LeaguerAffairDAO.getInstance().delete(id);
		LeaguerAffairRedisDAO.getInstance().delete(id);
	}

	public LeaguerAffair get(String id) {
		LeaguerAffair target = LeaguerAffairRedisDAO.getInstance().get(id);
		if(target == null){
			target = LeaguerAffairDAO.getInstance().get(id);
			if(target != null){
				LeaguerAffairRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
}
