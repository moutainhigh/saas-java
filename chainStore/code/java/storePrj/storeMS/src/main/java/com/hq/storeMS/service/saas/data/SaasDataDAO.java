package com.hq.storeMS.service.saas.data;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.mongodb.MongodbDao;

public class SaasDataDAO extends MongodbDao<SaasData> {

	//saas 只有一个数据
	private final long saasId = 1;
	
	public static SaasDataDAO getInstance(){
		return HotSwap.getInstance().getSingleton(SaasDataDAO.class);
	}
	
	public SaasData get(){
		return get(saasId);
	}
	
}
