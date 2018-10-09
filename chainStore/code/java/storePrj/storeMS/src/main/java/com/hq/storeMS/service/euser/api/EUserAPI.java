package com.hq.storeMS.service.euser.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.euser.apiData.EUserAddForm;
import com.hq.storeMS.service.euser.apiData.EUserUpdateForm;
import com.hq.storeMS.service.euser.bs.EUserHandler;
import com.hq.storeMS.service.euser.data.EUser;

/** 
 * @ClassName: EUserAPI 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author helen 
 * @date 2018年1月19日 下午2:47:33 
 *  
 */


@RestController
@RequestMapping(value = "/euser" )
public class EUserAPI {
	
	@RequestMapping(value = "" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<EUser>> addEUser(@RequestBody EUserAddForm euserForm){  
		ReqResult<EUser> result = EUserHandler.getInstance().add(euserForm);
		ResponseEntity<RestResp<EUser>> respEntity = result.buildRespEntity();
		return respEntity;
    } 
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<EUser>> getEUser(@PathVariable("id") long id) {  
		ReqResult<EUser> result = EUserHandler.getInstance().get(id);
		ResponseEntity<RestResp<EUser>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	
	@RequestMapping(value = "/findByPhone" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<EUser>> findByPhone(@RequestParam("phone") long phone) {  
		ReqResult<EUser> result = EUserHandler.getInstance().findByPhone(phone);
		ResponseEntity<RestResp<EUser>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/findList" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<EUser>> getList(
    		@RequestParam(value = "pageItemCount")int pageItemCount,
    		@RequestParam(value = "pageNo")int pageNo) {  
		ReqResult<EUser> result = EUserHandler.getInstance().getList(pageItemCount,pageNo);
		ResponseEntity<RestResp<EUser>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.PUT,  produces="application/json")
	 public ResponseEntity<RestResp<EUser>> update(@RequestBody EUserUpdateForm updateForm){
		ReqResult<EUser> result = EUserHandler.getInstance().update(updateForm);
		ResponseEntity<RestResp<EUser>> respEntity = result.buildRespEntity();
		return respEntity;
	 }

}
