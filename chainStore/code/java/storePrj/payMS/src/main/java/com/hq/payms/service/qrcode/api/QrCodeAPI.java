package com.hq.payms.service.qrcode.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.payms.service.common.ReqResult;
import com.hq.payms.service.common.RestResp;
import com.hq.payms.service.qrcode.apiData.QrCodeAPIForm;
import com.hq.payms.service.qrcode.apiData.QrCodeResp;
import com.hq.payms.service.qrcode.bs.QrCodeHandler;

/**
 * 
 * ClassName: QrCodeAPI <br/>  
 * Function: TODO 二维码API <br/>  
 *  
 * @author kevin 
 * @version   
 * @since JDK 1.6
 */

@RestController
@RequestMapping(value = "/qrcode")
public class QrCodeAPI {
//	@RequestMapping(value = "/genQrCode", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<RestResp<QrCodeResp>> genQrCode(@RequestBody QrCodeAPIForm apiForm) {
//		ReqResult<QrCodeResp> result = QrCodeHandler.getInstance().genQrCode(apiForm);
//		ResponseEntity<RestResp<QrCodeResp>> respEntity = result.buildRespEntity();
//		return respEntity;
//	}
//	
//	@RequestMapping(value = "/genQrCodeList", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<RestResp<QrCodeResp>> genQrCodeList(@RequestBody List<QrCodeAPIForm> apiForms) {
//		ReqResult<QrCodeResp> result = QrCodeHandler.getInstance().genQrCodeList(apiForms);
//		ResponseEntity<RestResp<QrCodeResp>> respEntity = result.buildRespEntity();
//		return respEntity;
//	}
}
