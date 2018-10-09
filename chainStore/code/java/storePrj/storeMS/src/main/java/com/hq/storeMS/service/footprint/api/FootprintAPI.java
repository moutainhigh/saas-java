package com.hq.storeMS.service.footprint.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.footprint.apiData.FootprintAddForm;
import com.hq.storeMS.service.footprint.apiData.FootprintQueryForm;
import com.hq.storeMS.service.footprint.bs.FootprintHandler;
import com.hq.storeMS.service.footprint.data.Footprint;

@RestController
@RequestMapping(value = "/footprint")
public class FootprintAPI {
	// B端用户 查询足迹分页信息
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "buserId") long buserId,
			@RequestParam(value = "dynamicType", defaultValue = "") Set<Integer> dynamicType,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		FootprintQueryForm queryForm = FootprintQueryForm.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setBuserId(buserId).setDynamicType(dynamicType);
		ReqResult<PageResp> result = FootprintHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{footprintId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Footprint>> get(@PathVariable("footprintId") long footprintId) {
		ReqResult<Footprint> result = FootprintHandler.getInstance().get(footprintId);
		ResponseEntity<RestResp<Footprint>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Footprint>> add(@RequestBody FootprintAddForm inputForm) {
		ReqResult<Footprint> result = FootprintHandler.getInstance().add(inputForm);
		ResponseEntity<RestResp<Footprint>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
