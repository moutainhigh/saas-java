package com.hq.storeMS.service.vipLevel.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.vipLevel.apiData.StoreVipLevelQueryForm;
import com.hq.storeMS.service.vipLevel.bs.VipLevelHandler;
import com.hq.storeManagerRestClient.common.restClientResp.PageResp;
import com.hq.storeManagerRestClient.service.vipLevel.data.VipLevel;

@RestController
@RequestMapping(value = "/vipLevel")
public class VipLevelAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value = "vipType") long vipType,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		StoreVipLevelQueryForm queryForm = StoreVipLevelQueryForm.newInstance();
		queryForm.setPageItemCount(pageItemCount).setPageNo(pageNo).setVipType(vipType);
		ReqResult<PageResp> result = VipLevelHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<VipLevel>> getVipLevel(@PathVariable("id") long id) {
		ReqResult<VipLevel> result = VipLevelHandler.getInstance().getVipLevel(id);
		ResponseEntity<RestResp<VipLevel>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
