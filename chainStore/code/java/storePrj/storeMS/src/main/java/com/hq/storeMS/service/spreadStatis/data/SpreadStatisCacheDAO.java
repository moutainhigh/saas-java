package com.hq.storeMS.service.spreadStatis.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class SpreadStatisCacheDAO {

	public static SpreadStatisCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(SpreadStatisCacheDAO.class);
	}

	final private String suffix = "spreadStatis";

	public void saveList(SpreadStatisQueryForm queryForm, List<SpreadStatis> list) {
		SpreadStatisRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<SpreadStatis> getList(SpreadStatisQueryForm queryForm) {
		return SpreadStatisRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(SpreadStatis target) {
		SpreadStatisRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public SpreadStatis get(long storeId, long id) {
		return SpreadStatisRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(SpreadStatis target) {
		SpreadStatisRedisDAO.getInstance().delete(target.getId());
		SpreadStatisRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
