package com.hq.storeMS.service.serverConfig.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigAddForm;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigQueryForm;
import com.hq.storeMS.service.serverConfig.apiData.ServerConfigUpdForm;
import com.hq.storeMS.service.serverConfig.bs.ServerConfigHandler;
import com.hq.storeMS.service.serverConfig.data.ServerConfig;

@RestController
@RequestMapping(value = "/serverConfig")
public class ServerConfigAPI {
	
	@RequestMapping(value = "/findServerConfigs" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<ServerConfig>> findServerConfigs(
    		@RequestParam(value = "appNameCh", defaultValue="") String appNameCh,
    		@RequestParam(value = "appNameEn", defaultValue="") String appNameEn,
    		@RequestParam(value = "appVersion", defaultValue="") String appVersion,
    		@RequestParam(value = "pageItemCount", defaultValue="0") int pageItemCount,
    		@RequestParam(value = "pageNo", defaultValue="1") int pageNo){
		ServerConfigQueryForm queryForm = ServerConfigQueryForm.newInstance();
		queryForm.setAppNameCh(appNameCh);
		queryForm.setAppNameEn(appNameEn);
		queryForm.setAppVersion(appVersion);
		queryForm.setPageItemCount(pageItemCount);
		queryForm.setPageNo(pageNo);
		ReqResult<ServerConfig> result = ServerConfigHandler.getInstance().findServerConfigs(queryForm);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/findOneServerConfig" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<ServerConfig>> findOneServerConfig(
			@RequestParam(value = "appNameCh", defaultValue="") String appNameCh,
			@RequestParam(value = "appNameEn", defaultValue="") String appNameEn,
			@RequestParam(value = "appVersion") String appVersion){
		ReqResult<ServerConfig> result = ServerConfigHandler.getInstance().findOneServerConfig(appNameCh, appNameEn, appVersion);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{serverConfigId}" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<ServerConfig>> getServerConfig(@PathVariable("serverConfigId") long serverConfigId){  
		ReqResult<ServerConfig> result = ServerConfigHandler.getInstance().getServerConfig(serverConfigId);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{serverConfigId}" ,method = RequestMethod.PUT,  produces="application/json")
	public ResponseEntity<RestResp<ServerConfig>> updServerConfig(@PathVariable("serverConfigId") long serverConfigId,
			@RequestBody ServerConfigUpdForm updForm){  
		ReqResult<ServerConfig> result = ServerConfigHandler.getInstance().updServerConfig(serverConfigId, updForm);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<ServerConfig>> addServerConfig(
			@RequestBody ServerConfigAddForm addForm){  
		ReqResult<ServerConfig> result = ServerConfigHandler.getInstance().addServerConfig(addForm);
		ResponseEntity<RestResp<ServerConfig>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
}
