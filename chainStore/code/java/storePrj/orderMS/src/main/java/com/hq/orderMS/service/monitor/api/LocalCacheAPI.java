package com.hq.orderMS.service.monitor.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.orderMS.service.common.ReqResult;
import com.hq.orderMS.service.common.RestResp;
import com.hq.orderMS.service.monitor.bs.LocalCacheHandler;

@RestController
@RequestMapping(value = "/unAuth/localCache")
public class LocalCacheAPI {

	@RequestMapping(value = "/clear", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<String>> clear() {
		ReqResult<String> result = LocalCacheHandler.getInstance().clear();
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
