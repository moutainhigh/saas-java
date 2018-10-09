package com.hq.chainStore.service.orderComment.bs;

import java.util.List;

import com.hq.chainStore.service.orderComment.apiData.OrderCommentQueryParams;
import com.hq.chainStore.service.orderComment.apiData.SaveBeauticianCommentForm;
import com.hq.chainStore.service.orderComment.apiData.UpdateOrderCommentForm;
import com.hq.chainStore.service.orderComment.apiData.UpdateOrderCommentType;
import com.hq.chainStore.service.orderComment.data.OrderComment;
import com.hq.chainStore.service.orderComment.data.OrderCommentDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.ReqMap;

public class OrderCommentMgr {

	public static OrderCommentMgr getInstance() {
		return HotSwap.getInstance().getSingleton(OrderCommentMgr.class);
	}
	
	public List<OrderComment> findOrderCommentList(OrderCommentQueryParams params){
		final String findPath = "findOrderCommentList";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("storeId", params.getStoreId()).add("orderId", params.getOrderId())
			.add("beauticianId", params.getBeauticianId()).add("cuserId", params.getCuserId());
		return OrderCommentDAO.getInstance().findWithReqParam(findPath, reqMap, params.getPageItemCount(), params.getPageNo());
	}
	
	public OrderComment getOrderComment(long orderCommentId){
		return OrderCommentDAO.getInstance().get(orderCommentId);
	}
	
	public void updateOrderComment(long orderCommentId, UpdateOrderCommentForm updateForm){
		OrderCommentDAO.getInstance().update(orderCommentId, updateForm);
	}
	
	public void saveBeauticianComment(long orderCommentId, long storeId, SaveBeauticianCommentForm saveForm){
		UpdateOrderCommentForm updateForm = UpdateOrderCommentForm.newInstance();
		updateForm.setSaveBeauticianCommentForm(saveForm);
		updateForm.setStoreId(storeId);
		updateForm.setUpdateType(UpdateOrderCommentType.SaveBeauticianComment.ordinal());
		updateOrderComment(orderCommentId, updateForm);
	}

}
