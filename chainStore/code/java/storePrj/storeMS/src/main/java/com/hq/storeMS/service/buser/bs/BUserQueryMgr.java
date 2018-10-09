package com.hq.storeMS.service.buser.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.common.constants.ServerConstants;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.common.util.PageUtil;
import com.hq.storeMS.service.auth.buser.BUserAuthInfo;
import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.apiData.BUserQueryApiForm;
import com.hq.storeMS.service.buser.bs.handler.BUserRoleHelper;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.buser.data.BUserCount;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.store.bs.StoreMgr;
import com.hq.storeMS.service.store.data.StoreRO;
import com.hq.storeMS.service.storeClerkInfo.bs.StoreClerkInfoMgr;
import com.hq.storeMS.service.storeClerkInfo.data.StoreClerkInfo;
import com.hq.storeMS.service.storeClerkInfo.data.adminRole.StoreRolePermInfo;
import com.hq.storeMS.service.storeVipType.data.StoreVipLevelEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class BUserQueryMgr {

	public static BUserQueryMgr getInstance(){
		return HotSwap.getInstance().getSingleton(BUserQueryMgr.class);
	}

	public PageResp<BUser> findPageByCond(BUserCommQueryForm queryForm) {
		List<BUser> result = findBUserByCond(queryForm);
		Collections.sort(result, new Comparator<BUser>() {
			@Override
			public int compare(BUser o1, BUser o2) {
				return Long.compare(o2.getLastUpdateTime(), o1.getLastUpdateTime());
			}
		});
		return PageUtil.getInstance().buildPageResp(result, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	public List<BUser> findBUserByCond(BUserCommQueryForm queryForm){
		List<BUser> list = BUserDataHolder.getInstance().findListByCond(queryForm);
		return filterRecord(queryForm, list);
	}
	
	private List<BUser> filterRecord(BUserCommQueryForm queryForm, List<BUser> list){
		List<BUser> result = new ArrayList<BUser>();
		if(CollectionUtils.isNotEmpty(list)){
			for (BUser record : list) {
				if(isRightRecord(queryForm, record)){
					record.setPassword(null);//筛选password字段为空[前端不应该显示密码内容]
					result.add(record);
				}
			}
		}
		return result;
	}
	
	private boolean isRightRecord(BUserCommQueryForm queryForm, BUser record){
		if(!checkPhoneOrName(queryForm.getPhoneOrName(), record)){
			return false;
		}		
		return true;
	}
	
	private boolean checkPhoneOrName(String phoneOrName, BUser record) {
		if(StringUtils.isBlank(phoneOrName)) {
			return true;
		}
		String ps = String.valueOf(record.getPhone());
		if(ps.contains(phoneOrName)) {
			return true;
		}
		if(record.getName()!=null && record.getName().contains(phoneOrName)) {
			return true;
		}
		return false;
	}
	
	public BUser getSimple(long id) {
		return BUserDataHolder.getInstance().get(id);
	}
	
	public BUser get(long id) {
		BUser bUser = BUserDataHolder.getInstance().get(id);
		checkExpiredTime(bUser);
		return bUser;
	}
	
	public BUser findByPhone(long phone) {
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		queryForm.setPhone(phone);
		return BUserDataHolder.getInstance().findOneByCond(queryForm);
	}
	
	public BUser findByPriAccountNum(String priAccountNum) {
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		queryForm.setPriAccountNum(priAccountNum);
		return BUserDataHolder.getInstance().findOneByCond(queryForm);
	}
	
	public BUser findByWxUnionId(String wxUnionId) {
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		queryForm.setWxUnionId(wxUnionId);
		return BUserDataHolder.getInstance().findOneByCond(queryForm);
	}
	
	/**
	 * add by kevin
	 * 该方法  只能在体验仿真环境  用户数据量有限的情况下使用。正式环境  不能使用。
	 * @param queryForm
	 * @return
	 */
	public List<BUser> findBossList(BUserCommQueryForm queryForm) {
		List<BUser> findList = findBUserByCond(queryForm);
		List<BUser> result = new ArrayList<BUser>();
		for (BUser bUser : findList) {
			if(bUser.getPhone() != ServerConstants.TEST_DATA_PHONE){
				result.add(bUser);
			}
		}
		return result;
	}
	
	public BUserCount getCount(BUserQueryApiForm params) {
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		queryForm.setPhone(params.getBuserPhone());
		queryForm.setRoleSet(params.getRoleSet());
		queryForm.setPageItemCount(params.getPageItemCount());
		queryForm.setPageNo(params.getPageNo());
		return BUserDataHolder.getInstance().getCount(queryForm);
	}

	public BUserAuthInfo findRolePermInfoByPhone(long phone) {
		BUser buser = findByPhone(phone);
		
		Set<Long> storeIdSet = buser.getStoreIdSet();
		long buserId = buser.getId();
		BUserAuthInfo authInfo = BUserAuthInfo.newInstance(buserId );
		
		for (Long storeId : storeIdSet) {
			StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
			if(storeClerkInfo != null){
				StoreRolePermInfo rolePermInfo = storeClerkInfo.getRolePermInfo(buserId);
				authInfo.addRolePermInfo(storeId, rolePermInfo);
			}
		}

		return authInfo;
	}
	
	public BUserAuthInfo findRolePermInfoByAccount(String priAccountNum) {
		BUser buser = findByPriAccountNum(priAccountNum);
		
		Set<Long> storeIdSet = buser.getStoreIdSet();
		long buserId = buser.getId();
		BUserAuthInfo authInfo = BUserAuthInfo.newInstance(buserId );
		
		for (Long storeId : storeIdSet) {
			StoreClerkInfo storeClerkInfo = StoreClerkInfoMgr.getInstance().getByStoreId(storeId);
			if(storeClerkInfo != null){
				StoreRolePermInfo rolePermInfo = storeClerkInfo.getRolePermInfo(buserId);
				authInfo.addRolePermInfo(storeId, rolePermInfo);
			}
		}
		
		return authInfo;
	}
	
	//设置用户的有效时间与会员等级
	public void checkExpiredTime(BUser bUser) {
		try {
			if(bUser == null || BUserRoleHelper.getInstance().isInitRole(bUser)){
				return;
			}
			if(isBoss(bUser)){
				boolean updFlag = false;
				if(isOldVipType(bUser)){
					bUser.setVipType(StoreVipLevelEnum.StandardUser.ordinal());
					updFlag = true;
				}
				if(bUser.getBusinessPhone() == 0L){
					bUser.setBusinessPhone(ServerConstants.BUSINESS_PHONE);
					updFlag = true;
				}
				if(updFlag){
					BUserDataHolder.getInstance().updpate(bUser);
				}
			}else{
				BUser boss = findMyBoss(bUser.getStoreIdSet());
				if(boss!=null){
					bUser.setExpiredTime(boss.getExpiredTime());
					bUser.setVipType(boss.getVipType());
				}
			}
		} catch (Exception e) {
			MainLog.error(LogModule.BUser, "BUserMgr[checkExpiredTime]", "", e);
		}
	}
	
	private boolean isBoss(BUser bUser){
		return BUserRoleHelper.getInstance().isBossRole(bUser);
	}
	
	//早期的会员类型 0-4
	private boolean isOldVipType(BUser bUser){
		int vipType = (int)bUser.getVipType();
		return vipType < StoreVipLevelEnum.HonKonUser.ordinal();
	}
	
	private BUser findMyBoss(Set<Long> storeIdSet){
		BUser boss = null;
		if(CollectionUtils.isNotEmpty(storeIdSet)){
			List<StoreRO> stores = StoreMgr.getInstance().findByIdList(storeIdSet, storeIdSet.size(), 1);
			if(CollectionUtils.isNotEmpty(stores)){
				long buserId = stores.get(0).getBossId();
				boss = BUserDataHolder.getInstance().get(buserId);
			}
		}
		return boss;
	}
}
