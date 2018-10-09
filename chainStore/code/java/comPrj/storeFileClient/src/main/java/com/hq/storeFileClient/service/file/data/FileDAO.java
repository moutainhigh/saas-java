package com.hq.storeFileClient.service.file.data;

import com.hq.storeFileClient.service.common.StoreFileClientCfg;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class FileDAO {

	public static FileDAO getInstance() {
		return HotSwap.getInstance().getSingleton(FileDAO.class);
	}

	public FileResp postFile(Object postParam) {
		final String uriPattern = "{}/{}";
		String uri = StringFormatUtil.format(uriPattern, StoreFileClientCfg.getStoreFileServer(), "file");
		RestResp restResp = RestProxy.getInstance().postFile(uri, postParam);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), FileResp.class);
	}
	
	public FileResp saveFileWithOriginInfo(Object postParam) {
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, StoreFileClientCfg.getStoreFileServer(), "file", "saveFileWithOriginInfo");
		RestResp restResp = RestProxy.getInstance().postFile(uri, postParam);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), FileResp.class);
	}

	public void deleteFile(String filePath) {
		final String uriPattern = "{}/{}?filePath={}";
		String uri = StringFormatUtil.format(uriPattern, StoreFileClientCfg.getStoreFileServer(), "file", filePath);
		RestProxy.getInstance().delete(uri);
	}
}
