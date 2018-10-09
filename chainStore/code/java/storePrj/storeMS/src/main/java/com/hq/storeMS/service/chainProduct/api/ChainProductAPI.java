package com.hq.storeMS.service.chainProduct.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainClient.service.chainProduct.data.ChainProduct;
import com.hq.chainClient.service.chainProduct.data.ProductDetail;
import com.hq.storeMS.service.chainProduct.bs.ChainProductHandler;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chainProduct")
public class ChainProductAPI {
	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainProduct>> getChainProduct(
			@PathVariable("chainId") long chainId) {
		ReqResult<ChainProduct> result = ChainProductHandler.getInstance().getChainProduct(chainId);
		ResponseEntity<RestResp<ChainProduct>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findProductDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductDetail>> findProductDetail(
			@RequestParam("productId") String productId,
			@RequestParam("chainId") long chainId) {
		ReqResult<ProductDetail> result = ChainProductHandler.getInstance().getProductDetail(productId, chainId);
		ResponseEntity<RestResp<ProductDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
