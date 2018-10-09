package com.hq.testClass.robot.opUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hq.chainStore.service.opuser.bs.OPUserMgr;
import com.hq.chainStore.service.opuser.data.OPUser;
import com.hq.chainStore.service.opuser.data.adminRole.OPAdminPermEnum;
import com.hq.chainStore.service.opuser.data.adminRole.OPAdminRole;
import com.hq.chainStore.service.saas.apiData.OPAdminRoleAddApiForm;
import com.hq.chainStore.service.saas.bs.OPAdminRoleMgr;
import com.hq.chainStore.service.saas.bs.OPStoreMgr;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.robot.opUser.robot.OPRobot;

public class OPSuper {
	private static OPSuper instance = new OPSuper();
	public static OPSuper getInstance(){
		return instance;
	}
	
	private OPRobot robot;
	
	public OPSuper (){
		//super op 的电话是SUPER_OP_PHONE = 13800000000L;
		OPRobot robot = OPRobot.newInstance(0);
		this.robot = robot;
		this.robot.getData().setPassword("123456");
		this.robot.getData().setUserId(1);
	}
	
	public boolean login(){
		return robot.login();
	}

	public OPUser getOPSuper(Long opId) {
		return robot.get(opId);
	}
	
	public List<OPAdminRole> listAll() {
		return OPAdminRoleMgr.getInstance().listAll();
	}
	
	//超级管理员添加角色、授权
	public OPAdminRole addAdminRole(OPAdminPermEnum... adminPermEnums) {
		
		AccessTokenMgr.getInstance().setOpIdTL(robot.getId());
		//获取角色信息
		List<OPAdminRole> listAll = listAll();
		
		Set<Integer> permSet = new HashSet<Integer>();
		for(OPAdminPermEnum adminPerm :adminPermEnums){
			permSet.add(adminPerm.ordinal());
		}
		//判断是否已经包含该角色
		for(OPAdminRole item:listAll){
			if(item.getPermSet().equals(permSet)){
				System.out.println("已经包含该角色!");
				return item;
			}
		}
		//构造角色
		OPAdminRoleAddApiForm opAdminRole = OPAdminRoleAddApiForm.newInstance();
		opAdminRole.setId(listAll.size()+1);
		opAdminRole.setName("saas平台加盟店审核"+(listAll.size()+1));
		opAdminRole.setDescript("saas平台加盟店审核"+(listAll.size()+1));
		opAdminRole.setPermSet(permSet);
		OPAdminRole adminRoleByAdd = OPAdminRoleMgr.getInstance().add(opAdminRole.toRole());
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		
		return adminRoleByAdd;
	}
	
	public void addRole2OPuser(OPAdmin opAdmin){
		//超级管理员登陆、添加角色、获取角色列表
		login();
		OPAdminRole addAdminRole = addAdminRole(OPAdminPermEnum.OP_STORE_CHECKER);

		//超级管理员登陆
		login();

		//获取管理员用户信息
		OPUser oPuser = opAdmin.getOPuser();
		
		AccessTokenMgr.getInstance().setOpIdTL(robot.getId());

		//给管理员添加角色权限
		Set<Integer> roleIdSet = new HashSet<Integer>();
		roleIdSet.add(addAdminRole.getId());

		OPUserMgr.getInstance().updateOPUserRole(oPuser.getId(), roleIdSet);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
		
	}

	public void approveStore(long storeId){
		login();
		long opId = this.robot.getId();

		AccessTokenMgr.getInstance().setOpIdTL(opId);
		boolean approved = true;
		
		OPStoreMgr.getInstance().approveStore(storeId, approved);
		
		AccessTokenMgr.getInstance().removeOpIdTL();

	}

	public OPRobot getRobot() {
		return robot;
	}

	public void setRobot(OPRobot robot) {
		this.robot = robot;
	}
	
}
