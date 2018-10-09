package com.hq.storeMS.service.saas.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.saas.apiData.OPStoreQueryApiForm;
import com.hq.storeMS.service.saas.apiData.OPStoreUpdateApiForm;
import com.hq.storeMS.service.saas.bs.SaasHandler;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreRO;

@RestController
@RequestMapping(value = "/op/store" )
public class OPStoreAPI {
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<Store>> updateStore(@PathVariable("id") long storeId,@RequestBody OPStoreUpdateApiForm inputForm)  
    {  
		
		ReqResult<Store> result = SaasHandler.getInstance().updateStore(storeId, inputForm);
		
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreRO>> list(
    		@RequestParam("pageItemCount")int pageItemCount,
    		@RequestParam("pageNo")int pageNo)  
    {  
		ReqResult<StoreRO> result = SaasHandler.getInstance().list(pageItemCount,pageNo);
		
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreRO>> getStore(@PathVariable("id") long id)  
    {  
		ReqResult<StoreRO> result = SaasHandler.getInstance().findById(id);
		
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/findStoreList" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreRO>> findStoreList(@ModelAttribute OPStoreQueryApiForm queryForm)  
    {  
		ReqResult<StoreRO> result = SaasHandler.getInstance().findStoreList(queryForm);
		
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	

}
