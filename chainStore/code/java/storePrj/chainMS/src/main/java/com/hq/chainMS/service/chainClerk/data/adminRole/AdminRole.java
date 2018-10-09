package com.hq.chainMS.service.chainClerk.data.adminRole;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.common.StringFormatUtil;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class AdminRole {
	//角色ID
	private int id;
	//角色名称
	private String name;
	//连锁店ID
	private long chainId;
	//描述
	private String descript;
	//role的状态，是否有效等  AdminRoleState
	private int state;
	//权限 对应 AdminPermEnum
	private Set<Integer> permSet = new HashSet<Integer>();
	
	private long createdTime;
	private long lastUpdateTime;
	
	public static AdminRole newInstance(int idP){
		AdminRole role = new AdminRole();
		role.id = idP;
		long curTime = System.currentTimeMillis();
		role.createdTime = curTime;
		role.lastUpdateTime = curTime;
		return role;
	}
	
	public AdminRoleState getStateEnum() {
		return AdminRoleState.valueOf(this.state);
	}
	
	public void setStateEnum(AdminRoleState state) {
		this.state = state.ordinal();
	}
	
	//获取权限字符串列表
	public Set<String> getPermSet(long chainId){
		Set<String> permSetTmp = new HashSet<String>();
		for (int adminPermOrdinalTmp : permSet) {
			AdminPermEnum adminPermTmp = AdminPermEnum.valueOf(adminPermOrdinalTmp);
			permSetTmp.add(adminPermTmp.getPerm(chainId));
		}
		return permSetTmp;
	}

	//获取角色的名称
	public String getRole(long chainId){
		final String roleTemplate = "{}:{}";
		return StringFormatUtil.format(roleTemplate, this.name, chainId);
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getChainId() {
		return chainId;
	}

	public void setChainId(long chainId) {
		this.chainId = chainId;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
