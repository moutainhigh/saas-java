package com.hq.chainMS.service.chainProduct.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.chainProduct.apiData.ChainProductUpdateForm;
import com.hq.chainMS.service.chainProduct.bs.ChainProductHandler;
import com.hq.chainMS.service.chainProduct.data.ChainProduct;

@RestController
@RequestMapping(value = "/chainProduct")
public class ChainProductAPI {

	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainProduct>> getChainProduct(@PathVariable("chainId") long chainId) {
		ReqResult<ChainProduct> result = ChainProductHandler.getInstance().getChainProduct(chainId);
		ResponseEntity<RestResp<ChainProduct>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<ChainProduct>> updateChainProduct(
			@PathVariable("chainId") long chainId,
			@RequestBody ChainProductUpdateForm updateForm) {
		ReqResult<ChainProduct> result = ChainProductHandler.getInstance().update(chainId, updateForm);
		ResponseEntity<RestResp<ChainProduct>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
