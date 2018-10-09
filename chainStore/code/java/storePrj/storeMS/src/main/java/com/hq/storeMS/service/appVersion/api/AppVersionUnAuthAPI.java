package com.hq.storeMS.service.appVersion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.appVersion.bs.AppVersionHandler;
import com.hq.storeMS.service.appVersion.data.AppVersion;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/appVersionUnAuth")
public class AppVersionUnAuthAPI {
	@RequestMapping(value = "/findByName" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<AppVersion>> findByName(@RequestParam("appName") String appName){  
		ReqResult<AppVersion> result = AppVersionHandler.getInstance().findByName(appName);
		ResponseEntity<RestResp<AppVersion>> respEntity = result.buildRespEntity();
		return respEntity;
    }
}
