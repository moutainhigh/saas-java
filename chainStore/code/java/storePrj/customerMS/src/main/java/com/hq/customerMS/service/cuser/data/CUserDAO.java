package com.hq.customerMS.service.cuser.data;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.customerMS.service.common.EntityState;
import com.hq.customerMS.service.cuser.apiData.CUserQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class CUserDAO extends MongodbDao<CUser> {

	public static CUserDAO getInstance() {
		return HotSwap.getInstance().getSingleton(CUserDAO.class);
	}

	public CUser findOne(CUserQueryForm queryForm) {
		Query query = buildQuery(queryForm);
		return super.findOne(query);
	}

	@Override
	public void delete(Object id) {
		// 只做软删除
		CUser target = get(id);
		if (target != null) {
			target.setState(EntityState.Deleted.ordinal());
			updpate(target);
		}
	}

	public List<CUser> findPriAccountNumNotExists() {
		Criteria criteria = new Criteria();
		criteria.and("priAccountNum").exists(false);
		Query query = new Query(criteria);
		return super.find(query);
	}
	
	public List<CUser> findByCond(CUserQueryForm queryForm) {
		Query query = buildQuery(queryForm);
		return super.find(query);
	}

	private Query buildQuery(CUserQueryForm queryForm) {
		Criteria criteria = new Criteria();
		if (queryForm.getPhone() > 0L) {
			criteria.and("phone").is(queryForm.getPhone());
		}
		if (StringUtils.isNoneBlank(queryForm.getName())) {
			criteria.and("name").is(queryForm.getName());
		}
		if (StringUtils.isNoneBlank(queryForm.getWxUnionId())) {
			criteria.and("wxUnionId").is(queryForm.getWxUnionId());
		}
		if (StringUtils.isNoneBlank(queryForm.getPriAccountNum())) {
			criteria.and("priAccountNum").is(queryForm.getPriAccountNum());
		}
		return new Query(criteria);
	}

}
