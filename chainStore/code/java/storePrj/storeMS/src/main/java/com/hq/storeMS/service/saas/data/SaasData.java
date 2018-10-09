package com.hq.storeMS.service.saas.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.service.opuser.data.adminRole.OPAdminRole;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "saas")
public class SaasData {

	@Id
	private long id;

	private long lastUpdateTime;

	private int nextOPAdminRoleId;

	private Map<Integer, OPAdminRole> opAdminRoleMap = new HashMap<Integer, OPAdminRole>();

	private long ver;

	public static SaasData newInstance() {
		SaasData data = new SaasData();
		data.id = 1;
		data.lastUpdateTime = System.currentTimeMillis();
		data.ver = 1;
		data.nextOPAdminRoleId = 1;
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public OPAdminRole getSuperOPAdminRole() {
		final int superRoleId = 1;
		return opAdminRoleMap.get(superRoleId);
	}

	public void addOPAdminRole(OPAdminRole role) {
		if (role.getId() == this.nextOPAdminRoleId) {
			opAdminRoleMap.put(role.getId(), role);
			this.nextOPAdminRoleId = this.nextOPAdminRoleId + 1;
		}
	}

	public void updateOPAdminRole(OPAdminRole role) {
		OPAdminRole opAdminRole = opAdminRoleMap.get(role.getId());
		if (opAdminRole != null) {
			opAdminRoleMap.put(role.getId(), role);
		}
	}

	public OPAdminRole getOPAdminRole(int roleId) {
		return opAdminRoleMap.get(roleId);
	}

	public List<OPAdminRole> listOPAdminRole() {
		List<OPAdminRole> rolelist = new ArrayList<OPAdminRole>();
		rolelist.addAll(opAdminRoleMap.values());
		return rolelist;
	}

	public Map<Integer, OPAdminRole> getOpAdminRoleMap() {
		return opAdminRoleMap;
	}

	public void setOpAdminRoleMap(Map<Integer, OPAdminRole> opAdminRoleMap) {
		this.opAdminRoleMap = opAdminRoleMap;
	}

	public int getNextOPAdminRoleId() {
		return nextOPAdminRoleId;
	}

	public void setNextOPAdminRoleId(int nextOPAdminRoleId) {
		this.nextOPAdminRoleId = nextOPAdminRoleId;
	}

	public Set<OPAdminRole> getOPAdminRoleSet(Set<Integer> roleIdSet) {
		Set<OPAdminRole> roleSet = new HashSet<OPAdminRole>();
		for (Integer roleIdTmp : roleIdSet) {
			OPAdminRole roleTmp = opAdminRoleMap.get(roleIdTmp);
			if (roleTmp != null) {
				roleSet.add(roleTmp);
			}
		}
		return roleSet;
	}

}
