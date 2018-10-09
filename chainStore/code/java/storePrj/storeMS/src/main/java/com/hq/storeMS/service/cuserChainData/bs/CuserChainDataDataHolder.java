package com.hq.storeMS.service.cuserChainData.bs;

import java.util.Collection;
import java.util.List;

import com.hq.storeMS.service.cuserChainData.data.CuserChainData;
import com.hq.storeMS.service.cuserChainData.data.CuserChainDataCacheDAO;
import com.hq.storeMS.service.cuserChainData.data.CuserChainDataDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class CuserChainDataDataHolder {
	
	public static CuserChainDataDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(CuserChainDataDataHolder.class);
	}
	
	public void addWithId(CuserChainData target) {
		CuserChainDataDAO.getInstance().addWithId(target);
		CuserChainDataCacheDAO.getInstance().delete(target);
	}

	public void updpate(CuserChainData target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		CuserChainDataDAO.getInstance().updpate(target);
		CuserChainDataCacheDAO.getInstance().delete(target);
	}
	
	public void delete(CuserChainData target) {
		CuserChainDataDAO.getInstance().delete(target.getId());
		CuserChainDataCacheDAO.getInstance().delete(target);
	}
	
	public CuserChainData get(long id) {
		CuserChainData target = CuserChainDataCacheDAO.getInstance().get(id);
		if(target == null){
			target = CuserChainDataDAO.getInstance().get(id);
			if(target != null){
				CuserChainDataCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<CuserChainData> getListByIds(Collection<Long> ids) {
		return CuserChainDataDAO.getInstance().getListByIds(ids);
	}
}
