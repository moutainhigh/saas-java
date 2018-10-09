package com.hq.storeMS.service.store.data;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.saas.apiData.OPStoreQueryApiForm;
import com.hq.storeMS.service.store.apiData.StoreQueryForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class StoreDAO extends MongodbDao<Store> {

	public static StoreDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreDAO.class);
	}
	
	public List<Store> findByName(String name, int pageItemCount,int pageNo) {
		Criteria criteria = new Criteria();

		criteria.and("name").regex("^.*" + name + ".*$");
//		Pattern pattern = Pattern.compile("^.*"+name+".*$");
//		criteria.and("name").regex(pattern);
		criteria.and("state").is(StoreState.Open.ordinal());
	    Query query = new Query(criteria);
		return super.find(query);
	}
	
	public List<Store> findByIdList(Collection<Long> storeIdSet,int pageItemCount,int pageNo) {
		Criteria criteria = new Criteria();  
		criteria.and("_id").in(storeIdSet);
		criteria.and("state").is(StoreState.Open.ordinal());
	    Query query = new Query(criteria);  
		return super.find(query);
	}

	@Override
	public void delete(Object id) {
		//只做软删除
		Store store = get(id);
		if(store!=null){
			store.setEntityState(EntityState.Deleted.ordinal());
			updpate(store);
		}
	}
	
	public List<Store> findStoreByCond(StoreQueryForm queryForm) {
	    Query query = new Query(buildCondCriteria(queryForm));
		return super.find(query);
	}
	
	private Criteria buildCondCriteria(StoreQueryForm queryForm) {
		Criteria criteria = new Criteria();  
		if(StringUtils.isNotBlank(queryForm.getName())){
			criteria.and("name").regex("^.*" + queryForm.getName() + ".*$");
		}
		if(queryForm.getChainId() > 0){
			criteria.and("chainIds").in(queryForm.getChainId());
		}
		if(CollectionUtils.isNotEmpty(queryForm.getStoreIds())) {
			criteria.and("_id").in(queryForm.getStoreIds());
		}
		criteria.and("state").is(StoreState.Open.ordinal());
		return criteria;
	}
	
	public List<Store> findByPage(int pageItemCount, int pageNo) {
		Criteria criteria = new Criteria();  
		Query query = new Query(criteria);
		if(pageItemCount == 0){
			pageItemCount=ServerConstants.PAGE_ITEM_COUNT;
		}
		if(pageNo == 0){
			pageNo=1;
		}
		return findPage(query, pageItemCount, pageNo);
	}
	
	public List<Store> findStoreList(OPStoreQueryApiForm queryForm) {
		Criteria criteria = buildCriteria(queryForm);
	    Query query = new Query(criteria);  
		return super.find(query);
	}

	private Criteria buildCriteria(OPStoreQueryApiForm queryForm) {
		Criteria criteria = new Criteria();  
		
		//店铺名称
		if(StringUtils.isNotBlank(queryForm.getName())){
			criteria.and("name").is(queryForm.getName());
		}
		
		//店铺区域
		if(StringUtils.isNotBlank(queryForm.getArea())){
			criteria.and("area").is(queryForm.getArea());
		}
		
		//店铺详细地址
		if(StringUtils.isNotBlank(queryForm.getAddress())){
			criteria.and("address").is(queryForm.getAddress());
		}
		
		//公司名称
		if(StringUtils.isNotBlank(queryForm.getCompany())){
			criteria.and("company").is(queryForm.getCompany());
		}
		
		//公司区域
		if(StringUtils.isNotBlank(queryForm.getCompanyArea())){
			criteria.and("companyArea").is(queryForm.getCompanyArea());
		}

		//公司地址
		if(StringUtils.isNotBlank(queryForm.getCompanyAddress())){
			criteria.and("companyAddress").is(queryForm.getCompanyAddress());
		}
		
		//店铺状态
		if(queryForm.getState() > -1){
			criteria.and("state").is(queryForm.getState());
		}
		return criteria;
	}
	
}
