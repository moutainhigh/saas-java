package com.hq.storeMS.service.store.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.store.bs.StoreHandler;
import com.hq.storeMS.service.store.data.StoreRO;

@RestController
@RequestMapping(value = "/storeUnAuth" )
public class StoreUnAuthAPI {
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreRO>> getStore(@PathVariable("id") long id)  
    {  
		ReqResult<StoreRO> result = StoreHandler.getInstance().get(id);
		
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
}
