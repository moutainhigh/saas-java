package com.hq.chainStore.service.orderComment.data;

import com.hq.common.dataSyn.bs.AbsDataSynDataHolder;
import com.hq.common.dataSyn.info.DataSynType;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;

public class OrderCommentSynDataHolder extends AbsDataSynDataHolder<OrderComment> {

	public static OrderCommentSynDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(OrderCommentSynDataHolder.class);
	}

	final private DataSynType synType = DataSynType.OrderComment;

	protected Class<OrderComment> getClazz() {
		return OrderComment.class;
	}

	protected RestDao<OrderComment> getDao() {
		return OrderCommentDAO.getInstance();
	}

	public DataSynType getSynType() {
		return synType;
	}
	
	public OrderComment getOrderComment(String ownerId, Long orderId){
		return super.getData(ownerId, String.valueOf(orderId));
	}

}
