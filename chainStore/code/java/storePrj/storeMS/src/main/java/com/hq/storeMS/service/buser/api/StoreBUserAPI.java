package com.hq.storeMS.service.buser.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buser.bs.StoreBUserHandler;
import com.hq.storeMS.service.buser.data.StoreBUser;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/storeBUser" )
public class StoreBUserAPI {
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreBUser>> getStoreBUserInfo(@PathVariable("id") long id) {  
		ReqResult<StoreBUser> result = StoreBUserHandler.getInstance().get(id);
		ResponseEntity<RestResp<StoreBUser>> respEntity = result.buildRespEntity();
		return respEntity;
    }
    
}
