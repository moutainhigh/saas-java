package com.hq.storeMS.service.chainCard.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainClient.service.chainCard.data.ChainCard;
import com.hq.chainClient.service.chainCard.data.MembershipCardDetail;
import com.hq.chainClient.service.chainCard.data.ProductCardDetail;
import com.hq.storeMS.service.chainCard.bs.ChainCardHandler;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chainCard")
public class ChainCardAPI {
	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainCard>> getChainCard(
			@PathVariable("chainId") long chainId) {
		ReqResult<ChainCard> result = ChainCardHandler.getInstance().getChainCard(chainId);
		ResponseEntity<RestResp<ChainCard>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findProductCardDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ProductCardDetail>> findProductCardDetail(
			@RequestParam("productCardId") String productCardId,
			@RequestParam("chainId") long chainId) {
		ReqResult<ProductCardDetail> result = ChainCardHandler.getInstance().getProductCardDetail(productCardId, chainId);
		ResponseEntity<RestResp<ProductCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findMemberCardDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MembershipCardDetail>> findMemberCardDetail(
			@RequestParam("memberCardId") String memberCardId,
			@RequestParam("chainId") long chainId) {
		ReqResult<MembershipCardDetail> result = ChainCardHandler.getInstance().getMembershipCardDetail(memberCardId, chainId);
		ResponseEntity<RestResp<MembershipCardDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
