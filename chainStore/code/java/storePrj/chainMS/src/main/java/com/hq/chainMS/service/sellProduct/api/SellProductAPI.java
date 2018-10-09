package com.hq.chainMS.service.sellProduct.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.sellProduct.apiData.SellProductQueryForm;
import com.hq.chainMS.service.sellProduct.apiData.SellProductUpdateApiForm;
import com.hq.chainMS.service.sellProduct.bs.SellProductHandler;
import com.hq.chainMS.service.sellProduct.data.SellProduct;

@RestController
@RequestMapping(value = "/sellProduct")
public class SellProductAPI {
	@RequestMapping(value = "/{chainId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<SellProduct>> updateSellProduct(@PathVariable("chainId") long chainId,
			@RequestBody SellProductUpdateApiForm sellProductForm) {
		ReqResult<SellProduct> result = SellProductHandler.getInstance().updateSellProduct(chainId, sellProductForm);
		ResponseEntity<RestResp<SellProduct>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getPageInfo(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "stateArray", defaultValue = "") Set<Integer> stateArray,
			@RequestParam(value = "sellProductTypeArray", defaultValue = "") Set<Integer> sellProductTypeArray,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		SellProductQueryForm queryForm = SellProductQueryForm.newInstance();
		queryForm.setChainId(chainId).setNumberOrName(numberOrName).setTypeId(typeId).setStateArray(stateArray)
				.setSellProductTypeArray(sellProductTypeArray).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = SellProductHandler.getInstance().getPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
}
