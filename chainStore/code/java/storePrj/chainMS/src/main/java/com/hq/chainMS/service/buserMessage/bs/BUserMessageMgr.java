package com.hq.chainMS.service.buserMessage.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hq.chainMS.common.log.LogHelper;
import com.hq.chainMS.common.log.LogModule;
import com.hq.chainMS.common.log.MainLog;
import com.hq.chainMS.service.buserMessage.apiData.ProductMessageForm;
import com.hq.chainMS.service.chain.bs.ChainMgr;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.store.bs.StoreMgr;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeClient.service.message.apiData.BUserMessageAddForm;
import com.hq.storeClient.service.message.apiData.BUserMessageAddListForm;
import com.hq.storeClient.service.message.data.TriggerTypeEnum;
import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageMgr {

	public static BUserMessageMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageMgr.class);
	}

	public void addBUserMessage(BUserMessageAddForm inputForm) {
		BUserMessageAddListForm list = BUserMessageAddListForm.newInstance();
		list.getMessageAddForms().add(inputForm);
		addBUserMessageList(list);
	}
	
	public void addBUserMessageList(BUserMessageAddListForm inputForm) {
		BUserMessageDataHolder.getInstance().addBUserMessage(inputForm);
	}

	public void passApplyChain(long storeId, long chainId) {
		try {
			Store store = StoreMgr.getInstance().get(storeId);
			Chain chain = ChainMgr.getInstance().get(chainId);

			TriggerTypeEnum triggerTypeEnum = TriggerTypeEnum.PASS_APPLY_CHAIN;
			String body = StringFormatUtil.format(triggerTypeEnum.getTips(), chain.getName());

			BUserMessageAddForm inputForm = BUserMessageAddForm.newInstance();
			inputForm.setBuserId(store.getBossId()).setStoreId(storeId).setMessageType(triggerTypeEnum.ordinal())
					.setMessageObjId(String.valueOf(storeId)).setMessageBody(body);
			addBUserMessage(inputForm);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId, storeId);
			MainLog.error(LogModule.BUserMessage, "BUserMessageMgr[passApplyChain]", reason, e);
		}
	}

	public void allotClerkFromChain(long storeId, long chainId, BUser buser) {
		try {
			Store store = StoreMgr.getInstance().get(storeId);
			Chain chain = ChainMgr.getInstance().get(chainId);

			TriggerTypeEnum triggerTypeEnum = TriggerTypeEnum.CHAIN_ALLOT_CLERK;
			String body = StringFormatUtil.format(triggerTypeEnum.getTips(), chain.getName(), buser.getName(),
					store.getName());

			BUserMessageAddForm inputForm = BUserMessageAddForm.newInstance();
			inputForm.setBuserId(store.getBossId()).setStoreId(storeId).setMessageType(triggerTypeEnum.ordinal())
					.setMessageObjId(String.valueOf(buser.getId())).setMessageBody(body);
			addBUserMessage(inputForm);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId, storeId);
			MainLog.error(LogModule.BUserMessage, "BUserMessageMgr[allotClerkFromChain]", reason, e);
		}
	}

	public void updateChainProduct(long chainId, ProductMessageForm messageForm) {
		try {
			Chain chain = ChainMgr.getInstance().get(chainId);
			Set<Long> storeIds = messageForm.getStoreIds();

			TriggerTypeEnum triggerTypeEnum = TriggerTypeEnum.CHAIN_PRODUCT_UPDATE;
			String body = StringFormatUtil.format(triggerTypeEnum.getTips(), chain.getName(), messageForm.getName());

			Map<Long, BUserMessageAddForm> inputFormMap = new HashMap<Long, BUserMessageAddForm>();
			for (Long storeId : storeIds) {
				Store store = StoreMgr.getInstance().get(storeId);
				BUserMessageAddForm inputForm = BUserMessageAddForm.newInstance();
				inputForm.setBuserId(store.getBossId()).setStoreId(storeId).setMessageType(triggerTypeEnum.ordinal())
						.setMessageObjId(messageForm.getId()).setMessageBody(body);
				inputFormMap.put(inputForm.getBuserId(), inputForm);
			}
			BUserMessageAddListForm listForm = BUserMessageAddListForm.newInstance();
			listForm.getMessageAddForms().addAll(inputFormMap.values());
			addBUserMessageList(listForm);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId, messageForm);
			MainLog.error(LogModule.BUserMessage, "BUserMessageMgr[updateChainProduct]", reason, e);
		}

	}

	public void openChainProduct(long chainId, ProductMessageForm messageForm) {
		try {
			Chain chain = ChainMgr.getInstance().get(chainId);
			Set<Long> storeIds = messageForm.getStoreIds();

			TriggerTypeEnum triggerTypeEnum = TriggerTypeEnum.CHAIN_PRODUCT_UP;
			String body = StringFormatUtil.format(triggerTypeEnum.getTips(), chain.getName(), messageForm.getName());

			Map<Long, BUserMessageAddForm> inputFormMap = new HashMap<Long, BUserMessageAddForm>();
			for (Long storeId : storeIds) {
				Store store = StoreMgr.getInstance().get(storeId);
				BUserMessageAddForm inputForm = BUserMessageAddForm.newInstance();
				inputForm.setBuserId(store.getBossId()).setStoreId(storeId).setMessageType(triggerTypeEnum.ordinal())
						.setMessageObjId(messageForm.getId()).setMessageBody(body);
				inputFormMap.put(inputForm.getBuserId(), inputForm);
			}
			BUserMessageAddListForm listForm = BUserMessageAddListForm.newInstance();
			listForm.getMessageAddForms().addAll(inputFormMap.values());
			addBUserMessageList(listForm);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(chainId, messageForm);
			MainLog.error(LogModule.BUserMessage, "BUserMessageMgr[openChainProduct]", reason, e);
		}
	}

}
