package com.hq.chainStore.service.user.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;

public class UserDAO extends RestDao<User> {

	public static UserDAO getInstance(){
		return HotSwap.getInstance().getSingleton(UserDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<User> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
	
	public User findByPhone(long phone){
		
		final String uriPathPattern = "/phone-{}";
		String uriPath =  StringFormatUtil.format(uriPathPattern, phone);
		
		return super.findOne(uriPath);
	}
	
	public static void main(String[] args) {
//		RestTemplateMgr.getInstance().init();
//		List<Store> list = StoreDAO.getInstance().list(10, 0);
//		System.out.println(list.size());
		
		new ClassInfo(User.class);
		
	}
	
}
