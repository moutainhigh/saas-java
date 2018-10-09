package com.hq.storeMS.service.incomePay.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.incomePay.apiData.IncomePayQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class IncomePayDAO extends MongodbMTDao<IncomePay> {

    public static IncomePayDAO getInstance() {
        return HotSwap.getInstance().getSingleton(IncomePayDAO.class);
    }

    public List<IncomePay> findByCond(long bossId, IncomePayQueryForm queryForm) {
        Criteria criteria = buildCriteria(queryForm);
        Query query = new Query(criteria);
        List<IncomePay> incomePays = super.find(bossId, query);
        return filter(incomePays);
    }

    private Criteria buildCriteria(IncomePayQueryForm queryForm) {
        Criteria criteria = new Criteria();
        criteria.and("storeId").is(queryForm.getStoreId());
        if(queryForm.getMaxIncomePayTime() > 0) {
			criteria.and("incomePayTime").gte(queryForm.getMinIncomePayTime()).lte(queryForm.getMaxIncomePayTime());
		}else {
			criteria.and("incomePayTime").gte(queryForm.getMinIncomePayTime());
		}
        return criteria;
    }

    private List<IncomePay> filter(List<IncomePay> list) {
        List<IncomePay> result = new ArrayList<IncomePay>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (IncomePay data : list) {
                if (data.getEntityState() == EntityState.Deleted.ordinal()) continue;
                result.add(data);
            }
        }
        return result;
    }
}
