package com.hq.storeManagerMS.service.storeMenu.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeClient.service.storeMenu.data.StoreMenu;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;
import com.hq.storeManagerMS.service.storeMenu.bs.StoreMenuHandler;

@RestController
@RequestMapping(value = "/storeMenu")
public class StoreMenuAPI {
	@RequestMapping(value = "/getStoreMenu", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreMenu>> getStoreMenu() {
		ReqResult<StoreMenu> result = StoreMenuHandler.getInstance().getStoreMenu();
		ResponseEntity<RestResp<StoreMenu>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
