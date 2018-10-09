package com.hq.storeMS.service.chainGoods.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainClient.service.chainGoods.data.ChainGoods;
import com.hq.chainClient.service.chainGoods.data.GoodsDetail;
import com.hq.storeMS.service.chainGoods.bs.ChainGoodsHandler;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chainGoods")
public class ChainGoodsAPI {
	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainGoods>> getChainGoods(
			@PathVariable("chainId") long chainId) {
		ReqResult<ChainGoods> result = ChainGoodsHandler.getInstance().getChainGoods(chainId);
		ResponseEntity<RestResp<ChainGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findGoodsDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<GoodsDetail>> findGoodsDetail(
			@RequestParam("goodsId") String goodsId,
			@RequestParam("chainId") long chainId) {
		ReqResult<GoodsDetail> result = ChainGoodsHandler.getInstance().getGoodsDetail(goodsId, chainId);
		ResponseEntity<RestResp<GoodsDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
