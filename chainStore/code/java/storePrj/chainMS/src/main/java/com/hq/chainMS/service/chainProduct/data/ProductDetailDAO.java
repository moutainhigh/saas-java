package com.hq.chainMS.service.chainProduct.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.chainMS.service.chainProduct.apiData.ProductDetailQueryForm;
import com.hq.chainMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class ProductDetailDAO extends MongodbMTDao<ProductDetail> {

	public static ProductDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ProductDetailDAO.class);
	}
	
	public List<ProductDetail> findProductDetailList(long bossId, ProductDetailQueryForm queryForm){
		Query query = buildQuery(queryForm);
	    List<ProductDetail> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<ProductDetail> filter(List<ProductDetail> list){
		List<ProductDetail> result = new ArrayList<ProductDetail>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (ProductDetail data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private Query buildQuery(ProductDetailQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("chainId").is(queryForm.getChainId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	    
	}

}
