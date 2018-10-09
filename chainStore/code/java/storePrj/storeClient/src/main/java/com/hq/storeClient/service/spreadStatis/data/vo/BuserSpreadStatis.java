package com.hq.storeClient.service.spreadStatis.data.vo;

import java.util.HashMap;
import java.util.Map;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 店铺用户推广 订单个数统计实体
 * 
 * @author kevin
 *
 */
@SynClass
public class BuserSpreadStatis {
	// 用户ID
	private long buserId;
	// 每日的个数统计
	private Map<String, DateSpreadStatis> dateSpreadStatisMap = new HashMap<String, DateSpreadStatis>();

	public static BuserSpreadStatis newInstance() {
		BuserSpreadStatis data = new BuserSpreadStatis();
		return data;
	}
	
	public static BuserSpreadStatis newInstance(long buserIdP) {
		BuserSpreadStatis data = newInstance();
		data.buserId = buserIdP;
		return data;
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public Map<String, DateSpreadStatis> getDateSpreadStatisMap() {
		return dateSpreadStatisMap;
	}

	public void setDateSpreadStatisMap(Map<String, DateSpreadStatis> dateSpreadStatisMap) {
		this.dateSpreadStatisMap = dateSpreadStatisMap;
	}
}
