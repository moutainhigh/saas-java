package com.hq.chainStore.pressTest.robot;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.chainStore.service.storeLeaguerInfo.data.Leaguer;
import com.hq.chainStore.service.storeLeaguerInfo.data.StoreLeaguerInfo;
import com.hq.testClass.robot.storeLeaguerInfo.LeaguerRobot;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreLeaguerInfoActor {
	
	public static StoreLeaguerInfoActor getInstance(){
		return HotSwap.getInstance().getSingleton(StoreLeaguerInfoActor.class);
	}
	
	/**
	 * 查询
	 * @param storeId
	 * @return
	 */
	public StoreLeaguerInfo get(long storeId){
		return StoreLeaguerInfoMgr.getInstance().findSimpleStoreInfo(storeId);
	}
	
	/**
	 * 获取客户列表
	 * @param storeId
	 * @return
	 */
	public List<Leaguer> getLeaguerList(long storeId){
		return StoreLeaguerInfoMgr.getInstance().getLeaguerList(storeId);
	}
	
	/**
	 * 获取客户列表
	 * @param storeId
	 * @return
	 */
	public Leaguer getRandomLeaguer(long storeId){
		List<Leaguer> leaguerList = StoreLeaguerInfoMgr.getInstance().getLeaguerList(storeId);
		return leaguerList.get(RandomUtils.nextInt(0,leaguerList.size()));
	}
	
	/**
	 * 添加客户
	 * @param storeId
	 */
	public Leaguer addLeaguer(long storeId){
		LeaguerRobot robot = LeaguerRobot.newRandom();
		return robot.addLeaguer(storeId);
	}

}
