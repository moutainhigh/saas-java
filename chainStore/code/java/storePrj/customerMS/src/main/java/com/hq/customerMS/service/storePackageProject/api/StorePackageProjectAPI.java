package com.hq.customerMS.service.storePackageProject.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.storePackageProject.bs.StorePackageProjectHandler;
import com.hq.storeClient.service.storePackageProject.data.StorePackageProject;

@RestController
@RequestMapping(value = "/storePackageProject")
public class StorePackageProjectAPI {

	@RequestMapping(value = "/{storeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StorePackageProject>> getStorePackageProject(
			@PathVariable("storeId") long storeId) {
		ReqResult<StorePackageProject> result = StorePackageProjectHandler.getInstance().getStorePackageProject(storeId);
		ResponseEntity<RestResp<StorePackageProject>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
}
