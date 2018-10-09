package com.hq.storeManagerMS.service.vipLevel.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeManagerMS.service.vipLevel.apiData.QueryVipLevelForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class VipLevelDAO extends MongodbDao<VipLevel> {

	public static VipLevelDAO getInstance() {
		return HotSwap.getInstance().getSingleton(VipLevelDAO.class);
	}

	public List<VipLevel> findList(QueryVipLevelForm queryForm) {
		Criteria criteria = new Criteria();
		if (queryForm.getState() > -1) {
			criteria.and("state").is(queryForm.getState());
		}
		if (queryForm.getTypeId() > -1) {
			criteria.and("typeId").is(queryForm.getTypeId());
		}
		if(StringUtils.isNotBlank(queryForm.getName())) {
			criteria.and("name").regex("^.*" + queryForm.getName() + ".*$");
		}
		Query query = new Query(criteria);
		return super.find(query);
	}
	
	public VipLevel findOneByMaxId(long maxId) {
		Criteria criteria = new Criteria();
		criteria.and("_id").gte(maxId);
		Query query = new Query(criteria);
		return super.findOne(query);
	}

}
