package com.hq.storeMS.service.orderTrack.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.orderTrack.apiData.OrderTrackUpdateApiForm;
import com.hq.storeMS.service.orderTrack.bs.OrderTrackHandler;
import com.hq.storeMS.service.orderTrack.data.OrderTrack;

@RestController
@RequestMapping(value = "/orderTrack")
public class OrderTrackAPI {
	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderTrack>> get(@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId) {
		ReqResult<OrderTrack> result = OrderTrackHandler.getInstance().get(storeId, orderId);
		ResponseEntity<RestResp<OrderTrack>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<OrderTrack>> update(@PathVariable("orderId") long orderId,
			@PathVariable("storeId") long storeId, @RequestBody OrderTrackUpdateApiForm inputForm) {
		ReqResult<OrderTrack> result = OrderTrackHandler.getInstance().update(storeId, orderId, inputForm);
		ResponseEntity<RestResp<OrderTrack>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
