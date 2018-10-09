package com.hq.customerMS.service.wxJsApiTicket.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.wxJsApiTicket.bs.WxJsApiTicketHandler;
import com.hq.storeClient.service.wxJsApiTicket.apiData.GenJssdkSignatureApiForm;
import com.hq.storeClient.service.wxJsApiTicket.data.WxJsApiTicket;


/**
 * 获取微信JSAPI的签名信息<br>
 * 不同的appId、pageUrl需要生成不同的签名
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "/wxJsApiTicket" )
public class WxJsApiTicketAPI {
	
	@RequestMapping(value = "/genJssdkSignature" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<WxJsApiTicket>> genJssdkSignature(@RequestBody GenJssdkSignatureApiForm form) {  
		ReqResult<WxJsApiTicket> result = WxJsApiTicketHandler.getInstance().genJssdkSignature(form);
		ResponseEntity<RestResp<WxJsApiTicket>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
}
