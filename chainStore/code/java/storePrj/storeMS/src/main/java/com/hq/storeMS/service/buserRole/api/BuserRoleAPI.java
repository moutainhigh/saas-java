package com.hq.storeMS.service.buserRole.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buserRole.apiData.BatchPermForm;
import com.hq.storeMS.service.buserRole.apiData.BuserRoleUpdateApiForm;
import com.hq.storeMS.service.buserRole.bs.BuserRoleHandler;
import com.hq.storeMS.service.buserRole.data.BuserRole;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

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
	
	//批量更改用户的系统权限
	@RequestMapping(value = "/batchPerm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BuserRole>> batchPerm(
			@RequestBody BatchPermForm inputForm) {
		ReqResult<BuserRole> result = BuserRoleHandler.getInstance().batchPerm(inputForm);
		ResponseEntity<RestResp<BuserRole>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
