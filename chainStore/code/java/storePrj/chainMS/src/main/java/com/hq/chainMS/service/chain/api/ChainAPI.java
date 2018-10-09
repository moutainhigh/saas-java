package com.hq.chainMS.service.chain.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.chain.apiData.ChainAddForm;
import com.hq.chainMS.service.chain.apiData.ChainQueryForm;
import com.hq.chainMS.service.chain.apiData.ChainUpdateForm;
import com.hq.chainMS.service.chain.bs.ChainHandler;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

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
		queryForm.setChainIds(chainIds).setNumber(number).setPageItemCount(pageItemCount).setPageNo(pageNo);
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

	@RequestMapping(value = "/findByUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Chain>> findByBuser(@RequestParam("userId") long userId) {
		ReqResult<Chain> result = ChainHandler.getInstance().findUserChains(userId);
		ResponseEntity<RestResp<Chain>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Chain>> getChain(@PathVariable("chainId") long chainId) {
		ReqResult<Chain> result = ChainHandler.getInstance().get(chainId);
		ResponseEntity<RestResp<Chain>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Chain>> newChain(@RequestBody ChainAddForm chainForm) {
		ReqResult<Chain> result = ChainHandler.getInstance().add(chainForm);
		ResponseEntity<RestResp<Chain>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Chain>> updateChain(@RequestBody ChainUpdateForm chainForm) {
		ReqResult<Chain> result = ChainHandler.getInstance().update(chainForm);
		ResponseEntity<RestResp<Chain>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
