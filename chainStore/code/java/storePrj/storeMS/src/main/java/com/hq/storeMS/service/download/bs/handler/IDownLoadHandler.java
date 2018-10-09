package com.hq.storeMS.service.download.bs.handler;

import com.hq.storeMS.service.download.apiData.DownLoadDataForm;
import com.hq.storeMS.service.download.data.DownLoadDataResult;

public interface IDownLoadHandler {
	public DownLoadDataResult download(DownLoadDataForm formInfo);
}
