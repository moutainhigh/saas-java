package com.hq.storeMS.service.opLog.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.opLog.apiData.OpLogQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class OpLogCacheDAO {

	public static OpLogCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(OpLogCacheDAO.class);
	}

	final private String suffix = "opLog";

	public void saveList(OpLogQueryForm queryForm, List<OpLog> list) {
		OpLogRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<OpLog> getList(OpLogQueryForm queryForm) {
		return OpLogRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(OpLog target) {
		OpLogRedisDAO.getInstance().save(target);
	}
	
	public OpLog get(long id) {
		return OpLogRedisDAO.getInstance().get(id);
	}

	public void delete(OpLog target) {
		OpLogRedisDAO.getInstance().delete(target.getId());
		OpLogRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
