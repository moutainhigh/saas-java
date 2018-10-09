package com.hq.storeMS.service.storeCardInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeCardInfo.apiData.StoreCardInfoUpdateApiForm;
import com.hq.storeMS.service.storeCardInfo.bs.StoreCardInfoHandler;
import com.hq.storeMS.service.storeCardInfo.data.StoreCardInfo;

/**
 * 
 * ClassName: StoreCardInfoAPI <br/>  
 * Function: TODO 店铺卡片管理API<br/>  
 *  
 * @author kevin 
 * @version 1.0
 * @since JDK 1.6
 */
@RestController
@RequestMapping(value = "/storeCardInfo")
public class StoreCardInfoAPI {

	@RequestMapping(value = "/findSimpleStoreInfo/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreCardInfo>> findSimpleStoreInfo(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreCardInfo> result = StoreCardInfoHandler.getInstance().getStoreCardInfoSimple(storeId);
		ResponseEntity<RestResp<StoreCardInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreCardInfo>> getStoreCardInfo(
			@PathVariable("storeId") long storeId) {
		ReqResult<StoreCardInfo> result = StoreCardInfoHandler.getInstance().getStoreCardInfoSimple(storeId);
		ResponseEntity<RestResp<StoreCardInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StoreCardInfo>> updateStoreCardInfo(
			@PathVariable("storeId") long storeId,
			@RequestBody StoreCardInfoUpdateApiForm inputForm) {
		ReqResult<StoreCardInfo> result = StoreCardInfoHandler.getInstance().updateStoreCardInfo(storeId, inputForm);
		ResponseEntity<RestResp<StoreCardInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
