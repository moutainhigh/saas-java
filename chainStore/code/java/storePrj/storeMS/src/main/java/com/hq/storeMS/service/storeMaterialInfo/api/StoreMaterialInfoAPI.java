package com.hq.storeMS.service.storeMaterialInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeMaterialInfo.apiData.StoreMaterialInfoUpdateForm;
import com.hq.storeMS.service.storeMaterialInfo.bs.StoreMaterialInfoHandler;
import com.hq.storeMS.service.storeMaterialInfo.data.StoreMaterialInfo;

@RestController
@RequestMapping(value = "/storeMaterialInfo")
public class StoreMaterialInfoAPI {
	
	@RequestMapping(value = "/{storeId}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreMaterialInfo>> getByStoreId(@PathVariable("storeId") long storeId)  
    {  
		ReqResult<StoreMaterialInfo> result = StoreMaterialInfoHandler.getInstance().getByStoreId(storeId);
		
		ResponseEntity<RestResp<StoreMaterialInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<StoreMaterialInfo>> update(@RequestBody StoreMaterialInfoUpdateForm updateForm)  
    {  
		ReqResult<StoreMaterialInfo> result = StoreMaterialInfoHandler.getInstance().update(updateForm);
		
		ResponseEntity<RestResp<StoreMaterialInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	

}
