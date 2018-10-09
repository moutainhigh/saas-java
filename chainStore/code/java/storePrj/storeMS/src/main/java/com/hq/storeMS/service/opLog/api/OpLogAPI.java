package com.hq.storeMS.service.opLog.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.opLog.apiData.OpLogQueryForm;
import com.hq.storeMS.service.opLog.bs.OpLogHandler;

@RestController
@RequestMapping(value = "/opLog")
public class OpLogAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value = "buserName", defaultValue = "") String buserName,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "type", defaultValue = "") Set<Integer> type, 
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		OpLogQueryForm queryForm = OpLogQueryForm.newInstance();
		queryForm.setBuserName(buserName).setName(name).setType(type).setStoreId(storeId).setMinTime(minTime)
				.setMaxTime(maxTime).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = OpLogHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
}
