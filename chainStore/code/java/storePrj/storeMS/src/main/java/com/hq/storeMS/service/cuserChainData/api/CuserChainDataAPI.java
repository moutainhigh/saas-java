package com.hq.storeMS.service.cuserChainData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.cuserChainData.bs.CuserChainDataHandler;
import com.hq.storeMS.service.cuserChainData.data.CuserChainData;

@RestController
@RequestMapping(value = "/cuserChainData")
public class CuserChainDataAPI {
	@RequestMapping(value = "/{cuserId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<CuserChainData>> getCuserChainData(
			@PathVariable("cuserId") long cuserId) {
		ReqResult<CuserChainData> result = CuserChainDataHandler.getInstance().getCuserChainData(cuserId);
		ResponseEntity<RestResp<CuserChainData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
