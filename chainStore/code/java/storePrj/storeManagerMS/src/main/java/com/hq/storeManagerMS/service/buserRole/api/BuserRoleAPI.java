package com.hq.storeManagerMS.service.buserRole.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeClient.service.buserRole.apiData.BuserRoleUpdateApiForm;
import com.hq.storeClient.service.buserRole.data.BuserRole;
import com.hq.storeManagerMS.service.buserRole.bs.BuserRoleHandler;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/buserRole")
public class BuserRoleAPI {
	@RequestMapping(value = "/{buserId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BuserRole>> getBuserRole(
			@PathVariable("buserId") long buserId) {
		ReqResult<BuserRole> result = BuserRoleHandler.getInstance().getBuserRole(buserId);
		ResponseEntity<RestResp<BuserRole>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{buserId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<BuserRole>> updateBuserRole(
			@PathVariable("buserId") long buserId,
			@RequestBody BuserRoleUpdateApiForm updateForm) {
		ReqResult<BuserRole> result = BuserRoleHandler.getInstance().updateBuserRole(buserId, updateForm);
		ResponseEntity<RestResp<BuserRole>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
