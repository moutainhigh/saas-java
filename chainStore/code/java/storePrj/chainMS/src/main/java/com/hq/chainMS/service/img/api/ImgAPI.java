package com.hq.chainMS.service.img.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hq.chainMS.service.common.ReqResult;
import com.hq.chainMS.service.common.RestResp;
import com.hq.chainMS.service.img.apiData.FileUploadApiForm;
import com.hq.chainMS.service.img.apiData.FilesUploadApiForm;
import com.hq.chainMS.service.img.apiData.ImgResp;
import com.hq.chainMS.service.img.bs.ImgHandler;

@RestController
@RequestMapping(value = "/img")
public class ImgAPI {

	@RequestMapping(value = "/saveImg", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ImgResp>> saveImg(@ModelAttribute FileUploadApiForm apiForm) {
		ReqResult<ImgResp> result = ImgHandler.getInstance().saveImg(apiForm);
		ResponseEntity<RestResp<ImgResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/saveImgs", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ImgResp>> saveImgs(@ModelAttribute FilesUploadApiForm apiForm) {
		ReqResult<ImgResp> result = ImgHandler.getInstance().saveImgs(apiForm);
		ResponseEntity<RestResp<ImgResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 为IOS特别写的方法
	 * @param fileType
	 * @param moduleType
	 * @param moduleId
	 * @param img
	 * @return
	 */
	@RequestMapping(value = "/saveImg/{fileType}/{moduleType}/{moduleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ImgResp>> saveImg(
			@PathVariable(value = "fileType") String fileType,
			@PathVariable(value = "moduleType") String moduleType,
			@PathVariable(value = "moduleId") String moduleId,
			@RequestParam("img") MultipartFile img) {
		FileUploadApiForm apiForm = FileUploadApiForm.newInstance();
		apiForm.setFileType(fileType);
		apiForm.setImg(img);
		apiForm.setModuleId(moduleId.replaceAll("_", "/"));
		apiForm.setModuleType(moduleType);
		ReqResult<ImgResp> result = ImgHandler.getInstance().saveImg(apiForm);
		ResponseEntity<RestResp<ImgResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	@RequestMapping(value = "/saveImgs/{fileType}/{moduleType}/{moduleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<ImgResp>> saveImgs(
			@PathVariable(value = "fileType") String fileType,
			@PathVariable(value = "moduleType") String moduleType,
			@PathVariable(value = "moduleId") String moduleId,
			@RequestParam("imgs") MultipartFile[] imgs) {
		FilesUploadApiForm apiForm = FilesUploadApiForm.newInstance();
		apiForm.setFileType(fileType);
		apiForm.setImgs(imgs);
		apiForm.setModuleId(moduleId.replaceAll("_", "/"));
		apiForm.setModuleType(moduleType);
		ReqResult<ImgResp> result = ImgHandler.getInstance().saveImgs(apiForm);
		ResponseEntity<RestResp<ImgResp>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
