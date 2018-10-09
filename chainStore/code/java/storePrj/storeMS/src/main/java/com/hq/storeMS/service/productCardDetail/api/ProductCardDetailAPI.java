package com.hq.storeMS.service.productCardDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.productCardDetail.apiData.ProductCardDetailQueryForm;
import com.hq.storeMS.service.productCardDetail.bs.ProductCardDetailHandler;
import com.hq.storeMS.service.productCardDetail.data.ProductCardDetail;

@RestController
@RequestMapping(value = "/productCardDetail")
public class ProductCardDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getProductCardDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getProductCardDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "cardNameOrNumber", defaultValue = "") String cardNameOrNumber,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId).setStatus(status).setTypeId(typeId)
				.setCardNameOrNumber(cardNameOrNumber).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ProductCardDetailHandler.getInstance().getProductCardDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{productCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductCardDetail>> getStoreProductCardDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("productCardDetailId") String productCardDetailId) {
		ReqResult<ProductCardDetail> result = ProductCardDetailHandler.getInstance().getStoreProductCardDetail(storeId, productCardDetailId);
		ResponseEntity<RestResp<ProductCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{productCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductCardDetail>> getProductCardDetail(
			@PathVariable("productCardDetailId") String productCardDetailId) {
		ReqResult<ProductCardDetail> result = ProductCardDetailHandler.getInstance().getStoreProductCardDetail(0, productCardDetailId);
		ResponseEntity<RestResp<ProductCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
