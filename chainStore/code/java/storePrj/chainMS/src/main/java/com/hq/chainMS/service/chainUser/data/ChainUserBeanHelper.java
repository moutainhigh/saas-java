package com.hq.chainMS.service.chainUser.data;

import java.util.Map;

import com.zenmind.common.hotSwap.HotSwap;

public class ChainUserBeanHelper {

	public static ChainUserBeanHelper getInstance(){
		return HotSwap.getInstance().getSingleton(ChainUserBeanHelper.class);
	}
	
	public boolean addChainBoss(ChainUser chainUser, Long chainId) {
		Map<Long, ChainStoreUserRelative> chainSUMap = chainUser.getChainSUMap();
		if(!chainSUMap.containsKey(chainId)) {
			ChainStoreUserRelative data = ChainStoreUserRelative.newInstance();
			data.setChainId(chainId);
			data.setRole(ChainUserRoleEnum.BOSS.ordinal());
			chainSUMap.put(chainId, data);
			return true;
		}
		return false;
	}
	
	public boolean addChainClerk(ChainUser chainUser, Long chainId) {
		Map<Long, ChainStoreUserRelative> chainSUMap = chainUser.getChainSUMap();
		if(!chainSUMap.containsKey(chainId)) {
			ChainStoreUserRelative data = ChainStoreUserRelative.newInstance();
			data.setChainId(chainId);
			data.setRole(ChainUserRoleEnum.CLERK.ordinal());
			chainSUMap.put(chainId, data);
			return true;
		}
		return false;
	}
	
	public boolean addStore(ChainUser chainUser, Long chainId, Long storeId) {
		Map<Long, ChainStoreUserRelative> chainSUMap = chainUser.getChainSUMap();
		if(chainSUMap.containsKey(chainId)) {
			ChainStoreUserRelative data = chainSUMap.get(chainId);
			data.setChainId(chainId);
			data.addStoreId(storeId);
			return true;
		}
		return false;
	}
	
	public boolean removeStore(ChainUser chainUser, Long chainId, Long storeId) {
		Map<Long, ChainStoreUserRelative> chainSUMap = chainUser.getChainSUMap();
		if(chainSUMap.containsKey(chainId)) {
			ChainStoreUserRelative data = chainSUMap.get(chainId);
			data.setChainId(chainId);
			data.removeStoreId(storeId);
			return true;
		}
		return false;
	}
}
