package com.hq.chainStore.service.storeClerkInfo.bs;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.store.apiData.StoreAddApiForm;
import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeClerkInfo.data.ApplyClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.ApplyState;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRoleState;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.Clerk;
import com.hq.testClass.robot.buser.robot.BRobot;

public class StoreClerkInfoMgrTest {

	private static Boss boss;
	
	private static String storeName;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		boss = Boss.newBoss(BRobot.newRandom());
		boss.reg();
		boss.login();
		
		StoreAddApiForm formInfo = StoreAddApiForm.newInstance();
		long bossId = boss.getId();
		String storeName = "store_"+bossId;
		formInfo.setBossId(bossId).setName(storeName);
		boss.openStore(formInfo);
		Store store = boss.getStore();
		storeName = store.getName();
	}

	@Test
	public void testAddClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		boolean b = StoreClerkInfoMgr.getInstance().addClerk(boss.getStoreId(), 40L);
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertTrue("新增店铺员工",b);
	}
	
	@Test
	public void testGet() {
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		Store store = StoreMgr.getInstance().get(boss.getStoreId());
		String clerkInfoId = store.getClerkInfoId();
		
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().get(clerkInfoId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		Assert.assertNotNull("storeClerkInfo 应该非空", storeClerkInfo);
	}
	
	@Test
	public void applyClerkInfo() {
		Clerk clerk = Clerk.newClerk(BRobot.newRandom());
		clerk.reg();
		clerk.login();
		
		List<Store> storeList = clerk.findStoreByName(storeName);
		Store store = storeList.get(0);
		
		clerk.applyAsStoreClerk(store.getId(), store.getClerkInfoId());
		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		ApplyClerkInfo applyClerkInfo = storeClerkInfo.getApplyClerkInfoMap().get(clerk.getId());
		
		Assert.assertNotNull("applyClerkInfo 应该存在",applyClerkInfo);
		Assert.assertNotNull("applyClerkInfo 状态应该是pending",applyClerkInfo.getStateEnum() == ApplyState.Pending);
		
	}
	
	@Test
	public void handleApplyClerkInfo() {
		Clerk clerk = Clerk.newClerk(BRobot.newRandom());
		clerk.reg();
		clerk.login();
		
		List<Store> storeList = clerk.findStoreByName(storeName);
		Store store = storeList.get(0);
		
		clerk.applyAsStoreClerk(store.getId(), store.getClerkInfoId());
		boolean approved = true;
		boss.handleApplyClerk(store.getId(), store.getClerkInfoId(), clerk.getId(), approved );
		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		ApplyClerkInfo applyClerkInfo = storeClerkInfo.getApplyClerkInfoMap().get(clerk.getId());
		Assert.assertTrue("applyClerkInfo 状态应该是 approved", applyClerkInfo.getStateEnum() == ApplyState.Approved);
		ClerkInfo clerkInfo = storeClerkInfo.getClerkInfoMap().get(clerk.getId());
		Assert.assertNotNull("clerkInfo 应该存在", clerkInfo);
		
	}
//	@Test
	public void testAddClerkInfo() {
		ClerkInfo clerkInfo = addClerk();
		Assert.assertNotNull("clerkInfo 应该存在", clerkInfo);
	}



	private ClerkInfo addClerk() {
		Clerk clerk = Clerk.newClerk(BRobot.newRandom());
		clerk.reg();
		clerk.login();
		
		boss.addClerk(clerk.getId());
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		
		ClerkInfo clerkInfo = storeClerkInfo.getClerkInfoMap().get(clerk.getId());
		return clerkInfo;
	}

	@Test
	public void testAddStoreAdminRole() {
		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		int roleIdIndex = storeClerkInfo.getRoleIdIndex()+1;
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		StoreAdminRoleInfo4Add addStoreAdminRoleInfo = StoreAdminRoleInfo4Add.newInstance()
																.setStoreId(storeClerkInfo.getStoreId())
																.setName("店员")
																.setRoleIdIndex(roleIdIndex)
																.setStoreClerkInfoId(storeClerkInfo.getId())
																.addPermSet(StoreAdminPermEnum.LEAGUER_ADMIN)
																.setDescript("店员管理会员")
																;
		StoreClerkInfoMgr.getInstance().addStoreAdminRole(addStoreAdminRoleInfo );
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		StoreClerkInfo storeClerkInfoFresh = storeClerkInfo = boss.getStoreClerkInfo();
		Map<Integer, StoreAdminRole> roleMap = storeClerkInfoFresh.getRoleMap();
		int roleIdIndexNew = storeClerkInfoFresh.getRoleIdIndex();
		Assert.assertTrue(roleMap.get(roleIdIndexNew).getName().equals("店员"));
	
		//updateStoreAdminRole
		testUpdateStoreAdminRole(roleIdIndex);
		
		ClerkInfo clerkInfo = addClerk();

		//addRole2Clerk(); 给员工添加单个角色
		testAddRole2Clerk(clerkInfo, roleIdIndex);
		
		//addRole2Clerk() 给员工添加多个角色
		Set<Integer> roleIdSet = new HashSet<Integer>();
		roleIdSet.add(roleIdIndex);
		testAddRoleSet2Clerk(clerkInfo, roleIdSet);
		
		//reomveRoleOfClerk
		testReomveRoleOfClerk(clerkInfo, roleIdIndex);
		
		//reomveClerk
		testReomveClerk(clerkInfo);
		
	}

	public void testUpdateStoreAdminRole(int roleIndex) {
		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		StoreAdminRole storeAdminRole = storeClerkInfo.getRoleMap().get(roleIndex);
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		StoreAdminRoleInfo4Update updateRoleInfo = StoreAdminRoleInfo4Update.newInstance()
																	.setStoreId(storeClerkInfo.getStoreId())
																	.setName("店员")
																	.setRoleIdIndex(storeAdminRole.getId())
																	.setStoreClerkInfoId(storeClerkInfo.getId())
																	.addPermSet(StoreAdminPermEnum.LEAGUER_ADMIN)
																	.setState(StoreAdminRoleState.Invalide)
																	.setDescript("店员只能管理会员")
																	;
		StoreClerkInfoMgr.getInstance().updateStoreAdminRole(updateRoleInfo );
	
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		StoreClerkInfo storeClerkInfoFresh = boss.getStoreClerkInfo();
		check(updateRoleInfo,storeClerkInfoFresh);
		
	}

	
	private void check(StoreAdminRoleInfo4Update updateRoleInfo, StoreClerkInfo storeClerkInfoFresh) {
		
		StoreAdminRole storeAdminRole = storeClerkInfoFresh.getRoleMap().get(updateRoleInfo.getRoleIdIndex());
		Assert.assertEquals(updateRoleInfo.getName(), storeAdminRole.getName());
		Assert.assertEquals(updateRoleInfo.getDescript(), storeAdminRole.getDescript());
		
	}



	public void testAddRole2Clerk(ClerkInfo clerkInfo, int roleId) {

		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		Set<Integer> roleIdSet = new HashSet<Integer>();
		roleIdSet.add(roleId);
		StoreClerkInfoMgr.getInstance().addRoleSet2Clerk(storeClerkInfo.getStoreId(), storeClerkInfo.getId(), clerkInfo.getBuserId(), roleIdSet);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		

		StoreClerkInfo storeClerkInfoFresh = boss.getStoreClerkInfo();
		ClerkInfo clerkInfoFresh = storeClerkInfoFresh.getClerkInfoMap().get(clerkInfo.getBuserId());
		Set<Integer> clerkRoleSet = clerkInfoFresh.getRoleSet();
		Assert.assertTrue("员工应该包含刚刚赋予的权限", clerkRoleSet.contains(roleId));

	}
	
	public void testAddRoleSet2Clerk(ClerkInfo clerkInfo, Set<Integer> roleIdSet) {

		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		StoreClerkInfoMgr.getInstance().addRoleSet2Clerk(storeClerkInfo.getStoreId(), storeClerkInfo.getId(), clerkInfo.getBuserId(), roleIdSet);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		

		StoreClerkInfo storeClerkInfoFresh = boss.getStoreClerkInfo();
		ClerkInfo clerkInfoFresh = storeClerkInfoFresh.getClerkInfoMap().get(clerkInfo.getBuserId());
		Set<Integer> clerkRoleSet = clerkInfoFresh.getRoleSet();
		Assert.assertTrue("员工应该包含刚刚赋予的权限", clerkRoleSet.equals(roleIdSet));

	}

	
	public void testReomveRoleOfClerk(ClerkInfo clerkInfo, int roleId) {
		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		StoreClerkInfoMgr.getInstance().reomveRoleOfClerk(storeClerkInfo.getStoreId(), storeClerkInfo.getId(), clerkInfo.getBuserId(), roleId);
		
		AccessTokenMgr.getInstance().removeOpIdTL();

		StoreClerkInfo storeClerkInfoFresh = boss.getStoreClerkInfo();
		ClerkInfo clerkInfoFresh = storeClerkInfoFresh.getClerkInfoMap().get(clerkInfo.getBuserId());
		Set<Integer> clerkRoleSet = clerkInfoFresh.getRoleSet();
		Assert.assertFalse("员工应该移除刚刚赋予的权限", clerkRoleSet.contains(roleId));
	}
	
	public void testReomveClerk(ClerkInfo clerkInfo) {
		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		
		StoreClerkInfoMgr.getInstance().reomveClerk(storeClerkInfo.getStoreId(), storeClerkInfo.getId(), clerkInfo.getBuserId());
		
		AccessTokenMgr.getInstance().removeOpIdTL();

		StoreClerkInfo storeClerkInfoFresh = boss.getStoreClerkInfo();
		ClerkInfo clerkInfoFresh = storeClerkInfoFresh.getClerkInfoMap().get(clerkInfo.getBuserId());
		Assert.assertNotNull("员工应该为空", clerkInfoFresh == null);
	}
	
	@Test
	public void setMonthPayDays() {

		int monthPayDays = RandomUtils.nextInt(15, 22);
		
		StoreClerkInfo storeClerkInfo = boss.getStoreClerkInfo();

		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());

		StoreClerkInfoMgr.getInstance().setMonthPayDays(storeClerkInfo.getStoreId(), storeClerkInfo.getId(), monthPayDays);

		AccessTokenMgr.getInstance().removeOpIdTL();

		StoreClerkInfo storeClerkInfoFresh = boss.getStoreClerkInfo();
		
		Assert.assertTrue("月结天数应该不为 0", storeClerkInfoFresh.getMonthPayDays()>0);
	}

}
