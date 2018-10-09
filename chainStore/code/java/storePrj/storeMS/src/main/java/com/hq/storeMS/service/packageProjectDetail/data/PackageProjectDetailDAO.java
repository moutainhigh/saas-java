package com.hq.storeMS.service.packageProjectDetail.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class PackageProjectDetailDAO extends MongodbMTDao<PackageProjectDetail> {

	public static PackageProjectDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(PackageProjectDetailDAO.class);
	}
	
	public List<PackageProjectDetail> findPackageProjectDetailList(long bossId, PackageProjectDetailQueryForm queryForm){
		Query query = buildQuery(queryForm);
	    List<PackageProjectDetail> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<PackageProjectDetail> filter(List<PackageProjectDetail> list){
		List<PackageProjectDetail> result = new ArrayList<PackageProjectDetail>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (PackageProjectDetail data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private Query buildQuery(PackageProjectDetailQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("storeId").is(queryForm.getStoreId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	    
	}

}
