package com.hq.storeManagerMS.service.buser.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeClient.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeManagerMS.service.buser.bs.BUserHandler;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/buser")
public class BUserAPI {

	@RequestMapping(value = "/{buserId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> get(@PathVariable("buserId") long buserId) {
		ReqResult<BUser> result = BUserHandler.getInstance().get(buserId);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findByPhone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> findByPhone(@RequestParam("phone") long phone) {
		ReqResult<BUser> result = BUserHandler.getInstance().findByPhone(phone);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{buserId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> updateBUser(@PathVariable("buserId") long buserId,
			@RequestBody BUserUpdateApiForm updateForm) {
		ReqResult<BUser> result = BUserHandler.getInstance().update(buserId, updateForm);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
