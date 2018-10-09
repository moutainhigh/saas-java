package com.hq.storeMS.service.storePackageProject.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;
import com.hq.storeMS.service.storePackageProject.apiData.StorePackageProjectUpdateForm;
import com.hq.storeMS.service.storePackageProject.bs.StorePackageProjectHandler;
import com.hq.storeMS.service.storePackageProject.data.StorePackageProject;

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
	
	@RequestMapping(value = "/{storeId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<StorePackageProject>> updateStorePackageProject(
			@PathVariable("storeId") long storeId,
			@RequestBody StorePackageProjectUpdateForm inputForm) {
		ReqResult<StorePackageProject> result = StorePackageProjectHandler.getInstance().updateStorePackageProject(storeId, inputForm);
		ResponseEntity<RestResp<StorePackageProject>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
