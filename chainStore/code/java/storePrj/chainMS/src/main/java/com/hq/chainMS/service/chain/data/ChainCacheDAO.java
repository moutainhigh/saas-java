package com.hq.chainMS.service.chain.data;

import java.util.List;

import com.hq.chainMS.service.chain.apiData.ChainQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainCacheDAO {

	public static ChainCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ChainCacheDAO.class);
	}
	
	final private String groupName = "chain";
	
	public void saveList(ChainQueryForm queryForm, List<Chain> list) {
		ChainRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<Chain> getList(ChainQueryForm queryForm) {
		return ChainRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}

	public void save(Chain target) {
		ChainRedisDAO.getInstance().save(target);
	}
	
	public void saveOne(String key, Chain target) {
		ChainRedisDAO.getInstance().saveOne(groupName, key, target);
	}
	
	public Chain get(Object id) {
		return ChainRedisDAO.getInstance().get(id);
	}
	
	public Chain getOne(String key) {
		return ChainRedisDAO.getInstance().findByOne(groupName, key);
	}

	public void delete(Chain target) {
		ChainRedisDAO.getInstance().delete(target.getId());
		ChainRedisDAO.getInstance().deleteList(groupName);
	}
}
