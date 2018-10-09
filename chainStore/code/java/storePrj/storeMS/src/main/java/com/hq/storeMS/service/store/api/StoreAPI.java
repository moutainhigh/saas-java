package com.hq.storeMS.service.store.api;

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
import com.hq.storeMS.service.store.apiData.JoinStoreForm;
import com.hq.storeMS.service.store.apiData.StoreAddApiForm;
import com.hq.storeMS.service.store.apiData.StoreQueryForm;
import com.hq.storeMS.service.store.apiData.StoreUpdateApiForm;
import com.hq.storeMS.service.store.bs.StoreHandler;
import com.hq.storeMS.service.store.data.Store;
import com.hq.storeMS.service.store.data.StoreRO;

@RestController
@RequestMapping(value = "/store")
public class StoreAPI {
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreRO>> list(@RequestParam("pageItemCount") int pageItemCount,
			@RequestParam("pageNo") int pageNo) {
		ReqResult<StoreRO> result = StoreHandler.getInstance().list(pageItemCount, pageNo);
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByBuser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreRO>> findByBuser(@RequestParam("buserId") long buserId,
			@RequestParam("pageItemCount") int pageItemCount, @RequestParam("pageNo") int pageNo,
			@RequestParam("findType") int findType) {
		ReqResult<StoreRO> result = StoreHandler.getInstance().findBUserStores(buserId, pageItemCount, pageNo, findType);
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/findByName", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreRO>> findByName(@RequestParam("name") String name,
			@RequestParam("pageItemCount") int pageItemCount, @RequestParam("pageNo") int pageNo) {
		ReqResult<StoreRO> result = StoreHandler.getInstance().findByName(name, pageItemCount, pageNo);
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<StoreRO>> getStore(@PathVariable("id") long id) {
		ReqResult<StoreRO> result = StoreHandler.getInstance().get(id);
		ResponseEntity<RestResp<StoreRO>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Store>> newStore(@RequestBody StoreAddApiForm storeForm) {
		ReqResult<Store> result = StoreHandler.getInstance().add(storeForm);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<Store>> updateStore(@RequestBody StoreUpdateApiForm storeForm) {
		ReqResult<Store> result = StoreHandler.getInstance().update(storeForm);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findStoreByCond", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findStoreByCond(
			@RequestParam(value = "chainId", defaultValue = "0") long chainId,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "storeIds", defaultValue = "") Set<Long> storeIds,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		StoreQueryForm queryForm = StoreQueryForm.newInstance();
		queryForm.setChainId(chainId).setName(name).setStoreIds(storeIds).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = StoreHandler.getInstance().findStoreByCond(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}
	
	/*************************** customer *****************************************/
	
	@RequestMapping(value = "/joinStoreForCuser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<Store>> joinStore(@RequestBody JoinStoreForm joinStoreForm) {
		ReqResult<Store> result = StoreHandler.getInstance().joinStoreForCuser(joinStoreForm);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findByCuser" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<Store>> findByCuser(@RequestParam("cuserId") long cuserId){
		ReqResult<Store> result = StoreHandler.getInstance().findByCuser(cuserId);
		ResponseEntity<RestResp<Store>> respEntity = result.buildRespEntity();
		return respEntity;
    }

}
