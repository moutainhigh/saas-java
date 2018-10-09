package com.hq.storeMS.service.billRecord.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.billRecord.apiData.BillRecordQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class BillRecordCacheDAO {

	public static BillRecordCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BillRecordCacheDAO.class);
	}

	final private String suffix = "billRecord";

	public void saveList(BillRecordQueryForm queryForm, List<BillRecord> list) {
		BillRecordRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<BillRecord> getList(BillRecordQueryForm queryForm) {
		return BillRecordRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(BillRecord target) {
		BillRecordRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public BillRecord get(long storeId, long id) {
		return BillRecordRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(BillRecord target) {
		BillRecordRedisDAO.getInstance().delete(target.getId());
		BillRecordRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
