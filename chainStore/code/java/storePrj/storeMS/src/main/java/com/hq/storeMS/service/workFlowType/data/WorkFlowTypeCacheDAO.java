package com.hq.storeMS.service.workFlowType.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowTypeCacheDAO {

	public static WorkFlowTypeCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowTypeCacheDAO.class);
	}

	final private String suffix = "workFlowType";

	public void saveList(QueryWorkFlowTypeForm queryForm, List<WorkFlowType> list) {
		WorkFlowTypeRedisDAO.getInstance().saveList(list, getGroupName(null), queryForm.getListId());
	}

	public List<WorkFlowType> getList(QueryWorkFlowTypeForm queryForm) {
		return WorkFlowTypeRedisDAO.getInstance().getList(getGroupName(null), queryForm.getListId());
	}
	
	//保存单个对象，放在组里面的一个列表  用户非ID的键  如：电话号码、名称等
	public void saveOne(String key, WorkFlowType target) {
		WorkFlowTypeRedisDAO.getInstance().saveOne(getGroupName(null), key, target);
	}
	
	//获取单个对象，从组里面的一个列表获取  用户非ID的键  如：电话号码、名称等
	public WorkFlowType getOne(String key) {
		return WorkFlowTypeRedisDAO.getInstance().findByOne(getGroupName(null), key);
	}
	
	public void save(WorkFlowType target) {
		WorkFlowTypeRedisDAO.getInstance().save(target);
	}
	
	public WorkFlowType get(long id) {
		return WorkFlowTypeRedisDAO.getInstance().get(id);
	}

	public void delete(WorkFlowType target) {
		WorkFlowTypeRedisDAO.getInstance().delete(target.getId());
		WorkFlowTypeRedisDAO.getInstance().deleteList(getGroupName(null));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
