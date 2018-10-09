package com.hq.storeClient.service.buserDevice.bs;

import java.util.List;

import com.hq.storeClient.service.buserDevice.data.DeviceDetailDAO;
import com.hq.storeClient.service.buserDevice.data.vo.DeviceDetail;
import com.zenmind.common.hotSwap.HotSwap;

/**
 * @ClassName: DeviceDetailMgr
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author helen
 * @date 2018年2月2日 下午4:16:57
 * 
 */
public class DeviceDetailClientMgr {

	public static DeviceDetailClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(DeviceDetailClientMgr.class);
	}

	public List<DeviceDetail> getList(long buserId) {
		String uriPath = "getList/" + buserId;
		int pageItemCount = -1;
		int pageNo = -1;
		return DeviceDetailDAO.getInstance().findList(uriPath, pageItemCount, pageNo);
	}
}
