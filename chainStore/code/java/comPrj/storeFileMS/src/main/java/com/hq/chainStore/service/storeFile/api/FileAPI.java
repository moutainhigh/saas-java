package com.hq.chainStore.service.storeFile.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.chainStore.service.common.ReqResult;
import com.hq.chainStore.service.common.RestResp;
import com.hq.chainStore.service.storeFile.apiData.FileOriginForm;
import com.hq.chainStore.service.storeFile.apiData.FileResp;
import com.hq.chainStore.service.storeFile.apiData.FileUploadApiForm;
import com.hq.chainStore.service.storeFile.bs.FileHandler;

@RestController
@RequestMapping(value = "/file")
public class FileAPI {

	@RequestMapping(value = "/saveFileWithOriginInfo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<FileResp>> saveFileWithOriginInfo(@ModelAttribute FileOriginForm originForm) {
		ReqResult<FileResp> result = FileHandler.getInstance().saveFileWithOriginInfo(originForm);
		ResponseEntity<RestResp<FileResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<FileResp>> upload(@ModelAttribute FileUploadApiForm uploadForm) {
		ReqResult<FileResp> result = FileHandler.getInstance().uploadFile(uploadForm);
		ResponseEntity<RestResp<FileResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/excel", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<FileResp>> uploadExcel(@ModelAttribute FileUploadApiForm uploadForm) {
		ReqResult<FileResp> result = FileHandler.getInstance().uploadExcel(uploadForm);
		ResponseEntity<RestResp<FileResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 上传商户证书文件
	 * @param uploadForm
	 * @return
	 */
	@RequestMapping(value = "/cert", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<FileResp>> uploadCertFile(@ModelAttribute FileUploadApiForm uploadForm) {
		ReqResult<FileResp> result = FileHandler.getInstance().uploadCertFile(uploadForm);
		ResponseEntity<RestResp<FileResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<RestResp<FileResp>> delete(@RequestParam(value = "filePath") String filePath) {
		ReqResult<FileResp> result = FileHandler.getInstance().delete(filePath);
		ResponseEntity<RestResp<FileResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
