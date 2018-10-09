package com.hq.storeMS.service.workFlowData.bs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.message.trigger.MessageTriggerMgr;
import com.hq.storeMS.common.message.trigger.TriggerForm;
import com.hq.storeMS.common.message.trigger.TriggerTypeEnum;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.common.validate.ValidateInfoThreadLocal;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionMgr;
import com.hq.storeMS.service.detailDataVersion.data.DataVersionEnum;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.storeLeaguerInfo.bs.StoreLeaguerInfoMgr;
import com.hq.storeMS.service.storeLeaguerInfo.data.Leaguer;
import com.hq.storeMS.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.storeMS.service.workFlowData.data.BonusInfo;
import com.hq.storeMS.service.workFlowData.data.LeaguerInfo;
import com.hq.storeMS.service.workFlowData.data.UserBonus;
import com.hq.storeMS.service.workFlowData.data.WorkFlowData;
import com.zenmind.common.hotSwap.HotSwap;

public class WorkFlowDataMgr {

	public static WorkFlowDataMgr getInstance() {
		return HotSwap.getInstance().getSingleton(WorkFlowDataMgr.class);
	}

	private final DataVersionEnum dataVersionEnum = DataVersionEnum.WorkflowData;
	
	public void addAndReturnId(WorkFlowData target) {
		WorkFlowDataDataHolder.getInstance().addAndReturnId(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
		pushMessage2UMeng(target.getStoreId(), getServiceUser(target), target.getId());
	}

	public void updateWorkFlowData(WorkFlowData target) {
		//修改前  获取变动的服务员ID
		Set<Long> buserIds = getPushTarget(target);
		WorkFlowDataDataHolder.getInstance().update(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
		pushMessage2UMeng(target.getStoreId(), buserIds, target.getId());
	}

	public void delete(WorkFlowData target) {
		WorkFlowDataDataHolder.getInstance().delete(target);
		DetailDataVersionMgr.getInstance().updateByStoreId(target.getStoreId(), dataVersionEnum);
	}
	
	private long getStoreId() {
		return ValidateInfoThreadLocal.getInstance().getStoreId();
	}
	
	public WorkFlowData get(long id) {
		return get(getStoreId(), id);
	}
	
	public WorkFlowData get(long storeId, long id) {
		if(storeId == 0L) {
			storeId = getStoreId();
		}
		return WorkFlowDataDataHolder.getInstance().get(storeId, id);
	}

	public PageResp<WorkFlowData> findWorkFlowDataPageInfo(WorkFlowDataQueryForm queryForm) {
		List<WorkFlowData> list = WorkFlowDataDataHolder.getInstance().findByCond(queryForm);
		List<WorkFlowData> result = filterRecord(queryForm, list);
		return PageUtil.getInstance().buildPageResp(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	public List<WorkFlowData> findByCond(WorkFlowDataQueryForm queryForm) {
		List<WorkFlowData> list = WorkFlowDataDataHolder.getInstance().findByCond(queryForm);
		List<WorkFlowData> result = filterRecord(queryForm, list);
		return PageUtil.getInstance().getPageItemList(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}

	private List<WorkFlowData> filterRecord(WorkFlowDataQueryForm queryForm, List<WorkFlowData> list) {
		List<WorkFlowData> result = new ArrayList<WorkFlowData>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (WorkFlowData record : list) {
				if (isRightRecord(queryForm, record)) {
					result.add(record);
				}
			}
		}

		Collections.sort(result, new Comparator<WorkFlowData>() {
			@Override
			public int compare(WorkFlowData o1, WorkFlowData o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return result;
	}

	// 谨记：内存筛选 一定要记得 不应该再操作数据库
	private boolean isRightRecord(WorkFlowDataQueryForm queryForm, WorkFlowData record) {
		if (!checkStatus(queryForm.getStatusSet(), record)) {
			return false;
		}

		if (!checkLeaguerInfo(queryForm.getLeaguerNameOrPhone(), record)) {
			return false;
		}

		if (!checkBUserId(queryForm.getBuserId(), record)) {
			return false;
		}

//		if (!checkWorkFlowTypeId(queryForm.getWorkFlowTypeId(), record)) {
//			return false;
//		}

		return true;
	}

	private boolean checkStatus(Set<Integer> statusSet, WorkFlowData record) {
		if (CollectionUtils.isNotEmpty(statusSet) && !statusSet.contains(record.getStatus())) {
			return false;
		}
		return true;
	}

	private boolean checkLeaguerInfo(String leaguerNameOrPhone, WorkFlowData record) {
		if (StringUtils.isNoneBlank(leaguerNameOrPhone)) {
			String number = record.getNumber();
			if(StringUtils.isNoneBlank(number) &&  number.contains(leaguerNameOrPhone)) {
				return true;
			}
			LeaguerInfo leaguerInfo = record.getLeaguerInfo();
			if (leaguerInfo == null) {
				return false;
			}
			Leaguer leaguer = StoreLeaguerInfoMgr.getInstance().get(record.getStoreId()).takeLeaguerById(leaguerInfo.getLeaguerId());
			if (leaguer == null) {
				return false;
			}
			String leaguerPhone = String.valueOf(leaguer.getPhone());
			String leaguerName = leaguer.getName() == null ? "" : leaguer.getName();
			if (!leaguerName.contains(leaguerNameOrPhone) && !leaguerPhone.contains(leaguerNameOrPhone)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkBUserId(long buserId, WorkFlowData record) {
		if (buserId == 0) {
			return true;
		}
		Set<Long> serviceUser = getServiceUser(record);
		if(serviceUser.contains(buserId)) {
			return true;
		}
		return false;
	}

//	private boolean checkWorkFlowTypeId(long workFlowTypeId, WorkFlowData record) {
//		if (workFlowTypeId == 0) {
//			return true;
//		}
//		if (workFlowTypeId == record.getWorkFlowTypeId()) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 向友盟推送消息
	 * 
	 * @param appointment
	 * @return
	 */
	private void pushMessage2UMeng(long storeId, Set<Long> buserIds, long workFlowDataId) {
		try {
			if(CollectionUtils.isEmpty(buserIds)) {
				return ;
			}
			StoreRO store = StoreMgr.getInstance().getReadOnly(storeId);
			for (Long buserId : buserIds) {
				TriggerForm data = TriggerForm.newInstance(storeId, buserId);
				data.setTriggerType(TriggerTypeEnum.NEW_MY_WORKFLOW.ordinal());
				data.setStoreName(store.getName());
				data.setId(String.valueOf(workFlowDataId));
				MessageTriggerMgr.getInstance().triggerMessage(data);
			}
		} catch (Exception e) {
			MainLog.error(LogModule.WorkFlowData, "WorkFlowDataMgr[pushMessage2UMeng]", "推送消息失败", e);
		}
		
	}
	
	//获取推送的BUserID列表
	private Set<Long> getPushTarget(WorkFlowData target){
		Set<Long> result = getServiceUser(target);
		if(CollectionUtils.isNotEmpty(result)) {
			Set<Long> oldData = getServiceUser(get(target.getStoreId(), target.getId()));
			result.removeAll(oldData);
		}
		return result;
	}
	
	//获取工作流的服务人员和跟进人员
	private Set<Long> getServiceUser(WorkFlowData record){
		Set<Long> result = new HashSet<Long>();
		try {
			//跟进者
			LeaguerInfo leaguerInfo = record.getLeaguerInfo();
			if(leaguerInfo != null) {
				long followUserId = leaguerInfo.getFollowUserId();
				if(followUserId!=0) {
					result.add(followUserId);
				}
			}
			
			Map<String, BonusInfo> bonusInfoMap = record.getBonusInfoMap();
			// 服务者
			if (MapUtils.isNotEmpty(bonusInfoMap)) {
				Collection<BonusInfo> values = bonusInfoMap.values();
				for (BonusInfo bonusInfo : values) {
					Map<Long, UserBonus> userBonusMap = bonusInfo.getUserBonusMap();
					result.addAll(userBonusMap.keySet());
				}
			}
			result.remove(record.getBuserId());
		} catch (Exception e) {
			MainLog.error(LogModule.WorkFlowData, "WorkFlowDataMgr[getServiceUser]", "获取服务用户失败", e);
		}
		return result;
	}

}
