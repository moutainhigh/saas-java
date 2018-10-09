package com.hq.storeMS.service.detailDataVersion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.detailDataVersion.bs.DetailDataVersionHandler;
import com.hq.storeMS.service.detailDataVersion.data.DetailDataVersion;

@RestController
@RequestMapping(value = "/detailDataVersion")
public class DetailDataVersionAPI {

	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<DetailDataVersion>> getDetailDataVersion(@PathVariable("storeId") long storeId) {
		ReqResult<DetailDataVersion> result = DetailDataVersionHandler.getInstance().get(storeId);
		ResponseEntity<RestResp<DetailDataVersion>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
