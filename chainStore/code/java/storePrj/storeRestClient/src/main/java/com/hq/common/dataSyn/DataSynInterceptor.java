package com.hq.common.dataSyn;

import com.zenmind.dao.rest.RestHeader;
import com.zenmind.dao.rest.RestResp;
import com.zenmind.dao.rest.interceptor.IntfRestInterceptor;

public class DataSynInterceptor implements IntfRestInterceptor{

	public static DataSynInterceptor newInstance(){
		DataSynInterceptor tmp = new DataSynInterceptor();
		return tmp;
	}


	@Override
	public void preHandleReq(RestHeader restHeader) {
		
		DataSynVerCtrl.getInstance().preHandleReq(restHeader);
		
	}
	
	
	@Override
	public void preHandleResp(RestResp restResp) {
		
		DataSynVerCtrl.getInstance().preHandleResp(restResp);
	}



}
