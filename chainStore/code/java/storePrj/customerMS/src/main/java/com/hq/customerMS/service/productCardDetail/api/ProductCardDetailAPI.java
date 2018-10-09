package com.hq.customerMS.service.productCardDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.productCardDetail.bs.ProductCardDetailHandler;
import com.hq.storeClient.service.productCardDetail.data.ProductCardDetail;

@RestController
@RequestMapping(value = "/productCardDetail")
public class ProductCardDetailAPI {

	@RequestMapping(value = "/{storeId}/{productCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductCardDetail>> getProductCardDetail(@PathVariable("storeId") long storeId,
			@PathVariable("productCardDetailId") String productCardDetailId) {
		ReqResult<ProductCardDetail> result = ProductCardDetailHandler.getInstance().getProductCardDetail(storeId, productCardDetailId);
		ResponseEntity<RestResp<ProductCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
