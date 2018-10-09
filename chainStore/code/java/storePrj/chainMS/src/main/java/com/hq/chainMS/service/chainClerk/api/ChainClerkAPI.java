package com.hq.chainMS.service.chainClerk.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.chainClerk.apiData.ChainClerkUpdateForm;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkHandler;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chainClerk")
public class ChainClerkAPI {

	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainClerk>> getChainClerk(@PathVariable("chainId") long chainId) {
		ReqResult<ChainClerk> result = ChainClerkHandler.getInstance().get(chainId);
		ResponseEntity<RestResp<ChainClerk>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<ChainClerk>> updateChainClerk(@PathVariable("chainId") long chainId,
			@RequestBody ChainClerkUpdateForm chainClerkForm) {
		ReqResult<ChainClerk> result = ChainClerkHandler.getInstance().update(chainId, chainClerkForm);
		ResponseEntity<RestResp<ChainClerk>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
