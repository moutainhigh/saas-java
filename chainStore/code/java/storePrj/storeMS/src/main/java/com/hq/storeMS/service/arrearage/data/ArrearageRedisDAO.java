package com.hq.storeMS.service.arrearage.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.arrearage.apiData.ArrearageQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ArrearageRedisDAO extends RedisDao<Arrearage> {

	public static ArrearageRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageRedisDAO.class);
	}
	
	final private String suffix = "arrearage";

	public void saveArrearageList(ArrearageQueryForm queryForm, List<Arrearage> list) {
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();

		super.saveList(list, groupName, listId);
	}

	public List<Arrearage> getArrearageList(ArrearageQueryForm queryForm) {
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();

		return super.getList(groupName, listId);
	}

	public void deleteArrearage(Arrearage target) {
		super.delete(target.getId());
		super.deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object id) {
		return AppUtils.joinByUnderline(suffix, id);
	}

}
