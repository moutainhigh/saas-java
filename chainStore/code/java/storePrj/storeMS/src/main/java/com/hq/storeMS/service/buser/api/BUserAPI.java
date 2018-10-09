package com.hq.storeMS.service.buser.api;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buser.apiData.BUserAddApiForm;
import com.hq.storeMS.service.buser.apiData.BUserAddByChainForm;
import com.hq.storeMS.service.buser.apiData.BUserChainQueryForm;
import com.hq.storeMS.service.buser.apiData.BUserCommQueryForm;
import com.hq.storeMS.service.buser.apiData.BUserLoginApiForm;
import com.hq.storeMS.service.buser.apiData.BUserLoginWithJsCodeForm;
import com.hq.storeMS.service.buser.apiData.BUserUpdateApiForm;
import com.hq.storeMS.service.buser.apiData.LoginResp;
import com.hq.storeMS.service.buser.apiData.QueryVipUserForm;
import com.hq.storeMS.service.buser.apiData.WxLoginResp;
import com.hq.storeMS.service.buser.bs.BUserHandler;
import com.hq.storeMS.service.buser.bs.ChainBUserHandler;
import com.hq.storeMS.service.buser.data.BUser;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/buser")
public class BUserAPI {
	@Autowired
	private HttpServletResponse response;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<LoginResp>> getBUser(@RequestBody BUserLoginApiForm loginForm) {
		ReqResult<LoginResp> result = BUserHandler.getInstance().login(loginForm, response);
		ResponseEntity<RestResp<LoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/loginWithJsCode", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<WxLoginResp>> loginWithJsCode(@RequestBody BUserLoginWithJsCodeForm loginForm) {
		ReqResult<WxLoginResp> result = BUserHandler.getInstance().loginWithJsCode(loginForm);
		ResponseEntity<RestResp<WxLoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> newBUser(@RequestBody BUserAddApiForm buserForm) {
		ReqResult<BUser> result = BUserHandler.getInstance().add(buserForm);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> getBUser(@PathVariable("id") long id) {
		ReqResult<BUser> result = BUserHandler.getInstance().get(id);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> updateBUser(@PathVariable("id") long id,
			@RequestBody BUserUpdateApiForm inputForm) {
		ReqResult<BUser> result = BUserHandler.getInstance().update(id, inputForm);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByPhone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> findByPhone(@RequestParam("phone") long phone) {
		ReqResult<BUser> result = BUserHandler.getInstance().findByPhone(phone);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByMultitId", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> findByMultitId(@RequestParam("idList") String idList,
			@RequestParam("pageItemCount") int pageItemCount, @RequestParam("pageNo") int pageNo) {
		ReqResult<BUser> result = BUserHandler.getInstance().findByMultitId(idList, pageItemCount, pageNo);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findDevUserList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> findDevUserList(@RequestParam("pageItemCount") int pageItemCount,
			@RequestParam("pageNo") int pageNo) {
		ReqResult<BUser> result = BUserHandler.getInstance().findDevUserList(pageItemCount, pageNo);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	// 体验账号，免密码登录
	@RequestMapping(value = "/loginWithTestPhone", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<LoginResp>> loginWithTestPhone(@RequestBody BUserLoginApiForm loginForm) {
		ReqResult<LoginResp> result = BUserHandler.getInstance().loginWithTestPhone(loginForm, response);
		ResponseEntity<RestResp<LoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findVipUserList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> findVipUserList(
			@RequestParam(value = "buserId", defaultValue = "0") Long buserId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "phone", defaultValue = "0") Long phone,
			@RequestParam(value = "vipType", defaultValue = "0") Integer vipType,
			@RequestParam(value = "minTime", defaultValue = "0") Long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") Long maxTime,
			@RequestParam(value = "pageItemCount", defaultValue = "0") Integer pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
		QueryVipUserForm queryForm = QueryVipUserForm.newInstance();
		queryForm.setPhone(phone);
		ReqResult<BUser> result = BUserHandler.getInstance().findVipUserList(queryForm);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findByChain", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findByChain(
			@RequestParam(value = "phoneOrName", defaultValue = "") String phoneOrName,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		BUserChainQueryForm queryForm = BUserChainQueryForm.newInstance();
		queryForm.setChainId(chainId).setPhoneOrName(phoneOrName).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainBUserHandler.getInstance().findByChain(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/regByChainForm", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUser>> regByChainForm(@RequestBody BUserAddByChainForm inputForm) {
		ReqResult<BUser> result = ChainBUserHandler.getInstance().regByChainForm(inputForm);
		ResponseEntity<RestResp<BUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findByCond", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findByCond(
			@RequestParam(value = "phoneOrName", defaultValue = "") String phoneOrName,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "vipType", defaultValue = "-1") int vipType,
			@RequestParam(value = "roleSet", defaultValue = "") Set<Integer> roleSet,
			@RequestParam(value = "buserIds", defaultValue = "") Set<Long> buserIds,
			@RequestParam(value = "chainId", defaultValue = "0") long chainId,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "phone", defaultValue = "0") long phone,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		BUserCommQueryForm queryForm = BUserCommQueryForm.newInstance();
		queryForm.setPhoneOrName(phoneOrName).setName(name).setVipType(vipType).setRoleSet(roleSet)
			.setBuserIds(buserIds).setChainId(chainId).setBuserId(buserId).setPhone(phone).setMaxTime(maxTime).setMinTime(minTime)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = BUserHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
}
