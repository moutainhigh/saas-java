package com.hq.customerMS.service.cuser.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.cuser.apiData.CUserAddApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginByCodeApiForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginWithJscodeForm;
import com.hq.customerMS.service.cuser.apiData.CUserLoginWithWxInfoForm;
import com.hq.customerMS.service.cuser.apiData.CUserResetPasswordForm;
import com.hq.customerMS.service.cuser.apiData.CUserUpdateApiForm;
import com.hq.customerMS.service.cuser.apiData.CuserAdd4Ms;
import com.hq.customerMS.service.cuser.apiData.CuserUpdate4Ms;
import com.hq.customerMS.service.cuser.apiData.LoginResp;
import com.hq.customerMS.service.cuser.apiData.WxLoginResp;
import com.hq.customerMS.service.cuser.bs.CUserHandler;
import com.hq.customerMS.service.cuser.data.CUser;

@RestController
@RequestMapping(value = "/cuser")
public class CUserAPI {
	@Autowired
	private HttpServletResponse response;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<LoginResp>> login(@RequestBody CUserLoginApiForm loginForm) {
		ReqResult<LoginResp> result = CUserHandler.getInstance().login(loginForm, response);
		ResponseEntity<RestResp<LoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/loginByCode", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<LoginResp>> loginByCode(@RequestBody CUserLoginByCodeApiForm loginForm) {
		ReqResult<LoginResp> result = CUserHandler.getInstance().loginByCode(loginForm, response);
		ResponseEntity<RestResp<LoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/loginWithJscode", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WxLoginResp>> loginWithJscode(@RequestBody CUserLoginWithJscodeForm loginForm) {
		ReqResult<WxLoginResp> result = CUserHandler.getInstance().loginWithJscode(loginForm);
		ResponseEntity<RestResp<WxLoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/loginByWxInfo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WxLoginResp>> loginByWxInfo(@RequestBody CUserLoginWithWxInfoForm loginForm) {
		ReqResult<WxLoginResp> result = CUserHandler.getInstance().loginByWxInfo(loginForm);
		ResponseEntity<RestResp<WxLoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<CUser>> reg(@RequestBody CUserAddApiForm addForm) {
		ReqResult<CUser> result = CUserHandler.getInstance().add(addForm);
		ResponseEntity<RestResp<CUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{cuserId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<CUser>> getUser(@PathVariable("cuserId") long cuserId) {
		ReqResult<CUser> result = CUserHandler.getInstance().get(cuserId);
		ResponseEntity<RestResp<CUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{cuserId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<CUser>> updateUser(
			@PathVariable("cuserId") long cuserId,
			@RequestBody CUserUpdateApiForm inputForm) {
		ReqResult<CUser> result = CUserHandler.getInstance().update(cuserId, inputForm);
		ResponseEntity<RestResp<CUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByPhone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<CUser>> findByPhone(@RequestParam("phone") long phone) {
		ReqResult<CUser> result = CUserHandler.getInstance().findByPhone(phone);
		ResponseEntity<RestResp<CUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	//未登录状态修改密码
	@RequestMapping(value = "/resetPassword" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<CUser>> resetPassword(@RequestBody CUserResetPasswordForm inputForm) {  
		ReqResult<CUser> result = CUserHandler.getInstance().resetPassword(inputForm);
		ResponseEntity<RestResp<CUser>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/addFromMs" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<CUser>> addFromMs(@RequestBody CuserAdd4Ms addForm) {  
		ReqResult<CUser> result = CUserHandler.getInstance().addFromMs(addForm);
		ResponseEntity<RestResp<CUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/updateFromMs" ,method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<RestResp<CUser>> updateFromMs(@RequestBody CuserUpdate4Ms updateForm) {  
		ReqResult<CUser> result = CUserHandler.getInstance().updateFromMs(updateForm);
		ResponseEntity<RestResp<CUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
