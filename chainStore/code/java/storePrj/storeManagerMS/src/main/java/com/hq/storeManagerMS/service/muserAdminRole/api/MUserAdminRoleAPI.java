package com.hq.storeManagerMS.service.muserAdminRole.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleAddApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleQueryApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.apiData.MUserAdminRoleUpdApiForm;
import com.hq.storeManagerMS.service.muserAdminRole.bs.MUserAdminRoleHandler;
import com.hq.storeManagerMS.service.muserAdminRole.data.MUserAdminRole;

@RestController
@RequestMapping(value = "/muserAdminRole" )
public class MUserAdminRoleAPI {
	
	@RequestMapping(value = "" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<MUserAdminRole>> addMUserAdminRole(@RequestBody MUserAdminRoleAddApiForm addForm){  
		ReqResult<MUserAdminRole> result = MUserAdminRoleHandler.getInstance().addMUserAdminRole(addForm);
		ResponseEntity<RestResp<MUserAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<MUserAdminRole>> updateMUserAdminRole(@RequestBody MUserAdminRoleUpdApiForm updForm) {  
		ReqResult<MUserAdminRole> result = MUserAdminRoleHandler.getInstance().updateMUserAdminRole(updForm);
		ResponseEntity<RestResp<MUserAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<MUserAdminRole>> getMUserAdminRole(@PathVariable("id") long id) {  
		ReqResult<MUserAdminRole> result = MUserAdminRoleHandler.getInstance().getMUserAdminRole(id);
		ResponseEntity<RestResp<MUserAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/findMUserAdminRoles" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<MUserAdminRole>> findMUserAdminRoles(
			@RequestParam(value="name", required = false, defaultValue="") String name,
			@RequestParam(value="state", required = false, defaultValue="-1") int state,
			@RequestParam(value="pageItemCount", required = false, defaultValue="0") int pageItemCount,
			@RequestParam(value="pageNo", required = false, defaultValue="1") int pageNo) {
		MUserAdminRoleQueryApiForm queryForm = MUserAdminRoleQueryApiForm.newInstance();
		queryForm.setName(name);
		queryForm.setState(state);
		queryForm.setPageItemCount(pageItemCount);
		queryForm.setPageNo(pageNo);
		ReqResult<MUserAdminRole> result = MUserAdminRoleHandler.getInstance().findMUserAdminRoles(queryForm);
		ResponseEntity<RestResp<MUserAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
	}  
}
