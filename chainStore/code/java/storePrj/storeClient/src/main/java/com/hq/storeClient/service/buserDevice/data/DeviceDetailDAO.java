package com.hq.storeClient.service.buserDevice.data;

import com.hq.storeClient.common.restClientResp.RestClientCfg;
import com.hq.storeClient.service.buserDevice.data.vo.DeviceDetail;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

/** 
 * @ClassName: DeviceDetailDAO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年2月2日 下午2:37:49 
 *  
 */
public class DeviceDetailDAO extends RestDao<DeviceDetail>{

	public static DeviceDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(DeviceDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getService();
	}
	


}
