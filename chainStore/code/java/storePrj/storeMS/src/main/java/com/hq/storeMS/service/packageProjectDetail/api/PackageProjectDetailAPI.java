package com.hq.storeMS.service.packageProjectDetail.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.packageProjectDetail.apiData.PackageProjectDetailQueryForm;
import com.hq.storeMS.service.packageProjectDetail.bs.PackageProjectDetailHandler;
import com.hq.storeMS.service.packageProjectDetail.data.PackageProjectDetail;

@RestController
@RequestMapping(value = "/packageProjectDetail")
public class PackageProjectDetailAPI {
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getPackageProjectDetailPageInfo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> getPackageProjectDetailPageInfo(
			@RequestParam(value = "minTime", defaultValue = "0") long minTime,
			@RequestParam(value = "maxTime", defaultValue = "0") long maxTime,
			@RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "status", defaultValue = "") String status,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "nameOrNumber", defaultValue = "") String nameOrNumber,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		PackageProjectDetailQueryForm queryForm = PackageProjectDetailQueryForm.newInstance();
		queryForm.setMinTime(minTime).setMaxTime(maxTime).setStoreId(storeId).setStatus(status).setTypeId(typeId)
				.setNameOrNumber(nameOrNumber).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = PackageProjectDetailHandler.getInstance().getPackageProjectDetailPageInfo(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{storeId}/{packageProjectDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PackageProjectDetail>> getStorePackageProjectDetail(
			@PathVariable("storeId") long storeId,
			@PathVariable("packageProjectDetailId") String packageProjectDetailId) {
		ReqResult<PackageProjectDetail> result = PackageProjectDetailHandler.getInstance().getStorePackageProjectDetail(storeId, packageProjectDetailId);
		ResponseEntity<RestResp<PackageProjectDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@Deprecated
	@RequestMapping(value = "/{packageProjectDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PackageProjectDetail>> getPackageProjectDetail(
			@PathVariable("packageProjectDetailId") String packageProjectDetailId) {
		ReqResult<PackageProjectDetail> result = PackageProjectDetailHandler.getInstance().getStorePackageProjectDetail(0L, packageProjectDetailId);
		ResponseEntity<RestResp<PackageProjectDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
