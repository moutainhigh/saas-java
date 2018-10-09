package com.hq.customerMS.service.areaCode.bs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hq.customerMS.common.log.LogHelper;
import com.hq.customerMS.common.log.LogModule;
import com.hq.customerMS.common.log.MainLog;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RespStatus;
import com.hq.storeClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeClient.service.areaCode.data.AreaCode;
import com.zenmind.common.hotSwap.HotSwap;

public class AreaCodeHandler {

	public static AreaCodeHandler getInstance() {
		return HotSwap.getInstance().getSingleton(AreaCodeHandler.class);
	}
	
	private final LogModule logModule = LogModule.AreaCode;
	
	public ReqResult<AreaCode> findByCond(AreaCodeQueryForm queryForm) {
		ReqResult<AreaCode> result = ReqResult.newInstance(false, AreaCode.class);
		try {
			List<AreaCode> areaCodes = AreaCodeMgr.getInstance().findByCond(queryForm);
			if(CollectionUtils.isEmpty(areaCodes)){
				areaCodes = new ArrayList<AreaCode>();
			}
			result.setTargetList(areaCodes);
			result.setSuccess(true);
		} catch (Exception e) {
			final String reason = LogHelper.getInstance().exceptionReason(queryForm);
			result.setStatus(RespStatus.INTERNAL_SERVER_ERROR);
			result.setTips("服务暂不可用，请稍后尝试。");
			MainLog.error(logModule, "AreaCodeHandler[findByCond]", reason, e);
		}
		return result;
	}
}
