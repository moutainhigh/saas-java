package com.hq.storeMS.service.chain.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainClient.common.restClientResp.PageResp;
import com.hq.chainClient.service.chain.apiData.ChainQueryForm;
import com.hq.chainClient.service.chain.apiData.ChainUpdateForm;
import com.hq.chainClient.service.chain.data.Chain;
import com.hq.storeMS.service.chain.bs.ChainHandler;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chain")
public class ChainAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findChainByCond", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findChainByCond(
			@RequestParam(value = "number", defaultValue = "") String number,
			@RequestParam(value = "chainIds", defaultValue = "") Set<Long> chainIds,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ChainQueryForm queryForm = ChainQueryForm.newInstance();
		queryForm.setNumber(number).setChainIds(chainIds)
		.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainHandler.getInstance().findChainByCond(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByNumber", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Chain>> findByNumber(@RequestParam("number") String number) {
		ReqResult<Chain> result = ChainHandler.getInstance().findByNumber(number);
		ResponseEntity<RestResp<Chain>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Chain>> getChain(@PathVariable("chainId") long chainId) {
		ReqResult<Chain> result = ChainHandler.getInstance().getChain(chainId);
		ResponseEntity<RestResp<Chain>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Chain>> updateChain(@RequestBody ChainUpdateForm chainForm) {
		ReqResult<Chain> result = ChainHandler.getInstance().updateChain(chainForm);
		ResponseEntity<RestResp<Chain>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
