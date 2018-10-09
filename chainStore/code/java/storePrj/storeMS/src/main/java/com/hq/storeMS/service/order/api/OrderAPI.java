package com.hq.storeMS.service.order.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderDateGroup;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.order.apiData.MallOrderQueryForm;
import com.hq.storeMS.service.order.apiData.OrderAddByWorkflowDataIdForm;
import com.hq.storeMS.service.order.apiData.OrderItemAddForm;
import com.hq.storeMS.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeMS.service.order.apiData.PreOrderAddForm;
import com.hq.storeMS.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeMS.service.order.bs.OrderHandler;

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
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setNumberOrName(numberOrName).setOrderType(orderType).setStatus(status).setOrigin(origin)
				.setMinTime(minTime).setMaxTime(maxTime).setLeaguerId(leaguerId).setCuserId(cuserId).setBuserId(buserId)
				.setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<OrderDateGroup> result = OrderHandler.getInstance().findOrderDateGroupList(params);
		ResponseEntity<RestResp<OrderDateGroup>> respEntity = result.buildRespEntity();
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
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OrderQueryForm params = OrderQueryForm.newInstance();
		params.setNumberOrName(numberOrName).setOrderType(orderType).setStatus(status).setOrigin(origin)
				.setMinTime(minTime).setMaxTime(maxTime).setLeaguerId(leaguerId).setCuserId(cuserId).setBuserId(buserId)
				.setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<OrderCount> result = OrderHandler.getInstance().getOrderCount(params);
		ResponseEntity<RestResp<OrderCount>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Order>> getStoreOrder(@PathVariable("storeId") long storeId,
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
	public ResponseEntity<RestResp<Order>> deleteStoreOrder(@PathVariable("storeId") long storeId,
			@PathVariable("orderId") long orderId) {
		ReqResult<Order> result = OrderHandler.getInstance().deleteOrder(storeId, orderId);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/addOrderByWorkflowDataId", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> addOrderByWorkflowDataId(
			@RequestBody OrderAddByWorkflowDataIdForm inputForm) {
		ReqResult<Order> result = OrderHandler.getInstance().addOrderByWorkflowDataId(inputForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/addPreOrder", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> addPreOrder(@RequestBody PreOrderAddForm inputForm) {
		ReqResult<Order> result = OrderHandler.getInstance().addPreOrder(inputForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/*************************** 小程序商城 ***************************/
	@RequestMapping(value = "/addPreOrderForCuser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> addPreOrderForCuser(@RequestBody PreOrderForCuserAddForm inputForm) {
		ReqResult<Order> result = OrderHandler.getInstance().addPreOrderForCuser(inputForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/payOrderByCuser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> payOrderByCuser(@RequestBody PayOrderForCuserForm inputForm) {
		ReqResult<Order> result = OrderHandler.getInstance().payOrderByCuser(inputForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/*************************** 已过时的接口 ***************************/
	@Deprecated
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Order>> getOrder(@PathVariable("orderId") long orderId) {
		ReqResult<Order> result = OrderHandler.getInstance().getOrder(0L, orderId);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@Deprecated
	@RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<Order>> deleteOrder(@PathVariable("orderId") long orderId) {
		ReqResult<Order> result = OrderHandler.getInstance().deleteOrder(0L, orderId);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@Deprecated
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Order>> addOrder(@RequestBody OrderItemAddForm inputForm) {
		ReqResult<Order> result = OrderHandler.getInstance().addOrder(inputForm);
		ResponseEntity<RestResp<Order>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
