package com.hq.storeMS.service.specialData.bs;

import com.hq.storeMS.service.specialData.data.SpecialData;
import com.hq.storeMS.service.specialData.data.SpecialDataDAO;
import com.hq.storeMS.service.specialData.data.SpecialDataRedisDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class SpecialDataDataHolder{
	
	public static SpecialDataDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(SpecialDataDataHolder.class);
	}
	
	/**
	 * 业务层一定要区分是add还是update,用此方法的时候id必须是long型的自增字段
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void addWithId(SpecialData target) {
		SpecialDataDAO.getInstance().addWithId(target);
	}

	/**
	 * 业务层一定要区分是add还是update
	 * @param target
	 * @return
	 * @throws RdbDaoException
	 */
	public void updpate(SpecialData target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		SpecialDataDAO.getInstance().updpate(target);
		SpecialDataRedisDAO.getInstance().delete(target.getId());
	}
	
	public void delete(String id) {
		SpecialDataDAO.getInstance().delete(id);
		SpecialDataRedisDAO.getInstance().delete(id);
	}

	public SpecialData get(String id) {
		SpecialData target = SpecialDataRedisDAO.getInstance().get(id);
		if(target == null){
			target = SpecialDataDAO.getInstance().get(id);
			if(target != null){
				SpecialDataRedisDAO.getInstance().save(target);
			}
		}
		return target;
	}
}
