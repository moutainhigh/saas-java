package com.hq.storeMS.eventHandle.handler;

import org.apache.commons.lang3.StringUtils;

import com.hq.storeMS.eventHandle.IStoreEventHandler;
import com.hq.storeMS.service.leaguerDetail.bs.notice.LeaguerNoticeMgr;
import com.hq.stream.event.StoreEvent;
import com.hq.stream.event.data.EventContent;
import com.zenmind.common.json.JsonUtil;

public class LeaguerBirthdayNoticeHandler implements IStoreEventHandler {
	@Override
	public void handle(StoreEvent event) {
		EventContent data = JsonUtil.getInstance().fromJson(event.getJsonData(), EventContent.class);
		if(data != null && StringUtils.isNoneBlank(data.getRemark())) {
			LeaguerNoticeMgr.getInstance().batchStoreNotice();
		}
	}
}
