package com.hq.storeMS.service.bossPayInfo.data;

import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeMS.common.config.StoreMSCfgMgr;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestResp;

public class CertFileRestDAO {
	private final String reqPath = "file";

	public static CertFileRestDAO getInstance(){
		return HotSwap.getInstance().getSingleton(CertFileRestDAO.class);
	}
	
	public FileResp uploadCertFile(Object postParam){
		final String uriPattern = "{}/{}/{}";
		String findPath = "cert";
		String uri = StringFormatUtil.format(uriPattern, StoreMSCfgMgr.getProp().getFileHost(),reqPath,findPath);
		RestResp restResp = RestProxy.getInstance().postFile(uri, postParam);
		return JsonUtil.getInstance().fromJson(restResp.gettJson(), FileResp.class);
	}
	
	
}
