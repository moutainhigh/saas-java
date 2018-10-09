package com.hq.chainMS.service.chainPackageProject.api;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.chainPackageProject.apiData.PackageProjectDetailQueryForm;
import com.hq.chainMS.service.chainPackageProject.bs.PackageProjectDetailHandler;
import com.hq.chainMS.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.chainMS.service.common.PageResp;
import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/packageProjectDetail")
public class PackageProjectDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getPackageProjectDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getPackageProjectDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "chainId") long chainId,
			@RequestParam(value = "statusSet", defaultValue = "") Set<Integer> statusSet,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "nameOrNumber", defaultValue = "") String nameOrNumber,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		PackageProjectDetailQueryForm queryForm = PackageProjectDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setChainId(chainId).setStatusSet(statusSet).setTypeId(typeId)
				.setNameOrNumber(nameOrNumber).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = PackageProjectDetailHandler.getInstance().getPackageProjectDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{chainId}/{packageProjectDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PackageProjectDetail>> getPackageProjectDetail(
			@PathVariable("chainId") long chainId,
			@PathVariable("packageProjectDetailId") String packageProjectDetailId) {
		ReqResult<PackageProjectDetail> result = PackageProjectDetailHandler.getInstance().getPackageProjectDetail(chainId, packageProjectDetailId);
		ResponseEntity<RestResp<PackageProjectDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
