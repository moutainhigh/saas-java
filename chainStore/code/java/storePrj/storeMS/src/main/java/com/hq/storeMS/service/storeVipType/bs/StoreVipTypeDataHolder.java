package com.hq.storeMS.service.storeVipType.bs;

import java.util.List;

import com.hq.storeMS.service.storeVipType.data.StoreVipType;
import com.hq.storeMS.service.storeVipType.data.StoreVipTypeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreVipTypeDataHolder {

	public static StoreVipTypeDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(StoreVipTypeDataHolder.class);
	}

	public void addWithId(StoreVipType target) {
		StoreVipTypeDAO.getInstance().addWithId(target);
	}

	public void update(StoreVipType target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		StoreVipTypeDAO.getInstance().updpate(target);
	}

	public void delete(long id) {
		StoreVipTypeDAO.getInstance().delete(id);
	}

	public StoreVipType findByLevel(int vipLevel) {
		return StoreVipTypeDAO.getInstance().findByLevel(vipLevel);
	}

	public StoreVipType findByName(String vipName) {
		return StoreVipTypeDAO.getInstance().findByName(vipName);
	}

	public StoreVipType get(long id) {
		return StoreVipTypeDAO.getInstance().get(id);
	}

	public List<StoreVipType> findPage(int pageItemCount, int pageNo) {
		return StoreVipTypeDAO.getInstance().findPage(pageItemCount, pageNo);
	}
}
