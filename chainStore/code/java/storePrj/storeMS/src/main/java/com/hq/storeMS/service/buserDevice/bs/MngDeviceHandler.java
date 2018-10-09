package com.hq.storeMS.service.buserDevice.bs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.apiData.BUserQueryApiForm;
import com.hq.storeMS.service.buser.bs.BUserQueryMgr;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BUserCount;
import com.hq.storeMS.service.buser.data.BuserRoleEnum;
import com.hq.storeMS.service.buserDevice.data.BUserDevice;
import com.hq.storeMS.service.buserDevice.data.vo.BUserBindInfo;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RespStatus;
import com.hq.storeMS.service.store.apiData.StoreFindTypeEnum;
import com.hq.storeMS.service.store.bs.StoreHandler;
import com.hq.storeMS.service.store.data.StoreRO;
import com.zenmind.common.hotSwap.HotSwap;

public class MngDeviceHandler {
	public static MngDeviceHandler getInstance() {
		return HotSwap.getInstance().getSingleton(MngDeviceHandler.class);
	}
	private final static LogModule logModule = LogModule.BUserDevice;
	
	public ReqResult<BUserBindInfo> findBUserBindInfoList(BUserQueryApiForm queryForm) {
		ReqResult<BUserBindInfo> result = ReqResult.newInstance(false,
				BUserBindInfo.class);
		List<BUserBindInfo> buserBindInfoList = new ArrayList<BUserBindInfo>();
		try {
			addBossRole2QueryForm(queryForm);
			
			BUserCommQueryForm params = BUserCommQueryForm.newInstance();
			params.setPhone(queryForm.getBuserPhone()).setRoleSet(queryForm.getRoleSet())
				.setPageItemCount(queryForm.getPageItemCount()).setPageNo(queryForm.getPageNo());
			PageResp<BUser> buserPage = BUserQueryMgr.getInstance().findPageByCond(params);
			List<BUser> buserList = buserPage.getList();
			
			for (BUser bUserRO : buserList) {
				BUserBindInfo buserBindInfo = BUserBindInfo.newInstance();
				//组装商家基本信息
				buserBindInfo.setBuserId(bUserRO.getId());
				buserBindInfo.setBuserName(bUserRO.getName());
				buserBindInfo.setBuserPhone(bUserRO.getPhone());
				//组装店铺地区
				ReqResult<StoreRO> storeResult = StoreHandler.getInstance().findBUserStores(buserBindInfo.getBuserId(), 1, 1, StoreFindTypeEnum.Ownered.ordinal());
				List<StoreRO> storeList = storeResult.getTargetList();
				if(storeList != null && storeList.size() > 0){
					buserBindInfo.setStoreArea(storeList.get(0).getArea());
				}
				//组装客户代表信息
				BUserDevice buserDevice = BUserDeviceMgr.getInstance().getByUser(buserBindInfo.getBuserId());
				if(buserDevice != null){
					buserBindInfo.setSalesman(buserDevice.getSalesman());
					buserBindInfo.setSalesmanPhone(buserDevice.getSalesmanPhone());
					buserBindInfo.setBindDeviceCount(buserDevice.getDeviceList().size());
				}
				buserBindInfoList.add(buserBindInfo);
			}
			
			result.setTargetList(buserBindInfoList);
			result.setSuccess(true);
			result.setStatus(RespStatus.OK);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "MngDeviceHandler[findBUserBindInfoList]", reason, e);
		}
		return result;

	}
	
	public ReqResult<BUserCount> getBUserBindInfoCount(BUserQueryApiForm queryForm) {
		ReqResult<BUserCount> result = ReqResult.newInstance(false, BUserCount.class);
		try {
			addBossRole2QueryForm(queryForm);
			BUserCount buserCount = BUserQueryMgr.getInstance().getCount(queryForm);
			result.setTarget(buserCount);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "MngDeviceHandler[getBUserCount]", reason, e);
		}
		return result;
	}
	
	/**
	 * 查询角色为老板的buser
	 * @param queryForm
	 */
	private void addBossRole2QueryForm(BUserQueryApiForm queryForm) {
		Set<Integer> roleSet = new HashSet<Integer>();
		roleSet.add(BuserRoleEnum.BOSS.ordinal());
		queryForm.setRoleSet(roleSet);
	}
}
