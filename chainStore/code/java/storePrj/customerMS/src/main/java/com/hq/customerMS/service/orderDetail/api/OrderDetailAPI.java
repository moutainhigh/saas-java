package com.hq.customerMS.service.orderDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.orderDetail.bs.OrderDetailHandler;
import com.hq.storeClient.service.orderDetail.data.OrderDetail;

@RestController
@RequestMapping(value = "/orderDetail")
public class OrderDetailAPI{
	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderDetail>> getOrderDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId) {
		ReqResult<OrderDetail> result = OrderDetailHandler.getInstance().getOrderDetail(storeId, orderId);
		ResponseEntity<RestResp<OrderDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
