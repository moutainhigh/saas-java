package com.hq.storeMS.service.leaguerDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.leaguerDetail.apiData.LeaguerDetailQueryForm;
import com.hq.storeMS.service.leaguerDetail.bs.LeaguerDetailHandler;
import com.hq.storeMS.service.leaguerDetail.data.LeaguerDetail;
import com.hq.storeMS.service.leaguerDetail.data.MemberCardExist;

@RestController
@RequestMapping(value = "/leaguerDetail")
public class LeaguerDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getLeaguerDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getLeaguerDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
			@RequestParam(value = "leaguerType", defaultValue = "0") int leaguerType,
			@RequestParam(value = "sortType", defaultValue = "0") int sortType,
			@RequestParam(value = "sort", defaultValue = "0") int sort,
			@RequestParam(value = "buserId", defaultValue = "0") long buserId,
			@RequestParam(value = "memberCardExpiredState", defaultValue = "0") int memberCardExpiredState,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		LeaguerDetailQueryForm queryForm = LeaguerDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId).setLeaguerNameOrPhone(leaguerNameOrPhone)
			.setLeaguerType(leaguerType).setSortType(sortType).setSort(sort).setBuserId(buserId).setMemberCardExpiredState(memberCardExpiredState)
			.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = LeaguerDetailHandler.getInstance().getLeaguerDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	//该接口已过时，请使用getStoreLeaguerDetail替换
	@Deprecated
	@RequestMapping(value = "/{leaguerDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerDetail>> getLeaguerDetail(
			@PathVariable("leaguerDetailId") String leaguerDetailId) {
		ReqResult<LeaguerDetail> result = LeaguerDetailHandler.getInstance().getStoreLeaguerDetail(0, leaguerDetailId);
		ResponseEntity<RestResp<LeaguerDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{storeId}/{leaguerDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerDetail>> getStoreLeaguerDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("leaguerDetailId") String leaguerDetailId) {
		ReqResult<LeaguerDetail> result = LeaguerDetailHandler.getInstance().getStoreLeaguerDetail(storeId, leaguerDetailId);
		ResponseEntity<RestResp<LeaguerDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/checkMemberCardExist/{storeId}/{memberCardNumber}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MemberCardExist>> checkMemberCardExist(
			@PathVariable("storeId") long storeId,
			@PathVariable("memberCardNumber") String memberCardNumber) {
		ReqResult<MemberCardExist> result = LeaguerDetailHandler.getInstance().checkMemberCardExist(memberCardNumber, storeId);
		ResponseEntity<RestResp<MemberCardExist>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	//测试专用
	@RequestMapping(value = "/triggerNotice/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<LeaguerDetail>> triggerNotice(@PathVariable("storeId") long storeId) {
		ReqResult<LeaguerDetail> result = LeaguerDetailHandler.getInstance().triggerNotice(storeId);
		ResponseEntity<RestResp<LeaguerDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
