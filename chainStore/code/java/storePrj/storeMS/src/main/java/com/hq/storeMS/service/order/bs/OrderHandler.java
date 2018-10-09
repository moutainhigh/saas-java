package com.hq.storeMS.service.order.bs;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderRestClient.service.order.apiData.OrderAddApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderQueryForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateApiForm;
import com.hq.orderRestClient.service.order.apiData.OrderUpdateType;
import com.hq.orderRestClient.service.order.apiData.PayOrderWithNoteApiForm;
import com.hq.orderRestClient.service.order.data.Order;
import com.hq.orderRestClient.service.order.data.OrderCount;
import com.hq.orderRestClient.service.order.data.OrderDataTypeEnum;
import com.hq.orderRestClient.service.order.data.OrderDateGroup;
import com.hq.orderRestClient.service.order.data.OrderOriginEnum;
import com.hq.orderRestClient.service.order.data.OrderStatusEnum;
import com.hq.orderRestClient.service.order.data.OrderTypeEnum;
import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.OperateTips;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailMgr;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.opLog.bs.asyn.OpLogTaskMgr;
import com.hq.storeMS.service.opLog.data.OpLog;
import com.hq.storeMS.service.opLog.data.OpLogTypeEnum;
import com.hq.storeMS.service.order.apiData.MallOrderQueryForm;
import com.hq.storeMS.service.order.apiData.OrderAddByWorkflowDataIdForm;
import com.hq.storeMS.service.order.apiData.OrderItemAddForm;
import com.hq.storeMS.service.order.apiData.PayOrderForCuserForm;
import com.hq.storeMS.service.order.apiData.PayOrderForm;
import com.hq.storeMS.service.order.apiData.PreOrderAddForm;
import com.hq.storeMS.service.order.apiData.PreOrderForCuserAddForm;
import com.hq.storeMS.service.order.bs.pay.OrderBonusMgr;
import com.hq.storeMS.service.order.bs.pay.OrderPayMgr;
import com.hq.storeMS.service.order.data.MallOrder;
import com.hq.storeMS.service.order.data.OrderBeanHelper;
import com.hq.storeMS.service.orderNotes.apiData.OrderNotesAddForm;
import com.hq.storeMS.service.orderNotes.bs.OrderNotesMgr;
import com.hq.storeMS.service.orderTrack.bs.OrderTrackMgr;
import com.hq.storeMS.service.workFlowData.apiData.BonusInfoAddForm;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataMgr;
import com.hq.storeMS.service.workFlowData.data.OrderInfo;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataStatusEnum;
import com.hq.storeMS.service.workFlowType.apiData.QueryWorkFlowTypeForm;
import com.hq.storeMS.service.workFlowType.bs.WorkFlowTypeMgr;
import com.hq.storeMS.service.workFlowType.data.WorkFlowType;
import com.zenmind.common.hotSwap.HotSwap;

public class OrderHandler {
	public static OrderHandler getInstance() {
		return HotSwap.getInstance().getSingleton(OrderHandler.class);
	}

	private final LogModule logModule = LogModule.Order;

	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findMallOrderPage(MallOrderQueryForm params) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<MallOrder> pageResp = OrderMgr.getInstance().findMallOrderPage(params);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[findOrderPageInfo]", reason, e);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public ReqResult<PageResp> findOrderPageInfo(OrderQueryForm params) {
		ReqResult<PageResp> result = ReqResult.newInstance(false, PageResp.class);
		try {
			PageResp<Order> pageResp = OrderMgr.getInstance().findOrderPageInfo(params);
			result.setTarget(pageResp);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[findOrderPageInfo]", reason, e);
		}
		return result;
	}

	public ReqResult<Order> findOrderList(OrderQueryForm params) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			List<Order> orders = OrderMgr.getInstance().findOrderList(params);
			result.setTargetList(orders);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[findOrderList]", reason, e);
		}
		return result;
	}

	public ReqResult<OrderDateGroup> findOrderDateGroupList(OrderQueryForm params) {
		ReqResult<OrderDateGroup> result = ReqResult.newInstance(false, OrderDateGroup.class);
		try {
			List<OrderDateGroup> orders = OrderMgr.getInstance().findOrderDateGroupList(params);
			result.setTargetList(orders);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[findOrderDateGroupList]", reason, e);
		}
		return result;
	}

	public ReqResult<OrderCount> getOrderCount(OrderQueryForm params) {
		ReqResult<OrderCount> result = ReqResult.newInstance(false, OrderCount.class);
		try {
			OrderCount orderCount = OrderMgr.getInstance().getOrderCount(params);
			result.setTarget(orderCount);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(params);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[countOrder]", reason, e);
		}
		return result;
	}

	public ReqResult<Order> getOrder(long storeId, long orderId) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			Order order = OrderMgr.getInstance().get(storeId, orderId);
			if (order != null) {
				result.setTarget(order);
				result.setSuccess(true);
			} else {
				result.setTips("获取订单详情失败");
				result.setStatus(RespStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(orderId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[get]", reason, e);
		}
		return result;
	}

	public ReqResult<Order> updateOrder(long orderId, OrderUpdateApiForm updateInfo) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			long storeId = updateInfo.getStoreId();
			if(storeId == 0) {
				storeId = getStoreId();
			}
			OperateTips operateTips = OperateTips.newInstance(false, "修改订单失败");
			OrderUpdateType updateType = OrderUpdateType.valueOf(updateInfo.getUpdateType());
			switch (updateType) {
			case UpdateInfo:
				OrderMgr.getInstance().update(orderId, updateInfo);
				operateTips.setSuccess(true);
				break;
			case UpdateState:
				OrderMgr.getInstance().update(orderId, updateInfo);
				operateTips.setSuccess(true);
				break;
			case DeleteOrder:
				OrderMgr.getInstance().update(orderId, updateInfo);
				operateTips.setSuccess(true);
				break;
			case UpdatePayItem:
				Order order = OrderMgr.getInstance().get(storeId, orderId);
				operateTips = OrderPayMgr.getInstance().updateOrderByPayItem(order, updateInfo.getUpdatePayItemApiForm().getPayItems());
				break;
			case PayOrderWithNote:
				PayOrderWithNoteApiForm payOrderWithNoteApiForm = updateInfo.getPayOrderWithNoteApiForm();
				Order target = OrderMgr.getInstance().get(storeId, orderId);
				Long sessionBUserId = BUserAuthUtils.getInstance().getSessionBUserId();
				BUser creator = BUserQueryMgr.getInstance().getSimple(sessionBUserId);
				PayOrderForm payForm = PayOrderForm.newInstance(payOrderWithNoteApiForm.getPayItems(), payOrderWithNoteApiForm.getRemark(), creator);
				operateTips = payOrder(target, payForm);
				break;
			default:
				break;
			}
			if (operateTips.isSuccess()) {
				result.setSuccess(true);
				result.setTips("操作成功");
			} else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[updateOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(orderId, updateInfo);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	public ReqResult<Order> deleteOrder(long storeId, long orderId) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			if(storeId == 0) {
				storeId = getStoreId();
			}
			Order order = OrderMgr.getInstance().get(storeId, orderId);
			if (order != null) {
				OrderMgr.getInstance().delete(orderId, storeId);
				result.setSuccess(true);
			} else {
				result.setStatus(RespStatus.INVALID_REQUEST);
				result.setTips("删除的订单不存在");
			}
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(storeId, orderId);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(LogModule.Order, "OrderHandler[deleteOrder]", reason, e);
		}
		return result;
	}

	// 从WorkFlowData里面获取信息，生成订单 和 支付订单
	public ReqResult<Order> addOrderByWorkflowDataId(OrderAddByWorkflowDataIdForm addForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = addPreOrder(addForm.getWorkFlowDataId());
			if (order == null) {
				result.setTips("生成订单失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}

			Long sessionBUserId = BUserAuthUtils.getInstance().getSessionBUserId();
			BUser creator = BUserQueryMgr.getInstance().getSimple(sessionBUserId);
			PayOrderForm payForm = PayOrderForm.newInstance(addForm.getPayItems(), addForm.getRemark(), creator);
			OperateTips operateTips = payOrder(order, payForm);

			if (operateTips.isSuccess()) {
				result.setTarget(order);
				result.setSuccess(true);
			} else {
				result.setTarget(order);
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[addOrderByWorkflowDataId]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	// 根据流程id 预生成未支付订单
	public ReqResult<Order> addPreOrder(PreOrderAddForm addForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = addPreOrder(addForm.getWorkFlowDataId());
			if (order == null) {
				result.setTips("生成订单失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			result.setTarget(order);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[addPreOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

	// 根据流程id 预生成未支付订单
	public Order addPreOrder(long workFlowDataId) {
		Long sessionBUserId = BUserAuthUtils.getInstance().getSessionBUserId();
		if (sessionBUserId == null) {
			return null;
		}

		WorkFlowData data = WorkFlowDataMgr.getInstance().get(workFlowDataId);
		Map<Long, WorkFlowType> workFlowTypeMap = WorkFlowTypeMgr.getInstance().findWorkFlowTypeMap(QueryWorkFlowTypeForm.newInstance());
		long workFlowTypeId = data.getWorkFlowTypeId();
		WorkFlowType workFlowType = workFlowTypeMap.get(workFlowTypeId);
		OrderTypeEnum orderType = OrderTypeEnum.PURCHASE;
		if (OrderTypeEnum.RECHARGE.getMark().equals(workFlowType.getWfCompName())) {
			orderType = OrderTypeEnum.RECHARGE;
		}
		LeaguerDetail leaguerDetail = LeaguerDetailMgr.getInstance().get(data.getStoreId(), data.getLeaguerInfo().getLeaguerId());
		OrderAddApiForm orderAddApiForm = OrderBeanHelper.getInstance().buildOrderAddApiForm(orderType, sessionBUserId, data, leaguerDetail);
		
		Order order = OrderMgr.getInstance().addOrder(orderAddApiForm);
		if (order != null) {
			List<BonusInfoAddForm> bonusInfoAddForms = OrderBeanHelper.getInstance().buildBonusInfoAddForms(data);
			OrderBonusMgr.getInstance().saveBonusRecord(order, bonusInfoAddForms);
			orderCallBack(order);
			if(order.getRecordType() == OrderDataTypeEnum.OLD_RCD.ordinal()) {
				OpLogTaskMgr.getInstance().add(OpLog.newInstance(order.getStoreId(), order.getNumber(), OpLogTypeEnum.Order, "补单"));
			}
		}
		return order;
	}

	// 支付订单
	private OperateTips payOrder(Order order, PayOrderForm payForm) {
		OperateTips operateTips = OperateTips.newInstance(true);
		// 根据条件支付订单
		PayOrderWithNoteApiForm form = PayOrderWithNoteApiForm.newInstance();
		form.setPayItems(payForm.getPayItems());
		form.setRemark(payForm.getRemark());
		operateTips = OrderPayMgr.getInstance().payOrderWithNote(order, form);
		if (!operateTips.isSuccess()) {
			return operateTips;
		}

		// 生成订单附属信息
		if(StringUtils.isNoneBlank(payForm.getRemark())) {
			OrderNotesAddForm addForm = OrderNotesAddForm.newInstance();
			addForm.setCreatorId(payForm.getCreator().getId());
			addForm.setCreatorName(payForm.getCreator().getName());
			addForm.setOrderId(order.getId());
			addForm.setRemark(payForm.getRemark());
			addForm.setStoreId(order.getStoreId());
			OrderNotesMgr.getInstance().addOrderNotes(addForm);
		}
		
		if(order.getRealPay() != order.getCost()) {
			OpLogTaskMgr.getInstance().add(OpLog.newInstance(order.getStoreId(), order.getNumber(), OpLogTypeEnum.Order, "该订单的实收价格被手动修改"));
		}
		orderCallBack(order);
		return operateTips;
	}

	// 订单的回调操作
	private void orderCallBack(Order order) {
		//C端的订单
		if(order.getOrigin() == OrderOriginEnum.CUSTOMER.ordinal()) {
			return ;
		}
		// 更新流程的状态信息
		WorkFlowData data = WorkFlowDataMgr.getInstance().get(order.getWorkFlowDataId());
		if(data==null) {
			return;
		}
		OrderInfo orderInfo = OrderInfo.newInstance();
		orderInfo.setOrderId(order.getId());
		data.setOrderInfo(orderInfo);
		if (order.getStatus() == OrderStatusEnum.NOT_PAY.ordinal()) {
			data.setStatus(WorkFlowDataStatusEnum.COMPLETE.ordinal());
		} else {
			data.setStatus(WorkFlowDataStatusEnum.HASPAY.ordinal());
		}
		WorkFlowDataMgr.getInstance().updateWorkFlowData(data);
	}
	
	/***************************小程序商城***************************/	
	public ReqResult<Order> addPreOrderForCuser(PreOrderForCuserAddForm inputForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			LeaguerDetail detail = LeaguerDetailMgr.getInstance().getSimple(inputForm.getStoreId(), inputForm.getLeaguerId());
			if(detail == null) {
				result.setTips("客户不存在，生成订单失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			
			Order order = OrderMgr.getInstance().addOrder(inputForm.toOrderAddApiForm(detail.getName()));
			if (order == null) {
				result.setTips("生成订单失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			//增加商品的物流信息
			OrderTrackMgr.getInstance().addWithId(inputForm.toOrderTrack(order.getId()));
			
			result.setTarget(order);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "OrderHandler[addPreOrderForCuser]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	public ReqResult<Order> payOrderByCuser(PayOrderForCuserForm inputForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			Order order = OrderMgr.getInstance().get(inputForm.getStoreId(), inputForm.getOrderId());
			if (order == null) {
				result.setTips("该订单不存在，支付失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
				return result;
			}
			OperateTips operateTips = OrderPayMgr.getInstance().payOrderWithNote(order, inputForm.toPayOrderWithNoteApiForm());
			if (operateTips.isSuccess()) {
				result.setTarget(order);
				result.setSuccess(true);
			}else {
				HandlerHelper.getInstance().handleReqResult(result, operateTips);
			}
		} catch (Exception e) {
			final String logId = "OrderHandler[payOrderByCuser]";
			final String reason = LogHelper.getInstance().exceptionReason(inputForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
	
	@Deprecated
	public ReqResult<Order> addOrder(OrderItemAddForm addForm) {
		ReqResult<Order> result = ReqResult.newInstance(false, Order.class);
		try {
			result.setTips("生成订单失败");
			result.setStatus(RespStatus.INVALID_REQUEST);
			return result;
		} catch (Exception e) {
			final String logId = "OrderHandler[addOrder]";
			final String reason = LogHelper.getInstance().exceptionReason(addForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance().withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}
		return result;
	}

}
