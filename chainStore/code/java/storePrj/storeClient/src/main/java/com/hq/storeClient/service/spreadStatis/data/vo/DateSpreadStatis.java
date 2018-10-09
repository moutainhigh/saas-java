package com.hq.storeClient.service.spreadStatis.data.vo;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 按日统计订单数量
 * 
 * @author kevin
 *
 */
@SynClass
public class DateSpreadStatis {
	// 日期：yyyyMMdd
	private String date;
	// 日期yyyyMMdd 00:00:00 时间戳
	private long dateTime;
	// 数据量
	private int count;

	public static DateSpreadStatis newInstance() {
		DateSpreadStatis data = new DateSpreadStatis();
		return data;
	}
	
	public static DateSpreadStatis newInstance(String dateP, long dateTimeP) {
		DateSpreadStatis data = newInstance();
		data.date = dateP;
		data.dateTime = dateTimeP;
		return data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}
}
