package com.hq.storeManagerMS.service.mngDevice.bs;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeClient.service.buser.data.BUserCount;
import com.hq.storeManagerMS.common.constants.ServerConstants;
import com.hq.storeManagerMS.common.validate.AppIdThreadLocal;
import com.hq.storeManagerMS.service.mngDevice.apiData.MngDeviceQueryForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BUserQueryApiForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BindDeviceForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.mctrl.MCtrlLockApiForm;
import com.hq.storeManagerMS.service.mngDevice.data.MngDeviceRestDAO;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserBindInfo;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserDevice;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.DeviceDetail;
import com.hq.storeManagerMS.service.mngDevice.data.mclient.MClient;
import com.zenmind.common.hotSwap.HotSwap;

public class MngDeviceDataHolder {

	public static MngDeviceDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(MngDeviceDataHolder.class);
	}

	public List<DeviceDetail> findDeviceDetailList(MngDeviceQueryForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<DeviceDetail> data = MngDeviceRestDAO.getInstance().findDeviceDetailList(params);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public MClient lockDevice(long id, MCtrlLockApiForm lockForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MClient data = MngDeviceRestDAO.getInstance().lockClient(id, lockForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public BUserDevice bindDevice(BindDeviceForm bindForm, String actionName) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		BUserDevice data = MngDeviceRestDAO.getInstance().bindDevice(bindForm, actionName);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public List<BUserBindInfo> findBUserBindInfoList(BUserQueryApiForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		List<BUserBindInfo> data = MngDeviceRestDAO.getInstance().findBUserBindInfoList(params);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public BUserCount getBUserBindInfoCount(BUserQueryApiForm params) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		BUserCount data = MngDeviceRestDAO.getInstance().getBUserBindInfoCount(params);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}

	public List<MClient> findMClientList(String snCode) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		MClient mclient = MngDeviceRestDAO.getInstance().getMClientBySnCode(snCode);
		AppIdThreadLocal.getInstance().set(null);
		List<MClient> list = new ArrayList<MClient>();
		if (mclient != null)
			list.add(mclient);
		return list;
	}

}