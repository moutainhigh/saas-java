package com.hq.storeMS.service.bonusRecord.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class BonusRecordCacheDAO {

	public static BonusRecordCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRecordCacheDAO.class);
	}

	final private String suffix = "bonusRecord";

	public void saveList(BonusRecordQueryForm queryForm, List<BonusRecord> list) {
		BonusRecordRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<BonusRecord> getList(BonusRecordQueryForm queryForm) {
		return BonusRecordRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(BonusRecord target) {
		BonusRecordRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public BonusRecord get(long storeId, long id) {
		return BonusRecordRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(BonusRecord target) {
		BonusRecordRedisDAO.getInstance().delete(target.getId());
		BonusRecordRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
