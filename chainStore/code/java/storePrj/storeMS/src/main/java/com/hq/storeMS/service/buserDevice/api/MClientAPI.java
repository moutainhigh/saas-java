package com.hq.storeMS.service.buserDevice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buserDevice.apiData.MCtrlSendParamApiForm;
import com.hq.storeMS.service.buserDevice.bs.BUserDeviceHandler;
import com.hq.storeMS.service.buserDevice.data.MClient;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

/**
 * @ClassName: MClientAPI
 * @Description: 获取设备信息相关接口
 * @author helen
 * @date 2018年2月5日 下午4:17:57
 * 
 */

@RestController
@RequestMapping(value = "/mclient")
public class MClientAPI {

	// 通过设备记录id获取设备信息 iotRecordId
	@RequestMapping(value = "/getMClient/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MClient>> getList(@PathVariable("id") long id) {

		ReqResult<MClient> result = BUserDeviceHandler.getInstance().getMClient(id);
		ResponseEntity<RestResp<MClient>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	// 通过设备id获取设备信息 clientId
	@RequestMapping(value = "/findByClientId", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<MClient>> findByClientId(
			@RequestParam(value = "clientId", required = true) String clientId) {
		ReqResult<MClient> result = BUserDeviceHandler.getInstance().findByClientId(clientId);
		ResponseEntity<RestResp<MClient>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	// 发送参数给设备
	@RequestMapping(value = "/sendParam", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<MClient>> sendClientParam(@RequestBody MCtrlSendParamApiForm sendParamForm) {
		ReqResult<MClient> result = BUserDeviceHandler.getInstance().sendClientParam(sendParamForm);
		ResponseEntity<RestResp<MClient>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
