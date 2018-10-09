package com.hq.storeManagerMS.service.muser.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;
import com.hq.storeManagerMS.service.muser.apiData.LoginResp;
import com.hq.storeManagerMS.service.muser.apiData.MUserAddApiForm;
import com.hq.storeManagerMS.service.muser.apiData.MUserLoginApiForm;
import com.hq.storeManagerMS.service.muser.apiData.MUserUpdateApiForm;
import com.hq.storeManagerMS.service.muser.bs.MUserHandler;
import com.hq.storeManagerMS.service.muser.data.MUserRO;

@RestController
@RequestMapping(value = "/muser" )
public class MUserAPI {
	
	@RequestMapping(value = "/login" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<LoginResp>> login(@RequestBody MUserLoginApiForm loginForm){  
		ReqResult<LoginResp> result = MUserHandler.getInstance().login(loginForm);
		ResponseEntity<RestResp<LoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/reg" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<MUserRO>> addMUser(@RequestBody MUserAddApiForm addForm){  
		ReqResult<MUserRO> result = MUserHandler.getInstance().addMUser(addForm);
		ResponseEntity<RestResp<MUserRO>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<MUserRO>> getMUser(@PathVariable("id") long id) {  
		ReqResult<MUserRO> result = MUserHandler.getInstance().getMUser(id);
		ResponseEntity<RestResp<MUserRO>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<MUserRO>> updateMUser(@RequestBody MUserUpdateApiForm inputForm) {  
		ReqResult<MUserRO> result = MUserHandler.getInstance().update(inputForm);
		ResponseEntity<RestResp<MUserRO>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/findByAccount" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<MUserRO>> findByAccount(@RequestParam("account") String account) {  
		ReqResult<MUserRO> result = MUserHandler.getInstance().findByAccount(account);
		ResponseEntity<RestResp<MUserRO>> respEntity = result.buildRespEntity();
		return respEntity;
    }    
}
