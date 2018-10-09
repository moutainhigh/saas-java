package com.hq.chainStore.service.cardOrder.bs;

import java.util.List;

import com.hq.chainStore.service.cardOrder.apiData.AddCardOrderForm;
import com.hq.chainStore.service.cardOrder.apiData.CancelCardOrderForm;
import com.hq.chainStore.service.cardOrder.apiData.CardOrderQueryForm;
import com.hq.chainStore.service.cardOrder.apiData.CardOrderUpdateType;
import com.hq.chainStore.service.cardOrder.apiData.PayCardOrderForm;
import com.hq.chainStore.service.cardOrder.apiData.UpdateCardOrderForm;
import com.hq.chainStore.service.cardOrder.data.CardOrder;
import com.hq.chainStore.service.cardOrder.data.CardOrderDAO;
import com.zenmind.common.hotSwap.HotSwap;

public class CardOrderMgr {

	public static CardOrderMgr getInstance() {
		return HotSwap.getInstance().getSingleton(CardOrderMgr.class);
	}

	public List<CardOrder> findCardOrderList(CardOrderQueryForm params) {
		final String findPath = "findCardOrderList";
		return CardOrderDAO.getInstance().findWithReqParam(findPath, params.toReqMap(), params.getPageItemCount(), params.getPageNo());
	}
	
	public CardOrder getCardOrder(long cardOrderId) {
		return CardOrderDAO.getInstance().get(cardOrderId);
	}

	public void updateCardOrder(long cardOrderId, UpdateCardOrderForm updateForm) {
		CardOrderDAO.getInstance().update(cardOrderId, updateForm);
	}
	
	public CardOrder addCardOrder(AddCardOrderForm addForm) {
		return CardOrderDAO.getInstance().add(addForm);
	}
	
	//=====================具体的更新操作=====================
	
	public void payCardOrder(long cardOrderId, long storeId, PayCardOrderForm formData) {
		UpdateCardOrderForm updateForm = UpdateCardOrderForm.newInstance();
		updateForm.setUpdateType(CardOrderUpdateType.PayOrder.ordinal());
		updateForm.setPayCardOrder(formData);
		updateForm.setStoreId(storeId);
		
		updateCardOrder(cardOrderId, updateForm);
	}
	
	public void cancelCardOrder(long cardOrderId, long storeId, CancelCardOrderForm formData) {
		UpdateCardOrderForm updateForm = UpdateCardOrderForm.newInstance();
		updateForm.setUpdateType(CardOrderUpdateType.CancelOrder.ordinal());
		updateForm.setCancelCardOrder(formData);
		updateForm.setStoreId(storeId);
		
		updateCardOrder(cardOrderId, updateForm);
	}
}
