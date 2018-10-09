package com.hq.thirdPartyServer.service.businessCard.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hq.thirdPartyServer.service.businessCard.apiData.BusinessCardForm;
import com.hq.thirdPartyServer.service.businessCard.bs.BusinessCardMgr;
import com.hq.thirdPartyServer.service.businessCard.data.BusinessCardResp;
import com.hq.thirdPartyServer.service.common.ReqResult;
import com.hq.thirdPartyServer.service.common.RestResp;

/**
 * 
 * ClassName: SmsAPI <br/>
 * Function: TODO 名片识别API <br/>
 * 
 * @author kevin
 * @version
 * @since JDK 1.6
 */

@RestController
@RequestMapping(value = "/businessCard")
public class BusinessCardAPI {
	@RequestMapping(value = "/readCardInfo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BusinessCardResp>> readCardInfo(
			@RequestParam(value="img") MultipartFile img) {
		ReqResult<BusinessCardResp> result = BusinessCardMgr.getInstance().readIdCardInfo(img);
		ResponseEntity<RestResp<BusinessCardResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/readCardInfoByBase64", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BusinessCardResp>> readCardInfoByBase64(
			@RequestBody BusinessCardForm apiForm) {
		ReqResult<BusinessCardResp> result = BusinessCardMgr.getInstance().readCardInfoByBase64(apiForm);
		ResponseEntity<RestResp<BusinessCardResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
