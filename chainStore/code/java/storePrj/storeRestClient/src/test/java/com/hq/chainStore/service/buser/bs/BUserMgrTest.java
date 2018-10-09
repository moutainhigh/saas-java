package com.hq.chainStore.service.buser.bs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.buser.data.BUser;
import com.hq.chainStore.service.buser.data.BUserSynDataHolder;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.buser.Boss;
import com.hq.testClass.robot.buser.robot.BRobot;

public class BUserMgrTest {

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}

	@Test
	public void testRegAndLogin() {
		BRobot robotTmp = BRobot.newRandom();
		robotTmp.getData().setPhone(13660623983L);
		boolean regSuccess = robotTmp.reg();
		assertTrue("注册成功应该返回true。", regSuccess);
		
		boolean loginSuccess = robotTmp.login();
		assertTrue("登陆成功应该返回true。", loginSuccess);
		
	}

	
	@Test
	public void testGet() {
		BRobot robotTmpA = BRobot.newRandom();
		BRobot robotTmpB = BRobot.newRandom();
		
		assertTrue("A用户注册成功", robotTmpA.reg());
		assertTrue("B用户注册成功", robotTmpB.reg());
		
		assertTrue("A登陆成功", robotTmpA.login());
		
		BUser buser = robotTmpA.getBuser(robotTmpB.getId());
		
		assertTrue("A获取B信息成功", buser!=null);
		Assert.assertEquals(robotTmpB.getId(), buser.getId());
		
		BUser findByPhone = robotTmpA.findByPhone();
		assertNotNull("findByPhone 不为空", findByPhone);
		
		String idList = "410,411";
		List<BUser> buserList = robotTmpA.findByMultitId(idList);
		assertTrue("findByMultitId 应该有两条数据", buserList.size()>1);
		
	}
	
	@Test
	public void testUpdate() {
		BRobot robotTmp = BRobot.newRandom();
		boolean regSuccess = robotTmp.reg();
		assertTrue("注册成功应该返回true。", regSuccess);
		
		boolean loginSuccess = robotTmp.login();
		assertTrue("登陆成功应该返回true。", loginSuccess);
		
		robotTmp.getBuserFromHodler();
		//修改用户信息
		boolean updateInfo = robotTmp.updateInfo();
		assertTrue("修改成功应该返回true。", updateInfo);
		
		//登录状态修改密码
		boolean changePassword = robotTmp.changePassword();
		assertTrue("修改成功应该返回true。", changePassword);
		
		//忘记密码  未登录状态修改密码
		boolean resetPassword = robotTmp.resetPassword();
		assertTrue("修改成功应该返回true。", resetPassword);
		
	}
	
	
	@Test
	public void testFindByMultitId() {
		Boss boss = Boss.newBoss(BRobot.newRandom());
		boss.getRobot().getData().setPhone(13660623953L);
		boss.getRobot().getData().setPassword("123456");
		Assert.assertTrue("boss 登陆成功", boss.login());
		
		AccessTokenMgr.getInstance().setOpIdTL(boss.getId());
		Set<Long> ids = new HashSet<Long>();
		ids.add(342L);
//		List<BUser> users = BUserMgr.getInstance().findByMultitId(ids);
		List<BUser> users = BUserSynDataHolder.getInstance().findByMultitId(ids);
		for (BUser user : users) {
			System.out.println(user.getId()+"----"+user.getName());
		}
		AccessTokenMgr.getInstance().removeOpIdTL();
	}

}
