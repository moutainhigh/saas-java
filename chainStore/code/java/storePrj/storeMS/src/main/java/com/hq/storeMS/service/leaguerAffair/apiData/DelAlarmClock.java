package com.hq.storeMS.service.leaguerAffair.apiData;

public class DelAlarmClock {
	private long alarmClockId;

	public static DelAlarmClock newInstance() {
		return new DelAlarmClock();
	}

	public long getAlarmClockId() {
		return alarmClockId;
	}

	public void setAlarmClockId(long alarmClockId) {
		this.alarmClockId = alarmClockId;
	}

}
