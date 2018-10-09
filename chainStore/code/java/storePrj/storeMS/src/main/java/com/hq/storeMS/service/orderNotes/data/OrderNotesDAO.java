package com.hq.storeMS.service.orderNotes.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.dataReport.apiData.DataReportQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class OrderNotesDAO extends MongodbMTDao<OrderNotes> {

    public static OrderNotesDAO getInstance() {
        return HotSwap.getInstance().getSingleton(OrderNotesDAO.class);
    }

    private Query buildQuery(DataReportQueryForm queryForm) {
        Criteria criteria = new Criteria();
        criteria.and("storeId").is(queryForm.getStoreId());
        // 强制使用时间的条件 可以强制走索引查询 minTime与maxTime默认值是0
        if (queryForm.getMaxTime() > 0) {
            criteria.and("createTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
        } else {
            criteria.and("createTime").gte(queryForm.getMinTime());
        }
        return new Query(criteria);
    }


    /**
     * 根据Id获取退款记录
     * @param routeId
     * @param storeId
     * @param ids
     * @return
     */
    public List<OrderNotes> findOrderByIds(long routeId, long storeId, String ids) {
        Criteria criteria = new Criteria();
        criteria.and("storeId").is(storeId);
         List<Long> idsSet =  AppUtils.getIdList(ids);
        criteria.and("_id").in(idsSet);
        Query query = new Query(criteria);
        return find(routeId, query);

    }

    public List<OrderNotes> findOrderNotesList(long routeId, DataReportQueryForm queryForm) {
        return find(routeId, buildQuery(queryForm));
    }

}
