package com.hq.chainStore.service.saas.bs;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hq.chainStore.service.opuser.data.OPUser;
import com.hq.chainStore.service.opuser.data.adminRole.OPAdminPermEnum;
import com.hq.chainStore.service.opuser.data.adminRole.OPAdminRole;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.TestEnv;
import com.hq.testClass.robot.opUser.OPAdmin;
import com.hq.testClass.robot.opUser.OPSuper;
import com.hq.testClass.robot.opUser.robot.OPRobot;


public class OPAdminRoleMgrTest {

	@BeforeClass
	public static void initEnv(){
		TestEnv.initTestEnv();
	}

	@Test
	public void testListAll() {
		OPSuper.getInstance().login();
		AccessTokenMgr.getInstance().setOpIdTL(OPSuper.getInstance().getRobot().getId());
		List<OPAdminRole> listAll = OPSuper.getInstance().listAll();
		AccessTokenMgr.getInstance().removeOpIdTL();
		Assert.assertNotNull("OPAdminRole 应该非空", listAll);
	}

	@Test
	public void testAddAdminRole() {
		OPSuper.getInstance().login();
		//定义管理员审核角色
		OPAdminPermEnum opAdminPermCheck = OPAdminPermEnum.OP_STORE_CHECKER;
		OPAdminRole addAdminRole = OPSuper.getInstance().addAdminRole(opAdminPermCheck);
		Assert.assertNotNull("addAdminRole 应该非空", addAdminRole.getPermSet());
	}
	
	@Test
	public void AddRole2OPuser() {
		//管理员用户注册、登陆
		OPAdmin opAdmin = OPAdmin.newInstance(OPRobot.newRandom());
		opAdmin.reg();
		opAdmin.login();

		//管理员用户赋予角色权限
		OPSuper.getInstance().addRole2OPuser(opAdmin);
		
		OPUser oPuserFresh = opAdmin.getOPuser();
		Assert.assertFalse("权限应该不为空", oPuserFresh.getOpAdminRoleSet().isEmpty());
	}
	

}
