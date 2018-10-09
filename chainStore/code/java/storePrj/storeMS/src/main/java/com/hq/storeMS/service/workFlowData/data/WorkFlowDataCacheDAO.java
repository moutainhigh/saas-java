package com.hq.storeMS.service.workFlowData.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataCacheDAO {

	public static WorkFlowDataCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataCacheDAO.class);
	}

	final private String suffix = "workFlowData";

	public void saveList(WorkFlowDataQueryForm queryForm, List<WorkFlowData> list) {
		WorkFlowDataRedisDAO.getInstance().saveList(list, getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}

	public List<WorkFlowData> getList(WorkFlowDataQueryForm queryForm) {
		return WorkFlowDataRedisDAO.getInstance().getList(getGroupName(queryForm.getStoreId()), queryForm.getListId());
	}
	
	public void save(WorkFlowData target) {
		WorkFlowDataRedisDAO.getInstance().saveOne(getGroupName(target.getStoreId()), String.valueOf(target.getId()), target);
	}
	
	public WorkFlowData get(long storeId, long id) {
		return WorkFlowDataRedisDAO.getInstance().findByOne(getGroupName(storeId), String.valueOf(id));
	}

	public void delete(WorkFlowData target) {
		WorkFlowDataRedisDAO.getInstance().delete(target.getId());
		WorkFlowDataRedisDAO.getInstance().deleteList(getGroupName(target.getStoreId()));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
