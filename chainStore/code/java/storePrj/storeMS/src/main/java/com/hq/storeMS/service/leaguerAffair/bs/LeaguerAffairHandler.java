package com.hq.storeMS.service.leaguerAffair.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.service.auth.buser.BUserAuthUtils;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.ExceptionInfo;
import com.hq.storeMS.service.common.HandlerHelper;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.leaguerAffair.apiData.AddAlarmClock;
import com.hq.storeMS.service.leaguerAffair.apiData.AddArchives;
import com.hq.storeMS.service.leaguerAffair.apiData.AddLeaguerDiscountCard;
import com.hq.storeMS.service.leaguerAffair.apiData.AddLeaguerMembershipCard;
import com.hq.storeMS.service.leaguerAffair.apiData.AddLeaguerProductCard;
import com.hq.storeMS.service.leaguerAffair.apiData.DelAlarmClock;
import com.hq.storeMS.service.leaguerAffair.apiData.DelArchives;
import com.hq.storeMS.service.leaguerAffair.apiData.DelLeaguerDiscountCard;
import com.hq.storeMS.service.leaguerAffair.apiData.DelLeaguerMembershipCard;
import com.hq.storeMS.service.leaguerAffair.apiData.DelLeaguerProductCard;
import com.hq.storeMS.service.leaguerAffair.apiData.LeaguerAffairUpdateApiForm;
import com.hq.storeMS.service.leaguerAffair.apiData.LeaguerAffairUpdateType;
import com.hq.storeMS.service.leaguerAffair.data.Archives;
import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffair;
import com.hq.storeMS.service.leaguerAffair.data.LeaguerAffairBeanHelper;
import com.hq.storeMS.service.schedule.bs.ScheduleMgr;
import com.hq.storeMS.service.schedule.data.Schedule;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreAdminPermEnum;
import com.zenmind.common.beanCopy.FastBeanCopyer;
import com.zenmind.common.hotSwap.HotSwap;

public class LeaguerAffairHandler {

	public static LeaguerAffairHandler getInstance() {
		return HotSwap.getInstance().getSingleton(LeaguerAffairHandler.class);
	}
	
	private final LogModule logModule = LogModule.LeaguerAffair;
	
	public ReqResult<LeaguerAffair> getAffair(String id) {
		ReqResult<LeaguerAffair> result = ReqResult.newInstance(false, LeaguerAffair.class);
		try {
			LeaguerAffair leaguerAffair = LeaguerAffairMgr.getInstance().get(id);
			result.setTarget(leaguerAffair);
			result.setSuccess(true);
		} catch (Exception e) {
			final String logId = "StoreCardInfoHandler[getAffair]";
			final String reason = LogHelper.getInstance().exceptionReason(id);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}

		return result;
	}
	
	public ReqResult<LeaguerAffair> updateAffair(String leaguerAffairId, LeaguerAffairUpdateApiForm updateForm) {
		long storeId = updateForm.getStoreId();
		BUserAuthUtils.getInstance().checkPermission(storeId, StoreAdminPermEnum.LEAGUER_ADMIN);
		ReqResult<LeaguerAffair> result = ReqResult.newInstance(false, LeaguerAffair.class);
		try {
			LeaguerAffairUpdateType updateType = LeaguerAffairUpdateType.valueOf(updateForm.getUpdateType());
			boolean success = false;
			switch (updateType) {
			case AddMembershipCard:
				success = addMembershipCard(leaguerAffairId, updateForm.getAddMembershipCard());
				break;
			case DelMembershipCard:
				success = delMembershipCard(leaguerAffairId, updateForm.getDelMembershipCard());
				break;
			case AddDiscountCard:
				success = addDiscountCard(leaguerAffairId, updateForm.getAddDiscountCard());
				break;
			case DelDiscountCard:
				success = delDiscountCard(leaguerAffairId, updateForm.getDelDiscountCard());
				break;
			case AddArchives:
				success = addArchives(leaguerAffairId, updateForm.getAddArchives());
				break;
			case DelArchives:
				success = delArchives(leaguerAffairId, updateForm.getDelArchives());
				break;
			case AddAlarmClock:
				success = addAlarmClock(leaguerAffairId, updateForm.getAddAlarmClock());
				break;
			case DelAlarmClock:
				success = delAlarmClock(leaguerAffairId, updateForm.getDelAlarmClock());
				break;
			case AddProductCard:
				success = addProductCard(leaguerAffairId, updateForm.getAddProductCard());
				break;
			case DelProductCard:
				success = delProductCard(leaguerAffairId, updateForm.getDelProductCard());
				break;
			default:
				break;
			}
			if (success) {
				result.setSuccess(true);
			} else {
				result.setTips(updateType.getMark() + "失败");
				result.setStatus(RespStatus.INVALID_REQUEST);
			}
		} catch (Exception e) {
			final String logId = "StoreCardInfoHandler[updateAffair]";
			final String reason = LogHelper.getInstance().exceptionReason(leaguerAffairId, updateForm);
			ExceptionInfo exceptionInfo = ExceptionInfo.newInstance()
					.withLogModule(logModule).withLogId(logId)
					.withReason(reason).withResult(result);
			HandlerHelper.getInstance().handleException(exceptionInfo, e);
		}

		return result;
	}
	
	private boolean addMembershipCard(String leaguerAffairId, AddLeaguerMembershipCard data){
		return false;
	}
	
	private boolean delMembershipCard(String leaguerAffairId, DelLeaguerMembershipCard data){
		return false;
	}
	
	private boolean addDiscountCard(String leaguerAffairId, AddLeaguerDiscountCard data){
		return false;
	}
	
	private boolean delDiscountCard(String leaguerAffairId, DelLeaguerDiscountCard data){
		return false;
	}
	
	private boolean addProductCard(String leaguerAffairId, AddLeaguerProductCard data){
		return false;
	}
	
	private boolean delProductCard(String leaguerAffairId, DelLeaguerProductCard data){
		return false;
	}
	
	private boolean addArchives(String leaguerAffairId, AddArchives data){
		boolean b = false;
		BUser sessionUser = BUserAuthUtils.getInstance().getSessionBUser();
		LeaguerAffair leaguerAffair = LeaguerAffairMgr.getInstance().get(leaguerAffairId);
		Archives archives = data.toArchives();
		archives.setCreatorId(sessionUser.getId());
		archives.setCreatorName(sessionUser.getName());
		if(LeaguerAffairBeanHelper.getInstance().addArchives(leaguerAffair, archives)){
			LeaguerAffairMgr.getInstance().update(leaguerAffair);
			b = true;
		}
		return b;
	}
	
	private boolean delArchives(String leaguerAffairId, DelArchives data){
		boolean b = false;
		LeaguerAffair leaguerAffair = LeaguerAffairMgr.getInstance().get(leaguerAffairId);
		if(LeaguerAffairBeanHelper.getInstance().delArchives(leaguerAffair, data)){
			LeaguerAffairMgr.getInstance().update(leaguerAffair);
			b = true;
		}
		return b;
	}
	
	private boolean addAlarmClock(String leaguerAffairId, AddAlarmClock data){
		boolean b = false;
		LeaguerAffair leaguerAffair = LeaguerAffairMgr.getInstance().get(leaguerAffairId);
		if(LeaguerAffairBeanHelper.getInstance().addAlarmClock(leaguerAffair, data)){
			LeaguerAffairMgr.getInstance().update(leaguerAffair);
			b = true;
		}
		
		//添加闹钟生成一个待办事项
		if(b){
			Schedule schedule = Schedule.newInstance();
			FastBeanCopyer.getInstance().copy(data, schedule);
			schedule.setLeaguerId(leaguerAffairId);
			schedule.setNoticeTime(System.currentTimeMillis() + data.getHours() * 3600L * 1000);
			schedule.setStoreId(leaguerAffair.getStoreId());
			ScheduleMgr.getInstance().addAndReturnId(schedule);
		}
		return b;
	}
	
	private boolean delAlarmClock(String leaguerAffairId, DelAlarmClock data){
		boolean b = false;
		LeaguerAffair leaguerAffair = LeaguerAffairMgr.getInstance().get(leaguerAffairId);
		if(LeaguerAffairBeanHelper.getInstance().delAlarmClock(leaguerAffair, data)){
			LeaguerAffairMgr.getInstance().update(leaguerAffair);
			b = true;
		}
		return b;
	}

}
