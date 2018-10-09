package com.hq.chainStore.service.buserDevice.bs;

import com.hq.chainStore.service.buserDevice.apiData.MCtrlSendParamApiForm;
import com.hq.chainStore.service.buserDevice.data.MClient;
import com.hq.chainStore.service.buserDevice.data.MClientDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

/** 
 * @ClassName: DeviceDetailMgr 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年2月2日 下午4:16:57 
 *  
 */
public class MClientMgr {


	public static MClientMgr getInstance(){
		return HotSwap.getInstance().getSingleton(MClientMgr.class);
	}

	public MClient getMClient(long id) {
		String uriPath = "getMClient/"+id;
		return MClientDAO.getInstance().findOne(uriPath);
	}
	
	public MClient sendClientParam(MCtrlSendParamApiForm sendParamForm) {
		String uriPath = "sendParam";
		RestResp restResp = MClientDAO.getInstance().rawReq(uriPath,sendParamForm);
		MClient mclient = JsonUtil.getInstance().fromJson(restResp.gettJson(), MClient.class);
		return mclient;
	}
	
	public MClient findByClientId(String clientId) {
		String uriPath = "findByClientId";
		ReqMap reqMap = new ReqMap();
		reqMap.add("clientId", clientId);
		return MClientDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
	}
}
