package com.hq.chainStore.service.message.data;

import java.util.List;

import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.classinfo.ClassInfo;
import com.zenmind.dao.rest.RestDao;

public class MessageDAO extends RestDao<Message> {

	public static MessageDAO getInstance(){
		return HotSwap.getInstance().getSingleton(MessageDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	@Override
	public List<Message> list(int pageItemCount, int pageNo){
		return super.list(pageItemCount, pageNo);
		
	}
	
	public static void main(String[] args) {
		new ClassInfo(Message.class);
	}
	
}
