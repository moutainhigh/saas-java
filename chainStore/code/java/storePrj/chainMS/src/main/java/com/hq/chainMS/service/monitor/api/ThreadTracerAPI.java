package com.hq.chainMS.service.monitor.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.monitor.apiData.TracerCfg;
import com.hq.chainMS.service.monitor.bs.ThreadTraceHandler;

@RestController
@RequestMapping(value = "/unAuth/trace" )
public class ThreadTracerAPI {
	
	@RequestMapping(value = "/{enabled}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<String>> enable(@PathVariable("enabled") boolean enabled){  
		ReqResult<String> result = ThreadTraceHandler.getInstance().enableTrace(enabled);
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
	@RequestMapping(value = "/clear" ,method = RequestMethod.PUT,  produces="application/json")
	public ResponseEntity<RestResp<String>> clear(){  
		ReqResult<String> result = ThreadTraceHandler.getInstance().clearTrace();
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
	} 
	
	@RequestMapping(value = "/cfg" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<String>> reCfg(@RequestBody TracerCfg cfg){  
		ReqResult<String> result = ThreadTraceHandler.getInstance().reCfg(cfg);
		ResponseEntity<RestResp<String>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
	@RequestMapping(value = "/getInfo" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<String>> getInfo(){  
		ReqResult<String> result = ThreadTraceHandler.getInstance().getTraceInfo();
		ResponseEntity<RestResp<String>> respEntity = result.buildJsonRespEntity();
		return respEntity;
    } 
}
