package com.hq.chainMS.service.chainClerk.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.chainMS.service.chainClerk.data.adminRole.AdminRole;
import com.hq.chainMS.service.chainClerk.data.adminRole.RolePermInfo;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "chainClerk")
public class ChainClerk {
	@Id	
	private long id;
	private long chainId;

	private int roleIdIndex = 1;
	//管理员角色权限定义
	private Map<Integer, AdminRole> roleMap = new HashMap<Integer, AdminRole>();
	
	private Map<Long, ClerkInfo> clerkInfoMap = new HashMap<Long,ClerkInfo>();
	
	private long createdTime;
	private long lastUpdateTime;
	private long ver;
	
	public static ChainClerk newInstance(long chainId, long bossId){
		ChainClerk chainClerk = new ChainClerk();
		chainClerk.id = chainId;
		chainClerk.chainId = chainId;
		long curTime = System.currentTimeMillis();
		chainClerk.createdTime = curTime;
		chainClerk.lastUpdateTime = curTime;
		ChainClerkBeanHelper.getInstance().init(chainClerk, bossId);
		return chainClerk;
	}
	
	//获取用户的权限列表信息
	public Set<Integer> takeUserPermission(long userId){
		Set<Integer> result = new HashSet<Integer>();
		ClerkInfo clerkInfo = clerkInfoMap.get(userId);//获取用户
		Set<Integer> roleSet = clerkInfo.getRoleSet();//用户角色
		for (Integer roleIndex : roleSet) {//遍历角色
			AdminRole storeAdminRole = roleMap.get(roleIndex);//角色的详情
			result.addAll(storeAdminRole.getPermSet());//每个角色对应的权限
		}
		return result;
	}
	
	//获取用户的角色列表信息
	public Set<AdminRole> takeUserRole(long userId){
		ClerkInfo clerkInfo = clerkInfoMap.get(userId);
		Set<AdminRole> result = new HashSet<AdminRole>();
		Set<Integer> roleSet = clerkInfo.getRoleSet();
		for (Integer roleIdTmp : roleSet) {
			AdminRole roleTmp = roleMap.get(roleIdTmp);
			result.add(roleTmp);
		}
		return result;
	}
	
	public RolePermInfo takeRolePermInfo(long userId) {
		ClerkInfo clerkInfo = clerkInfoMap.get(userId);
		RolePermInfo rolePermInfo = null;
		if(clerkInfo!=null){
			Set<AdminRole> roleSet = takeUserRole(userId);
			rolePermInfo = RolePermInfo.newInstance(userId, roleSet, chainId);
		}
		return rolePermInfo;
	}
	
	public void incrVer() {
		this.ver = ver + 1;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public int getRoleIdIndex() {
		return roleIdIndex;
	}

	public void setRoleIdIndex(int roleIdIndex) {
		this.roleIdIndex = roleIdIndex;
	}

	public Map<Integer, AdminRole> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<Integer, AdminRole> roleMap) {
		this.roleMap = roleMap;
	}

	public Map<Long, ClerkInfo> getClerkInfoMap() {
		return clerkInfoMap;
	}

	public void setClerkInfoMap(Map<Long, ClerkInfo> clerkInfoMap) {
		this.clerkInfoMap = clerkInfoMap;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getVer() {
		return ver;
	}

	public void setVer(long ver) {
		this.ver = ver;
	}
}
