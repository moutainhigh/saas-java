package com.hq.storeMS.service.goodsDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeMS.service.goodsDetail.bs.GoodsDetailHandler;
import com.hq.storeMS.service.goodsDetail.data.GoodsDetail;

@RestController
@RequestMapping(value = "/goodsDetail")
public class GoodsDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getGoodsDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getGoodsDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "state", defaultValue = "-1") int state,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		GoodsDetailQueryForm queryForm = GoodsDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId)
			.setNumberOrName(numberOrName).setTypeId(typeId).setState(state)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = GoodsDetailHandler.getInstance().getGoodsDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{goodsDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<GoodsDetail>> getStoreGoodsDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("goodsDetailId") String goodsDetailId) {
		ReqResult<GoodsDetail> result = GoodsDetailHandler.getInstance().getStoreGoodsDetail(storeId, goodsDetailId);
		ResponseEntity<RestResp<GoodsDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{goodsDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<GoodsDetail>> getGoodsDetail(
			@PathVariable("goodsDetailId") String goodsDetailId) {
		ReqResult<GoodsDetail> result = GoodsDetailHandler.getInstance().getStoreGoodsDetail(0L, goodsDetailId);
		ResponseEntity<RestResp<GoodsDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
