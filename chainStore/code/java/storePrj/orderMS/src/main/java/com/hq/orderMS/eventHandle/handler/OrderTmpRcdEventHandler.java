package com.hq.orderMS.eventHandle.handler;

import org.apache.commons.lang3.StringUtils;

import com.hq.orderMS.common.log.LogModule;
import com.hq.orderMS.common.log.MainLog;
import com.hq.orderMS.eventHandle.IStoreEventHandler;
import com.hq.orderMS.service.orderTmpRcd.bs.OrderTmpRcdMgr;
import com.hq.stream.event.StoreEvent;
import com.hq.stream.event.data.EventContent;
import com.zenmind.common.json.JsonUtil;

public class OrderTmpRcdEventHandler implements IStoreEventHandler {
	@Override
	public void handle(StoreEvent event) {
		EventContent data = JsonUtil.getInstance().fromJson(event.getJsonData(), EventContent.class);
		if(data != null && StringUtils.isNoneBlank(data.getRemark())) {
			MainLog.info(LogModule.Tmp, "OrderTmpRcdEventHandler[handle]", data.getRemark());
			OrderTmpRcdMgr.getInstance().checkOrderTmpRcd();
		}
	}
}
