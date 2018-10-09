package com.hq.chainMS.service.detailDataVersion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.detailDataVersion.bs.DetailDataVersionHandler;
import com.hq.chainMS.service.detailDataVersion.data.DetailDataVersion;

@RestController
@RequestMapping(value = "/detailDataVersion")
public class DetailDataVersionAPI {

	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<DetailDataVersion>> getDetailDataVersion(@PathVariable("chainId") long chainId) {
		ReqResult<DetailDataVersion> result = DetailDataVersionHandler.getInstance().get(chainId);
		ResponseEntity<RestResp<DetailDataVersion>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
