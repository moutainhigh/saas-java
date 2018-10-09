package com.hq.chainStore.service.img.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.img.apiData.FileUploadApiForm;
import com.hq.chainStore.service.img.apiData.ImgResp;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class ImgDAO {
	
	public static ImgDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ImgDAO.class);
	}
	
	public ImgResp saveImg(Object postParam){
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "saveImg");
		RestResp restResp = RestProxy.getInstance().postFile(uri, postParam);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveImg(FileUploadApiForm apiForm, File file){
		
		final String uriPattern = "{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "saveImg");
		Map<String, Object> postParam = apiForm.toMap();
		Map<String,File> fileMap = new HashMap<String, File>();
		fileMap.put("img", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveImgs(FileUploadApiForm apiForm, List<File> files){
		//storeServcie/img/saveImgs/{fileType}/{moduleType}/{moduleId}
		final String uriPattern = "{}/{}/{}/{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "saveImgs", apiForm.getFileType(), apiForm.getModuleType(), apiForm.getModuleId());
		Map<String, Object> postParam = apiForm.toMap();
		Map<String, List<File>> filesMap = new HashMap<String, List<File>>();
		filesMap.put("imgs", files);
		RestResp restResp = RestProxy.getInstance().postMutiFile(uri, postParam, filesMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveBUserImg(long buserId, List<File> files){
		final String uriPattern = "{}/{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "buser",buserId);
		Map<String, Object> postParam = new HashMap<String, Object>();
		Map<String, List<File>> filesMap = new HashMap<String, List<File>>();
		filesMap.put("buserImage", files);
		RestResp restResp = RestProxy.getInstance().postMutiFile(uri, postParam, filesMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveStoreImg(long storeId, List<File> files){
		final String uriPattern = "{}/{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "store",storeId);
		Map<String, Object> postParam = new HashMap<String, Object>();
		Map<String, List<File>> filesMap = new HashMap<String, List<File>>();
		filesMap.put("storeImage", files);
		RestResp restResp = RestProxy.getInstance().postMutiFile(uri, postParam, filesMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveStorePayQrCode(long storeId, Object postParam){
		final String uriPattern = "{}/{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "storePay", storeId);
		RestResp restResp = RestProxy.getInstance().postFile(uri, postParam);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveStorePayQrCode(long storeId, Integer payType, File file){
		final String uriPattern = "{}/{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "storePay",storeId);
		Map<String, Object> postParam = new HashMap<String, Object>();
		postParam.put("payType", String.valueOf(payType));
		Map<String, File> fileMap = new HashMap<String,File>();
		fileMap.put("storePayImage", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
	
	public ImgResp saveProductImg(long storeId, String productId, List<File> files){
		final String uriPattern = "{}/{}/{}/{}/{}";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), "img", "product", storeId, productId);
		Map<String, Object> postParam = new HashMap<String, Object>();
		Map<String, List<File>> filesMap = new HashMap<String,List<File>>();
		filesMap.put("productImage", files);
		RestResp restResp = RestProxy.getInstance().postMutiFile(uri, postParam, filesMap);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), ImgResp.class);
	}
}
