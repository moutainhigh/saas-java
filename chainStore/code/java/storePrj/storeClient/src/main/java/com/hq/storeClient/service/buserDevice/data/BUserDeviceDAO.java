package com.hq.storeClient.service.buserDevice.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.service.buserDevice.apiData.AddStoreDeviceData;
import com.hq.storeClient.service.buserDevice.apiData.BUserDeviceUpdateForm;
import com.hq.storeClient.service.buserDevice.apiData.BUserDeviceUpdateType;
import com.hq.storeClient.service.buserDevice.apiData.BindDeviceForm;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestResp;

/**
 * @ClassName: BUserDeviceDAO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年1月27日 上午11:04:58
 * 
 */
public class BUserDeviceDAO extends RestDao<BUserDevice> {

	public static BUserDeviceDAO getInstance() {
		return HotSwap.getInstance().getSingleton(BUserDeviceDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}

	public void addStoreDevice(long id, AddStoreDeviceData addStoreDeviceData) {
		BUserDeviceUpdateForm updateForm = BUserDeviceUpdateForm.newInstance();
		updateForm.setBuserId(addStoreDeviceData.getBuserId());
		updateForm.setUpdateType(BUserDeviceUpdateType.AddStoreDevice.ordinal());
		updateForm.setAddStoreDeviceData(addStoreDeviceData);
		BUserDeviceDAO.getInstance().update(id, updateForm);
	}

	public RestResp bindDevice(BindDeviceForm bindForm) {
		String uriPath = "binding";
		return BUserDeviceDAO.getInstance().rawReq(uriPath, bindForm);
	}

	public RestResp lockDevice(long iotRecordId, MCtrlLockApiForm lockForm) {
		String uriPath = "lock/" + iotRecordId;
		return BUserDeviceDAO.getInstance().rawReq(uriPath, lockForm);
	}

}
