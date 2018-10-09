package com.hq.customerMS.service.membershipCardDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.membershipCardDetail.bs.MembershipCardDetailHandler;
import com.hq.storeClient.service.membershipCardDetail.data.MembershipCardDetail;

@RestController
@RequestMapping(value = "/membershipCardDetail")
public class MembershipCardDetailAPI {
	
	@RequestMapping(value = "/{storeId}/{membershipCardDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MembershipCardDetail>> getMembershipCardDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("membershipCardDetailId") String membershipCardDetailId) {
		ReqResult<MembershipCardDetail> result = MembershipCardDetailHandler.getInstance().getMembershipCardDetail(storeId,membershipCardDetailId);
		ResponseEntity<RestResp<MembershipCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
