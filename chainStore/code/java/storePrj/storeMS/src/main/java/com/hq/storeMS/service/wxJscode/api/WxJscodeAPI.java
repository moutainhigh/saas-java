package com.hq.storeMS.service.wxJscode.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.wxJscode.apiData.DecryptWxAppletForm;
import com.hq.storeMS.service.wxJscode.apiData.WxJsCodeForm;
import com.hq.storeMS.service.wxJscode.bs.WxJscodeHandler;
import com.hq.storeMS.service.wxJscode.data.UserInfo;
import com.hq.storeMS.service.wxJscode.data.WxJscode;

/**
 * 获取微信用户的OpenId/session_key/unionId<br>
 * 
 * @author: kevin
 */
@RestController
@RequestMapping(value = "/wxJscode")
public class WxJscodeAPI {
	@RequestMapping(value = "/jsCode2Session", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WxJscode>> jsCode2Session(@RequestBody WxJsCodeForm inputForm) {
		ReqResult<WxJscode> result = WxJscodeHandler.getInstance().jsCode2Session(inputForm);
		ResponseEntity<RestResp<WxJscode>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/decryptWXAppletInfo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<UserInfo>> decryptWXAppletInfo(@RequestBody DecryptWxAppletForm inputForm) {
		ReqResult<UserInfo> result = WxJscodeHandler.getInstance().decryptWXAppletInfo(inputForm);
		ResponseEntity<RestResp<UserInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
