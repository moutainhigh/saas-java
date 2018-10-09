package com.hq.storeMS.service.serverConfig.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.serverConfig.data.ServerConfig;

/**
 * 
 * @Deprecated 已经被ServerConfigAPI替代
 *
 */
@RestController
@RequestMapping(value = "/serverCfg")
public class ServerCfgAPI {
	@RequestMapping(value = "/getServerCfg" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<ServerConfig>> getServerCfg(@RequestParam("cfgType") int cfgType){  
		ReqResult<ServerConfig> result = ReqResult.newInstance(false, ServerConfig.class);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
    }
}
