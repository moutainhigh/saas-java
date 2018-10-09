package com.hq.storeMS.service.saas.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.opuser.data.adminRole.OPAdminRole;
import com.hq.storeMS.service.saas.apiData.OPAdminRoleAddApiForm;
import com.hq.storeMS.service.saas.apiData.OPAdminRoleUpdateApiForm;
import com.hq.storeMS.service.saas.bs.SaasHandler;

@RestController
@RequestMapping(value = "/op/opAdminRole" )
public class OPAdminRoleAPI {
	
	@RequestMapping(value = "" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<OPAdminRole>> addOPAdminRole(@RequestBody OPAdminRoleAddApiForm addForm)  
    {  
		
		ReqResult<OPAdminRole> result = SaasHandler.getInstance().addOPAdminRole(addForm);
		
		ResponseEntity<RestResp<OPAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<OPAdminRole>> getRoles()  
	{  
		ReqResult<OPAdminRole> result = SaasHandler.getInstance().listOPAdminRole();
		
		ResponseEntity<RestResp<OPAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
	}  
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<OPAdminRole>> getRole(@PathVariable("id") int roleId)  
	{  
		ReqResult<OPAdminRole> result = SaasHandler.getInstance().getOPAdminRole(roleId);
		
		ResponseEntity<RestResp<OPAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
	}  
	
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,  produces="application/json")
    public ResponseEntity<RestResp<OPAdminRole>> updateRole(@RequestBody OPAdminRoleUpdateApiForm inputForm)  
    {  
		ReqResult<OPAdminRole> result = SaasHandler.getInstance().update(inputForm);
		
		ResponseEntity<RestResp<OPAdminRole>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	

}
