package com.hq.storeMS.service.productDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.productDetail.apiData.ProductDetailQueryForm;
import com.hq.storeMS.service.productDetail.bs.ProductDetailHandler;
import com.hq.storeMS.service.productDetail.data.ProductDetail;

@RestController
@RequestMapping(value = "/productDetail")
public class ProductDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getProductDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getProductDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "state", defaultValue = "-1") int state,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ProductDetailQueryForm queryForm = ProductDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId)
			.setNumberOrName(numberOrName).setTypeId(typeId).setState(state)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ProductDetailHandler.getInstance().getProductDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{productDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductDetail>> getStoreProductDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("productDetailId") String productDetailId) {
		ReqResult<ProductDetail> result = ProductDetailHandler.getInstance().getStoreProductDetail(storeId, productDetailId);
		ResponseEntity<RestResp<ProductDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{productDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductDetail>> getProductDetail(
			@PathVariable("productDetailId") String productDetailId) {
		ReqResult<ProductDetail> result = ProductDetailHandler.getInstance().getStoreProductDetail(0, productDetailId);
		ResponseEntity<RestResp<ProductDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
