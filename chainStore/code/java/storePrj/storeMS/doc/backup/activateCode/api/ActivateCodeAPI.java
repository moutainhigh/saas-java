package com.hq.storeMS.service.activateCode.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.activateCode.apiData.ActivateCodeGenApiForm;
import com.hq.storeMS.service.activateCode.apiData.AddActivateCodeForm;
import com.hq.storeMS.service.activateCode.apiData.QueryActivateCodeForm;
import com.hq.storeMS.service.activateCode.bs.ActivateCodeHandler;
import com.hq.storeMS.service.activateCode.data.ActivateCode;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/activateCode")
public class ActivateCodeAPI {
	
	@RequestMapping(value = "/findByCond" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<ActivateCode>> findByCond(
    		@RequestParam(value="phone",defaultValue="0")Long phone,
    		@RequestParam(value="status",defaultValue="0")Integer status,
    		@RequestParam(value="activateCode",defaultValue="0")String activateCode,
    		@RequestParam(value="pageItemCount", defaultValue="0")Integer pageItemCount,
    		@RequestParam(value="pageNo", defaultValue="1")Integer pageNo){
		QueryActivateCodeForm queryForm = QueryActivateCodeForm.newInstance();
		queryForm.setPageItemCount(pageItemCount);
		queryForm.setPageNo(pageNo);
		queryForm.setActivateCode(activateCode);
		queryForm.setPhone(phone);
		queryForm.setStatus(status);
		ReqResult<ActivateCode> result = ActivateCodeHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<ActivateCode>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{activateCodeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ActivateCode>> get(
			@PathVariable("activateCodeId") long activateCodeId) {
		ReqResult<ActivateCode> result = ActivateCodeHandler.getInstance().getActivateCode(activateCodeId);
		ResponseEntity<RestResp<ActivateCode>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/genActivateCodes", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ActivateCode>> genActivateCodes(
			@RequestBody ActivateCodeGenApiForm inputForm) {
		ReqResult<ActivateCode> result = ActivateCodeHandler.getInstance().genActivateCodes(inputForm);
		ResponseEntity<RestResp<ActivateCode>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ActivateCode>> add(
			@RequestBody AddActivateCodeForm inputForm) {
		ReqResult<ActivateCode> result = ActivateCodeHandler.getInstance().addActivateCode(inputForm);
		ResponseEntity<RestResp<ActivateCode>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
