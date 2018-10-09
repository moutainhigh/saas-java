package com.hq.chainStore.service.buserDevice.bs;

import com.hq.chainStore.service.buserDevice.apiData.AddStoreDeviceData;
import com.hq.chainStore.service.buserDevice.apiData.BindDeviceForm;
import com.hq.chainStore.service.buserDevice.data.BUserDevice;
import com.hq.chainStore.service.buserDevice.data.BUserDeviceDAO;
import com.hq.chainStore.service.buserDevice.data.MCtrlLockApiForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

/**
 * @ClassName: BUserDeviceMgr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年1月27日 上午11:29:02
 * 
 */
public class BUserDeviceMgr {

	public static BUserDeviceMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserDeviceMgr.class);
	}

	public BUserDevice getByUser(long buserId) {
		String uriPath = "getByBUser";
		ReqMap reqMap = new ReqMap();
		reqMap.add("buserId", buserId);
		return BUserDeviceDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
	}
	
	public BUserDevice get(long id) {
		return BUserDeviceDAO.getInstance().get(id);
	}

	public void update(BUserDevice buserDevice) {
		BUserDeviceDAO.getInstance().update(buserDevice);
	}

	public RestResp bindDevice(BindDeviceForm bindForm) {
		return BUserDeviceDAO.getInstance().bindDevice(bindForm);
	}

	public void addStoreDevcie(long id,AddStoreDeviceData addStoreDeviceData) {
		BUserDeviceDAO.getInstance().addStoreDevice(id, addStoreDeviceData);
	}
	
	public RestResp lock(long iotRecordId,MCtrlLockApiForm mctrlLockApiForm) {
		return BUserDeviceDAO.getInstance().lockDevice(iotRecordId, mctrlLockApiForm);
	}

}
