package com.hq.storeManagerMS.service.muserAdminRole.data;

import java.util.List;

import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleQueryApiForm;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserAdminRoleCacheDAO {

	public static MUserAdminRoleCacheDAO getInstance() {
		return HotSwap.getInstance().getSingleton(MUserAdminRoleCacheDAO.class);
	}

	final private String groupName = "muserAdminRole";

	public void saveList(MUserAdminRoleQueryApiForm queryForm, List<MUserAdminRole> list) {
		MUserAdminRoleRedisDAO.getInstance().saveList(list, groupName, queryForm.getListId());
	}

	public List<MUserAdminRole> getList(MUserAdminRoleQueryApiForm queryForm) {
		return MUserAdminRoleRedisDAO.getInstance().getList(groupName, queryForm.getListId());
	}
	
	public void save(MUserAdminRole target) {
		MUserAdminRoleRedisDAO.getInstance().save(target);
	}
	
	public MUserAdminRole get(long id) {
		return MUserAdminRoleRedisDAO.getInstance().get(id);
	}

	public void delete(MUserAdminRole target) {
		MUserAdminRoleRedisDAO.getInstance().delete(target.getId());
		MUserAdminRoleRedisDAO.getInstance().deleteList(groupName);
	}
}
