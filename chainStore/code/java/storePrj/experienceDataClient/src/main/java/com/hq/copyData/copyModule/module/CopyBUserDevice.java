package com.hq.copyData.copyModule.module;

import com.hq.chainStore.service.buserDevice.apiData.AddStoreDeviceData;
import com.hq.chainStore.service.buserDevice.apiData.BindDeviceForm;
import com.hq.chainStore.service.buserDevice.bs.BUserDeviceMgr;
import com.hq.chainStore.service.buserDevice.bs.MClientMgr;
import com.hq.chainStore.service.buserDevice.data.BUserDevice;
import com.hq.chainStore.service.buserDevice.data.MClient;
import com.hq.copyData.copyModule.AbstractCopyModule;
import com.hq.zenmind.dao.rest.restSTImpl.AccessTokenMgr;

public class CopyBUserDevice extends AbstractCopyModule {

	public static CopyBUserDevice newInstance() {
		CopyBUserDevice data = new CopyBUserDevice();
		return data;
	}
	
	private static long iotRecordId = 4L; //前4台分配给主体验账号，两家店，每家店分配2台

	public void copy() {
		// 每次绑定并分配两台仪器
		long iotRecordId1 = ++iotRecordId;
		long iotRecordId2 = ++iotRecordId;
		try {
			AccessTokenMgr.getInstance().setOpIdTL(getTargetBossId());
			// 绑定仪器
			MClient mclient1 = MClientMgr.getInstance().getMClient(iotRecordId1);
			BindDeviceForm bindForm1 = BindDeviceForm.newInstance();
			bindForm1.setBuserId(getTargetBossId());
			bindForm1.setSnCode(mclient1.getSnCode());
			BUserDeviceMgr.getInstance().bindDevice(bindForm1);

			MClient mclient2 = MClientMgr.getInstance().getMClient(iotRecordId2);
			BindDeviceForm bindForm2 = BindDeviceForm.newInstance();
			bindForm2.setBuserId(getTargetBossId());
			bindForm2.setSnCode(mclient2.getSnCode());
			BUserDeviceMgr.getInstance().bindDevice(bindForm2);
			// 分配到店
			BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(getTargetBossId());
			AddStoreDeviceData addStoreDeviceData1 = AddStoreDeviceData.newInstance();
			addStoreDeviceData1.setBuserId(getTargetBossId());
			addStoreDeviceData1.setStoreId(getTargetStoreId());
			BUserDeviceMgr.getInstance().addStoreDevcie(buserDevice.getIndex() - 1, addStoreDeviceData1);

			AddStoreDeviceData addStoreDeviceData2 = AddStoreDeviceData.newInstance();
			addStoreDeviceData2.setBuserId(getTargetBossId());
			addStoreDeviceData2.setStoreId(getTargetStoreId());
			BUserDeviceMgr.getInstance().addStoreDevcie(buserDevice.getIndex(), addStoreDeviceData2);

			AccessTokenMgr.getInstance().removeOpIdTL();
			System.out.println("copy buserDevice finish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
