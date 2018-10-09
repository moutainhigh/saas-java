package com.hq.customerMS.service.goodsDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.goodsDetail.bs.GoodsDetailHandler;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.goodsDetail.apiData.GoodsDetailQueryForm;
import com.hq.storeClient.service.goodsDetail.data.GoodsDetail;

@RestController
@RequestMapping(value = "/goodsDetail")
public class GoodsDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getGoodsDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getGoodsDetailPageInfo(@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		GoodsDetailQueryForm queryForm = GoodsDetailQueryForm.newInstance();
		queryForm.setNumberOrName(numberOrName).setTypeId(typeId).setPageItemCount(pageItemCount).setPageNo(pageNo)
				.setStoreId(storeId);
		ReqResult<PageResp> result = GoodsDetailHandler.getInstance().getGoodsDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{goodsDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<GoodsDetail>> getGoodsDetail(@PathVariable("storeId") long storeId,
			@PathVariable("goodsDetailId") String goodsDetailId) {
		ReqResult<GoodsDetail> result = GoodsDetailHandler.getInstance().getGoodsDetail(storeId, goodsDetailId);
		ResponseEntity<RestResp<GoodsDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
