package com.hq.storeMS.service.appVersion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.appVersion.apiData.AddAppVersionForm;
import com.hq.storeMS.service.appVersion.apiData.QueryAppVersionForm;
import com.hq.storeMS.service.appVersion.apiData.UpdAppVersionForm;
import com.hq.storeMS.service.appVersion.bs.AppVersionHandler;
import com.hq.storeMS.service.appVersion.data.AppVersion;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/appVersion")
public class AppVersionAPI {
	
	@RequestMapping(value = "/findByCond" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<AppVersion>> findByCond(
    		@RequestParam(value="pageItemCount", required=true)Integer pageItemCount,
    		@RequestParam(value="pageNo", required=true)Integer pageNo){
		QueryAppVersionForm queryForm = QueryAppVersionForm.newInstance();
		queryForm.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<AppVersion> result = AppVersionHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<AppVersion>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/findByName" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<AppVersion>> findByName(@RequestParam("appName") String appName){  
		ReqResult<AppVersion> result = AppVersionHandler.getInstance().findByName(appName);
		ResponseEntity<RestResp<AppVersion>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/{appVersionId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<AppVersion>> get(
			@PathVariable("appVersionId") long appVersionId) {
		ReqResult<AppVersion> result = AppVersionHandler.getInstance().getAppVersion(appVersionId);
		ResponseEntity<RestResp<AppVersion>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{appVersionId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<AppVersion>> update(
			@PathVariable("appVersionId") long appVersionId,
			@RequestBody UpdAppVersionForm inputForm) {
		ReqResult<AppVersion> result = AppVersionHandler.getInstance().updateAppVersion(appVersionId, inputForm);
		ResponseEntity<RestResp<AppVersion>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<AppVersion>> add(
			@RequestBody AddAppVersionForm inputForm) {
		ReqResult<AppVersion> result = AppVersionHandler.getInstance().addAppVersion(inputForm);
		ResponseEntity<RestResp<AppVersion>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
