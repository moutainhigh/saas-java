package com.hq.storeManagerMS.service.muserAdminRole.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.zenmind.dao.classinfo.IndexField;
import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 角色 角色包含多个权限 MUserAdminPermEnum permSet
 */
@SynClass
@Table(name = "muserAdminRole")
public class MUserAdminRole {
	@Id
	private long id;
	@IndexField
	private String name;
	private String descript;
	// role的状态，是否有效等 MUserAdminRoleState
	@IndexField
	private int state;
	// MUserAdminPermEnum 权限集合
	private Set<Integer> permSet = new HashSet<Integer>();

	private long createdTime;
	private long lastUpdateTime;
	private long ver;

	public static MUserAdminRole newInstance() {
		MUserAdminRole role = new MUserAdminRole();
		role.state = MUserAdminRoleState.Available.ordinal();

		long curTime = System.currentTimeMillis();
		role.createdTime = curTime;
		role.lastUpdateTime = curTime;
		return role;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public MUserAdminRoleState getStateEnum() {
		return MUserAdminRoleState.valueOf(this.state);
	}

	public void setStateEnum(MUserAdminRoleState state) {
		this.state = state.ordinal();
	}

	public Set<Integer> getPermSet() {
		return permSet;
	}

	public void setPermSet(Set<Integer> permSet) {
		this.permSet = permSet;
	}

	public MUserAdminRole addPerm(MUserAdminPermEnum permEnum) {
		this.permSet.add(permEnum.ordinal());
		return this;
	}

	// 获取权限字符串集合 如：mng:*:*:*
	public Set<String> getPermSet4Shiro() {
		Set<String> permSetTmp = new HashSet<String>();
		for (int adminPermOrdinalTmp : permSet) {
			MUserAdminPermEnum adminPermTmp = MUserAdminPermEnum
					.valueOf(adminPermOrdinalTmp);
			permSetTmp.add(adminPermTmp.getPerm());
		}
		return permSetTmp;
	}

	public String getRole4Shiro() {
		return this.name;
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
