package com.hq.storeManagerMS.service.vipLevel.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeManagerMS.service.common.PageResp;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;
import com.hq.storeManagerMS.service.vipLevel.apiData.AddVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.apiData.QueryVipLevelForm;
import com.hq.storeManagerMS.service.vipLevel.apiData.VipLevelUpdateForm;
import com.hq.storeManagerMS.service.vipLevel.bs.VipLevelHandler;
import com.hq.storeManagerMS.service.vipLevel.data.VipLevel;

@RestController
@RequestMapping(value = "/vipLevel")
public class VipLevelAPI {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<VipLevel>> getVipLevel(@PathVariable("id") long id) {
		ReqResult<VipLevel> result = VipLevelHandler.getInstance().getVipLevel(id);
		ResponseEntity<RestResp<VipLevel>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value="name", required=false, defaultValue="") String name,
			@RequestParam(value = "state", defaultValue = "-1") int state,
			@RequestParam(value = "typeId", defaultValue = "-1") int typeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		QueryVipLevelForm queryForm = QueryVipLevelForm.newInstance();
		queryForm.setPageItemCount(pageItemCount).setPageNo(pageNo)
		.setState(state).setTypeId(typeId).setName(name);
		ReqResult<PageResp> result = VipLevelHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<VipLevel>> addVipLevel(@RequestBody AddVipLevelForm addForm) {
		ReqResult<VipLevel> result = VipLevelHandler.getInstance().addVipLevel(addForm);
		ResponseEntity<RestResp<VipLevel>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<VipLevel>> updateStoreVipTypeInfo(@PathVariable("id") long id,
			@RequestBody VipLevelUpdateForm updateForm) {
		ReqResult<VipLevel> result = VipLevelHandler.getInstance().updateVipLevel(id, updateForm);
		ResponseEntity<RestResp<VipLevel>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
