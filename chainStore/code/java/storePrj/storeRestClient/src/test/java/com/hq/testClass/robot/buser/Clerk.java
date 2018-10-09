package com.hq.testClass.robot.buser;

import java.util.List;

import com.hq.chainStore.service.store.bs.StoreMgr;
import com.hq.chainStore.service.store.data.Store;
import com.hq.chainStore.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.testClass.AccessTokenMgr;
import com.hq.testClass.robot.buser.robot.BRobot;
import com.hq.testClass.robot.buser.robot.BRobotData;

public class Clerk {

	private long storeId;

	private BRobot robot;

	public static Clerk newClerk(BRobot robot) {
		Clerk clerk = new Clerk();
		clerk.robot = robot;
		return clerk;
	}

	public boolean reg() {
		return robot.reg();
	}

	public boolean login() {
		return robot.login();
	}

	public void applyAsStoreClerk(Long storeId, String storeClerkInfoId) {
		BRobotData data = robot.getData();

		AccessTokenMgr.getInstance().setOpIdTL(data.getUserId());

		StoreClerkInfoMgr.getInstance().applyClerkInfo(storeId,
				storeClerkInfoId, this.robot.getId());

		AccessTokenMgr.getInstance().removeOpIdTL();
	}

	public List<Store> findStoreByName(String storeName) {

		AccessTokenMgr.getInstance().setOpIdTL(this.robot.getId());

		List<Store> storeList = StoreMgr.getInstance().findByName(storeName,
				20, 0);

		AccessTokenMgr.getInstance().removeOpIdTL();

		return storeList;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public BRobot getRobot() {
		return robot;
	}

	public void setRobot(BRobot robot) {
		this.robot = robot;
	}

	public long getId() {
		return this.robot.getId();
	}

}
