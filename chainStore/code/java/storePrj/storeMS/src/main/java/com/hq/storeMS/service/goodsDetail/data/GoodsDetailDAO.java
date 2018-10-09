package com.hq.storeMS.service.goodsDetail.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class GoodsDetailDAO extends MongodbMTDao<GoodsDetail> {

	public static GoodsDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(GoodsDetailDAO.class);
	}
	
	public List<GoodsDetail> findGoodsDetailList(long bossId, GoodsDetailQueryForm queryForm){
		Query query = buildQuery(queryForm);
	    List<GoodsDetail> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<GoodsDetail> filter(List<GoodsDetail> list){
		List<GoodsDetail> result = new ArrayList<GoodsDetail>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (GoodsDetail data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private Query buildQuery(GoodsDetailQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("storeId").is(queryForm.getStoreId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	    
	}

}
