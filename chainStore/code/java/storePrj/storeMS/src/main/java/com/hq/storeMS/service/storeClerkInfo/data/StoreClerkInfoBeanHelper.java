package com.hq.storeMS.service.storeClerkInfo.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.storeMS.service.common.EntityState;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreRolePermInfo;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreClerkInfoBeanHelper {

	public static StoreClerkInfoBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(StoreClerkInfoBeanHelper.class);
	}
	
	private StoreAdminRole newBossRole(long storeId, int roleIndex) {
		StoreAdminRole bossRole = StoreAdminRole.newInstance(roleIndex);
		bossRole.setDescript("boss");
		bossRole.setName("老板");
		bossRole.setStoreId(storeId);
		
		Set<Integer> permSet = new HashSet<Integer>();
		permSet.add(StoreAdminPermEnum.BOSS.ordinal());
		bossRole.setPermSet(permSet);
		return bossRole;
	}
	
	private StoreAdminRole newStoreManagerRole(long storeId, int roleIndex) {
		StoreAdminRole storeManagerRole = StoreAdminRole.newInstance(roleIndex);
		storeManagerRole.setDescript("店长角色");
		storeManagerRole.setName("店长");
		storeManagerRole.setStoreId(storeId);
		
		Set<Integer> permSet = new HashSet<Integer>();
		StoreAdminPermEnum[] values = StoreAdminPermEnum.values();
		//店长默认有所有权限
		for (StoreAdminPermEnum storeAdminPermEnum : values) {
			if(storeAdminPermEnum==StoreAdminPermEnum.BOSS) {
				continue;
			}
			permSet.add(storeAdminPermEnum.ordinal());
		}
//		permSet.add(StoreAdminPermEnum.APPOINTMENT_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.CLERK_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.PURCHASE_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.ARREARAGE_ADMIN.ordinal());
//		
//		permSet.add(StoreAdminPermEnum.GOODS_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.LEAGUER_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.RECHARGE_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.REPORT_ADMIN.ordinal());
//		
//		permSet.add(StoreAdminPermEnum.CARD_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.ORDER_ADMIN.ordinal());
		//店铺初始化的时候 老板角色尚未确定 故不能给仪器管理的权限  --add by kevin 2018-6-8
//		permSet.add(StoreAdminPermEnum.DEVICE_ADMIN.ordinal());
//		permSet.add(StoreAdminPermEnum.PHONE_ADMIN.ordinal());
		storeManagerRole.setPermSet(permSet);
		return storeManagerRole;
	}
	
	private StoreAdminRole newClerkRole(long storeId, int roleIndex) {
		StoreAdminRole clerkRole = StoreAdminRole.newInstance(roleIndex);
		clerkRole.setDescript("店员角色");
		clerkRole.setName("店员");
		clerkRole.setStoreId(storeId);
		
		Set<Integer> permSet = new HashSet<Integer>();
		permSet.add(StoreAdminPermEnum.PURCHASE_ADMIN.ordinal());
		permSet.add(StoreAdminPermEnum.RECHARGE_ADMIN.ordinal());
		clerkRole.setPermSet(permSet);
		return clerkRole;
	}
	
	public void init(StoreClerkInfo storeClerkInfo, long bossId){
		//初始化老板角色  
		int roleIndex = storeClerkInfo.getRoleIdIndex()+1;
		StoreAdminRole bossRole = newBossRole(storeClerkInfo.getStoreId(), roleIndex);
		storeClerkInfo.addStoreAdminRole(bossRole);
		
		//初始化店长角色 添加会员管理、预约管理、订单管理权限
		StoreAdminRole storeAdminRole = newStoreManagerRole(storeClerkInfo.getStoreId(), roleIndex+1);
		storeClerkInfo.addStoreAdminRole(storeAdminRole);
		
		//初始化店员角色 添加会员管理权限
		StoreAdminRole clerkRole = newClerkRole(storeClerkInfo.getStoreId(), roleIndex+2);
		storeClerkInfo.addStoreAdminRole(clerkRole);
		
		//添加老板为员工、赋予老板权限
		ClerkInfo clerkInfo = ClerkInfo.newInstance(bossId);
		storeClerkInfo.addClerkInfo(clerkInfo);
		storeClerkInfo.addRole2Clerk(bossId, bossRole);
	}

	
	public StoreRolePermInfo getRolePermInfo(long storeId, Map<Long,ClerkInfo> clerkInfoMap, Map<Integer,StoreAdminRole> roleMap, long bUserId){
		ClerkInfo clerkInfo = clerkInfoMap.get(bUserId);
		StoreRolePermInfo rolePermInfo = null;
		if(clerkInfo!=null){
			Set<StoreAdminRole> roleSet = getRoleSet(roleMap, clerkInfo.getRoleSet());
			rolePermInfo = StoreRolePermInfo.newInstance(bUserId, roleSet,storeId);
		}
		return rolePermInfo;
	}
	
	public Set<StoreAdminRole> getRoleSet(Map<Integer,StoreAdminRole> roleMap, Set<Integer> roleIdSet){
		Set<StoreAdminRole> roleSet = new HashSet<StoreAdminRole>();
		for (Integer roleIdTmp : roleIdSet) {
			StoreAdminRole roleTmp = roleMap.get(roleIdTmp);
			roleSet.add(roleTmp);
		}
		return roleSet;
	}
	
	public boolean updateStoreAdminRole(Map<Integer,StoreAdminRole> roleMap, StoreAdminRole role){
		if(roleMap.containsKey(role.getId())){
			role.setLastUpdateTime(System.currentTimeMillis());
			roleMap.put(role.getId(), role);
			return true;
		}
		return false;
	}
	
	public boolean removeStoreAdminRole(Map<Integer,StoreAdminRole> roleMap, int roleId){
		if(roleMap.containsKey(roleId)){
			roleMap.remove(roleId);
			return true;
		}
		return false;
	}
	
	public boolean applyClerkInfo(Map<Long,ApplyClerkInfo> applyClerkInfoMap, ApplyClerkInfo applyClerkInfo){
		if(!applyClerkInfoMap.containsKey(applyClerkInfo.getBuserId())){
			applyClerkInfoMap.put(applyClerkInfo.getBuserId(), applyClerkInfo);
			return true;
		}else{
			return false;
		}
	}

	public ApplyClerkInfo handleApplyClerkInfo(Map<Long, ApplyClerkInfo> applyClerkInfoMap, long buserId, boolean approved) {
		ApplyClerkInfo applyClerkInfo = applyClerkInfoMap.get(buserId);
		if(applyClerkInfo!=null){
			if(approved){
				applyClerkInfo.setStateEnum(ApplyState.Approved);
			}else{
				applyClerkInfo.setStateEnum(ApplyState.Rejected);
			}
			applyClerkInfoMap.remove(buserId);
		}
		return applyClerkInfo;
	}
	
	public boolean addClerkInfo(Map<Long,ClerkInfo> clerkInfoMap, ClerkInfo clerkInfo){
		boolean success = false;
		long buserId = clerkInfo.getBuserId();
		if(!clerkInfoMap.containsKey(buserId)){
			clerkInfoMap.put(buserId, clerkInfo);
			success = true;
		}else {
			ClerkInfo clerk = clerkInfoMap.get(buserId);
			//客户、员工删除后新增按恢复处理（改变状态，不做新增）  --add by kevin 2018-6-15
			if(clerk.getEntityState() == EntityState.Deleted.ordinal()) {
				clerk.setEntityState(EntityState.Normal.ordinal());
				clerkInfoMap.put(buserId, clerk);//如果已存在并且为删除状态，则覆盖添加员工
				success = true;
			}
		}
		return success;
	}
	
	public boolean updateClerkInfo(Map<Long,ClerkInfo> clerkInfoMap, ClerkInfo clerkInfo){
		if(clerkInfoMap.containsKey(clerkInfo.getBuserId())){
			clerkInfoMap.put(clerkInfo.getBuserId(), clerkInfo);
			return true;
		}else{
			return false;
		}
	}
	
	public void addRole2Clerk(Map<Long,ClerkInfo> clerkInfoMap, long clerkBUserId, StoreAdminRole role){
		ClerkInfo clerkInfo = clerkInfoMap.get(clerkBUserId);
		if(clerkInfo!=null && role!=null){
			clerkInfo.addRole(role.getId());
		}
	}
	
	public boolean addRoleSet2Clerk(Map<Long,ClerkInfo> clerkInfoMap, long clerkBUserId, Set<Integer> roleIdSet){
		ClerkInfo clerkInfo = clerkInfoMap.get(clerkBUserId);
		if(clerkInfo!=null){
			clerkInfo.setRoleSet(roleIdSet);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeRoleOfClerk(Map<Long,ClerkInfo> clerkInfoMap, long clerkBUserId, StoreAdminRole role){
		ClerkInfo clerkInfo = clerkInfoMap.get(clerkBUserId);
		if(clerkInfo!=null && role!=null){
			clerkInfo.removeRole(role.getId());
			return true;
		}else{
			return false;
		}
	}
	
	public boolean removeClerk(Map<Long,ClerkInfo> clerkInfoMap, long clerkBUserId){
		ClerkInfo clerkInfo = clerkInfoMap.get(clerkBUserId);
		if(clerkInfo!=null){
//			clerkInfoMap.remove(clerkBUserId);
			clerkInfo.setEntityState(EntityState.Deleted.ordinal());//状态标记删除
			return true;
		}else{
			return false;
		}
	}
	
	public void removeApplyClerk(Map<Long,ApplyClerkInfo> applyClerkInfoMap, long clerkBUserId){
		ApplyClerkInfo applyClerkInfo = applyClerkInfoMap.get(clerkBUserId);
		if(applyClerkInfo!=null){
			applyClerkInfoMap.remove(clerkBUserId);
		}
	}
	
	public boolean addStoreAdminRole(StoreClerkInfo storeClerkInfo, StoreAdminRole role){
		if(!storeClerkInfo.getRoleMap().containsKey(role.getId()) && role.getId() == storeClerkInfo.getRoleIdIndex() + 1 ){
			storeClerkInfo.getRoleMap().put(role.getId(), role);
			storeClerkInfo.setRoleIdIndex(role.getId());
			return true;
		}else{
			return false;
		}
	}

}
