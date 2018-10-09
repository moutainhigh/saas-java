package com.hq.chainStore.service.euser.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;


public class EUserDAO extends RestDao<EUser>{

	public static EUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(EUserDAO.class);
	}
	
	
	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<EUser> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}

}
