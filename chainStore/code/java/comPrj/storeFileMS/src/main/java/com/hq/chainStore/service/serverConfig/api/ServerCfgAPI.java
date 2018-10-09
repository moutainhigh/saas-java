package com.hq.chainStore.service.serverConfig.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainStore.service.common.ReqResult;
import com.hq.chainStore.service.common.RestResp;
import com.hq.chainStore.service.serverConfig.bs.ServerCfgHandler;
import com.hq.chainStore.service.serverConfig.data.ServerConfig;

@RestController
@RequestMapping(value = "/serverCfg")
public class ServerCfgAPI {
	
	@RequestMapping(value = "/getServerCfg" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<ServerConfig>> getServerCfg(@RequestParam("cfgType") int cfgType){  
		ReqResult<ServerConfig> result = ServerCfgHandler.getInstance().getServerCfg(cfgType);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
    }  

}
