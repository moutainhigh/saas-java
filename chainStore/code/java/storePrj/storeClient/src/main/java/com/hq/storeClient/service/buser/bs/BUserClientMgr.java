package com.hq.storeClient.service.buser.bs;

import java.util.List;
import java.util.Set;

import com.hq.storeClient.common.restClientResp.PageResp;
import com.hq.storeClient.common.utils.StringUtils4Client;
import com.hq.storeClient.service.buser.apiData.BUserAddApiForm;
import com.hq.storeClient.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeClient.service.buser.apiData.BUserChainQueryForm;
import com.hq.storeClient.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeClient.service.buser.apiData.BUserLoginApiForm;
import com.hq.storeClient.service.buser.apiData.BUserLoginWithJsCodeForm;
import com.hq.storeClient.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeClient.service.buser.data.BUser;
import com.hq.storeClient.service.buser.data.BUserDAO;
import com.zenmind.common.hotSwap.HotSwap;
import com.zenmind.common.json.JsonUtil;
import com.zenmind.dao.rest.ReqMap;
import com.zenmind.dao.rest.RestResp;

public class BUserClientMgr {

	public static BUserClientMgr getInstance() {
		return HotSwap.getInstance().getSingleton(BUserClientMgr.class);
	}
	
	public RestResp login(BUserLoginApiForm loginForm) {
		final String actionName = "login";
		RestResp resp = BUserDAO.getInstance().rawReq(actionName , loginForm);
		return resp;
	}
	
	public RestResp loginWithJsCode(BUserLoginWithJsCodeForm loginForm) {
		final String actionName = "loginWithJsCode";
		RestResp resp = BUserDAO.getInstance().rawReq(actionName , loginForm);
		return resp;
	}
	
	public RestResp reg(BUserAddApiForm regForm) {
		final String actionName = "reg";
		RestResp resp = BUserDAO.getInstance().rawReq(actionName , regForm);
		return resp;
	}
	
	public BUser get(long id) {
		return BUserDAO.getInstance().get(id);
	}
	
	public BUser update(long buserId, BUserUpdateApiForm updateForm) {
		return BUserDAO.getInstance().updateWithResp(buserId, updateForm);
	}
	
	public BUser findByPhone(long phone) {
		final String uriPath = "findByPhone";
		ReqMap reqMap = ReqMap.newInstance().add("phone", phone);
		BUser buser = BUserDAO.getInstance().findOneWithReqParam(uriPath, reqMap);
		return buser;
	}
	
	public List<BUser> findByMultitId(Set<Long> ids) {
		final String findPath = "findByMultitId";
		ReqMap reqMap = ReqMap.newInstance();
		reqMap.add("idList", StringUtils4Client.join(ids, ","));
		return BUserDAO.getInstance().findWithReqParam(findPath, reqMap, ids.size(), 1);
	}
	
	public PageResp<BUser> findByChain(BUserChainQueryForm queryForm) {
		final String findPath = "findByChain";
		return BUserDAO.getInstance().findByChain(findPath, queryForm);
	}
	
	public BUser regByChainForm(BUserAddByChainForm formInfo) {
		final String actionName = "regByChainForm";
		RestResp resp = BUserDAO.getInstance().rawReq(actionName, formInfo);
		if(resp!=null && resp.gettJson() != null){
			return JsonUtil.getInstance().fromJson(resp.gettJson(), BUser.class);
		}
		return null;
	}
	
	public PageResp<BUser> findByCond(BUserCommQueryForm queryForm) {
		final String findPath = "findByCond";
		return BUserDAO.getInstance().findByCond(findPath, queryForm);
	}
}
