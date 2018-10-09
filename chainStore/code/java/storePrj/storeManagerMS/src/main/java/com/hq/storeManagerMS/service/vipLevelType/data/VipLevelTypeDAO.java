package com.hq.storeManagerMS.service.vipLevelType.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeManagerMS.service.common.EntityState;
import com.hq.storeManagerMS.service.vipLevelType.apiData.QueryVipLevelTypeForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class VipLevelTypeDAO extends MongodbDao<VipLevelType> {

	public static VipLevelTypeDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelTypeDAO.class);
	}
	
	public VipLevelType findOneByMaxId(long maxId) {
		Criteria criteria = new Criteria();
		criteria.and("_id").gte(maxId);
		Query query = new Query(criteria);
		return super.findOne(query);
	}

	public List<VipLevelType> findList(QueryVipLevelTypeForm queryForm) {
		Criteria criteria = new Criteria();
		if (queryForm.getState() > -1) {
			criteria.and("state").is(queryForm.getState());
		}
		if (StringUtils.isNotBlank(queryForm.getName())) {
			criteria.and("name").is(queryForm.getName());
		}
		Query query = new Query(criteria);
		List<VipLevelType> list = super.find(query);
		return filter(list);
	}

	private List<VipLevelType> filter(List<VipLevelType> list) {
		List<VipLevelType> result = new ArrayList<VipLevelType>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (VipLevelType data : list) {
				if (data.getEntityState() == EntityState.Deleted.ordinal())
					continue;
				result.add(data);
			}
		}
		return result;
	}
}
