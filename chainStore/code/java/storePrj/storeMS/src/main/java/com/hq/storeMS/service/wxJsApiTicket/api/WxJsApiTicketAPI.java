package com.hq.storeMS.service.wxJsApiTicket.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.wxJsApiTicket.apiData.GenJssdkSignatureApiForm;
import com.hq.storeMS.service.wxJsApiTicket.bs.WxJsApiTicketHandler;
import com.hq.storeMS.service.wxJsApiTicket.data.WxJsApiTicket;

/**
 * 获取微信JSAPI的签名信息<br>
 * 不同的主体公众号appId、pageUrl需要生成不同的签名
 * 
 * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115
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
