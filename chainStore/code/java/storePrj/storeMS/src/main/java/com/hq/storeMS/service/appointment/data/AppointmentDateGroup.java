package com.hq.storeMS.service.appointment.data;

import com.zenmind.dataSyn.annotation.SynClass;

/**
 * 按日期统计每天的订单数量
 * 
 * @author kevin
 *
 */
@SynClass
public class AppointmentDateGroup {
	// 日期：yyyyMMdd
	private String date;
	// 日期yyyyMMdd 00:00:00 时间戳
	private long dateTime;
	// 数据量
	private int count;

	public static AppointmentDateGroup newInstance() {
		AppointmentDateGroup data = new AppointmentDateGroup();
		return data;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
