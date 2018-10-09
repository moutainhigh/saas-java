package com.hq.chainMS.service.chainClerk.data.adminRole;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class RolePermInfo {
	// 用户ID
	private long userId;
	// 连锁店ID
	private long chainId;
	// 用户角色列表
	private Set<AdminRole> roles = new HashSet<AdminRole>();

	public static RolePermInfo newInstance(long userIdP, Set<AdminRole> rolesP, long chainIdP) {
		RolePermInfo target = new RolePermInfo();
		target.userId = userIdP;
		target.roles = rolesP;
		target.chainId = chainIdP;
		return target;
	}

	//获取用户的角色名称列表
	public Set<String> getRoleSet() {
		Set<String> rolesetTmp = new HashSet<String>();
		for (AdminRole roleTmp : roles) {
			if (roleTmp.getStateEnum() == AdminRoleState.Available) {
				rolesetTmp.add(roleTmp.getRole(this.chainId));
			}
		}
		return rolesetTmp;
	}

	//获取用户的权限字符串列表
	public Set<String> getPermSet() {
		Set<String> permSetTmp = new HashSet<String>();
		for (AdminRole roleTmp : roles) {
			if (roleTmp.getStateEnum() == AdminRoleState.Available) {
				permSetTmp.addAll(roleTmp.getPermSet(this.chainId));
			}
		}
		return permSetTmp;
	}

	//获取用户的权限列表
	public Set<Integer> getBuserPermSet() {
		Set<Integer> permSet = new HashSet<Integer>();
		for (AdminRole roleTmp : roles) {
			if (roleTmp.getStateEnum() == AdminRoleState.Available) {
				permSet.addAll(roleTmp.getPermSet());
			}
		}
		return permSet;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public Set<AdminRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<AdminRole> roles) {
		this.roles = roles;
	}

}
