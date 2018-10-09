package com.hq.storeMS.service.spreadStatis.data.vo;

import java.util.HashMap;
import java.util.Map;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 店铺用户推广 订单个数统计实体
 * @author kevin
 *
 */
@SynClass
public class StoreSpreadStatis {
	//店铺ID
	private long storeId;
	//店铺员工的统计
	private Map<Long, BuserSpreadStatis> buserSpreadStatisMap = new HashMap<Long, BuserSpreadStatis>();

	public static StoreSpreadStatis newInstance() {
		StoreSpreadStatis data = new StoreSpreadStatis();
		return data;
	}
	
	public static StoreSpreadStatis newInstance(long storeIdP) {
		StoreSpreadStatis data = newInstance();
		data.storeId = storeIdP;
		return data;
	}
	
	//获取店铺用户的统计信息
	public BuserSpreadStatis takeBuserSpreadStatis(long buserId) {
		return buserSpreadStatisMap.get(buserId);
	}
	
	//获取店铺用户指定日期的统计信息
	public DateSpreadStatis takeDateSpreadStatis(long buserId, String date) {
		BuserSpreadStatis buserSpreadStatis = buserSpreadStatisMap.get(buserId);
		if(buserSpreadStatis == null) {
			return null;
		}
		return buserSpreadStatis.getDateSpreadStatisMap().get(date);
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Map<Long, BuserSpreadStatis> getBuserSpreadStatisMap() {
		return buserSpreadStatisMap;
	}

	public void setBuserSpreadStatisMap(Map<Long, BuserSpreadStatis> buserSpreadStatisMap) {
		this.buserSpreadStatisMap = buserSpreadStatisMap;
	}
}
