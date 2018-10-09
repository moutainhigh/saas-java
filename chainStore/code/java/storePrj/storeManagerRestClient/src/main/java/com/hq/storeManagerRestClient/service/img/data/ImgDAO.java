package com.hq.storeManagerRestClient.service.img.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.hq.storeManagerRestClient.common.restClientResp.RestClientCfg;
import com.hq.storeManagerRestClient.service.img.apiData.FileUploadApiForm;
import com.hq.storeManagerRestClient.service.img.apiData.ImgResp;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class ImgDAO {
	
	public static ImgDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ImgDAO.class);
	}
	
	public String getService() {
		return RestClientCfg.getService();
	}
	
	public ImgResp saveImg(Object postParam){
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, getService(), "img", "saveImg");
		RestResp restResp = RestProxy.getInstance().postFile(uri, postParam);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveImg(FileUploadApiForm apiForm, File file){
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, getService(), "img", "saveImg");
		Map<String, Object> postParam = apiForm.toMap();
		Map<String,File> fileMap = new HashMap<String, File>();
		fileMap.put("img", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
}
