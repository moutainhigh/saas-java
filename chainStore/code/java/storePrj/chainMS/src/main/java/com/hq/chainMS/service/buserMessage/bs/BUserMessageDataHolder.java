package com.hq.chainMS.service.buserMessage.bs;

import com.hq.chainMS.common.constants.ServerConstants;
import com.hq.chainMS.common.validate.AppIdThreadLocal;
import com.hq.storeClient.service.message.apiData.BUserMessageAddListForm;
import com.hq.storeClient.service.message.bs.BUserMessageClientMgr;
import com.hq.storeClient.service.message.data.BUserMessage;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageDataHolder {

	public static BUserMessageDataHolder getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageDataHolder.class);
	}

	public BUserMessage addBUserMessage(BUserMessageAddListForm inputForm) {
		AppIdThreadLocal.getInstance().setValidateInfo(ServerConstants.appId);
		BUserMessage data = BUserMessageClientMgr.getInstance().addBUserMessage(inputForm);
		AppIdThreadLocal.getInstance().set(null);
		return data;
	}
}
