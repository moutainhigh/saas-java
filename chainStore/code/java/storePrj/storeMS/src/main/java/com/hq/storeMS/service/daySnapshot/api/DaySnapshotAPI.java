package com.hq.storeMS.service.daySnapshot.api;

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
import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotAddForm;
import com.hq.storeMS.service.daySnapshot.apiData.DaySnapshotQueryForm;
import com.hq.storeMS.service.daySnapshot.bs.DaySnapshotHandler;
import com.hq.storeMS.service.daySnapshot.data.DaySnapshot;
import com.hq.storeMS.service.daySnapshot.data.PreDaySnapshotData;

@RestController
@RequestMapping(value = "/daySnapshot")
public class DaySnapshotAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId, 
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		DaySnapshotQueryForm queryForm = DaySnapshotQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId).setName(name)
				.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = DaySnapshotHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{daySnapshotId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<DaySnapshot>> get(@PathVariable("daySnapshotId") long daySnapshotId) {
		ReqResult<DaySnapshot> result = DaySnapshotHandler.getInstance().get(daySnapshotId);
		ResponseEntity<RestResp<DaySnapshot>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/getPreDaySnapshotData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PreDaySnapshotData>> getPreDaySnapshotData(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId) {
		ReqResult<PreDaySnapshotData> result = DaySnapshotHandler.getInstance().getPreDaySnapshotData(minTime, maxTime, storeId);
		ResponseEntity<RestResp<PreDaySnapshotData>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<DaySnapshot>> add(@RequestBody DaySnapshotAddForm addForm) {
		ReqResult<DaySnapshot> result = DaySnapshotHandler.getInstance().add(addForm);
		ResponseEntity<RestResp<DaySnapshot>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
