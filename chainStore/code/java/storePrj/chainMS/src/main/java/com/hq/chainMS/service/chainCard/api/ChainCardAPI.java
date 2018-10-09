package com.hq.chainMS.service.chainCard.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.chainCard.apiData.ChainCardUpdateApiForm;
import com.hq.chainMS.service.chainCard.bs.ChainCardHandler;
import com.hq.chainMS.service.chainCard.data.ChainCard;

@RestController
@RequestMapping(value = "/chainCard")
public class ChainCardAPI {
	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainCard>> getChainCard(
			@PathVariable("chainId") long chainId) {
		ReqResult<ChainCard> result = ChainCardHandler.getInstance().getChainCard(chainId);
		ResponseEntity<RestResp<ChainCard>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{chainId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<ChainCard>> updateChainCard(
			@PathVariable("chainId") long chainId,
			@RequestBody ChainCardUpdateApiForm inputForm) {
		ReqResult<ChainCard> result = ChainCardHandler.getInstance().updateChainCard(chainId, inputForm);
		ResponseEntity<RestResp<ChainCard>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
