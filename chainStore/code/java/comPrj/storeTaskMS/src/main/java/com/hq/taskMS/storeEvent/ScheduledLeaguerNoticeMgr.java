package com.hq.taskMS.storeEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.hq.stream.event.StoreEvent;
import com.hq.stream.event.StoreEventType;
import com.hq.stream.event.data.EventContent;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class ScheduledLeaguerNoticeMgr {
	public static ScheduledLeaguerNoticeMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ScheduledLeaguerNoticeMgr.class);
	}

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	// 只初始化一次
	private AtomicBoolean isInit = new AtomicBoolean(false);

	// 一小时的毫秒数
	private final long oneHour = 3600L * 1000;
	// 1-6 运行时间点 凌晨1点到6点
	private final int startTime = 2;
	// 日期格式
	private final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void init() {
		if (isInit.compareAndSet(false, true)) {
			// 每24小时 触发一次
			executor.scheduleAtFixedRate(new Runnable() {
				@Override
				public void run() {
					EventContent content = EventContent.newInstance();
					content.setRemark("test");
					StoreEvent event = new StoreEvent();
					event.setTid(UUID.randomUUID().toString());
					event.setEventType(StoreEventType.LeaguerBirthdayNotice.ordinal());
					event.setStoreId(0);
					event.setJsonData(JsonUtil.getInstance().toJson(content));
					StoreEventSenderMgr.getInstance().send(event);
				}
			}, getDelayTime(), 24L * oneHour, TimeUnit.MILLISECONDS);
		}
	}

	private long getDelayTime() {
		Date now = new Date();
		// 第二天的指定时钟 毫秒数
		long delay = getNextDateTime(now) + startTime * oneHour;
		return delay - now.getTime();
	}

	// 第二天的毫秒数
	private long getNextDateTime(Date date) {
		try {
			String str1 = sdf.format(date);
			Calendar nextDate = Calendar.getInstance();
			nextDate.setTime(sdf.parse(str1));
			nextDate.add(Calendar.DATE, 1);
			return nextDate.getTime().getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
