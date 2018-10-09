package com.hq.storeClient.service.buserDevice.bs;

import java.util.List;

import com.hq.storeClient.service.buserDevice.data.DeviceParamDAO;
import com.hq.storeClient.service.buserDevice.data.vo.IotKeyValue;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * @ClassName: DeviceParamMgr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年2月7日 下午2:24:24
 * 
 */
public class DeviceParamClientMgr {

	public static DeviceParamClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DeviceParamClientMgr.class);
	}

	public List<IotKeyValue> getParams(long id) {
		String uriPath = "getParam/" + id;
		int pageItemCount = -1;
		int pageNo = -1;
		return DeviceParamDAO.getInstance().findList(uriPath, pageItemCount, pageNo);
	}
}
