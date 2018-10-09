package com.hq.storeMS.service.monitor.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.monitor.bs.MonitorHandler;

@RestController
@RequestMapping(value = "/monitor" )
public class MonitorAPI {
	
	@RequestMapping(value = "/{key}/{enabled}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<String>> enable(@PathVariable("key") String key,@PathVariable("enabled") boolean enabled){  
		ReqResult<String> result = MonitorHandler.getInstance().enable(key,enabled);
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
	@RequestMapping(value = "/{key}/reset" ,method = RequestMethod.PUT,  produces="application/json")
	public ResponseEntity<RestResp<String>> clear(@PathVariable("key") String key){  
		ReqResult<String> result = MonitorHandler.getInstance().reset(key);
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
	} 
	
	@RequestMapping(value = "/getInfo" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<String>> getInfo(){  
		ReqResult<String> result = MonitorHandler.getInstance().getInfo();
		ResponseEntity<RestResp<String>> respEntity = result.buildJsonRespEntity();
		return respEntity;
    } 
}
