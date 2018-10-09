package com.hq.chainStore.service.appVersion.bs;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.hq.chainStore.service.common.FileResp;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class UploadApkMgr {
//	private final String fileService="http://www.zhimeitimes.com:9115/storefilems/ws/v1/file";
	private final String fileService="http://192.168.40.220:9115/storefilems/ws/v1/file";
	
	public static UploadApkMgr getInstance(){
		return HotSwap.getInstance().getSingleton(UploadApkMgr.class);
	}
	
	public FileResp uploadApk(String path, File file) {
		Map<String, Object> postParam = new HashMap<String, Object>();
		postParam.put("path", path);
		final String uriPattern = "{}/{}";
		String uri = StringFormatUtil.format(uriPattern, fileService, "saveFileWithOriginInfo");
		Map<String,File> fileMap = new HashMap<String, File>();
		fileMap.put("file", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), FileResp.class);
	}
}
