package com.hq.storeMS.service.download.bs;

import com.hq.storeMS.common.log.LogHelper;
import com.hq.storeMS.common.log.LogModule;
import com.hq.storeMS.common.log.MainLog;
import com.hq.storeMS.service.download.apiData.DownLoadDataForm;
import com.hq.storeMS.service.download.bs.handler.DownLoadHandleHelper;
import com.hq.storeMS.service.download.data.DownLoadDataResult;
import com.zenmind.common.hotSwap.HotSwap;

public class DownLoadHandler {
	
	public static DownLoadHandler getInstance() {
		return HotSwap.getInstance().getSingleton(DownLoadHandler.class);
	}

	public DownLoadDataResult download(DownLoadDataForm formInfo) {
		DownLoadDataResult result = null;
		try {
			result = DownLoadHandleHelper.getInstance().download(formInfo);
			if(result == null ) {
				result = DownLoadDataResult.newInstance();
			}
			return result;
		} catch (Exception e) {
			String reason = LogHelper.getInstance().exceptionReason(formInfo);
			MainLog.error(LogModule.Tmp, "DownLoadHandler[download]", reason, e);
		}
		return result;
	}

}
