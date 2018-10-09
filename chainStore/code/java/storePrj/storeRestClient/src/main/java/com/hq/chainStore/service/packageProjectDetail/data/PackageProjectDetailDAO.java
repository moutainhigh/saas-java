package com.hq.chainStore.service.packageProjectDetail.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class PackageProjectDetailDAO extends RestDao<PackageProjectDetail> {

	public static PackageProjectDetailDAO getInstance(){
		return HotSwap.getInstance().getSingleton(PackageProjectDetailDAO.class);
	}

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}

	public PageResp<PackageProjectDetail> getPackageProjectDetailPageInfo(String findPath, PackageProjectDetailQueryForm queryForm) {
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), PackageProjectDetail.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("PackageProjectDetailDAO.getPackageProjectDetailPageInfo()", "packageProjectDetail", "", e));
		}
	}
	
}
