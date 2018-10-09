package com.hq.storeMS.service.buser.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buser.apiData.BUserResetPasswordForm;
import com.hq.storeMS.service.buser.bs.BUserHandler;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/reset")
public class BUserResetPasswordAPI {
	// 未登录状态修改密码
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> resetPassword(
			@RequestBody BUserResetPasswordForm inputForm) {
		ReqResult<BUser> result = BUserHandler.getInstance().resetPassword(inputForm);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
