package com.hq.storeMS.service.euser.data;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

/** 
 * @ClassName: EUserDAO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月19日 下午2:33:23 
 *  
 */
public class EUserDAO extends MongodbDao<EUser>{

	public static EUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(EUserDAO.class);
	}
	
	public EUser findByPhone(long phone){
		Criteria criteria = new Criteria();  
		criteria.and("phone").is(phone);
	    Query query = new Query(criteria);  
		return findOne(query);
	}
	
	public List<EUser> getList(int pageItemCount,int pageNo){
		Criteria criteria = new Criteria();  
	    Query query = new Query(criteria);  
		return findPage(query,pageItemCount,pageNo);
	}
	
	public List<EUser> findByName(String name,int pageItemCount,int pageNo){
		Criteria criteria = new Criteria();  
		criteria.and("name").is(name);
	    Query query = new Query(criteria);  
		return findPage(query,pageItemCount, pageNo);
	}
}
