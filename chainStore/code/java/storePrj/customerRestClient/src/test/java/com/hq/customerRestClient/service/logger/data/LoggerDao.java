package com.hq.customerRestClient.service.logger.data;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.hq.customerRestClient.service.logger.apiData.LoggerAddApiForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestProxy;

public class LoggerDao {
	public static LoggerDao getInstance() {
		return HotSwap.getInstance().getSingleton(LoggerDao.class);
	}

	public void saveLogger(LoggerAddApiForm addForm) {
		final String uriPattern = "{}/{}/";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getService(), "logger");
		RestProxy.getInstance().rawReq(uri, addForm);
	}
}
