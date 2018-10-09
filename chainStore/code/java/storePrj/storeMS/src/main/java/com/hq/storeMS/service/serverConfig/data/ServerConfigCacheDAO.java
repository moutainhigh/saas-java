package com.hq.storeMS.service.serverConfig.data;

import java.util.List;

import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigQueryForm;
import com.zenmind.common.hotSwap.HotSwap;

public class ServerConfigCacheDAO {

	public static ServerConfigCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(ServerConfigCacheDAO.class);
	}

	final private String suffix = "serverConfig";

	public void saveList(ServerConfigQueryForm queryForm, List<ServerConfig> list) {
		ServerConfigRedisDAO.getInstance().saveList(list, getGroupName(null), queryForm.getListId());
	}

	public List<ServerConfig> getList(ServerConfigQueryForm queryForm) {
		return ServerConfigRedisDAO.getInstance().getList(getGroupName(null), queryForm.getListId());
	}
	
	//保存单个对象，放在组里面的一个列表  用户非ID的键  如：电话号码、名称等
	public void saveOne(String key, ServerConfig target) {
		ServerConfigRedisDAO.getInstance().saveOne(getGroupName(null), key, target);
	}
	
	//获取单个对象，从组里面的一个列表获取  用户非ID的键  如：电话号码、名称等
	public ServerConfig getOne(String key) {
		return ServerConfigRedisDAO.getInstance().findByOne(getGroupName(null), key);
	}
	
	public void save(ServerConfig target) {
		ServerConfigRedisDAO.getInstance().save(target);
	}
	
	public ServerConfig get(long id) {
		return ServerConfigRedisDAO.getInstance().get(id);
	}

	public void delete(ServerConfig target) {
		ServerConfigRedisDAO.getInstance().delete(target.getId());
		ServerConfigRedisDAO.getInstance().deleteList(getGroupName(null));
	}

	private String getGroupName(Object storeId) {
		return AppUtils.joinByUnderline(suffix, storeId);
	}
}
