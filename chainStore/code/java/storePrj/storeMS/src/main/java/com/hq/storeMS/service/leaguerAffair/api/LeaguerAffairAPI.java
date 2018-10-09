package com.hq.storeMS.service.leaguerAffair.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.leaguerAffair.apiData.LeaguerAffairUpdateApiForm;
import com.hq.storeMS.service.leaguerAffair.bs.LeaguerAffairHandler;
import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffair;

@RestController
@RequestMapping(value = "/leaguerAffair")
public class LeaguerAffairAPI {
	
	@RequestMapping(value = "/{leaguerAffairId}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<LeaguerAffair>> get(@PathVariable("leaguerAffairId") String leaguerAffairId){  
		ReqResult<LeaguerAffair> result = LeaguerAffairHandler.getInstance().getAffair(leaguerAffairId);
		ResponseEntity<RestResp<LeaguerAffair>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{leaguerAffairId}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<LeaguerAffair>> add(@PathVariable("leaguerAffairId") String leaguerAffairId,
    		@RequestBody LeaguerAffairUpdateApiForm updateForm){  
		ReqResult<LeaguerAffair> result = LeaguerAffairHandler.getInstance().updateAffair(leaguerAffairId, updateForm);
		ResponseEntity<RestResp<LeaguerAffair>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
}
