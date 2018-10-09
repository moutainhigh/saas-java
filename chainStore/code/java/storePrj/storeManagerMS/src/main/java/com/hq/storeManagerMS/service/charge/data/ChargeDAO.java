package com.hq.storeManagerMS.service.charge.data;

import com.hq.storeManagerMS.service.charge.apiData.ChargeQueryForm;
import com.hq.storeManagerMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ChargeDAO extends MongodbDao<Charge> {

    public static ChargeDAO getInstance() {
        return HotSwap.getInstance().getSingleton(ChargeDAO.class);
    }

    public List<Charge> findByCond(ChargeQueryForm queryForm) {
        Criteria criteria = buildCriteria(queryForm);
        Query query = new Query(criteria);
        List<Charge> charges = super.find(query);
        return filter(charges);
    }

    private Criteria buildCriteria(ChargeQueryForm queryForm) {
        Criteria criteria = new Criteria();
        if(queryForm.getMaxCreateTime() > 0) {
        	criteria.and("createdTime").gte(queryForm.getMinCreateTime()).lte(queryForm.getMaxCreateTime());
        }else {
        	criteria.and("createdTime").gte(queryForm.getMinCreateTime());
        }
        return criteria;
    }

    private List<Charge> filter(List<Charge> list) {
        List<Charge> result = new ArrayList<Charge>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Charge data : list) {
                if (data.getEntityState() == EntityState.Deleted.ordinal()) continue;
                result.add(data);
            }
        }
        return result;
    }
}
