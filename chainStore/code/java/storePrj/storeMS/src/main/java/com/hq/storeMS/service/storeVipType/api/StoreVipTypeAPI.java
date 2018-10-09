package com.hq.storeMS.service.storeVipType.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storeVipType.apiData.AddStoreVipTypeForm;
import com.hq.storeMS.service.storeVipType.apiData.UpdateStoreVipTypeForm;
import com.hq.storeMS.service.storeVipType.bs.StoreVipTypeHandler;
import com.hq.storeMS.service.storeVipType.data.StoreVipType;

@RestController
@RequestMapping(value = "/storeVipType")
public class StoreVipTypeAPI {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreVipType>> get(@PathVariable("id") long id) {
		ReqResult<StoreVipType> result = StoreVipTypeHandler.getInstance().get(id);
		ResponseEntity<RestResp<StoreVipType>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreVipType>> findPage(
			@RequestParam("pageItemCount") int pageItemCount,
			@RequestParam("pageNo") int pageNo) {
		ReqResult<StoreVipType> result = StoreVipTypeHandler.getInstance().findPage(pageItemCount, pageNo);
		ResponseEntity<RestResp<StoreVipType>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findVipByName" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<StoreVipType>> findVipByName(@RequestParam("vipName") String vipName)  
    {  
		ReqResult<StoreVipType> result = StoreVipTypeHandler.getInstance().findVipByName(vipName);
		
		ResponseEntity<RestResp<StoreVipType>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
    
    @RequestMapping(value = "/" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<StoreVipType>> addStoreVipType(@RequestBody AddStoreVipTypeForm addForm)  
    {  
		ReqResult<StoreVipType> result = StoreVipTypeHandler.getInstance().addStoreVipType(addForm);
		
		ResponseEntity<RestResp<StoreVipType>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
    
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<StoreVipType>> updateStoreVipTypeInfo(@RequestBody UpdateStoreVipTypeForm updateForm)  
    {  
		ReqResult<StoreVipType> result = StoreVipTypeHandler.getInstance().updateStoreVipTypeInfo(updateForm);
		
		ResponseEntity<RestResp<StoreVipType>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
}
