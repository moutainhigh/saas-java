package com.hq.customerMS.service.leaguerDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.leaguerDetail.bs.LeaguerDetailHandler;
import com.hq.storeClient.service.leaguerDetail.data.LeaguerDetail;

@RestController
@RequestMapping(value = "/leaguerDetail")
public class LeaguerDetailAPI {

	@RequestMapping(value = "/{storeId}/{leaguerDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerDetail>> getLeaguerDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("leaguerDetailId") String leaguerDetailId) {
		ReqResult<LeaguerDetail> result = LeaguerDetailHandler.getInstance().getLeaguerDetail(storeId,leaguerDetailId);
		ResponseEntity<RestResp<LeaguerDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
