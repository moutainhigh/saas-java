package com.hq.chainStore.service.storeClerkInfo.bs;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.common.ClientConstants;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRole;
import com.hq.chainStore.service.storeClerkInfo.data.adminRole.StoreAdminRoleState;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.zenmind.common.json.JsonUtil;

public class StoreClerkInfoMgrNewTest {
	private static Boss boss;
	private static long storeId=0L;
	
	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
		//老板登陆
		boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		boss.login();
		storeId = boss.getRobotStoreId();
	}

	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeClerkInfo));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		storeId = 16052L;
		StoreClerkInfoMgr.getInstance().addClerk(storeId, boss.getId());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		long clerkId = 248762L;
		ClerkInfo clerkInfo = storeClerkInfo.getClerkInfoMap().get(clerkId);
		StoreClerkInfoMgr.getInstance().reomveClerk(storeId, storeClerkInfo.getId(), clerkInfo.getBuserId());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddRoleSet2Clerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		long clerkId = 1L;
		Set<Integer> roleIdSet = new HashSet<>();
		roleIdSet.add(91);
		roleIdSet.add(4);
		StoreClerkInfoMgr.getInstance().addRoleSet2Clerk(storeId, storeClerkInfo.getId(), clerkId, roleIdSet);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testReomveRoleOfClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		long clerkId = 1L;
		StoreClerkInfoMgr.getInstance().reomveRoleOfClerk(storeId, storeClerkInfo.getId(), clerkId, 4);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testApplyClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		storeId = 2L;
		long bUserId = 7L;
		StoreClerkInfoMgr.getInstance().applyClerkInfo(storeId, ClientConstants.STORE_CLERKINFO_ID_SUFFFIX+storeId, bUserId);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHandleApplyClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		StoreClerkInfoMgr.getInstance().handleApplyClerkInfo(storeId, storeClerkInfo.getId(), 472L, false);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testHandleGroupApplyClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		Set<Long> buserIdSet=new HashSet<>();
		buserIdSet.add(248761L);
		buserIdSet.add(248762L);
		StoreClerkInfoMgr.getInstance().HandleGroupApplyClerk(storeId, storeClerkInfo.getId(), buserIdSet, true);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	
	@Test
	public void testSetMonthPayDays() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		StoreClerkInfoMgr.getInstance().setMonthPayDays(storeId, storeClerkInfo.getId(), 22);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveStoreAdminRole() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		System.out.println(JsonUtil.getInstance().toJson(storeClerkInfo));
//		Collection<StoreAdminRole> values = storeClerkInfo.getRoleMap().values();
//		for (StoreAdminRole storeAdminRole : values) {
//			if(storeAdminRole.getId() != 2 && storeAdminRole.getId() != 4){
//				RemoveStoreAdminRoleData removeStoreAdminRoleData = RemoveStoreAdminRoleData.newInstance();
//				removeStoreAdminRoleData.setRoleId(storeAdminRole.getId());
//				removeStoreAdminRoleData.setStoreId(storeId);
//				StoreClerkInfoMgr.getInstance().removeStoreAdminRole(storeClerkInfo.getId(), removeStoreAdminRoleData);
//			}
//		}
//		RemoveStoreAdminRoleData removeStoreAdminRoleData = RemoveStoreAdminRoleData.newInstance();
//		removeStoreAdminRoleData.setRoleId(5);
//		removeStoreAdminRoleData.setStoreId(storeId);
//		StoreClerkInfoMgr.getInstance().removeStoreAdminRole(storeClerkInfo.getId(), removeStoreAdminRoleData);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateStoreAdminRole() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		StoreAdminRole storeAdminRole = storeClerkInfo.getRoleMap().get(4);
		StoreAdminRoleInfo4Update updateRoleInfo=StoreAdminRoleInfo4Update.newInstance();
		updateRoleInfo.setDescript(storeAdminRole.getDescript());
		updateRoleInfo.setName(storeAdminRole.getName()+"-测试");
		updateRoleInfo.setPermSet(storeAdminRole.getPermSet());
		updateRoleInfo.setState(StoreAdminRoleState.Available);
		updateRoleInfo.setStoreClerkInfoId(storeClerkInfo.getId());
		updateRoleInfo.setStoreId(storeId);
		updateRoleInfo.setRoleIdIndex(storeAdminRole.getId());
		StoreClerkInfoMgr.getInstance().updateStoreAdminRole(updateRoleInfo);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddStoreAdminRole() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
		StoreAdminRoleInfo4Add addRoleInfo=StoreAdminRoleInfo4Add.newInstance();
		addRoleInfo.setDescript("店长");
		addRoleInfo.setName("店长");
		addRoleInfo.setStoreClerkInfoId(storeClerkInfo.getId());
		addRoleInfo.setStoreId(storeId);
		addRoleInfo.setRoleIdIndex(storeClerkInfo.getRoleIdIndex()+1);
		
		Set<Integer> permSet = new HashSet<Integer>();
		for (int i = 1; i < StoreAdminPermEnum.values().length; i++) {
			if(i==StoreAdminPermEnum.MATERIAL_ADMIN.ordinal() || i==StoreAdminPermEnum.SALARY_ADMIN.ordinal()){
				continue;
			}
			permSet.add(i);
		}
		addRoleInfo.setPermSet(permSet);
		StoreClerkInfoMgr.getInstance().addStoreAdminRole(addRoleInfo);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
