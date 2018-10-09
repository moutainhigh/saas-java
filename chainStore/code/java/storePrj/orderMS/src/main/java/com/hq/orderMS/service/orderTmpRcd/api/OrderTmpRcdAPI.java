package com.hq.orderMS.service.orderTmpRcd.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.orderMS.service.common.ReqResult;
import com.hq.orderMS.service.common.RestResp;
import com.hq.orderMS.service.orderTmpRcd.bs.OrderTmpRcdHandler;
import com.hq.orderMS.service.orderTmpRcd.data.OrderTmpRcd;

@RestController
@RequestMapping(value = "/orderTmpRcd")
public class OrderTmpRcdAPI {
	@RequestMapping(value = "/checkOrderTmpRcd", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<OrderTmpRcd>> checkOrderTmpRcd() {
		ReqResult<OrderTmpRcd> result = OrderTmpRcdHandler.getInstance().checkOrderTmpRcd();
		ResponseEntity<RestResp<OrderTmpRcd>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
}
