package com.hq.chainMS.service.chainCard.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hq.chainMS.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.hq.chainMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.mt.MongodbMTDao;

public class MembershipCardDetailDAO extends MongodbMTDao<MembershipCardDetail> {

	public static MembershipCardDetailDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MembershipCardDetailDAO.class);
	}
	
	public List<MembershipCardDetail> findMembershipCardDetailList(long bossId, MembershipCardDetailQueryForm queryForm){
		Query query = buildQuery(queryForm);
	    List<MembershipCardDetail> list = super.find(bossId, query);
		return filter(list);
	}
	
	private List<MembershipCardDetail> filter(List<MembershipCardDetail> list){
		List<MembershipCardDetail> result = new ArrayList<MembershipCardDetail>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (MembershipCardDetail data : list) {
				if(data.getEntityState() != EntityState.Deleted.ordinal()) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private Query buildQuery(MembershipCardDetailQueryForm queryForm){
		Criteria criteria = new Criteria();  
		criteria.and("chainId").is(queryForm.getChainId());
		//强制使用时间的条件  可以强制走索引查询 minTime与maxTime默认值是0 
		if(queryForm.getMaxTime() > 0) {
			criteria.and("createdTime").gte(queryForm.getMinTime()).lte(queryForm.getMaxTime());
		}else {
			criteria.and("createdTime").gte(queryForm.getMinTime());
		}
	    return new Query(criteria);
	    
	}

}
