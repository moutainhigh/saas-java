package com.hq.chainStore.service.workFlowData.data;

import com.hq.chainStore.service.common.PageResp;
import com.hq.chainStore.service.common.RestClientCfg;
import com.hq.chainStore.service.workFlowData.apiData.WorkFlowDataQueryForm;
import com.hq.common.JsonUtil4Client;
import com.zenmind.common.StringFormatUtil;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.RestDao;
import com.zenmind.dao.rest.RestDaoException;
import com.zenmind.dao.rest.RestProxy;
import com.zenmind.dao.rest.RestProxyException;
import com.zenmind.dao.rest.RestResp;

public class WorkFlowDataDAO extends RestDao<WorkFlowData> {

	public static WorkFlowDataDAO getInstance(){
		return HotSwap.getInstance().getSingleton(WorkFlowDataDAO.class);
	}
	
	private final String tableName = "workFlowData";

	@Override
	public String getService() {
		return RestClientCfg.getStoreService();
	}
	
	public PageResp<WorkFlowData> findWorkFlowDataPageInfo(String findPath, WorkFlowDataQueryForm queryForm){
		try {
			RestResp restResp = super.rawGetReq(findPath, queryForm.toReqMap(), queryForm.getPageItemCount(), queryForm.getPageNo());
			return JsonUtil4Client.getInstance().parseTPage(restResp.gettJson(), WorkFlowData.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("WorkFlowDataDAO.findWorkFlowDataPageInfo()", tableName, "", e));
		}
	}
	
	/**
	 * ================================对子域的操作================================
	 */
	public WorkFlowData updateSubDomains(long workFlowDataId, Object id, Object inputForm, String module) {
		try {
			final String uriPattern = "{}/{}/{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), tableName, module, workFlowDataId, id);
			return RestProxy.getInstance().update(uri, inputForm, WorkFlowData.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("WorkFlowDataDAO.updateSubDomains()", tableName, "", e));
		}
	}
	
	public WorkFlowData deleteSubDomains(long workFlowDataId, Object id, String module) {
		try {
			final String uriPattern = "{}/{}/{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), tableName, module, workFlowDataId, id);
			return RestProxy.getInstance().delete(uri, WorkFlowData.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("WorkFlowDataDAO.deleteSubDomains()", tableName, "", e));
		}
	}
	
	public WorkFlowData addSubDomains(long workFlowDataId, Object inputForm, String module) {
		try {
			final String uriPattern = "{}/{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), tableName, module, workFlowDataId);
			return RestProxy.getInstance().add(uri, inputForm, WorkFlowData.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("WorkFlowDataDAO.addSubDomains()", tableName, "", e));
		}
	}
	
	public WorkFlowData rawReqSubDomains(String actionName, long workFlowDataId, Object inputForm, String module) {
		try {
			final String uriPattern = "{}/{}/{}/{}/{}";
			String uri = StringFormatUtil.format(uriPattern, RestClientCfg.getStoreService(), tableName, module, actionName, workFlowDataId);
			RestResp restResp=RestProxy.getInstance().rawReq(uri, inputForm);
			String tJson = restResp.gettJson();
			return JsonUtil.getInstance().fromJson(tJson, WorkFlowData.class);
		} catch (Exception e) {
			if(e instanceof RestProxyException){
				throw((RestProxyException)e);
			}
			throw(RestDaoException.newInstance("WorkFlowDataDAO.rawReqSubDomains()", tableName, "", e));
		}
	}
}
