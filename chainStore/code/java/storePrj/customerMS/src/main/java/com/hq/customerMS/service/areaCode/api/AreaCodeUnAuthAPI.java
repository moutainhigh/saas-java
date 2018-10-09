package com.hq.customerMS.service.areaCode.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.areaCode.bs.AreaCodeHandler;
import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.storeClient.service.areaCode.apiData.AreaCodeQueryForm;
import com.hq.storeClient.service.areaCode.data.AreaCode;

@RestController
@RequestMapping(value = "/unAuth/areaCode")
public class AreaCodeUnAuthAPI {
	@RequestMapping(value = "/findByCond", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<AreaCode>> findByCond(
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		AreaCodeQueryForm queryForm = AreaCodeQueryForm.newInstance();
		queryForm.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<AreaCode> result = AreaCodeHandler.getInstance().findByCond(queryForm);
		ResponseEntity<RestResp<AreaCode>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
