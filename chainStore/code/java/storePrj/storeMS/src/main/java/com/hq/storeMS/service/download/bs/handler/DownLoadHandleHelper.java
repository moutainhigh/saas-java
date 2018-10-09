package com.hq.storeMS.service.download.bs.handler;

import java.util.HashMap;
import java.util.Map;

import com.hq.storeMS.service.download.apiData.DownLoadDataForm;
import com.hq.storeMS.service.download.data.DownLoadDataResult;
import com.hq.storeMS.service.download.data.DownLoadTypeEnum;
import com.zenmind.common.hotSwap.HotSwap;

public class DownLoadHandleHelper {

	public static DownLoadHandleHelper getInstance() {
		return HotSwap.getInstance().getSingleton(DownLoadHandleHelper.class);
	}

	private Map<DownLoadTypeEnum, IDownLoadHandler> handleMapper = new HashMap<DownLoadTypeEnum, IDownLoadHandler>();

	public DownLoadHandleHelper() {
		handleMapper.put(DownLoadTypeEnum.StoreLeaguer, new IDownLoadHandler(){
			@Override
			public DownLoadDataResult download(DownLoadDataForm formInfo) {
				return StoreLeaguerDownLoadMgr.getInstance().downloadLeaguerData(formInfo);
			}
		});
	}

	public DownLoadDataResult download(DownLoadDataForm formInfo) {
		DownLoadTypeEnum downLoadTypeEnum = DownLoadTypeEnum.valueOf(formInfo.getDownLoadType());
		IDownLoadHandler handle = handleMapper.get(downLoadTypeEnum);
		if (handle!=null) {
			return handle.download(formInfo);
		}
		return null;
	}
}
