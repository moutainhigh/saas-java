package com.hq.customerRestClient.common.validate;

import com.hq.customerRestClient.common.validate.info.ValidateInfo;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestHeader;
import com.zenmind.dao.rest.RestResp;
import com.zenmind.dao.rest.interceptor.IntfRestInterceptor;

public class ValidateInterceptor implements IntfRestInterceptor {

	public static ValidateInterceptor newInstance() {
		ValidateInterceptor tmp = new ValidateInterceptor();
		return tmp;
	}
	
	private final String HEADER_ACCESS_VALIDATE_INFO = "validateInfo";

	@Override
	public void preHandleReq(RestHeader restHeader) {
		ValidateInfo validateInfo = ValidateThreadLocal.getInstance().getValidateInfo();
		if(validateInfo!=null){
			String jsonData = JsonUtil.getInstance().toJson(validateInfo);
			restHeader.addHeader(HEADER_ACCESS_VALIDATE_INFO, jsonData);
		}
	}

	@Override
	public void preHandleResp(RestResp restResp) {
		ValidateThreadLocal.getInstance().setValidateInfo(null);
	}
}
