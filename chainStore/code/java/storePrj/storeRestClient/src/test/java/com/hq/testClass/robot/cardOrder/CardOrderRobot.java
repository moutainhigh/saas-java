package com.hq.testClass.robot.cardOrder;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.hq.chainStore.service.cardOrder.apiData.AddCardOrderForm;
import com.hq.chainStore.service.cardOrder.apiData.CancelCardOrderForm;
import com.hq.chainStore.service.cardOrder.apiData.CardOrderQueryForm;
import com.hq.chainStore.service.cardOrder.apiData.PayCardOrderForm;
import com.hq.chainStore.service.cardOrder.bs.CardOrderMgr;
import com.hq.chainStore.service.cardOrder.data.CardOrder;
import com.hq.chainStore.service.cardOrder.data.StoreCardTypeEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;

public class CardOrderRobot {
	
	private CardOrderRobotData data;
	
	public static CardOrderRobot newRandom(){
		int mark = RandomUtils.nextInt(0, 10000);
		return newInstance(mark);
	}

	public static CardOrderRobot newInstance(int mark){
		CardOrderRobot robot = new CardOrderRobot();
		robot.data = CardOrderRobotData.newInstance(mark);
		return robot;
	}
	
	public CardOrderRobotData getData() {
		return data;
	}

	public void setData(CardOrderRobotData data) {
		this.data = data;
	}
	
	public List<CardOrder> findCardOrderList(CardOrderQueryForm params) {
		return CardOrderMgr.getInstance().findCardOrderList(params);
	}
	
	public CardOrder getCardOrder(long cardOrderId) {
		return CardOrderMgr.getInstance().getCardOrder(cardOrderId);
	}

	public CardOrder addCardOrder(String cardId, String leaguerId, long storeId) {
		AddCardOrderForm addForm = AddCardOrderForm.newInstance();
		FastBeanCopyer.getInstance().copy(this.data, addForm);
		addForm.setCardId(cardId);
		addForm.setCardType(StoreCardTypeEnum.PRODUCTCARD.ordinal());
		addForm.setLeaguerId(leaguerId);
		addForm.setStoreId(storeId);
		return CardOrderMgr.getInstance().addCardOrder(addForm);
	}
	
	public void payCardOrder(long cardOrderId, long storeId) {
		PayCardOrderForm formData = PayCardOrderForm.newInstance();
		FastBeanCopyer.getInstance().copy(this.data, formData);
		CardOrderMgr.getInstance().payCardOrder(cardOrderId, storeId, formData);
	}
	
	public void cancelCardOrder(long cardOrderId, long storeId, CancelCardOrderForm formData) {
		CardOrderMgr.getInstance().cancelCardOrder(cardOrderId, storeId, formData);
	}
	
}
