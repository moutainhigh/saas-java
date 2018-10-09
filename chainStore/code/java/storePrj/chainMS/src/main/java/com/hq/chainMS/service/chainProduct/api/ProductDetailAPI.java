package com.hq.chainMS.service.chainProduct.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.chainProduct.apiData.ProductDetailQueryForm;
import com.hq.chainMS.service.chainProduct.bs.ProductDetailHandler;
import com.hq.chainMS.service.chainProduct.data.ProductDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/productDetail")
public class ProductDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getProductDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getProductDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "stateArray", defaultValue = "") Set<Integer> stateArray,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ProductDetailQueryForm queryForm = ProductDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setChainId(chainId).setNumberOrName(numberOrName)
				.setTypeId(typeId).setStateArray(stateArray).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ProductDetailHandler.getInstance().getProductDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}/{productDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductDetail>> getProductDetail(@PathVariable("chainId") long chainId,
			@PathVariable("productDetailId") String productDetailId) {
		ReqResult<ProductDetail> result = ProductDetailHandler.getInstance().getProductDetail(chainId, productDetailId);
		ResponseEntity<RestResp<ProductDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
