package com.hq.chainStore.service.store.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;

public class StoreDAO extends RestDao<Store> {

	public static StoreDAO getInstance(){
		return HotSwap.getInstance().getSingleton(StoreDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<Store> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
	
	public static void main(String[] args) {
//		RestTemplateMgr.getInstance().init();
//		List<Store> list = StoreDAO.getInstance().list(10, 0);
//		System.out.println(list.size());
		
		new ClassInfo(Store.class);
		
	}
	
}
