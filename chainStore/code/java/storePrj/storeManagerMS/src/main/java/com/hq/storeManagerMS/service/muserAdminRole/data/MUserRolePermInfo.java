package com.hq.storeManagerMS.service.muserAdminRole.data;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户的角色 
 * 一个用户 对应多个角色 MUserAdminRole roleSet
 */
public class MUserRolePermInfo {
	private long muserId;

	private Set<MUserAdminRole> roleSet = new HashSet<MUserAdminRole>();

	public static MUserRolePermInfo newInstance(long muserId,
			Set<MUserAdminRole> roleSet) {
		MUserRolePermInfo target = new MUserRolePermInfo();
		target.muserId = muserId;
		target.roleSet = roleSet;
		return target;
	}

	// 获取角色名称 如：用户管理 收费管理等
	public Set<String> getRoleSet() {
		Set<String> rolesetTmp = new HashSet<String>();
		for (MUserAdminRole roleTmp : roleSet) {
			if (roleTmp.getStateEnum() == MUserAdminRoleState.Available) {
				rolesetTmp.add(roleTmp.getRole4Shiro());
			}
		}
		return rolesetTmp;
	}

	// 获取权限字符串集合 如：mng:*:*:*
	public Set<String> getPermSet() {
		Set<String> permSetTmp = new HashSet<String>();
		for (MUserAdminRole roleTmp : roleSet) {
			if (roleTmp.getStateEnum() == MUserAdminRoleState.Available) {
				permSetTmp.addAll(roleTmp.getPermSet4Shiro());
			}
		}
		return permSetTmp;
	}

	// 获取权限明细序号
	public Set<Integer> getMUserPermSet() {
		Set<Integer> permSet = new HashSet<Integer>();
		for (MUserAdminRole roleTmp : roleSet) {
			if (roleTmp.getStateEnum() == MUserAdminRoleState.Available) {
				permSet.addAll(roleTmp.getPermSet());
			}
		}
		return permSet;
	}

	public long getMuserId() {
		return muserId;
	}

}
