package com.hq.storeMS.service.buser.data;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class BUserDAO extends MongodbDao<BUser> {

	public static BUserDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserDAO.class);
	}
	
	public List<BUser> findPriAccountNumNotExists() {
		Criteria criteria = new Criteria();
		criteria.and("priAccountNum").exists(false);
		Query query = new Query(criteria);
		return super.find(query);
	}

	public BUser findOne(BUserCommQueryForm queryForm) {
		Query query = buildQuery(queryForm);
		return super.findOne(query);
	}

	@Override
	public void delete(Object id) {
		// 只做软删除
		BUser target = get(id);
		if (target != null) {
			target.setState(EntityState.Deleted);
			updpate(target);
		}
	}

	public List<BUser> findByCond(BUserCommQueryForm queryForm) {
		Query query = buildQuery(queryForm);
		return super.find(query);
	}

	public BUserCount getCount(BUserCommQueryForm queryForm) {
		Query query = buildQuery(queryForm);
		long count = super.count(query);
		return BUserCount.newInstance(count);
	}

	private Query buildQuery(BUserCommQueryForm queryForm) {
		Criteria criteria = new Criteria();
		if (queryForm.getPhone() > 0L) {
			criteria.and("phone").is(queryForm.getPhone());
		}

		// 时间段判断
		if (queryForm.getMaxTime() > 0L) {
			criteria.and("createdTime").lte(queryForm.getMaxTime());
		}
		criteria.and("createdTime").gte(queryForm.getMinTime());

		if (CollectionUtils.isNotEmpty(queryForm.getRoleSet())) {
			criteria.and("roleSet").all(queryForm.getRoleSet());
		}

		if (queryForm.getChainId() > 0L) {
			criteria.and("chainIds").in(queryForm.getChainId());
		}

		if (queryForm.getVipType() != ServerConstants.NONE_TYPE) {
			criteria.and("vipType").is(queryForm.getVipType());
		}

		if (StringUtils.isNoneBlank(queryForm.getName())) {
			criteria.and("name").is(queryForm.getName());
		}
		
		if (StringUtils.isNoneBlank(queryForm.getPriAccountNum())) {
			criteria.and("priAccountNum").is(queryForm.getPriAccountNum());
		}
		
		if (StringUtils.isNoneBlank(queryForm.getWxUnionId())) {
			criteria.and("wxUnionId").is(queryForm.getWxUnionId());
		}

		if (CollectionUtils.isNotEmpty(queryForm.getBuserIds())) {
			criteria.and("_id").in(queryForm.getBuserIds());
		} else if (queryForm.getBuserId() > 0L) {
			criteria.and("_id").is(queryForm.getBuserId());
		}

		return new Query(criteria);
	}
}
