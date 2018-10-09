package com.hq.customerMS.service.logger.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.customerMS.service.common.ReqResult;
import com.hq.customerMS.service.common.RestResp;
import com.hq.customerMS.service.logger.apiData.LoggerAddApiForm;
import com.hq.customerMS.service.logger.bs.LoggerHandler;
import com.hq.customerMS.service.logger.data.LoggerData;

@RestController
@RequestMapping(value = "/logger")
public class LoggerAPI {
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<LoggerData>> saveLogger(
			@RequestBody LoggerAddApiForm addForm) {
		ReqResult<LoggerData> result = LoggerHandler.getInstance().saveLogger(addForm);
		ResponseEntity<RestResp<LoggerData>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
