package com.hq.testClass.robot.opUser;

import com.hq.chainStore.service.opuser.data.OPUser;
import com.hq.chainStore.service.saas.bs.OPStoreMgr;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.robot.opUser.robot.OPRobot;

public class OPAdmin {
	
	private OPRobot robot;
	
	public static OPAdmin newInstance(OPRobot robot){
		OPAdmin opAdmin = new OPAdmin();
		opAdmin.robot = robot;
		return opAdmin;
	}
	
	public boolean reg(){
		return robot.reg();
	}
	
	public boolean login(){
		return robot.login();
	}
	
	public OPUser getOPuser(Long opId) {
		return robot.get(opId);
	}

	public OPUser getOPuser() {
		return robot.get();
	}
	
	public void approveStore(long storeId) {
		long userId = robot.getData().getUserId();
		AccessTokenMgr.getInstance().setOpIdTL(userId);
		
		boolean approved = true;
		OPStoreMgr.getInstance().approveStore(storeId, approved);
		
		AccessTokenMgr.getInstance().removeOpIdTL();
	}
	
}
