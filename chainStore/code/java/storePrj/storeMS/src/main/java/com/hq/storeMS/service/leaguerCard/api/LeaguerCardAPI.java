package com.hq.storeMS.service.leaguerCard.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.leaguerCard.apiData.LeaguerCardQueryForm;
import com.hq.storeMS.service.leaguerCard.bs.LeaguerCardHandler;

@RestController
@RequestMapping(value = "/leaguerCard")
public class LeaguerCardAPI {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getExpiredCardPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getExpiredCardPageInfo(
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "leaguerNameOrPhone", defaultValue = "") String leaguerNameOrPhone,
			@RequestParam(value = "sort", defaultValue = "0") int sort,
			@RequestParam(value = "loadType", defaultValue = "0") int loadType,
			@RequestParam(value = "cardTypeId", defaultValue = "") String cardTypeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		LeaguerCardQueryForm queryForm = LeaguerCardQueryForm.newInstance();
		queryForm.setStoreId(storeId).setLeaguerNameOrPhone(leaguerNameOrPhone)
		.setSort(sort).setLoadType(loadType).setCardTypeId(cardTypeId)
		.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = LeaguerCardHandler.getInstance().getExpiredCardPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

}
