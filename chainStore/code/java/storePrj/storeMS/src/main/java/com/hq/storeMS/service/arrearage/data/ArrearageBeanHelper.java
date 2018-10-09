package com.hq.storeMS.service.arrearage.data;

import java.util.List;

import com.hq.orderRestClient.service.order.data.PayItem;
import com.hq.storeMS.service.arrearage.apiData.ArrearageAddPaymentHistoryApiForm;
import com.zenmind.common.BigDecimalUtil;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class ArrearageBeanHelper {
	public static ArrearageBeanHelper getInstance() {
		return HotSwap.getInstance().getSingleton(ArrearageBeanHelper.class);
	}
	
	public boolean addPaymentHistory(Arrearage arrearage, ArrearageAddPaymentHistoryApiForm addPaymentHistoryApiForm){
		boolean b = false;
		float totalPay = getTotalPay(addPaymentHistoryApiForm.getPayItems());
		if(arrearage.getBalanceDue() >= totalPay){
			PaymentHistory paymentHistory = PaymentHistory.newInstance();
			FastBeanCopyer.getInstance().copy(addPaymentHistoryApiForm, paymentHistory);
			arrearage.getPaymentHistories().add(paymentHistory);
			
			arrearage.setBalanceDue(arrearage.getBalanceDue() - totalPay);
			//由于浮点数 相减之后 不一定为0
			if(BigDecimalUtil.round(arrearage.getBalanceDue(), 2) == 0){
				arrearage.setStatus(ArrearageStatusEnum.FINISH.ordinal());
			}
			b = true;
		}
		return b;
	}
	
	private float getTotalPay(List<PayItem> payItems){
		float result = 0;
		for (PayItem payItem : payItems) {
			result += payItem.getCost();
		}
		return result;
	}
}
