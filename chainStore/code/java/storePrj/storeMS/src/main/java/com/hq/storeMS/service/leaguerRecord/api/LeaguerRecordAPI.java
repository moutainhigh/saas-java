package com.hq.storeMS.service.leaguerRecord.api;

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
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordAddForm;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordApiForm;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordQueryForm;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordUpdateForm;
import com.hq.storeMS.service.leaguerRecord.apiData.LeaguerRecordUpdateType;
import com.hq.storeMS.service.leaguerRecord.bs.LeaguerRecordHandler;
import com.hq.storeMS.service.leaguerRecord.data.LeaguerRecord;

@RestController
@RequestMapping(value = "/leaguerRecord")
public class LeaguerRecordAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getLeaguerRecordPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getLeaguerRecordPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "leaguerId", defaultValue = "") String leaguerId,
			@RequestParam(value = "workFlowDataId", defaultValue = "0") long workFlowDataId,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		LeaguerRecordQueryForm queryForm = LeaguerRecordQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId).setLeaguerId(leaguerId)
				.setWorkFlowDataId(workFlowDataId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = LeaguerRecordHandler.getInstance().getLeaguerRecordPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{leaguerRecordId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerRecord>> getLeaguerRecord(
			@PathVariable("storeId") long storeId,
			@PathVariable("leaguerRecordId") long leaguerRecordId) {
		ReqResult<LeaguerRecord> result = LeaguerRecordHandler.getInstance().getLeaguerRecord(storeId, leaguerRecordId);
		ResponseEntity<RestResp<LeaguerRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{leaguerRecordId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerRecord>> deleteLeaguerRecord(
			@PathVariable("storeId") long storeId,
			@PathVariable("leaguerRecordId") long leaguerRecordId) {
		ReqResult<LeaguerRecord> result = LeaguerRecordHandler.getInstance().deleteLeaguerRecord(storeId, leaguerRecordId);
		ResponseEntity<RestResp<LeaguerRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/updateLeaguerRecord/{leaguerRecordId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerRecord>> updateLeaguerRecord(
			@PathVariable("leaguerRecordId") long leaguerRecordId, 
			@RequestBody LeaguerRecordApiForm inputForm) {
		ReqResult<LeaguerRecord> result = LeaguerRecordHandler.getInstance().updateLeaguerRecord(leaguerRecordId, inputForm);
		ResponseEntity<RestResp<LeaguerRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerRecord>> addLeaguerRecord(@RequestBody LeaguerRecordAddForm inputForm) {
		ReqResult<LeaguerRecord> result = LeaguerRecordHandler.getInstance().addLeaguerRecord(inputForm);
		ResponseEntity<RestResp<LeaguerRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{leaguerRecordId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerRecord>> getLeaguerRecord(
			@PathVariable("leaguerRecordId") long leaguerRecordId) {
		ReqResult<LeaguerRecord> result = LeaguerRecordHandler.getInstance().getLeaguerRecord(0, leaguerRecordId);
		ResponseEntity<RestResp<LeaguerRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@Deprecated
	@RequestMapping(value = "/{leaguerRecordId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerRecord>> deleteLeaguerRecord(
			@PathVariable("leaguerRecordId") long leaguerRecordId) {
		ReqResult<LeaguerRecord> result = LeaguerRecordHandler.getInstance().deleteLeaguerRecord(0, leaguerRecordId);
		ResponseEntity<RestResp<LeaguerRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{leaguerRecordId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerRecord>> updateLeaguerRecord(
			@PathVariable("leaguerRecordId") long leaguerRecordId, 
			@RequestBody LeaguerRecordUpdateForm inputForm) {
		LeaguerRecordApiForm apiForm = LeaguerRecordApiForm.newInstance();
		apiForm.setStoreId(0L);
		apiForm.setUpdateType(LeaguerRecordUpdateType.UpdateInfo.ordinal());
		apiForm.setLeaguerRecordUpdateForm(inputForm);
		ReqResult<LeaguerRecord> result = LeaguerRecordHandler.getInstance().updateLeaguerRecord(leaguerRecordId, apiForm);
		ResponseEntity<RestResp<LeaguerRecord>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
