package com.hq.customerRestClient.service.img.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.customerRestClient.common.restClientResp.RestClientCfg;
import com.hq.customerRestClient.service.img.apiData.FileUploadApiForm;
import com.hq.customerRestClient.service.img.apiData.ImgResp;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class ImgDAO {
	
	public static ImgDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ImgDAO.class);
	}
	
	public ImgResp saveImg(FileUploadApiForm apiForm, File file){
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getService(), "img", "saveImg");
		Map<String, Object> postParam = apiForm.toMap();
		Map<String,File> fileMap = new HashMap<String, File>();
		fileMap.put("img", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveImgs(FileUploadApiForm apiForm, List<File> files){
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getService(), "img", "saveImgs", apiForm.getFileType(), apiForm.getModuleType(), apiForm.getModuleId());
		Map<String, Object> postParam = apiForm.toMap();
		Map<String, List<File>> filesMap = new HashMap<String, List<File>>();
		filesMap.put("imgs", files);
		RestResp restResp = RestProxy.getInstance().postMutiFile(uri, postParam, filesMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
}
