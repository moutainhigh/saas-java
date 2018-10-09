package com.hq.storeMS.service.spreadStatis.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.spreadStatis.apiData.SpreadStatisQueryForm;
import com.hq.storeMS.service.spreadStatis.bs.SpreadStatisHandler;
import com.hq.storeMS.service.spreadStatis.data.vo.BuserSpreadStatis;
import com.hq.storeMS.service.spreadStatis.data.vo.StoreSpreadStatis;

@RestController
@RequestMapping(value = "/spreadStatis")
public class SpreadStatisAPI {
	@RequestMapping(value = "/findBuserSpreadStatis", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BuserSpreadStatis>> findBuserSpreadStatis(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId) {
		SpreadStatisQueryForm queryForm = SpreadStatisQueryForm.newInstance();
		queryForm.setStoreId(storeId).setMinTime(minTime).setMaxTime(maxTime).setBuserId(buserId);
		ReqResult<BuserSpreadStatis> result = SpreadStatisHandler.getInstance().findBuserSpreadStatis(queryForm);
		ResponseEntity<RestResp<BuserSpreadStatis>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findStoreSpreadStatis", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreSpreadStatis>> findStoreSpreadStatis(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId) {
		SpreadStatisQueryForm queryForm = SpreadStatisQueryForm.newInstance();
		queryForm.setStoreId(storeId).setMinTime(minTime).setMaxTime(maxTime);
		ReqResult<StoreSpreadStatis> result = SpreadStatisHandler.getInstance().findStoreSpreadStatis(queryForm);
		ResponseEntity<RestResp<StoreSpreadStatis>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
}
