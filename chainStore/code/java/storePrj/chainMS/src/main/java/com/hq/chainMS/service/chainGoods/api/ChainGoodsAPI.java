package com.hq.chainMS.service.chainGoods.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.chainGoods.apiData.ChainGoodsUpdateForm;
import com.hq.chainMS.service.chainGoods.bs.ChainGoodsHandler;
import com.hq.chainMS.service.chainGoods.data.ChainGoods;

@RestController
@RequestMapping(value = "/chainGoods")
public class ChainGoodsAPI {

	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainGoods>> getChainGoods(
			@PathVariable("chainId") long chainId) {
		ReqResult<ChainGoods> result = ChainGoodsHandler.getInstance().get(chainId);
		ResponseEntity<RestResp<ChainGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<ChainGoods>> updateChainGoods(
			@PathVariable("chainId") long chainId,
			@RequestBody ChainGoodsUpdateForm updateForm) {
		ReqResult<ChainGoods> result = ChainGoodsHandler.getInstance().update(chainId, updateForm);
		ResponseEntity<RestResp<ChainGoods>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
