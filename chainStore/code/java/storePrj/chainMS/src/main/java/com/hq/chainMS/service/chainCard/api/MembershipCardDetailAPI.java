package com.hq.chainMS.service.chainCard.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.chainCard.apiData.MembershipCardDetailQueryForm;
import com.hq.chainMS.service.chainCard.bs.MembershipCardDetailHandler;
import com.hq.chainMS.service.chainCard.data.MembershipCardDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/membershipCardDetail")
public class MembershipCardDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getMembershipCardDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getMembershipCardDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "statusSet", defaultValue = "") Set<Integer> statusSet,
			@RequestParam(value = "cardNameOrNumber", defaultValue = "") String cardNameOrNumber,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		MembershipCardDetailQueryForm queryForm = MembershipCardDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setChainId(chainId).setCardNameOrNumber(cardNameOrNumber)
				.setStatusSet(statusSet).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = MembershipCardDetailHandler.getInstance()
				.getMembershipCardDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}/{membershipCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MembershipCardDetail>> getMembershipCardDetail(@PathVariable("chainId") long chainId,
			@PathVariable("membershipCardDetailId") String membershipCardDetailId) {
		ReqResult<MembershipCardDetail> result = MembershipCardDetailHandler.getInstance()
				.getMembershipCardDetail(chainId, membershipCardDetailId);
		ResponseEntity<RestResp<MembershipCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
