package com.hq.chainMS.service.chainUser.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.chainUser.apiData.ResetPasswordForm;
import com.hq.chainMS.service.chainUser.bs.ChainUserHandler;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/reset")
public class ChainUserResetPasswordAPI {
	// 未登录状态修改密码
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ChainUser>> resetPassword(@RequestBody ResetPasswordForm inputForm) {
		ReqResult<ChainUser> result = ChainUserHandler.getInstance().resetPassword(inputForm);
		ResponseEntity<RestResp<ChainUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
