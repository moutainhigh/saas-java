package com.hq.storeMS.service.productCardDetail.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class ProductCardDetailDAO extends MongodbMTDao<ProductCardDetail> {

	public static ProductCardDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductCardDetailDAO.class);
	}
	
	public List<ProductCardDetail> findProductCardDetailList(long bossId, ProductCardDetailQueryForm queryForm){
		Query query = buildQuery(queryForm);
	    List<ProductCardDetail> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<ProductCardDetail> filter(List<ProductCardDetail> list){
		List<ProductCardDetail> result = new ArrayList<ProductCardDetail>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (ProductCardDetail data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private Query buildQuery(ProductCardDetailQueryForm queryForm){
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
