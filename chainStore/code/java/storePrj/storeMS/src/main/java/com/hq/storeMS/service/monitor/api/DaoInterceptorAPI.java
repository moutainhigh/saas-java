package com.hq.storeMS.service.monitor.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.monitor.bs.DaoInterceptorHandler;
import com.hq.storeMS.zenmind.dao.interceptor.LogInterceptorConfig;

@RestController
@RequestMapping(value = "/unAuth/daoInterceptor" )
public class DaoInterceptorAPI {
	@RequestMapping(value = "/reset" ,method = RequestMethod.PUT,  produces="application/json")
	public ResponseEntity<RestResp<String>> reset(@RequestBody LogInterceptorConfig config){  
		ReqResult<String> result = DaoInterceptorHandler.getInstance().reset(config);
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
