package com.hq.testClass.robot.storeBeauticianInfo;

import java.util.Collection;
import java.util.Map;

import com.hq.chainStore.service.storeBeauticianInfo.data.StoreBeauticianInfo;
import com.hq.chainStore.service.storeClerkInfo.data.ClerkInfo;
import com.hq.chainStore.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.testClass.robot.storeBeauticianInfo.robot.StoreBeauticianRobot;

public class StoreBeaut {

	private StoreBeauticianRobot robot; 
	
	public static StoreBeaut newInstance(StoreBeauticianRobot robot){
		StoreBeaut beaut = new StoreBeaut();
		beaut.robot = robot;
		return beaut;
	}
	
	public StoreBeauticianInfo addStoreBeautician(StoreClerkInfo storeClerkInfo, long clerkId) {
		StoreBeauticianInfo storeBeauticianInfo = null;
		
		Map<Long, ClerkInfo> clerkInfoMap = storeClerkInfo.getClerkInfoMap();
		Collection<ClerkInfo> clerkInfoSet = clerkInfoMap.values();
		
		//遍历所有员工、选择员工添加为医美师
		for(ClerkInfo item:clerkInfoSet){
			long buserId = item.getBuserId();
			long storeId = storeClerkInfo.getStoreId();
			if(buserId == clerkId){
				//添加
				storeBeauticianInfo = robot.add(buserId,storeId);
				
				break;
			}
		}
		
		return storeBeauticianInfo;
	}
	
	public StoreBeauticianInfo getById(){
		return robot.getById();
	}
	
	public void updateInfo(){
		robot.updateInfo();
	}
	
	public long getId(){
		return this.robot.getId();
	}
	
}
