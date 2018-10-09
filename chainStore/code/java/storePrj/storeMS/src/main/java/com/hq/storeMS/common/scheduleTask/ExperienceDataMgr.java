package com.hq.storeMS.common.scheduleTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.hq.storeMS.rabbitmq.TaskSenderMgr;
import com.hq.stream.task.StoreTask;
import com.hq.stream.task.StoreTaskType;
import com.hq.stream.task.data.TaskAwakeData;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;

public class ExperienceDataMgr {
	
	public static ExperienceDataMgr getInstance() {
		return HotSwap.getInstance().getSingleton(ExperienceDataMgr.class);
	}
	
	private static final DateFormat hourDf = new SimpleDateFormat("HH");
	
	//触发体验数据生成
	public void refreshAppoint(){
		if(!StoreMSCfgMgr.getProp().isGenDataEnabled()){
			return;
		}
		String format = hourDf.format(new Date());
		if("01".equals(format) || "02".equals(format)){
			StoreTask task = new StoreTask();
			TaskAwakeData data = new TaskAwakeData();
			data.setJarPath("/jarPath/experienceDataClient-0.0.1-SNAPSHOT-jar-with-dependencies.jar");
			data.setClassName("com.hq.GeneratePeriodData");
			data.setMethodName("genData");
			task.setJsonData(JsonUtil.getInstance().toJson(data));
			task.setTaskType(StoreTaskType.GenExperienceData.ordinal());
			task.setUserId(RandomUtils.nextLong(1000L, 9999L));
			
			if(TaskSenderMgr.getInstance().isOpen()){
				TaskSenderMgr.getInstance().sendTask(task);
			}
		}
	}
}
