package com.hq.chainStore.service.homePage.data;


/**
 * 统计数据
 */
public class StatisticsData {
	//今日预约
	private int appointCount;
	//客户总数
	private int leaguerCount;
	//本月销售额
	private float monthOrderCount;
	
	public static StatisticsData newInstance() {
		StatisticsData data = new StatisticsData();
		return data;
	}

	public int getAppointCount() {
		return appointCount;
	}

	public void setAppointCount(int appointCount) {
		this.appointCount = appointCount;
	}

	public int getLeaguerCount() {
		return leaguerCount;
	}

	public void setLeaguerCount(int leaguerCount) {
		this.leaguerCount = leaguerCount;
	}

	public float getMonthOrderCount() {
		return monthOrderCount;
	}

	public void setMonthOrderCount(float monthOrderCount) {
		this.monthOrderCount = monthOrderCount;
	}

}
