package com.hq.storeMS.service.specialData.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.specialData.apiData.UpdateSpecialDataApiForm;
import com.hq.storeMS.service.specialData.bs.SpecialDataHandler;
import com.hq.storeMS.service.specialData.data.SpecialData;

/**
 * 
 * ClassName: SpecialDataAPI <br/>  
 * Function: TODO 特别关心的项目、医美师、客户<br/>  
 *  
 * @author kevin 
 * @version 1.0
 * @since JDK 1.6
 */
@RestController
@RequestMapping(value = "/specialData")
public class SpecialDataAPI {
	
	@RequestMapping(value = "/{specialDataId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<SpecialData>> getSpecialData(
			@PathVariable("specialDataId") String specialDataId) {
		ReqResult<SpecialData> result = SpecialDataHandler.getInstance().getSpecialData(specialDataId);
		ResponseEntity<RestResp<SpecialData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{specialDataId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<SpecialData>> updateSpecialData(
			@PathVariable("specialDataId") String specialDataId,
			@RequestBody UpdateSpecialDataApiForm updateForm) {
		ReqResult<SpecialData> result = SpecialDataHandler.getInstance().updateSpecialData(specialDataId, updateForm);
		ResponseEntity<RestResp<SpecialData>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
