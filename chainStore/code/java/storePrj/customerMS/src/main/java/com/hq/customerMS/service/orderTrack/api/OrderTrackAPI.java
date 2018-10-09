package com.hq.customerMS.service.orderTrack.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.orderTrack.bs.OrderTrackHandler;
import com.hq.storeClient.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.storeClient.service.orderTrack.data.OrderTrack;

@RestController
@RequestMapping(value = "/orderTrack")
public class OrderTrackAPI{

	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderTrack>> getOrderTrack(
			@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId) {
		ReqResult<OrderTrack> result = OrderTrackHandler.getInstance().getOrderTrack(storeId, orderId);
		ResponseEntity<RestResp<OrderTrack>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<OrderTrack>> updateOrderTrack(
			@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId,
			@RequestBody OrderTrackUpdateApiForm inputForm) {
		ReqResult<OrderTrack> result = OrderTrackHandler.getInstance().updateOrderTrack(storeId, orderId, inputForm);
		ResponseEntity<RestResp<OrderTrack>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
