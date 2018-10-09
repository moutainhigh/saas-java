package com.hq.storeManagerMS.service.mngDevice.bs;

import java.util.List;

import com.hq.storeClient.service.buser.data.BUserCount;
import com.hq.storeManagerMS.service.mngDevice.apiData.MngDeviceQueryForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BUserQueryApiForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BindDeviceForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.mctrl.MCtrlLockApiForm;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserBindInfo;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserDevice;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.DeviceDetail;
import com.hq.storeManagerMS.service.mngDevice.data.mclient.MClient;
import com.zenmind.common.hotSwap.HotSwap;

public class MngDeviceMgr {
	public static MngDeviceMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MngDeviceMgr.class);
	}
	

	public List<DeviceDetail> findDeviceDetailList(MngDeviceQueryForm params) {
		List<DeviceDetail> mclientList = MngDeviceDataHolder.getInstance().findDeviceDetailList(params);
		return mclientList;
	}
	
	public MClient lockDevice(long id, MCtrlLockApiForm lockForm){
		return MngDeviceDataHolder.getInstance().lockDevice(id,lockForm);
	}

	public BUserDevice bindDevice(BindDeviceForm bindForm, String actionName) {
		return MngDeviceDataHolder.getInstance().bindDevice(bindForm,actionName);
	}
	
	public List<BUserBindInfo> findBUserBindInfoList(BUserQueryApiForm params){
		return MngDeviceDataHolder.getInstance().findBUserBindInfoList(params);
	}
	
	public BUserCount getBUserBindInfoCount(BUserQueryApiForm params){
		return MngDeviceDataHolder.getInstance().getBUserBindInfoCount(params);
	}
	
	public List<MClient> findMClientList(String snCode){
		return MngDeviceDataHolder.getInstance().findMClientList(snCode);
	}
}
