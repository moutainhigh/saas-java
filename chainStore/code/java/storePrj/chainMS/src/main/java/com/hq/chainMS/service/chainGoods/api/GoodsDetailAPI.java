package com.hq.chainMS.service.chainGoods.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.chainGoods.apiData.GoodsDetailQueryForm;
import com.hq.chainMS.service.chainGoods.bs.GoodsDetailHandler;
import com.hq.chainMS.service.chainGoods.data.GoodsDetail;

@RestController
@RequestMapping(value = "/goodsDetail")
public class GoodsDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getGoodsDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getGoodsDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "stateArray", defaultValue = "") Set<Integer> stateArray,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		GoodsDetailQueryForm queryForm = GoodsDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setChainId(chainId).setNumberOrName(numberOrName)
				.setTypeId(typeId).setStateArray(stateArray).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = GoodsDetailHandler.getInstance().getGoodsDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}/{goodsDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<GoodsDetail>> getGoodsDetail(@PathVariable("chainId") long chainId,
			@PathVariable("goodsDetailId") String goodsDetailId) {
		ReqResult<GoodsDetail> result = GoodsDetailHandler.getInstance().getGoodsDetail(chainId, goodsDetailId);
		ResponseEntity<RestResp<GoodsDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
