package com.hq.storeMS.service.storeVipType.bs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hq.storeMS.service.storeVipType.data.StoreVipLevelEnum;
import com.hq.storeMS.service.storeVipType.data.StoreVipType;
import com.zenmind.common.hotSwap.HotSwap;

public class StoreVipTypeMgr {
	
	public static StoreVipTypeMgr getInstance(){
		return HotSwap.getInstance().getSingleton(StoreVipTypeMgr.class);
	}
	
	/**
	 * 初始化四种会员类型
	 */
	public void init(){
		final int pageItemCount = 20;
		final int pageNo = 1;
		List<StoreVipType> findPage = findPage(pageItemCount,pageNo);
		if(CollectionUtils.isEmpty(findPage)){
			StoreVipLevelEnum[] values = StoreVipLevelEnum.values();
			for (StoreVipLevelEnum storeVipLevelEnum : values) {
				add(StoreVipType.newInstance(storeVipLevelEnum.ordinal()));
			}
		}else{
			Map<Integer, StoreVipType> vipMap = new HashMap<Integer, StoreVipType>();
			for (StoreVipType storeVipType : findPage) {
				vipMap.put(storeVipType.getLevel(), storeVipType);
			}
			StoreVipLevelEnum[] values = StoreVipLevelEnum.values();
			for (StoreVipLevelEnum storeVipLevelEnum : values) {
				if(vipMap.get((Integer)storeVipLevelEnum.ordinal()) == null){
					add(StoreVipType.newInstance(storeVipLevelEnum.ordinal()));
				}
			}
		}
	}
	
	public StoreVipType get(long id) {
		return StoreVipTypeDataHolder.getInstance().get(id);
	}
	
	public void add(StoreVipType target) {
		StoreVipTypeDataHolder.getInstance().addWithId(target);
	}
	
	public void update(StoreVipType storeVipType) {
		StoreVipTypeDataHolder.getInstance().update(storeVipType);
	}
	
	public List<StoreVipType> findPage(int pageItemCount,int pageNo){
		return StoreVipTypeDataHolder.getInstance().findPage(pageItemCount, pageNo);
	}
	
	public StoreVipType findByLevel(int vipLevel) {
		return StoreVipTypeDataHolder.getInstance().findByLevel(vipLevel);
	}
	
	public StoreVipType findByName(String vipName) {
		return StoreVipTypeDataHolder.getInstance().findByName(vipName);
	}
	
}
