package com.hq.storeMS.service.storeChain.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeChain.apiData.StoreChainUpdateApiForm;
import com.hq.storeMS.service.storeChain.bs.StoreChainHandler;
import com.hq.storeMS.service.storeChain.data.StoreChain;

/**
 * 连锁店掉智美店的接口
 * 项目、商品、套餐、卡包等  连锁店下线 智美店的数据也需要跟着下线  
 * @author kevin
 *
 */
@RestController
@RequestMapping(value = "/storeChain")
public class StoreChainAPI {
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreChain>> updateStoreChain(
			@PathVariable("id") String id,
			@RequestBody StoreChainUpdateApiForm inputForm) {
		ReqResult<StoreChain> result = StoreChainHandler.getInstance().updateStoreChain(inputForm);
		ResponseEntity<RestResp<StoreChain>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
