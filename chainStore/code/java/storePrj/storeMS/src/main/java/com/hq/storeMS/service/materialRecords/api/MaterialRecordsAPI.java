package com.hq.storeMS.service.materialRecords.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.materialRecords.apiData.MaterialRecordsAddApiForm;
import com.hq.storeMS.service.materialRecords.bs.MaterialRecordsHandler;
import com.hq.storeMS.service.materialRecords.data.MaterialRecords;

@RestController
@RequestMapping(value = "/materialRecords")
public class MaterialRecordsAPI {
	
	@RequestMapping(value = "/findByStoreId" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<MaterialRecords>> findByStoreId(
    		@RequestParam(value="maxTime", defaultValue="0")long maxTime,
    		@RequestParam(value="minTime", defaultValue="0")long minTime,
    		@RequestParam(value="storeId")int storeId,
    		@RequestParam(value="pageItemCount", defaultValue="0")int pageItemCount,
    		@RequestParam(value="pageNo", defaultValue="1")int pageNo)  
    {  
		ReqResult<MaterialRecords> result = MaterialRecordsHandler.getInstance().findByStoreId(storeId,maxTime,minTime,pageItemCount,pageNo);
		
		ResponseEntity<RestResp<MaterialRecords>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/findByMaterialId" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<MaterialRecords>> findByMaterialId(@RequestParam("materialId") String materialId,
    		@RequestParam("pageItemCount")int pageItemCount,
    		@RequestParam("pageNo")int pageNo)  
    {  
		ReqResult<MaterialRecords> result = MaterialRecordsHandler.getInstance().findByMaterialId(materialId,pageItemCount,pageNo);
		
		ResponseEntity<RestResp<MaterialRecords>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<MaterialRecords>> get(@PathVariable("id") long id)  
    {  
		ReqResult<MaterialRecords> result = MaterialRecordsHandler.getInstance().get(id);
		
		ResponseEntity<RestResp<MaterialRecords>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<MaterialRecords>> add(@RequestBody MaterialRecordsAddApiForm addForm)  
    {  
		ReqResult<MaterialRecords> result = MaterialRecordsHandler.getInstance().add(addForm);
		
		ResponseEntity<RestResp<MaterialRecords>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
}
