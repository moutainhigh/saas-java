package com.hq.orderMS.service.order.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.orderMS.service.common.PageResp;
import com.hq.orderMS.service.common.ReqResult;
import com.hq.orderMS.service.common.RestResp;
import com.hq.orderMS.service.order.apiData.OrderAddApiForm;
import com.hq.orderMS.service.order.apiData.OrderQueryForm;
import com.hq.orderMS.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderMS.service.order.bs.OrderHandler;
import com.hq.orderMS.service.order.data.Order;
import com.hq.orderMS.service.order.data.OrderCount;
import com.hq.orderMS.service.order.data.OrderDateGroup;

@RestController
@RequestMapping(value = "/order")
public class OrderAPI {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findOrderPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findOrderPageInfo(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "orderType", defaultValue = "-1") int orderType,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "origin", defaultValue = "") Set<Integer> origin,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "minPayTime", defaultValue = "0") long minPayTime,
			@RequestParam(value = "maxPayTime", defaultValue = "0") long maxPayTime,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "cuserId", defaultValue = "0") long cuserId,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setNumberOrName(numberOrName).setOrderType(orderType).setStatus(status).setOrigin(origin)
				.setMinTime(minTime).setMaxTime(maxTime).setMinPayTime(minPayTime).setMaxPayTime(maxPayTime)
				.setLeaguerId(leaguerId).setCuserId(cuserId).setBuserId(buserId).setStoreId(storeId)
				.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = OrderHandler.getInstance().findOrderPageInfo(params);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findLeaguerOrderList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Order>> findLeaguerOrderList(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "leaguerId") String leaguerId, @RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setMinTime(minTime).setMaxTime(maxTime).setLeaguerId(leaguerId).setStoreId(storeId)
				.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<Order> result = OrderHandler.getInstance().findOrderList(params);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findOrderDateGroupList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderDateGroup>> findOrderDateGroupList(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "orderType", defaultValue = "-1") int orderType,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "origin", defaultValue = "") Set<Integer> origin,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "cuserId", defaultValue = "0") long cuserId,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OrderQueryForm queryForm = OrderQueryForm.newInstance();
		queryForm.setNumberOrName(numberOrName).setOrderType(orderType).setStatus(status).setOrigin(origin)
				.setMinTime(minTime).setMaxTime(maxTime).setLeaguerId(leaguerId).setCuserId(cuserId).setBuserId(buserId)
				.setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<OrderDateGroup> result = OrderHandler.getInstance().findOrderDateGroupList(queryForm);
		ResponseEntity<RestResp<OrderDateGroup>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findOrderList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Order>> findOrderList(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "orderType", defaultValue = "-1") int orderType,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "origin", defaultValue = "") Set<Integer> origin,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "minPayTime", defaultValue = "0") long minPayTime,
			@RequestParam(value = "maxPayTime", defaultValue = "0") long maxPayTime,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "cuserId", defaultValue = "0") long cuserId,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setNumberOrName(numberOrName).setOrderType(orderType).setStatus(status).setOrigin(origin)
				.setMinTime(minTime).setMaxTime(maxTime).setMinPayTime(minPayTime).setMaxPayTime(maxPayTime)
				.setLeaguerId(leaguerId).setCuserId(cuserId).setBuserId(buserId).setStoreId(storeId)
				.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<Order> result = OrderHandler.getInstance().findOrderList(params);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/getOrderCount", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OrderCount>> getOrderCount(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "orderType", defaultValue = "-1") int orderType,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "origin", defaultValue = "") Set<Integer> origin,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "cuserId", defaultValue = "0") long cuserId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setNumberOrName(numberOrName).setOrderType(orderType).setStatus(status).setOrigin(origin)
				.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId).setPageItemCount(pageItemCount)
				.setPageNo(pageNo).setLeaguerId(leaguerId).setCuserId(cuserId);
		ReqResult<OrderCount> result = OrderHandler.getInstance().getOrderCount(params);
		ResponseEntity<RestResp<OrderCount>> respEntity = result.buildRespEntity();
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

	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<Order>> deleteOrder(@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId) {
		ReqResult<Order> result = OrderHandler.getInstance().deleteOrder(storeId, orderId);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> addOrder(@RequestBody OrderAddApiForm inputForm) {
		ReqResult<Order> result = OrderHandler.getInstance().addOrder(inputForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
