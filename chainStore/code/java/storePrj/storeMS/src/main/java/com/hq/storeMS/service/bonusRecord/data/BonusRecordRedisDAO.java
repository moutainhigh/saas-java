package com.hq.storeMS.service.bonusRecord.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.bonusRecord.apiData.BonusRecordQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class BonusRecordRedisDAO extends RedisDao<BonusRecord> {

	public static BonusRecordRedisDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BonusRecordRedisDAO.class);
	}
	
	final private String suffix = "bonusRecord";

	public void saveBonusRecordList(BonusRecordQueryForm queryForm, List<BonusRecord> list) {
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();

		super.saveList(list, groupName, listId);
	}

	public List<BonusRecord> getBonusRecordList(BonusRecordQueryForm queryForm) {
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();

		return super.getList(groupName, listId);
	}

	public void deleteBonusRecord(BonusRecord target) {
		super.delete(target.getId());
		super.deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object id) {
		return AppUtils.joinByUnderline(suffix, id);
	}
}
