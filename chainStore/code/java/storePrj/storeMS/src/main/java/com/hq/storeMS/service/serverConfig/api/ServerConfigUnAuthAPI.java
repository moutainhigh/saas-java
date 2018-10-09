package com.hq.storeMS.service.serverConfig.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.serverConfig.bs.ServerConfigHandler;
import com.hq.storeMS.service.serverConfig.data.ServerConfig;

@RestController
@RequestMapping(value = "/serverConfigUnAuth")
public class ServerConfigUnAuthAPI {
	@RequestMapping(value = "/findOneServerConfig" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<ServerConfig>> findOneServerConfig(
			@RequestParam(value = "appNameCh", defaultValue="") String appNameCh,
			@RequestParam(value = "appNameEn", defaultValue="") String appNameEn,
			@RequestParam(value = "appVersion") String appVersion){
		ReqResult<ServerConfig> result = ServerConfigHandler.getInstance().findOneServerConfig(appNameCh, appNameEn, appVersion);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
