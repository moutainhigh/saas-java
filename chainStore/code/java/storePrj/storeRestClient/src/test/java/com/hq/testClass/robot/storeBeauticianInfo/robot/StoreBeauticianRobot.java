package com.hq.testClass.robot.storeBeauticianInfo.robot;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.storeBeauticianInfo.apiData.AddBeauticianInfoData;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateForm;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.StoreBeauticianInfoUpdateType;
import com.hq.chainStore.service.storeBeauticianInfo.apiData.UpdateBeauticianInfoData;
import com.hq.chainStore.service.storeBeauticianInfo.bs.StoreBeauticianInfoMgr;
import com.hq.chainStore.service.storeBeauticianInfo.data.BeauticianState;
import com.hq.chainStore.service.storeBeauticianInfo.data.StoreBeauticianInfo;

public class StoreBeauticianRobot {
	
	private StoreBeauticianRobotData data;
	
	public static StoreBeauticianRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static StoreBeauticianRobot newInstance(int mark){
		StoreBeauticianRobot robot = new StoreBeauticianRobot();
		robot.data = StoreBeauticianRobotData.newInstance(mark);
		return robot;
	}
	
	public StoreBeauticianInfo add(long buserId,long storeId){
		AddBeauticianInfoData addBeauticianInfoData = AddBeauticianInfoData.newInstance();
		addBeauticianInfoData.setBuserId(buserId);
		
		StoreBeauticianInfoUpdateForm updateForm = StoreBeauticianInfoUpdateForm.newInstance();
		updateForm.setUpdateTypeEnum(StoreBeauticianInfoUpdateType.AddBeauticianInfo);
		updateForm.setStoreId(storeId);
		updateForm.setAddBeauticianInfoData(addBeauticianInfoData);
		StoreBeauticianInfoMgr.getInstance().update(storeId, updateForm);
		
		data.setId(storeId);
		data.setStoreId(storeId);
		data.setBuserId(buserId);
		
		StoreBeauticianInfo storeBeauticianInfo = getById();
		return storeBeauticianInfo;
	}
	
	public StoreBeauticianInfo getById(){
		return StoreBeauticianInfoMgr.getInstance().get(data.getId());
	}
	
	public void updateInfo(){
		UpdateBeauticianInfoData updateBeauticianInfoData = UpdateBeauticianInfoData.newInstance();
		updateBeauticianInfoData.setBuserId(data.getBuserId());
		updateBeauticianInfoData.setDescript("storeBeautician");
		updateBeauticianInfoData.setStateEnum(BeauticianState.OffWork);
		
		StoreBeauticianInfoUpdateForm updateForm = StoreBeauticianInfoUpdateForm.newInstance();
		updateForm.setStoreId(data.getStoreId());
		updateForm.setUpdateTypeEnum(StoreBeauticianInfoUpdateType.UpdateBeauticianInfo);
		updateForm.setUpdateBeauticianInfoData(updateBeauticianInfoData);
		StoreBeauticianInfoMgr.getInstance().update(data.getStoreId(), updateForm);
	}

	public StoreBeauticianRobotData getData() {
		return data;
	}

	public void setData(StoreBeauticianRobotData data) {
		this.data = data;
	}

	public long getId(){
		return this.data.getId();
	}
	
	
	
}
