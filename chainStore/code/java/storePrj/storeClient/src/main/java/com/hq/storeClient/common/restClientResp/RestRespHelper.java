package com.hq.storeClient.common.restClientResp;

import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class RestRespHelper {
	
	public static <T> T gettJsonObj(RestResp restResp, Class<T> tJsonClazz){
		if (restResp.getCode() == 200) {
			String tJson = restResp.gettJson();
			return JsonUtil.getInstance().fromJson(tJson, tJsonClazz);
		} else {
			throw (RestProxyException.newInstance(restResp.getCode(),restResp.getTips()));
		}
	}

}
