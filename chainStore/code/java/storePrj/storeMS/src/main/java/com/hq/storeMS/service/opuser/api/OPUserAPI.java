package com.hq.storeMS.service.opuser.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.opuser.apiData.OPLoginResp;
import com.hq.storeMS.service.opuser.apiData.OPUserAddApiForm;
import com.hq.storeMS.service.opuser.apiData.OPUserLoginApiForm;
import com.hq.storeMS.service.opuser.apiData.OPUserUpdateApiForm;
import com.hq.storeMS.service.opuser.apiData.OPuserQueryApiForm;
import com.hq.storeMS.service.opuser.bs.OPUserHandler;
import com.hq.storeMS.service.opuser.data.OPUser;

@RestController
@RequestMapping(value = "/op/opuser")
public class OPUserAPI {

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<OPLoginResp>> getOPUser(@RequestBody OPUserLoginApiForm loginForm) {

		ReqResult<OPLoginResp> result = OPUserHandler.getInstance().login(loginForm);

		ResponseEntity<RestResp<OPLoginResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<OPUser>> newOPUser(@RequestBody OPUserAddApiForm opuserForm) {

		ReqResult<OPUser> result = OPUserHandler.getInstance().add(opuserForm);

		ResponseEntity<RestResp<OPUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OPUser>> getOPUser(@PathVariable("id") long id) {
		ReqResult<OPUser> result = OPUserHandler.getInstance().get(id);

		ResponseEntity<RestResp<OPUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<OPUser>> updateOPUser(@RequestBody OPUserUpdateApiForm inputForm) {
		ReqResult<OPUser> result = OPUserHandler.getInstance().update(inputForm);

		ResponseEntity<RestResp<OPUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByName", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OPUser>> findByName(@RequestParam("name") String name) {
		ReqResult<OPUser> result = OPUserHandler.getInstance().findByName(name);

		ResponseEntity<RestResp<OPUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByPhone", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OPUser>> findByPhone(@RequestParam("phone") long phone) {
		ReqResult<OPUser> result = OPUserHandler.getInstance().findByPhone(phone);

		ResponseEntity<RestResp<OPUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findOPuserList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<OPUser>> findOPuserList(@ModelAttribute OPuserQueryApiForm queryForm) {
		ReqResult<OPUser> result = OPUserHandler.getInstance().findOPuserList(queryForm);

		ResponseEntity<RestResp<OPUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
