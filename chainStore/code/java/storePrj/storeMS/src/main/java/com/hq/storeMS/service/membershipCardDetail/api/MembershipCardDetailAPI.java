package com.hq.storeMS.service.membershipCardDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.membershipCardDetail.apiData.MembershipCardDetailQueryForm;
import com.hq.storeMS.service.membershipCardDetail.bs.MembershipCardDetailHandler;
import com.hq.storeMS.service.membershipCardDetail.data.MembershipCardDetail;

@RestController
@RequestMapping(value = "/membershipCardDetail")
public class MembershipCardDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getMembershipCardDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getMembershipCardDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "cardNameOrNumber", defaultValue = "") String cardNameOrNumber,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		MembershipCardDetailQueryForm queryForm = MembershipCardDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId).setCardNameOrNumber(cardNameOrNumber)
				.setStatus(status).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = MembershipCardDetailHandler.getInstance().getMembershipCardDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{membershipCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MembershipCardDetail>> getStoreMembershipCardDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("membershipCardDetailId") String membershipCardDetailId) {
		ReqResult<MembershipCardDetail> result = MembershipCardDetailHandler.getInstance().getStoreMembershipCardDetail(storeId, membershipCardDetailId);
		ResponseEntity<RestResp<MembershipCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{membershipCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MembershipCardDetail>> getMembershipCardDetail(
			@PathVariable("membershipCardDetailId") String membershipCardDetailId) {
		ReqResult<MembershipCardDetail> result = MembershipCardDetailHandler.getInstance().getStoreMembershipCardDetail(0L, membershipCardDetailId);
		ResponseEntity<RestResp<MembershipCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
