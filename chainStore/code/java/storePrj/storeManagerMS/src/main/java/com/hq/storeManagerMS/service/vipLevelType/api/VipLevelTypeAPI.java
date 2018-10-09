package com.hq.storeManagerMS.service.vipLevelType.api;

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
import com.hq.storeManagerMS.service.vipLevelType.apiData.AddVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.apiData.QueryVipLevelTypeForm;
import com.hq.storeManagerMS.service.vipLevelType.apiData.VipLevelTypeUpdateForm;
import com.hq.storeManagerMS.service.vipLevelType.bs.VipLevelTypeHandler;
import com.hq.storeManagerMS.service.vipLevelType.data.VipLevelType;

@RestController
@RequestMapping(value = "/vipLevelType")
public class VipLevelTypeAPI {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<VipLevelType>> getVipLevelType(@PathVariable("id") int id) {
		ReqResult<VipLevelType> result = VipLevelTypeHandler.getInstance().getVipLevelType(id);
		ResponseEntity<RestResp<VipLevelType>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value = "state", defaultValue = "-1") int state,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		QueryVipLevelTypeForm queryForm = QueryVipLevelTypeForm.newInstance();
		queryForm.setPageItemCount(pageItemCount).setPageNo(pageNo).setState(state).setName(name);
		ReqResult<PageResp> result = VipLevelTypeHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<VipLevelType>> addVipLevelType(@RequestBody AddVipLevelTypeForm addForm) {
		ReqResult<VipLevelType> result = VipLevelTypeHandler.getInstance().addVipLevelType(addForm);
		ResponseEntity<RestResp<VipLevelType>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<VipLevelType>> updateStoreVipTypeInfo(@PathVariable("id") int id,
			@RequestBody VipLevelTypeUpdateForm updateForm) {
		ReqResult<VipLevelType> result = VipLevelTypeHandler.getInstance().updateVipLevelType(id, updateForm);
		ResponseEntity<RestResp<VipLevelType>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
