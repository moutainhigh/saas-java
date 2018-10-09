package com.hq.storeMS.service.message.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.message.trigger.TriggerForm;
import com.hq.storeMS.common.message.trigger.TriggerTypeEnum;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.appointment.bs.AppointmentMgr;
import com.hq.storeMS.service.appointment.data.Appointment;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.message.apiData.BUserMessageAddForm;
import com.hq.storeMS.service.message.apiData.MessageQueryForm;
import com.hq.storeMS.service.message.data.BUserMessage;
import com.hq.storeMS.service.message.data.MsgUnReadCount;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.workFlowData.bs.WorkFlowDataMgr;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserMessageMgr {

	public static BUserMessageMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserMessageMgr.class);
	}

	public BUserMessage addBUserMessageByTriggerForm(TriggerForm form) {
		TriggerTypeEnum triggerTypeEnum = TriggerTypeEnum.valueOf(form.getTriggerType());
		String body = triggerTypeEnum.getTips();
		String name = getName(form.getStoreId(), triggerTypeEnum, form.getId());

		BUserMessage target = BUserMessage.newInstance();
		target.setBuserId(form.getBuserId());
		target.setStoreId(form.getStoreId());
		target.setStoreName(form.getStoreName());
		target.setMessageObjId(form.getId());
		target.setMessageType(form.getTriggerType());
		target.setMessageBody(StringFormatUtil.format(body, name));
		BUserMessageDataHolder.getInstance().addAndReturnId(target);
		return target;
	}

	private String getName(long storeId, TriggerTypeEnum triggerTypeEnum, String id) {
		String name = "";
		switch (triggerTypeEnum) {
		case NEW_MY_APPOINTMENT:
			name = getNameByAppointId(storeId, id);
			break;
		case NEW_MY_CLERK:
			name = getNameByBUserId(id);
			break;
		case NEW_MY_WORKFLOW:
			name = getNameByWorkFlowDataId(id);
			break;
		default:
			break;
		}
		return name;
	}

	private String getNameByAppointId(long storeId, String id) {
		Appointment appoint = AppointmentMgr.getInstance().get(storeId, Long.valueOf(id));
		if (appoint == null) {
			return "";
		}
		Leaguer leaguer = StoreLeaguerInfoMgr.getInstance().get(storeId).takeLeaguerById(appoint.getLeaguerId());
		if (leaguer != null) {
			return leaguer.getName();
		}
		return "";
	}

	private String getNameByBUserId(String id) {
		BUser buser = BUserQueryMgr.getInstance().getSimple(Long.valueOf(id));
		if (buser != null) {
			return buser.getName();
		}
		return "";
	}

	private String getNameByWorkFlowDataId(String id) {
		WorkFlowData workFlowData = WorkFlowDataMgr.getInstance().get(Long.valueOf(id));
		if (workFlowData == null) {
			return "";
		}
		String leaguerId = workFlowData.getLeaguerInfo().getLeaguerId();
		if (StringUtils.isBlank(leaguerId)) {
			return "";
		}
		Leaguer leaguer = StoreLeaguerInfoMgr.getInstance().get(workFlowData.getStoreId()).takeLeaguerById(leaguerId);
		if (leaguer != null) {
			return leaguer.getName();
		}
		return "";
	}

	public void addWithForm(BUserMessageAddForm addForm) {
		BUserMessage message = addForm.toBUserMessage();
		Store store = StoreMgr.getInstance().getSimple(message.getStoreId());
		message.setStoreName(store.getName());
		addAndReturnId(message);
	}
	
	public void addAndReturnId(BUserMessage target) {
		BUserMessageDataHolder.getInstance().addAndReturnId(target);
	}

	public void update(BUserMessage target) {
		BUserMessageDataHolder.getInstance().updpate(target);
	}

	public void delete(BUserMessage target) {
		BUserMessageDataHolder.getInstance().delete(target);
	}

	public BUserMessage get(long id) {
		return BUserMessageDataHolder.getInstance().get(id);
	}

	public MsgUnReadCount findUnReadCount(MessageQueryForm queryForm) {
		List<BUserMessage> list = BUserMessageDataHolder.getInstance().findBUserMessageList(queryForm);
		List<BUserMessage> resultList = filterRecord(queryForm, list);
		MsgUnReadCount count = MsgUnReadCount.newInstance();
		count.setCount(resultList.size());
		count.setBuserId(queryForm.getBuserId());
		return count;
	}
	
	public PageResp<BUserMessage> getBUserMessagePageInfo(MessageQueryForm queryForm) {
		List<BUserMessage> list = BUserMessageDataHolder.getInstance().findBUserMessageList(queryForm);
		List<BUserMessage> resultList = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<BUserMessage> filterRecord(MessageQueryForm queryForm, List<BUserMessage> list) {
		List<BUserMessage> result = new ArrayList<BUserMessage>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (BUserMessage record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}
		Collections.sort(result, new Comparator<BUserMessage>() {
			@Override
			public int compare(BUserMessage o1, BUserMessage o2) {
				int status = Integer.compare(o2.getStatus(), o1.getStatus());
				if(status==0) {//先按状态倒序 再按创建时间倒序
					return Long.compare(o2.getCreatedTime(), o1.getCreatedTime());
				}else {
					return status;
				}
			}
		});
		return result;
	}

	private boolean isRightRecord(MessageQueryForm queryForm, BUserMessage record) {
		if (!checkMessageType(queryForm.getMessageType(), record)) {
			return false;
		}
		if (!checkStatus(queryForm.getStatus(), record)) {
			return false;
		}
		return true;
	}

	private boolean checkMessageType(Set<Integer> messageType, BUserMessage record) {
		if (CollectionUtils.isEmpty(messageType) || messageType.contains(-1)) {
			return true;
		}
		if (messageType.contains(record.getMessageType())) {
			return true;
		}
		return false;
	}
	
	private boolean checkStatus(int status, BUserMessage record) {
		if (status == -1) {
			return true;
		}
		if (status == record.getStatus()) {
			return true;
		}
		return false;
	}
}
