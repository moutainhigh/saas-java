package com.hq.storeMS.service.workFlowData.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class WorkFlowDataRedisDAO extends RedisDao<WorkFlowData> {
	public static WorkFlowDataRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataRedisDAO.class);
	}
	
	final private String suffix = "workFlowData";
	
	public void saveWorkFlowDataList(WorkFlowDataQueryForm queryForm, List<WorkFlowData> list){
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();
		
		super.saveList(list, groupName, listId);
	}
	
	public List<WorkFlowData> getWorkFlowDataList(WorkFlowDataQueryForm queryForm){
		String groupName = getGroupName(queryForm.getStoreId());
		String listId = queryForm.getListId();
		
		return super.getList(groupName, listId);
	}
	
	public void deleteWorkFlowData(WorkFlowData target){
		super.delete(target.getId());
		super.deleteList(getGroupName(target.getStoreId()));
	}
	
	private String getGroupName(Object id){
		return AppUtils.joinByUnderline(suffix, id);
	}
}
