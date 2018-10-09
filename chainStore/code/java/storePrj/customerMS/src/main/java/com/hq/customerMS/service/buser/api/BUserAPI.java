package com.hq.customerMS.service.buser.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.buser.bs.BUserHandler;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.storeClient.service.buser.data.BUser;

@RestController
@RequestMapping(value = "/buser")
public class BUserAPI {
	@RequestMapping(value = "/findByMultitId", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> findByMultitId(@RequestParam("ids") Set<Long> ids) {
		ReqResult<BUser> result = BUserHandler.getInstance().findByMultitId(ids);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{buserId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> get(@PathVariable("buserId") long buserId) {
		ReqResult<BUser> result = BUserHandler.getInstance().get(buserId);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
