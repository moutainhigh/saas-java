package com.hq.storeMS.service.dynamic.api;

import java.util.Set;

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
import com.hq.storeMS.service.dynamic.apiData.DynamicAddForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicQueryForm;
import com.hq.storeMS.service.dynamic.apiData.DynamicQueryFormForCuser;
import com.hq.storeMS.service.dynamic.apiData.DynamicUpdateApiForm;
import com.hq.storeMS.service.dynamic.bs.DynamicHandler;
import com.hq.storeMS.service.dynamic.data.Dynamic;

@RestController
@RequestMapping(value = "/dynamic")
public class DynamicAPI {
	// B端用户 查询动态分页信息
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPage(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "buserId") long buserId,
			@RequestParam(value = "status", defaultValue = "") Set<Integer> status,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		DynamicQueryForm queryForm = DynamicQueryForm.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setContent(content).setStatus(status).setBuserId(buserId)
				.setStoreId(storeId).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = DynamicHandler.getInstance().findPage(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	// C端用户 查询动态分页信息
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findPageForCuser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findPageForCuser(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "buserIds") Set<Long> buserIds,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		DynamicQueryFormForCuser queryForm = DynamicQueryFormForCuser.newInstance();
		queryForm.setMaxTime(maxTime).setMinTime(minTime).setContent(content).setBuserIds(buserIds)
				.setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = DynamicHandler.getInstance().findPageForCuser(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{dynamicId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<Dynamic>> get(@PathVariable("dynamicId") long dynamicId) {
		ReqResult<Dynamic> result = DynamicHandler.getInstance().get(dynamicId);
		ResponseEntity<RestResp<Dynamic>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{dynamicId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Dynamic>> update(@PathVariable("dynamicId") long dynamicId,
			@RequestBody DynamicUpdateApiForm inputForm) {
		ReqResult<Dynamic> result = DynamicHandler.getInstance().update(dynamicId, inputForm);
		ResponseEntity<RestResp<Dynamic>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Dynamic>> add(@RequestBody DynamicAddForm inputForm) {
		ReqResult<Dynamic> result = DynamicHandler.getInstance().add(inputForm);
		ResponseEntity<RestResp<Dynamic>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
