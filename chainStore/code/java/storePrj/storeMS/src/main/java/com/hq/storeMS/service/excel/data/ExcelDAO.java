package com.hq.storeMS.service.excel.data;

import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

/** 
 * @ClassName: ExcelDAO 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年4月2日 上午10:44:00 
 *  
 */
public class ExcelDAO {

	private final String reqPath = "file";

	public static ExcelDAO getInstance(){
		return HotSwap.getInstance().getSingleton(ExcelDAO.class);
	}
	
	public FileResp saveExcel(Object postParam){
		final String uriPattern = "{}/{}/{}";
		String findPath = "excel";
		String uri = StringFormatUtil.format(uriPattern, StoreMSCfgMgr.getProp().getFileHost(),reqPath,findPath);
		RestResp restResp = RestProxy.getInstance().postFile(uri, postParam);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), FileResp.class);
	}
}
