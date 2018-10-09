package com.hq.storeMS.service.arrearage.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.arrearage.apiData.ArrearageQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ArrearageCacheDAO {

	public static ArrearageCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageCacheDAO.class);
	}

	final private String suffix = "arrearage";

	public void saveList(ArrearageQueryForm queryForm, List<Arrearage> list) {
		ArrearageRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<Arrearage> getList(ArrearageQueryForm queryForm) {
		return ArrearageRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(Arrearage target) {
		ArrearageRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public Arrearage get(long storeId, long id) {
		return ArrearageRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(Arrearage target) {
		ArrearageRedisDAO.getInstance().delete(target.getId());
		ArrearageRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
