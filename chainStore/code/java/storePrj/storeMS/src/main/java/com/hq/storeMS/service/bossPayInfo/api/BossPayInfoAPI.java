package com.hq.storeMS.service.bossPayInfo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.payRestClient.service.bossPayInfo.apiData.BossPayInfoAddApiForm;
import com.hq.payRestClient.service.bossPayInfo.data.BossPayInfo;
import com.hq.storeFileClient.service.file.data.FileResp;
import com.hq.storeMS.service.bossPayInfo.apiData.CertFileUpLoadForm;
import com.hq.storeMS.service.bossPayInfo.bs.BossPayInfoHandler;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;


@RestController
@RequestMapping(value = "/bossPayInfo" )
public class BossPayInfoAPI {
	
	@RequestMapping(value = "/" ,method = RequestMethod.POST,  produces="application/json")
    public ResponseEntity<RestResp<BossPayInfo>> add(@RequestBody BossPayInfoAddApiForm addForm) {  
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().add(addForm);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<BossPayInfo>> get(@PathVariable("id") long id) {  
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().get(id);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }  
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<BossPayInfo>> update(
			@PathVariable("id") long id,
			@RequestBody BossPayInfoAddApiForm updateForm) {
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().update(id, updateForm);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/findByStoreId", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BossPayInfo>> findByStoreId(@RequestParam(value="storeId",required=true)long storeId) {
		ReqResult<BossPayInfo> result = BossPayInfoHandler.getInstance().findByStoreId(storeId);
		ResponseEntity<RestResp<BossPayInfo>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 上传商户证书文件
	 * @param uploadForm  (moduleType传入payMS/cert; moduleId 传入storeId)
	 * @return
	 */
	@RequestMapping(value = "/cert", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<FileResp>> uploadCertFile(
			@ModelAttribute CertFileUpLoadForm upForm) {
		ReqResult<FileResp> result = BossPayInfoHandler.getInstance().uploadCertFile(upForm);
		ResponseEntity<RestResp<FileResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
}
