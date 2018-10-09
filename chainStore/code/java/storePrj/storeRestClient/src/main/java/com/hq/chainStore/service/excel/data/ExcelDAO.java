package com.hq.chainStore.service.excel.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.excel.apiData.ExcelUpLoadForm;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

/** 
 * @ClassName: ExcelDAO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年4月2日 上午10:44:00 
 *  
 */
public class ExcelDAO extends RestDao<ExcelLeaguer>{

	private final String reqPath = "excel";

	public static ExcelDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ExcelDAO.class);
	}
	
	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public RestResp resolveLeaguerExcel(ExcelUpLoadForm upForm,File file){
		final String uriPattern = "{}/{}/{}";
		String findPath = "leaguer";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), reqPath, findPath);
		Map<String, Object> postParam = upForm.toMap();
		Map<String,File> fileMap = new HashMap<String, File>();
		fileMap.put("excel", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return restResp;
	}

	public RestResp resolveProductExcel(ExcelUpLoadForm upForm,File file){
		final String uriPattern = "{}/{}/{}";
		String findPath = "product";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), reqPath, findPath);
		Map<String, Object> postParam = upForm.toMap();
		Map<String,File> fileMap = new HashMap<String, File>();
		fileMap.put("excel", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return restResp;
	}
	
	public RestResp resolveGoodsExcel(ExcelUpLoadForm upForm,File file){
		final String uriPattern = "{}/{}/{}";
		String findPath = "goods";
		String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), reqPath, findPath);
		Map<String, Object> postParam = upForm.toMap();
		Map<String,File> fileMap = new HashMap<String, File>();
		fileMap.put("excel", file);
		RestResp restResp = RestProxy.getInstance().postSingleFile(uri, postParam, fileMap);
		return restResp;
	}
	
}
