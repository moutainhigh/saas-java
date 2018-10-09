package com.hq.storeMS.service.opuser.data.adminRole;

import java.util.HashSet;
import java.util.Set;

import com.hq.storeMS.service.opuser.data.OPUser;
import com.hq.storeMS.service.saas.bs.SaasMgr;

public class OPRolePermInfo {

	private long opId;
	
	private Set<OPAdminRole> roleSet = new HashSet<OPAdminRole>();
	
	public long getOpId() {
		return opId;
	}

	public static OPRolePermInfo newInstance(OPUser opuser){
		
		Set<OPAdminRole> roleSetP = SaasMgr.getInstance().getOPAdminRoleSet(opuser.getOpAdminRoleSet());
		
		OPRolePermInfo target = new OPRolePermInfo();
		target.opId = opuser.getId();
		target.roleSet = roleSetP;
		return target;
	}
	
	public Set<String> getRoleSet(){
		Set<String> rolesetTmp = new HashSet<String>();
		for (OPAdminRole roleTmp : roleSet) {
			if(roleTmp.getStateEnum() == OPAdminRoleState.Available){
				rolesetTmp.add(roleTmp.getRole4Shiro());
			}
		}
		return rolesetTmp;
	}
	
	public Set<String> getPermSet(){
		Set<String> permSetTmp = new HashSet<String>();
		for (OPAdminRole roleTmp : roleSet) {
			if(roleTmp.getStateEnum() == OPAdminRoleState.Available){
				permSetTmp.addAll(roleTmp.getPermSet4Shiro());
			}
		}
		return permSetTmp;
	}
	
}
