package com.hq.storeMS.service.chainPackageProject.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainClient.service.chainPackageProject.data.ChainPackageProject;
import com.hq.chainClient.service.chainPackageProject.data.PackageProjectDetail;
import com.hq.storeMS.service.chainPackageProject.bs.ChainPackageProjectHandler;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chainPackageProject")
public class ChainPackageProjectAPI {
	@RequestMapping(value = "/{chainId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<ChainPackageProject>> getChainPackageProject(
			@PathVariable("chainId") long chainId) {
		ReqResult<ChainPackageProject> result = ChainPackageProjectHandler.getInstance().getChainPackageProject(chainId);
		ResponseEntity<RestResp<ChainPackageProject>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findPackageProjectDetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PackageProjectDetail>> findPackageProjectDetail(
			@RequestParam("packageProjectId") String packageProjectId,
			@RequestParam("chainId") long chainId) {
		ReqResult<PackageProjectDetail> result = ChainPackageProjectHandler.getInstance().getPackageProjectDetail(packageProjectId, chainId);
		ResponseEntity<RestResp<PackageProjectDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
