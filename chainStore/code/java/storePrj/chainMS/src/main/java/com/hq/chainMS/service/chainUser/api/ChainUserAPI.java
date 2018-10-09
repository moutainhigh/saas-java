package com.hq.chainMS.service.chainUser.api;

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

import com.hq.chainMS.service.chainUser.apiData.ChainUserLoginForm;
import com.hq.chainMS.service.chainUser.apiData.ChainUserQueryForm;
import com.hq.chainMS.service.chainUser.apiData.ChainUserUpdateForm;
import com.hq.chainMS.service.chainUser.apiData.LoginResp;
import com.hq.chainMS.service.chainUser.apiData.RegistForm;
import com.hq.chainMS.service.chainUser.bs.ChainUserHandler;
import com.hq.chainMS.service.chainUser.data.ChainUser;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chainUser")
public class ChainUserAPI {
	@Autowired
	private HttpServletResponse response;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<LoginResp>> getChainUser(@RequestBody ChainUserLoginForm loginForm) {
		ReqResult<LoginResp> result = ChainUserHandler.getInstance().login(loginForm, response);
		ResponseEntity<RestResp<LoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ChainUser>> newChainUser(@RequestBody RegistForm registForm) {
		ReqResult<ChainUser> result = ChainUserHandler.getInstance().add(registForm);
		ResponseEntity<RestResp<ChainUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainUser>> getChainUser(@PathVariable("id") long id) {
		ReqResult<ChainUser> result = ChainUserHandler.getInstance().get(id);
		ResponseEntity<RestResp<ChainUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<ChainUser>> updateChainUser(@PathVariable("id") long id,
			@RequestBody ChainUserUpdateForm inputForm) {
		ReqResult<ChainUser> result = ChainUserHandler.getInstance().update(id, inputForm);
		ResponseEntity<RestResp<ChainUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByPhone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainUser>> findByPhone(@RequestParam("phone") long phone) {
		ReqResult<ChainUser> result = ChainUserHandler.getInstance().findByPhone(phone);
		ResponseEntity<RestResp<ChainUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findByCond", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findByCond(
			@RequestParam(value = "phoneOrName", defaultValue = "") String phoneOrName,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "chainUserIds", defaultValue = "") Set<Long> chainUserIds,
			@RequestParam(value = "crossClerks", defaultValue = "") Set<Integer> crossClerks,
			@RequestParam(value = "roleId", defaultValue = "0") int roleId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ChainUserQueryForm queryForm = ChainUserQueryForm.newInstance();
		queryForm.setPhoneOrName(phoneOrName).setChainId(chainId).setChainUserIds(chainUserIds)
				.setCrossClerks(crossClerks).setRoleId(roleId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainUserHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
}
