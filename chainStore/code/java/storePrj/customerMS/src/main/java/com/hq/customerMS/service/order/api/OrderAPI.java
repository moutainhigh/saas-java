package com.hq.customerMS.service.order.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.order.bs.OrderHandler;
import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.service.order.apiData.MallOrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderQueryForm;
import com.hq.storeClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.storeClient.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeClient.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeClient.service.order.data.Order;

@RestController
@RequestMapping(value = "/order")
public class OrderAPI {

	// 商城订单的分页查询
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findMallOrderPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findMallOrderPage(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		MallOrderQueryForm params = MallOrderQueryForm.newInstance();
		params.setNumberOrName(numberOrName).setStatus(status).setMinTime(minTime).setMaxTime(maxTime)
				.setLeaguerId(leaguerId).setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = OrderHandler.getInstance().findMallOrderPage(params);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findOrderPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findOrderPageInfo(
			@RequestParam(value = "orderType", defaultValue = "-1") int orderType,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "origin", defaultValue = "") Set<Integer> origin,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "cuserId", defaultValue = "0") long cuserId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setOrderType(orderType).setStatus(status).setOrigin(origin).setLeaguerId(leaguerId).setCuserId(cuserId)
				.setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = OrderHandler.getInstance().findOrderPageInfo(params);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Order>> getOrder(@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId) {
		ReqResult<Order> result = OrderHandler.getInstance().getOrder(storeId, orderId);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{orderId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Order>> updateOrder(@PathVariable("orderId") long orderId,
			@RequestBody OrderUpdateApiForm inputForm) {
		ReqResult<Order> result = OrderHandler.getInstance().updateOrder(orderId, inputForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> addOrder(@RequestBody PreOrderForCuserAddForm addForm) {
		ReqResult<Order> result = OrderHandler.getInstance().addOrder(addForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/payOrder", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> payOrder(@RequestBody PayOrderForCuserForm payForm) {
		ReqResult<Order> result = OrderHandler.getInstance().payOrder(payForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
