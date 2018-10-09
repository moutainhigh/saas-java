package com.hq.storeMS.service.storeClerkInfo.data.adminRole;

import java.util.HashSet;
import java.util.Set;

import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
public class StoreRolePermInfo {

	private long buserId;
	
	private long storeId;
	
	private Set<StoreAdminRole> roleSet = new HashSet<StoreAdminRole>();
	
	public long getBuserId() {
		return buserId;
	}

	public static StoreRolePermInfo newInstance(Long buserIdP,Set<StoreAdminRole> roleSet, Long storeId){
		StoreRolePermInfo target = new StoreRolePermInfo();
		target.buserId = buserIdP;
		target.roleSet = roleSet;
		target.storeId = storeId;
		return target;
	}
	
	public Set<String> getRoleSet(){
		Set<String> rolesetTmp = new HashSet<String>();
		for (StoreAdminRole roleTmp : roleSet) {
			if(roleTmp.getStateEnum() == StoreAdminRoleState.Available){
				rolesetTmp.add(roleTmp.getRole(this.storeId));
			}
		}
		return rolesetTmp;
	}
	
	public Set<String> getPermSet(){
		Set<String> permSetTmp = new HashSet<String>();
		for (StoreAdminRole roleTmp : roleSet) {
			if(roleTmp.getStateEnum() == StoreAdminRoleState.Available){
				permSetTmp.addAll(roleTmp.getPermSet(this.storeId));
			}
		}
		return permSetTmp;
	}
	
	public Set<Integer> getBuserPermSet(){
		Set<Integer> permSet = new HashSet<Integer>();
		for (StoreAdminRole roleTmp : roleSet) {
			if(roleTmp.getStateEnum() == StoreAdminRoleState.Available){
				permSet.addAll(roleTmp.getPermSet());
			}
		}
		return permSet;
	}
	
}
