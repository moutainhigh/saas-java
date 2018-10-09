package com.hq.chainMS.service.chainCard.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.chainCard.apiData.ProductCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.bs.ProductCardDetailHandler;
import com.hq.chainMS.service.chainCard.data.ProductCardDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/productCardDetail")
public class ProductCardDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getProductCardDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getProductCardDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "statusSet", defaultValue = "") Set<Integer> statusSet,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "cardNameOrNumber", defaultValue = "") String cardNameOrNumber,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ProductCardDetailQueryForm queryForm = ProductCardDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setChainId(chainId).setStatusSet(statusSet).setTypeId(typeId)
				.setCardNameOrNumber(cardNameOrNumber).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ProductCardDetailHandler.getInstance().getProductCardDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}/{productCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductCardDetail>> getProductCardDetail(@PathVariable("chainId") long chainId,
			@PathVariable("productCardDetailId") String productCardDetailId) {
		ReqResult<ProductCardDetail> result = ProductCardDetailHandler.getInstance().getProductCardDetail(chainId, productCardDetailId);
		ResponseEntity<RestResp<ProductCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
