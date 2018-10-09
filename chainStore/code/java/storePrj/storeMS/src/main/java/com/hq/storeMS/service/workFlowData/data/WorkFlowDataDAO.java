package com.hq.storeMS.service.workFlowData.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class WorkFlowDataDAO extends MongodbMTDao<WorkFlowData> {

	public static WorkFlowDataDAO getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataDAO.class);
	}
	
	public List<WorkFlowData> findByCond(long bossId, WorkFlowDataQueryForm queryForm) {
		Query query = new Query(buildCriteria(queryForm));
		List<WorkFlowData> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<WorkFlowData> filter(List<WorkFlowData> list){
		List<WorkFlowData> result = new ArrayList<WorkFlowData>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (WorkFlowData data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}

	private Criteria buildCriteria(WorkFlowDataQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(queryForm.getStoreId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
		
		if(queryForm.getWorkFlowTypeId() > 0) {
			criteria.and("workFlowTypeId").is(queryForm.getWorkFlowTypeId());
		}else {
			criteria.and("workFlowTypeId").gte(queryForm.getWorkFlowTypeId());
		}
		return criteria;
	}
}
