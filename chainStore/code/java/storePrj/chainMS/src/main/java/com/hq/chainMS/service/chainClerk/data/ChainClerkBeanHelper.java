package com.hq.chainMS.service.chainClerk.data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.hq.chainMS.service.chainClerk.data.adminRole.AdminPermEnum;
import com.hq.chainMS.service.chainClerk.data.adminRole.AdminRole;
import com.hq.chainMS.service.common.EntityState;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainClerkBeanHelper {

	public static ChainClerkBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ChainClerkBeanHelper.class);
	}

	/*************************************初始化*************************************/
	// 新开的连锁店 初始化连锁店的员工信息
	public void init(ChainClerk chainClerk, long bossId) {
		// 初始化老板角色
		int roleIndex = chainClerk.getRoleIdIndex() + 1;
		AdminRole bossRole = newBossRole(chainClerk.getChainId(), roleIndex);
		addAdminRole(chainClerk, bossRole);

		// 初始化店长角色 添加会员管理、预约管理、订单管理权限
		AdminRole adminRole = newStoreManagerRole(chainClerk.getChainId(), roleIndex + 1);
		addAdminRole(chainClerk, adminRole);

		// 添加老板为员工、赋予老板权限
		ClerkInfo clerkInfo = ClerkInfo.newInstance(bossId);
		addClerkInfo(chainClerk, clerkInfo);
		addRole2Clerk(chainClerk, bossId, bossRole);
	}

	private AdminRole newBossRole(long chainId, int roleIndex) {
		AdminRole bossRole = AdminRole.newInstance(roleIndex);
		bossRole.setDescript("boss");
		bossRole.setName("老板");
		bossRole.setChainId(chainId);

		Set<Integer> permSet = new HashSet<Integer>();
		permSet.add(AdminPermEnum.BOSS.ordinal());
		bossRole.setPermSet(permSet);
		return bossRole;
	}

	private AdminRole newStoreManagerRole(long chainId, int roleIndex) {
		AdminRole storeManagerRole = AdminRole.newInstance(roleIndex);
		storeManagerRole.setDescript("店长角色");
		storeManagerRole.setName("店长");
		storeManagerRole.setChainId(chainId);

		Set<Integer> permSet = new HashSet<Integer>();
		permSet.add(AdminPermEnum.CHAIN_CLERK_ADMIN.ordinal());
		permSet.add(AdminPermEnum.CHAIN_ADMIN.ordinal());
		permSet.add(AdminPermEnum.SELL_PRODUCT_ADMIN.ordinal());
		permSet.add(AdminPermEnum.CARD_ADMIN.ordinal());
		storeManagerRole.setPermSet(permSet);
		return storeManagerRole;
	}

	/*************************************连锁店岗位的操作*************************************/
	// 添加店铺岗位[角色]
	public boolean addAdminRole(ChainClerk chainClerk, AdminRole role) {
		Map<Integer, AdminRole> roleMap = chainClerk.getRoleMap();
		if (!roleMap.containsKey(role.getId()) && role.getId() == chainClerk.getRoleIdIndex() + 1) {
			roleMap.put(role.getId(), role);
			chainClerk.setRoleIdIndex(role.getId());
			return true;
		}
		return false;
	}

	// 更新店铺岗位[角色]
	public boolean updateAdminRole(ChainClerk chainClerk, AdminRole role) {
		Map<Integer, AdminRole> roleMap = chainClerk.getRoleMap();
		if (roleMap.containsKey(role.getId())) {
			role.setLastUpdateTime(System.currentTimeMillis());
			roleMap.put(role.getId(), role);
			return true;
		}
		return false;
	}

	// 删除店铺岗位[角色]
	public boolean removeAdminRole(ChainClerk chainClerk, int roleId) {
		Map<Integer, AdminRole> roleMap = chainClerk.getRoleMap();
		if (roleMap.containsKey(roleId)) {
			roleMap.remove(roleId);
			return true;
		}
		return false;
	}

	/*************************************连锁店员工的操作*************************************/
	// 添加连锁店员工
	public boolean addClerkInfo(ChainClerk chainClerk, ClerkInfo clerkInfo) {
		boolean success = false;
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		long userId = clerkInfo.getUserId();
		if (!clerkInfoMap.containsKey(userId)) {
			clerkInfoMap.put(userId, clerkInfo);
			success = true;
		} else {
			ClerkInfo clerk = clerkInfoMap.get(userId);
			if (clerk.getEntityState() == EntityState.Deleted.ordinal()) {
				clerk.setEntityState(EntityState.Normal.ordinal());
				success = true;
			}
		}
		return success;
	}

	// 更新连锁店员工
	public boolean updateClerkInfo(ChainClerk chainClerk, ClerkInfo clerkInfo) {
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		if (clerkInfoMap.containsKey(clerkInfo.getUserId())) {
			clerkInfoMap.put(clerkInfo.getUserId(), clerkInfo);
			return true;
		}
		return false;
	}
	
	// 删除连锁店员工
	public boolean removeClerk(ChainClerk chainClerk, long userId) {
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		if (clerkInfoMap.containsKey(userId)) {
			ClerkInfo clerkInfo = clerkInfoMap.get(userId);
			clerkInfo.setEntityState(EntityState.Deleted.ordinal());
			return true;
		}
		return false;
	}

	/*************************************员工岗位的操作*************************************/
	//设置员工的岗位
	public boolean addRoleSet2Clerk(ChainClerk chainClerk, long userId, Set<Integer> roleIdSet) {
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		if (clerkInfoMap.containsKey(userId)) {
			ClerkInfo clerkInfo = clerkInfoMap.get(userId);
			clerkInfo.setRoleSet(roleIdSet);
			return true;
		}
		return false;
	}

	//移除员工的岗位
	public boolean removeRoleOfClerk(ChainClerk chainClerk, long userId, AdminRole role) {
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		if (clerkInfoMap.containsKey(userId)) {
			ClerkInfo clerkInfo = clerkInfoMap.get(userId);
			clerkInfo.removeRole(role.getId());
			return true;
		}
		return false;
	}

	// 给员工添加角色
	public boolean addRole2Clerk(ChainClerk chainClerk, long userId, AdminRole role) {
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		if (clerkInfoMap.containsKey(userId)) {
			ClerkInfo clerkInfo = clerkInfoMap.get(userId);
			clerkInfo.addRole(role.getId());
			return true;
		}
		return false;
	}
}
