package com.hq.chainStore.service.activateCode.data;

import com.hq.chainStore.service.activateCode.apiData.ActivateCodeGenApiForm;
import com.hq.chainStore.service.common.RestClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class ActivateCodeDAO extends RestDao<ActivateCode> {

	public static ActivateCodeDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ActivateCodeDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public RestResp genActivateCodes(ActivateCodeGenApiForm inputForm){
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, getService(), "activateCode", "genActivateCodes");
		return RestProxy.getInstance().rawReq(uri, inputForm);
	}
	
}
