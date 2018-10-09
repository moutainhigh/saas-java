package com.hq.customerMS.service.wxOpenId.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.wxOpenId.bs.WxOpenIdHandler;
import com.hq.storeClient.service.wxOpenId.apiData.GenOpenIdApiForm;
import com.hq.storeClient.service.wxOpenId.data.WxOpenId;


/**
 * 获取微信用户的OpenId<br>
 * 不同的appId、jsCode需要重新获取OpenId
 * 
 * @see https://developers.weixin.qq.com/miniprogram/dev/api/api-login.html#wxloginobject
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "/wxOpenId")
public class WxOpenIdAPI {
	
	@RequestMapping(value = "/genOpenId" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<WxOpenId>> genOpenId(@RequestBody GenOpenIdApiForm form) {  
		ReqResult<WxOpenId> result = WxOpenIdHandler.getInstance().genOpenId(form);
		ResponseEntity<RestResp<WxOpenId>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
}
