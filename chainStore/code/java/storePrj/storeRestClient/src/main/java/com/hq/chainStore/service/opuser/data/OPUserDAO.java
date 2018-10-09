package com.hq.chainStore.service.opuser.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;

public class OPUserDAO extends RestDao<OPUser> {

	public static OPUserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(OPUserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<OPUser> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
	
	public static void main(String[] args) {
//		RestTemplateMgr.getInstance().init();
//		List<OPUser> list = OPUserDAO.getInstance().list(10, 0);
//		System.out.println(list.size());
		
		new ClassInfo(OPUser.class);
		
	}
	
}
