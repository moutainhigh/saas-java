package com.hq.storeMS.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.message.IntfMessageHolder;
import com.hq.storeMS.common.message.info.MessageResp;
import com.hq.storeMS.common.message.info.MessageTypeEnum;
import com.hq.storeMS.common.message.info.MsgQueryForm;
import com.hq.storeMS.common.util.AppUtils;
import com.hq.storeMS.service.store.bs.BossDataHolder;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.hq.storeMS.service.workFlowData.data.LeaguerInfo;
import com.hq.storeMS.service.workFlowData.data.UserBonus;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataCacheDAO;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataDAO;
import com.hq.storeMS.service.workFlowData.data.WorkFlowDataStatusEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataDataHolder implements IntfMessageHolder{
	
	public static WorkFlowDataDataHolder getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataDataHolder.class);
	}
	
	final private MessageTypeEnum messageType = MessageTypeEnum.WORKFLOW_MNG;
	
	public void addAndReturnId(WorkFlowData target) {
		WorkFlowDataDAO.getInstance().addAndReturnId(getBossId(target.getStoreId()), target);
		WorkFlowDataCacheDAO.getInstance().delete(target);
	}

	public void update(WorkFlowData target) {
		target.incrVer();
		target.setLastUpdateTime(System.currentTimeMillis());
		WorkFlowDataDAO.getInstance().updpate(getBossId(target.getStoreId()), target);
		WorkFlowDataCacheDAO.getInstance().delete(target);
	}
	
	public void delete(WorkFlowData target) {
		WorkFlowDataDAO.getInstance().delete(getBossId(target.getStoreId()), target.getId());
		WorkFlowDataCacheDAO.getInstance().delete(target);
	}

	public WorkFlowData get(long storeId, long id) {
		WorkFlowData target = WorkFlowDataCacheDAO.getInstance().get(storeId, id);
		if(target == null){
			target = WorkFlowDataDAO.getInstance().get(getBossId(storeId), id);
			if(target != null){
				WorkFlowDataCacheDAO.getInstance().save(target);
			}
		}
		return target;
	}
	
	public List<WorkFlowData> findByCond(WorkFlowDataQueryForm queryForm) {
		List<WorkFlowData> list = WorkFlowDataCacheDAO.getInstance().getList(queryForm);
		if(CollectionUtils.isEmpty(list)){
			list = WorkFlowDataDAO.getInstance().findByCond(getBossId(queryForm.getStoreId()), queryForm);
			if(CollectionUtils.isNotEmpty(list)){
				WorkFlowDataCacheDAO.getInstance().saveList(queryForm, list);
			}
		}
		return list;
	}
	
	private long getBossId(long storeId) {
		return BossDataHolder.getInstance().getBossId(storeId);
	}
	
	public MessageTypeEnum getMessageType() {
		return messageType;
	}

	@Override
	public MessageResp getMessageItem(MsgQueryForm queryForm) {
		MessageResp messageResp = MessageResp.newInstance();
	    messageResp.setCount(count(MsgQueryForm.newInstance(queryForm.getStoreId(), 0)));
	    messageResp.setMessageType(getMessageType().ordinal());
		return messageResp;
	}
	
	public long count(MsgQueryForm queryForm) {
		WorkFlowDataQueryForm form = WorkFlowDataQueryForm.newInstance();
		form.setStoreId(queryForm.getStoreId());
		long minTime = AppUtils.getNextDate(new Date()) - ServerConstants.ONE_MONTH;
		form.setMinTime(minTime);
		List<WorkFlowData> list = findByCond(form);
		List<WorkFlowData> result = filter(list, queryForm.getBuserId());
		return (long)result.size();
	}
	
	private List<WorkFlowData> filter(List<WorkFlowData> list, long buserId){
		List<WorkFlowData> result = new ArrayList<WorkFlowData>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (WorkFlowData data : list) {
				if(checkCond(buserId, data)) {
					result.add(data);
				}
			}
		}
		return result;
	}
	
	private boolean checkCond(long buserId, WorkFlowData record){
		//排除已完成的工作流
		if(record.getStatus() == WorkFlowDataStatusEnum.HASPAY.ordinal() || record.getStatus() == WorkFlowDataStatusEnum.CANCEL.ordinal()) {
			return false;
		}
		
		//流程管理
		if(buserId == 0) {
			return true;
		}
		
		//排除自己创建的工作流
		if(buserId == record.getBuserId()){
			return false;
		}
		
		//跟进者
		LeaguerInfo leaguerInfo = record.getLeaguerInfo();
		if(leaguerInfo != null && buserId == leaguerInfo.getFollowUserId()){
			return true;
		}
		
		//服务者
		Map<String, BonusInfo> bonusInfoMap = record.getBonusInfoMap();
		if(MapUtils.isNotEmpty(bonusInfoMap) ){
			Collection<BonusInfo> values = bonusInfoMap.values();
			for (BonusInfo bonusInfo : values) {
				Map<Long, UserBonus> userBonusMap = bonusInfo.getUserBonusMap();
				if(MapUtils.isNotEmpty(userBonusMap) && userBonusMap.keySet().contains((Long)buserId)){
					return true;
				}
			}
		}
		return false;
	}
}
