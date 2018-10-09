package com.hq.chainMS.service.chainPackageProject.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.chainPackageProject.apiData.ChainPackageProjectUpdateForm;
import com.hq.chainMS.service.chainPackageProject.bs.ChainPackageProjectHandler;
import com.hq.chainMS.service.chainPackageProject.data.ChainPackageProject;

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
	
	@RequestMapping(value = "/{chainId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<ChainPackageProject>> updateChainPackageProject(
			@PathVariable("chainId") long chainId,
			@RequestBody ChainPackageProjectUpdateForm inputForm) {
		ReqResult<ChainPackageProject> result = ChainPackageProjectHandler.getInstance().updateChainPackageProject(chainId, inputForm);
		ResponseEntity<RestResp<ChainPackageProject>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
