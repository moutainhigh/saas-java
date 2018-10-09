package com.hq.storeMS.service.incomePay.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.incomePay.apiData.IncomePayQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class IncomePayCacheDAO {

	public static IncomePayCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(IncomePayCacheDAO.class);
	}

	final private String suffix = "incomePay";

	public void saveList(IncomePayQueryForm queryForm, List<IncomePay> list) {
		IncomePayRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<IncomePay> getList(IncomePayQueryForm queryForm) {
		return IncomePayRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(IncomePay target) {
		IncomePayRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public IncomePay get(long storeId, long id) {
		return IncomePayRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(IncomePay target) {
		IncomePayRedisDAO.getInstance().delete(target.getId());
		IncomePayRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}

}
