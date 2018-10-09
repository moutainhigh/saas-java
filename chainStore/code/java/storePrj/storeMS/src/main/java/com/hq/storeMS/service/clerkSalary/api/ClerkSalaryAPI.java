package com.hq.storeMS.service.clerkSalary.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.clerkSalary.apiData.ClerkSalaryUpdateApiForm;
import com.hq.storeMS.service.clerkSalary.bs.ClerkSalaryHandler;
import com.hq.storeMS.service.clerkSalary.data.ClerkSalary;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/clerkSalary")
public class ClerkSalaryAPI {
	
	@RequestMapping(value = "/findById" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<ClerkSalary>> findById(@RequestParam("storeId")int storeId,
    		@RequestParam("clerkId")long clerkId)  
    {  
		ReqResult<ClerkSalary> result = ClerkSalaryHandler.getInstance().findById(storeId,clerkId);
		
		ResponseEntity<RestResp<ClerkSalary>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/findByStoreId" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<ClerkSalary>> findByStoreId(@RequestParam("storeId")int storeId,
    		@RequestParam("pageItemCount") int pageItemCount,@RequestParam("pageNo") int pageNo)  
    {  
		ReqResult<ClerkSalary> result = ClerkSalaryHandler.getInstance().findByStoreId(storeId,pageItemCount,pageNo);
		
		ResponseEntity<RestResp<ClerkSalary>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "{id}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<ClerkSalary>> update(@RequestBody ClerkSalaryUpdateApiForm formInfo)  
    {  
		ReqResult<ClerkSalary> result = ClerkSalaryHandler.getInstance().update(formInfo);
		
		ResponseEntity<RestResp<ClerkSalary>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
}
