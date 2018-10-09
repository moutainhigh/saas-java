package com.hq.storeMS.service.orderNotes.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.orderNotes.apiData.ChargeBackRecordAddForm;
import com.hq.storeMS.service.orderNotes.apiData.RevokeContentAddForm;
import com.hq.storeMS.service.orderNotes.bs.OrderNotesHandler;
import com.hq.storeMS.service.orderNotes.data.OrderNotes;

@RestController
@RequestMapping(value = "/orderNotes")
public class OrderNotesAPI {
	@Deprecated
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderNotes>> getOrderNotes(@PathVariable("orderId") long orderId) {
		ReqResult<OrderNotes> result = OrderNotesHandler.getInstance().getOrderNotes(0, orderId);
		ResponseEntity<RestResp<OrderNotes>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderNotes>> getOrderNotes(@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId) {
		ReqResult<OrderNotes> result = OrderNotesHandler.getInstance().getOrderNotes(storeId, orderId);
		ResponseEntity<RestResp<OrderNotes>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/chargeBackRecord/{storeId}/{orderId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<OrderNotes>> addChargeBackRecord(@PathVariable("orderId") long orderId,
			@PathVariable("storeId") long storeId, @RequestBody ChargeBackRecordAddForm inputForm) {
		ReqResult<OrderNotes> result = OrderNotesHandler.getInstance().addChargeBackRecord(storeId, orderId, inputForm);
		ResponseEntity<RestResp<OrderNotes>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@Deprecated
	@RequestMapping(value = "/chargeBackRecord/{orderId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<OrderNotes>> addChargeBackRecord(@PathVariable("orderId") long orderId,
			@RequestBody ChargeBackRecordAddForm inputForm) {
		ReqResult<OrderNotes> result = OrderNotesHandler.getInstance().addChargeBackRecord(0L, orderId, inputForm);
		ResponseEntity<RestResp<OrderNotes>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/revokeContent/{storeId}/{orderId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<OrderNotes>> revokeOrder(@PathVariable("orderId") long orderId,
			@PathVariable("storeId") long storeId, @RequestBody RevokeContentAddForm inputForm) {
		ReqResult<OrderNotes> result = OrderNotesHandler.getInstance().revokeOrder(storeId, orderId, inputForm);
		ResponseEntity<RestResp<OrderNotes>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
