package com.hq.chainMS.service.chainUser.bs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.hq.chainMS.common.util.PageUtil;
import com.hq.chainMS.service.auth.chainUser.ChainUserAuthInfo;
import com.hq.chainMS.service.auth.chainUser.ChainUserPwdHelper;
import com.hq.chainMS.service.chain.bs.ChainMgr;
import com.hq.chainMS.service.chain.data.Chain;
import com.hq.chainMS.service.chainClerk.bs.ChainClerkMgr;
import com.hq.chainMS.service.chainClerk.data.ChainClerk;
import com.hq.chainMS.service.chainClerk.data.ClerkInfo;
import com.hq.chainMS.service.chainClerk.data.adminRole.RolePermInfo;
import com.hq.chainMS.service.chainUser.apiData.ChainUserQueryForm;
import com.hq.chainMS.service.chainUser.apiData.ResetPasswordForm;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.chainUser.data.ChainUserDto;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.store.bs.StoreDataHolder;
import com.hq.storeClient.service.store.apiData.StoreQueryForm;
import com.hq.storeClient.service.store.data.Store;
import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserMgr {

	public static ChainUserMgr getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserMgr.class);
	}

	public void update(ChainUser target) {
		ChainUserDataHolder.getInstance().updpate(target);
	}
	
	public ChainUser get(long id) {
		return ChainUserDataHolder.getInstance().get(id);
	}
	
	public ChainUser findByPhone(long phone) {
		return ChainUserDataHolder.getInstance().findByPhone(phone);
	}
	
	public ChainUser createUser(ChainUser chainUser) {
		// 加密密码
		ChainUserPwdHelper.getInstance().encryptUser(chainUser);
		ChainUserDataHolder.getInstance().addAndReturnId(chainUser);
		return chainUser;
	}

	public ChainUser changePassword(ChainUser chainUser,String newPassword) {
		String encryptPassword = ChainUserPwdHelper.getInstance().getEncryptPassword(chainUser, newPassword);
		chainUser.setPassword(encryptPassword);
		update(chainUser);
		return chainUser;
	}
	
	public ChainUserAuthInfo findRolePermInfoByPhone(long phone) {
		ChainUser chainUser = findByPhone(phone);
		
		Set<Long> chainIds = chainUser.takeChainIds();
		long userId = chainUser.getId();
		ChainUserAuthInfo authInfo = ChainUserAuthInfo.newInstance(userId);
		
		for (long chainId : chainIds) {
			ChainClerk chainClerk = ChainClerkMgr.getInstance().get(chainId);
			if(chainClerk != null){
				RolePermInfo rolePermInfo = chainClerk.takeRolePermInfo(userId);
				authInfo.addRolePermInfo(chainId, rolePermInfo);
			}
		}
		return authInfo;
	}
	
	public ChainUser resetPassword(ResetPasswordForm resetPasswordData) {
		long phone =  resetPasswordData.getPhone();
		String newPassword = resetPasswordData.getPassword();
		ChainUser chainUser = ChainUserDataHolder.getInstance().findByPhone(phone);
		String encryptPassword = ChainUserPwdHelper.getInstance().getEncryptPassword(chainUser, newPassword);
		chainUser.setPassword(encryptPassword);
		update(chainUser);
		return chainUser;
	}

	public PageResp<ChainUserDto> findByCond(ChainUserQueryForm queryForm) {
		List<ChainUser> list = ChainUserDataHolder.getInstance().findByCond(queryForm);
		List<ChainUserDto> resultList = filterRecord(queryForm, list);
		attachStoreNames(resultList);
		return PageUtil.getInstance().buildPageResp(resultList, queryForm.getPageNo(), queryForm.getPageItemCount());
	}
	
	private void attachStoreNames(List<ChainUserDto> list) {
		//收集列表的店铺id集合
		Set<Long> storeIds = new HashSet<Long>();
		for (ChainUserDto chainUserDto : list) {
			storeIds.addAll(chainUserDto.getStoreIds());
		}
		//获取store列表  提取店铺名称
		if(CollectionUtils.isNotEmpty(storeIds)) {
			Map<Long, Store> storeMap = new HashMap<Long, Store>();
			StoreQueryForm queryForm = StoreQueryForm.newInstance();
			queryForm.setStoreIds(storeIds);
			com.hq.storeClient.common.restClientResp.PageResp<Store> page = StoreDataHolder.getInstance().findStoreByCond(queryForm);
			if(page!=null) {
				List<Store> list2 = page.getList();
				for (Store store : list2) {
					storeMap.put(store.getId(), store);
				}
			}
			if(MapUtils.isNotEmpty(storeMap)) {
				for (ChainUserDto chainUserDto : list) {
					chainUserDto.addStoreNames(storeMap);
				}
			}
		}
	}
	
	private List<ChainUserDto> filterRecord(ChainUserQueryForm queryForm, List<ChainUser> list) {
		List<ChainUserDto> result = new ArrayList<ChainUserDto>();
		ChainClerk chainClerk = ChainClerkMgr.getInstance().get(queryForm.getChainId());
		Chain chain = ChainMgr.getInstance().get(queryForm.getChainId());
		long bossId = chain.getBossId();
		Map<Long, ClerkInfo> clerkInfoMap = chainClerk.getClerkInfoMap();
		if (CollectionUtils.isNotEmpty(list)) {
			for (ChainUser record : list) {
				if(record.getId() == bossId) {//排除老板的用户
					continue;
				}
				if (isRightRecord(queryForm, record, clerkInfoMap)) {
					result.add(convertDto(record, chainClerk));
				}
			}
		}
		Collections.sort(result, new Comparator<ChainUserDto>() {
			@Override
			public int compare(ChainUserDto o1, ChainUserDto o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return result;
	}
	
	private ChainUserDto convertDto(ChainUser record, ChainClerk chainClerk) {
		ChainUserDto data = ChainUserDto.newInstance(record);
		data.addAdminRoles(chainClerk.takeUserRole(record.getId()));
		return data;
	}

	private boolean isRightRecord(ChainUserQueryForm queryForm, ChainUser record, Map<Long, ClerkInfo> clerkInfoMap) {
		if (!checkCrossClerks(queryForm.getCrossClerks(), record)) {
			return false;
		}
		
		if (!checkRoleId(queryForm.getRoleId(), record, clerkInfoMap)) {
			return false;
		}
		
		if (!checkPhoneOrName(queryForm.getPhoneOrName(), record)) {
			return false;
		}

		return true;
	}

	private boolean checkCrossClerks(Set<Integer> crossClerks, ChainUser record) {
		if (CollectionUtils.isEmpty(crossClerks)) {
			return true;
		}
		if(crossClerks.contains(record.getCrossClerk())) {
			return true;
		}
		return false;
	}
	
	private boolean checkRoleId(int roleId, ChainUser record, Map<Long, ClerkInfo> clerkInfoMap) {
		if (roleId == 0) {
			return true;
		}
		ClerkInfo clerkInfo = clerkInfoMap.get(record.getId());
		if(clerkInfo!=null && clerkInfo.getRoleSet().contains(roleId)) {
			return true;
		}
		return false;
	}
	
	private boolean checkPhoneOrName(String phoneOrName, ChainUser record) {
		if (StringUtils.isBlank(phoneOrName)) {
			return true;
		}

		if (record.getName()!=null && record.getName().contains(phoneOrName)) {
			return true;
		}
		
		if (String.valueOf(record.getPhone()).contains(phoneOrName)) {
			return true;
		}

		return false;
	}
}
