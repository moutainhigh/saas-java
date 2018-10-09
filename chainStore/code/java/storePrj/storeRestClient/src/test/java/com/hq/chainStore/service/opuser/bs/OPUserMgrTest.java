package com.hq.chainStore.service.opuser.bs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.store.data.StoreState;
import com.hq.chainStore.service.store.data.StoreSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.opUser.OPAdmin;
import com.hq.testClass.robot.opUser.OPSuper;
import com.hq.testClass.robot.opUser.robot.OPRobot;

public class OPUserMgrTest {

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}
	
	@Test
	public void testRegAndLogin() {

		OPAdmin opAdmin = OPAdmin.newInstance(OPRobot.newRandom());
		
		Assert.assertTrue("平台管理员注册成功", opAdmin.reg());
		Assert.assertTrue("平台管理员登陆成功", opAdmin.login());
		
	}
	
	@Test
	public void checkRoleOfOPUser() {
		//老板注册、登陆、开店、提交审核
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.reg();
		boss.login();
		
		Store store = boss.openSimpleStore(1);
		boss.submit4Check(store.getId());
		
		//管理员用户注册
		OPAdmin opAdmin = OPAdmin.newInstance(OPRobot.newRandom());
		opAdmin.reg();
		
		//管理员登陆
		opAdmin.login();
		
		//管理员用户获取角色权限
		OPSuper.getInstance().addRole2OPuser(opAdmin);
		
		//管理员审核店铺
		opAdmin.approveStore(store.getId());
		
		//验证店铺状态
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Store storeFresh = StoreSynDataHolder.getInstance().getData(boss.getId(), store.getId());
		Assert.assertEquals(StoreState.Open, storeFresh.getStateEnum());
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
	

}
