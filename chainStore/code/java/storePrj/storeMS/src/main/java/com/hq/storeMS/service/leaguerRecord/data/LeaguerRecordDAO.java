package com.hq.storeMS.service.leaguerRecord.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class LeaguerRecordDAO extends MongodbMTDao<LeaguerRecord> {

	public static LeaguerRecordDAO getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerRecordDAO.class);
	}

	public List<LeaguerRecord> findByCond(long bossId, LeaguerRecordQueryForm queryForm) {
		Query query = buildQuery(queryForm);
		return super.find(bossId, query);
	}

	private Query buildQuery(LeaguerRecordQueryForm queryForm) {
		Criteria criteria = new Criteria();
		criteria.and("storeId").is(queryForm.getStoreId());
		// 强制使用时间的条件 可以强制走索引查询 minTime与maxTime默认值是0
		if (queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		} else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
		return new Query(criteria);
	}
}
