package com.hq.storeManagerMS.service.areaCode.data;

import java.util.List;

import com.hq.storeManagerMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeCacheDAO {

	public static AreaCodeCacheDAO getInstance(){
		return HotSwap.getInstance().getSingleton(AreaCodeCacheDAO.class);
	}
	
	final private String groupName = "areaCode";

	public void saveList(AreaCodeQueryForm queryForm, List<AreaCode> list) {
		AreaCodeRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}
	
	public List<AreaCode> getList(AreaCodeQueryForm queryForm) {
		return AreaCodeRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}
	
	//保存单个对象，放在组里面的一个列表  用户非ID的键  如：电话号码、名称等
	public void saveOne(String key, AreaCode target) {
		AreaCodeRedisDAO.getInstance().saveOne(groupName, key, target);
	}
	
	//获取单个对象，从组里面的一个列表获取  用户非ID的键  如：电话号码、名称等
	public AreaCode getOne(String key) {
		return AreaCodeRedisDAO.getInstance().findByOne(groupName, key);
	}
	
	public void save(AreaCode target) {
		AreaCodeRedisDAO.getInstance().save(target);
	}
	
	public AreaCode get(long id) {
		return AreaCodeRedisDAO.getInstance().get(id);
	}

	public void deleteAreaCode(AreaCode target) {
		AreaCodeRedisDAO.getInstance().delete(target.getId());
		AreaCodeRedisDAO.getInstance().deleteList(groupName);
	}
}
