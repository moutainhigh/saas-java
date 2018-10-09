package com.hq.storeMS.service.storeClerkInfo.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreRolePermInfo;
import com.zenmind.dataSyn.annotation.SynClass;

@SynClass
@Table(name = "storeClerkInfo")
public class StoreClerkInfo {

	@Id	
	private String id;
	
	private long storeId;
	
	private int roleIdIndex = 1;
	//管理员角色权限定义
	private Map<Integer,StoreAdminRole> roleMap = new HashMap<Integer,StoreAdminRole>();
	
	private Map<Long,ClerkInfo> clerkInfoMap = new HashMap<Long,ClerkInfo>();
	
	private Map<Long,ApplyClerkInfo> applyClerkInfoMap = new HashMap<Long,ApplyClerkInfo>();
	
	//对应店铺工资月结天数
	private int monthPayDays = 22;
	
	private long createdTime;
	
	private long lastUpdateTime;
	
	private long ver;
	
	public static String getIdByStoreId(long storeId){
		return ServerConstants.STORE_CLERKINFO_ID_SUFFFIX + storeId;
	}
	
	public static StoreClerkInfo newInstance(long storeId,long bossId){
		StoreClerkInfo storeClerkInfo = new StoreClerkInfo();
		storeClerkInfo.id = getIdByStoreId(storeId);
		storeClerkInfo.storeId = storeId;
		long curTime = System.currentTimeMillis();
		
		storeClerkInfo.createdTime = curTime;
		storeClerkInfo.lastUpdateTime = curTime;
		StoreClerkInfoBeanHelper.getInstance().init(storeClerkInfo, bossId);
		return storeClerkInfo;
	}
	
	//获取用户的权限列表信息
	public Set<Integer> takeBUserPermission(long buserId){
		Set<Integer> result = new HashSet<Integer>();
		ClerkInfo clerkInfo = clerkInfoMap.get(buserId);//获取用户
		Set<Integer> roleSet = clerkInfo.getRoleSet();//用户角色
		for (Integer roleIndex : roleSet) {//遍历角色
			StoreAdminRole storeAdminRole = roleMap.get(roleIndex);//角色的详情
			result.addAll(storeAdminRole.getPermSet());//每个角色对应的权限
		}
		return result;
	}
	
	public StoreAdminRole getRole(int roleId) {
		return this.roleMap.get(roleId);
	}
	
	public StoreRolePermInfo getRolePermInfo(Long bUserId){
		return StoreClerkInfoBeanHelper.getInstance().getRolePermInfo(storeId, clerkInfoMap, roleMap, bUserId);
	}
	
	public boolean addStoreAdminRole(StoreAdminRole role){
		if(!roleMap.containsKey(role.getId()) && role.getId() == roleIdIndex+1 ){
			roleMap.put(role.getId(), role);
			roleIdIndex = roleIdIndex+1;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean updateStoreAdminRole(StoreAdminRole role){
		return StoreClerkInfoBeanHelper.getInstance().updateStoreAdminRole(roleMap, role);
	}
	public boolean applyClerkInfo(ApplyClerkInfo applyClerkInfo){
		return StoreClerkInfoBeanHelper.getInstance().applyClerkInfo(applyClerkInfoMap, applyClerkInfo);
	}
	
	public ApplyClerkInfo handleApplyClerkInfo(long buserId, boolean approved){
		return StoreClerkInfoBeanHelper.getInstance().handleApplyClerkInfo(applyClerkInfoMap,buserId,approved);
	}
	
	
	public boolean addClerkInfo(ClerkInfo clerkInfo){
		return StoreClerkInfoBeanHelper.getInstance().addClerkInfo(clerkInfoMap, clerkInfo);
	}
	
	public boolean updateClerkInfo(ClerkInfo clerkInfo){
		return StoreClerkInfoBeanHelper.getInstance().updateClerkInfo(clerkInfoMap, clerkInfo);
	}
	
	public void addRole2Clerk(long clerkBUserId, StoreAdminRole role){
		StoreClerkInfoBeanHelper.getInstance().addRole2Clerk(clerkInfoMap, clerkBUserId, role);
	}
	
	public boolean addRoleSet2Clerk(long clerkBUserId, Set<Integer> roleIdSet){
		return StoreClerkInfoBeanHelper.getInstance().addRoleSet2Clerk(clerkInfoMap, clerkBUserId, roleIdSet);
	}
	
	public boolean removeRoleOfClerk(long clerkBUserId, StoreAdminRole role){
		return StoreClerkInfoBeanHelper.getInstance().removeRoleOfClerk(clerkInfoMap, clerkBUserId, role);
	}
	
	public boolean removeClerk(long clerkBUserId){
		return StoreClerkInfoBeanHelper.getInstance().removeClerk(clerkInfoMap, clerkBUserId);
	}
	
	public void removeApplyClerk(long clerkBUserId){
		StoreClerkInfoBeanHelper.getInstance().removeApplyClerk(applyClerkInfoMap, clerkBUserId);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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
	
	public void incrVer() {
		this.ver = ver+1;
	}
	
	public Map<Long, ClerkInfo> getClerkInfoMap() {
		return clerkInfoMap;
	}

	public void setClerkInfoMap(Map<Long, ClerkInfo> clerkInfoMap) {
		this.clerkInfoMap = clerkInfoMap;
	}

	public int getRoleIdIndex() {
		return roleIdIndex;
	}

	public void setRoleIdIndex(int roleIdIndex) {
		this.roleIdIndex = roleIdIndex;
	}

	public Map<Integer, StoreAdminRole> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<Integer, StoreAdminRole> roleMap) {
		this.roleMap = roleMap;
	}

	public Map<Long, ApplyClerkInfo> getApplyClerkInfoMap() {
		return applyClerkInfoMap;
	}

	public void setApplyClerkInfoMap(Map<Long, ApplyClerkInfo> applyClerkInfoMap) {
		this.applyClerkInfoMap = applyClerkInfoMap;
	}

	public int getMonthPayDays() {
		return monthPayDays;
	}

	public void setMonthPayDays(int monthPayDays) {
		this.monthPayDays = monthPayDays;
	}
}
