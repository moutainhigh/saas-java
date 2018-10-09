package com.hq.storeMS.service.workFlowType.bs;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.hq.storeMS.service.workFlowType.data.WorkFlowTypeCacheDAO;
import com.hq.storeMS.service.workFlowType.data.WorkFlowTypeDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowTypeDataHolder{
	
	public static WorkFlowTypeDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowTypeDataHolder.class);
	}
	
	public void addAndReturnId(WorkFlowType target) {
		WorkFlowTypeDAO.getInstance().addAndReturnId(target);
		WorkFlowTypeCacheDAO.getInstance().delete(target);
	}

	public void update(WorkFlowType target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		WorkFlowTypeDAO.getInstance().updpate(target);
		WorkFlowTypeCacheDAO.getInstance().delete(target);
	}
	
	public void delete(WorkFlowType target) {
		WorkFlowTypeDAO.getInstance().delete(target.getId());
		WorkFlowTypeCacheDAO.getInstance().delete(target);
	}
	
	public WorkFlowType get(long id) {
		WorkFlowType target = WorkFlowTypeCacheDAO.getInstance().get(id);
		if(target == null){
			target = WorkFlowTypeDAO.getInstance().get(id);
			if(target != null){
				WorkFlowTypeCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public WorkFlowType findOne(String typeName) {
		WorkFlowType target = WorkFlowTypeCacheDAO.getInstance().getOne(typeName);
		if(target == null){
			target = WorkFlowTypeDAO.getInstance().findByOne(typeName);
			if(target != null){
				WorkFlowTypeCacheDAO.getInstance().saveOne(typeName, target);
			}
		}
		return target;
	}
	
	public List<WorkFlowType> findByCond(QueryWorkFlowTypeForm queryForm) {
		List<WorkFlowType> list = WorkFlowTypeCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = WorkFlowTypeDAO.getInstance().findByCond(queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				WorkFlowTypeCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
}
