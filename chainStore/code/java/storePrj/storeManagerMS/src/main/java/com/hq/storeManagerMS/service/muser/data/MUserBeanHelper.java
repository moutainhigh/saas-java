package com.hq.storeManagerMS.service.muser.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;
import com.zenmind.common.hotSwap.HotSwap;

public class MUserBeanHelper {
	public static MUserBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(MUserBeanHelper.class);
	}
	
	public Set<MUserAdminRole> getRoleSet(Set<Long> muserAdminRoleIds, Map<Long, MUserAdminRole> roleMap){
		Set<MUserAdminRole> roleSet = new HashSet<MUserAdminRole>();
		for (Long roleIdTmp : muserAdminRoleIds) {
			MUserAdminRole roleTmp = roleMap.get(roleIdTmp);
			roleSet.add(roleTmp);
		}
		return roleSet;
	}
}
