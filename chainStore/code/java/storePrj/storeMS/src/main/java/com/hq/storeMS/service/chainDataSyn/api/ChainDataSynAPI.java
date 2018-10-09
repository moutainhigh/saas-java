package com.hq.storeMS.service.chainDataSyn.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.chainDataSyn.apiData.ChainDataQueryForm;
import com.hq.storeMS.service.chainDataSyn.bs.ChainDataSynHandler;
import com.hq.storeMS.service.common.PageResp;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

@RestController
@RequestMapping(value = "/chainDataSyn")
public class ChainDataSynAPI {

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findChainGoods", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findChainGoods(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "chainId") long chainId, @RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "synStatus", defaultValue = "-1") int synStatus,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ChainDataQueryForm queryForm = ChainDataQueryForm.newInstance();
		queryForm.setChainId(chainId).setNumberOrName(numberOrName).setStoreId(storeId).setTypeId(typeId)
				.setSynStatus(synStatus).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainDataSynHandler.getInstance().findChainGoods(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findChainMemberCard", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findChainMemberCard(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "chainId") long chainId, @RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "synStatus", defaultValue = "-1") int synStatus,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ChainDataQueryForm queryForm = ChainDataQueryForm.newInstance();
		queryForm.setChainId(chainId).setNumberOrName(numberOrName).setStoreId(storeId)
		.setSynStatus(synStatus).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainDataSynHandler.getInstance().findChainMemberCard(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findChainPackageProject", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findChainPackageProject(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "chainId") long chainId, @RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "synStatus", defaultValue = "-1") int synStatus,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ChainDataQueryForm queryForm = ChainDataQueryForm.newInstance();
		queryForm.setChainId(chainId).setNumberOrName(numberOrName).setStoreId(storeId).setTypeId(typeId)
				.setSynStatus(synStatus).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainDataSynHandler.getInstance().findChainPackageProject(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findChainProduct", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findChainProduct(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "chainId") long chainId, @RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "synStatus", defaultValue = "-1") int synStatus,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ChainDataQueryForm queryForm = ChainDataQueryForm.newInstance();
		queryForm.setChainId(chainId).setNumberOrName(numberOrName).setStoreId(storeId).setTypeId(typeId)
				.setSynStatus(synStatus).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainDataSynHandler.getInstance().findChainProduct(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/findChainProductCard", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<PageResp>> findChainProductCard(
			@RequestParam(value = "numberOrName", defaultValue = "") String numberOrName,
			@RequestParam(value = "typeId", defaultValue = "") String typeId,
			@RequestParam(value = "chainId") long chainId, @RequestParam(value = "storeId") long storeId,
			@RequestParam(value = "synStatus", defaultValue = "-1") int synStatus,
			@RequestParam(value = "pageItemCount", defaultValue = "0") int pageItemCount,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		ChainDataQueryForm queryForm = ChainDataQueryForm.newInstance();
		queryForm.setChainId(chainId).setNumberOrName(numberOrName).setStoreId(storeId).setTypeId(typeId)
				.setSynStatus(synStatus).setPageItemCount(pageItemCount).setPageNo(pageNo);
		ReqResult<PageResp> result = ChainDataSynHandler.getInstance().findChainProductCard(queryForm);
		ResponseEntity<RestResp<PageResp>> respEntity = result.buildJsonRespEntity();
		return respEntity;
	}

}
