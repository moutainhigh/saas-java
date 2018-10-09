package com.hq.chainStore.service.saas.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.opuser.data.adminRole.OPAdminRole;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OPAdminRoleDAO extends RestDao<OPAdminRole> {

	public static OPAdminRoleDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OPAdminRoleDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public List<OPAdminRole> list(){
		int pageItemCount =-1; 
		int pageNo = -1;
		return super.list(pageItemCount, pageNo);
		
	}
	
}
