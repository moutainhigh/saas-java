package com.hq.storeMS.service.areaCode.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeMS.service.areaCode.bs.AreaCodeHandler;
import com.hq.storeMS.service.areaCode.data.AreaCode;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/unAuth/areaCode")
public class AreaCodeUnAuthAPI {
	
	@RequestMapping(value = "/findByCond" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<AreaCode>> findByCond(
    		@RequestParam(value="pageItemCount", required=true)Integer pageItemCount,
    		@RequestParam(value="pageNo", required=true)Integer pageNo){
		AreaCodeQueryForm queryForm = AreaCodeQueryForm.newInstance();
		queryForm.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<AreaCode> result = AreaCodeHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<AreaCode>> respEntity = result.buildRespEntity();
		return respEntity;
    }
}
