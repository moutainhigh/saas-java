package com.hq.storeMS.service.activateCode.data;

import java.util.ArrayList;
import java.util.List;

import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.redis.RedisDao;

public class ActivateCodeRedisDAO extends RedisDao<ActivateCode> {

	public static ActivateCodeRedisDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ActivateCodeRedisDAO.class);
	}
	
	public ActivateCode findByOne(String groupName, String listId){
		List<ActivateCode> list = getList(groupName, listId);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	public void saveOne(String groupName, String listId, ActivateCode target){
		if(target != null){
			List<ActivateCode> list = new ArrayList<ActivateCode>();
			list.add(target);
			saveList(list, groupName, listId);
		}
	}
}
