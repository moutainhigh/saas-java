package com.hq.chainClient.service.chainClerk.bs;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainClient.service.chainClerk.apiData.AdminRoleAddForm;
import com.hq.chainClient.service.chainClerk.apiData.AdminRoleRemoveForm;
import com.hq.chainClient.service.chainClerk.apiData.AdminRoleUpdateForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainAllotStoreForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkAddForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkReomveForm;
import com.hq.chainClient.service.chainClerk.apiData.ChainClerkUpdateInfoForm;
import com.hq.chainClient.service.chainClerk.apiData.ClerkRoleSaveForm;
import com.hq.chainClient.service.chainClerk.data.ChainClerk;
import com.hq.chainClient.service.chainClerk.data.adminRole.AdminRole;
import com.hq.chainClient.service.chainUser.bs.ChainUserClientMgr;
import com.hq.chainClient.service.chainUser.data.ChainUser;
import com.hq.chainClient.testClass.AccessTokenMgr;
import com.hq.chainClient.testClass.TestEnv;
import com.hq.chainClient.testClass.robot.Boss;
import com.hq.chainClient.testClass.robot.chainClerk.AdminRoleRobotData;
import com.hq.chainClient.testClass.robot.chainClerk.ChainClerkRobotData;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.json.JsonUtil;

public class ChainClerkClientMgrTest {
	private static Boss boss;
	private static long chainId = 15L;

	@BeforeClass
	public static void initEnv() {
		TestEnv.initTestEnv();
		// 老板登陆
		boss = Boss.newBoss(13660623953L, "123456");
		boss.login();
//		chainId = boss.getChainId();
	}
	
	@Test
	public void testAddClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainClerkAddForm inputForm = ChainClerkRobotData.newRandomInstance().toChainClerkAddForm(chainId);
		ChainClerkClientMgr.getInstance().addClerk(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testGet() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainClerk chainClerk = ChainClerkClientMgr.getInstance().get(chainId);
		System.out.println(JsonUtil.getInstance().toJson(chainClerk));
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainClerkReomveForm inputForm = ChainClerkReomveForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setUserId(3L);
		ChainClerkClientMgr.getInstance().reomveClerk(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAddAdminRole() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		ChainClerk chainClerk = ChainClerkClientMgr.getInstance().get(chainId);
		AdminRole role = AdminRoleRobotData.newRandomInstance().toAdminRole(chainClerk.getRoleIdIndex()+1);
		role.setChainId(chainId);
		AdminRoleAddForm inputForm = AdminRoleAddForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setRole(role);
		ChainClerkClientMgr.getInstance().addAdminRole(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateAdminRole() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AdminRole role = AdminRoleRobotData.newRandomInstance().toAdminRole(4);
		role.setChainId(chainId);
		AdminRoleUpdateForm inputForm = AdminRoleUpdateForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setRole(role);
		ChainClerkClientMgr.getInstance().updateAdminRole(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testRemoveAdminRole() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		AdminRoleRemoveForm inputForm = AdminRoleRemoveForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setRoleId(4);
		ChainClerkClientMgr.getInstance().removeAdminRole(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testSaveRoleSet2Clerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Set<Integer> roleIds = new HashSet<Integer>();
		roleIds.add(4);
		roleIds.add(5);
		ClerkRoleSaveForm inputForm = ClerkRoleSaveForm.newInstance();
		inputForm.setChainId(chainId);
		inputForm.setUserId(50L);
		inputForm.setRoleIds(roleIds);
		ChainClerkClientMgr.getInstance().saveRoleSet2Clerk(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testUpdateClerk() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long userId = 16L;
		ChainUser chainUser = ChainUserClientMgr.getInstance().get(userId);
		System.out.println(JsonUtil.getInstance().toJson(chainUser));
		ChainClerkUpdateInfoForm inputForm = ChainClerkUpdateInfoForm.newInstance();
		FastBeanCopyer.getInstance().copy(chainUser, inputForm);
		inputForm.setName("修改名称");
		inputForm.setPassword("123456");
		ChainClerkClientMgr.getInstance().updateClerk(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	@Test
	public void testAllotStore() {
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		long userId = 15L;
		ChainAllotStoreForm inputForm = ChainAllotStoreForm.newInstance();
		inputForm.setUserId(userId);
		inputForm.getStoreIds().add(16052L);
		ChainClerkClientMgr.getInstance().allotStore(chainId, inputForm);
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
