package com.hq.customerMS.service.wxMedia.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.wxMedia.bs.WxMediaHandler;
import com.hq.storeClient.common.restClientResp.ImgResp;
import com.hq.storeClient.service.wxMedia.apiData.WxMediaSaveApiForm;


/**
 * 微信端图片、语音、视频等多媒体文件上传<br>
 * 
 * @see https://developers.weixin.qq.com/miniprogram/dev/api/custommsg/material.html
 * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1444738730
 * @author: wujunwei 
 * @version: v1.0  
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "/wxMedia")
public class WxMediaAPI {
	
	@RequestMapping(value = "/saveImg", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ImgResp>> saveImg(@RequestBody WxMediaSaveApiForm form) {
		ReqResult<ImgResp> result = WxMediaHandler.getInstance().saveImg(form);
		ResponseEntity<RestResp<ImgResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
