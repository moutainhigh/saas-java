package com.hq.chainStore.service.buserDevice.bs;

import java.util.List;

import com.hq.chainStore.service.buserDevice.data.DeviceDetailDAO;
import com.hq.chainStore.service.buserDevice.data.vo.DeviceDetail;
import com.zenmind.common.hotSwap.HotSwap;

/** 
 * @ClassName: DeviceDetailMgr 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年2月2日 下午4:16:57 
 *  
 */
public class DeviceDetailMgr {


	public static DeviceDetailMgr getInstance(){
		return HotSwap.getInstance().getSingleton(DeviceDetailMgr.class);
	}

	public List<DeviceDetail> getList(long buserId) {
		String uriPath = "getList/" + buserId;
		int pageItemCount = -1;
		int pageNo = -1;
		return DeviceDetailDAO.getInstance().findList(uriPath, pageItemCount,pageNo);
	}
}
