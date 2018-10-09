package com.hq.storeMS.service.buserDevice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buserDevice.apiData.BUserDeviceUpdateForm;
import com.hq.storeMS.service.buserDevice.apiData.BindDeviceForm;
import com.hq.storeMS.service.buserDevice.apiData.OperateHistoryQueryForm;
import com.hq.storeMS.service.buserDevice.bs.BUserDeviceHandler;
import com.hq.storeMS.service.buserDevice.data.BUserDevice;
import com.hq.storeMS.service.buserDevice.data.MClient;
import com.hq.storeMS.service.buserDevice.data.MCtrlLockApiForm;
import com.hq.storeMS.service.buserDevice.data.OperateHistory;
import com.hq.storeMS.service.buserDevice.data.vo.IotKeyValue;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

/**
 * @ClassName: BUserDevcieAPI
 * @Description: 仪器管理API
 * @author helen
 * @date 2018年1月27日 上午11:13:44
 * 
 */

@RestController
@RequestMapping(value = "/buserDevice")
public class BUserDeviceAPI {


	/**
	 * 分配到店
	 * @param id  仪器记录id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RestResp<BUserDevice>> updateBUserDevcie(@PathVariable("id") long id,
			@RequestBody BUserDeviceUpdateForm updateForm) {
		ReqResult<BUserDevice> result = BUserDeviceHandler.getInstance().update(id, updateForm);

		ResponseEntity<RestResp<BUserDevice>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 绑定仪器
	 */
	@RequestMapping(value = "/binding", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUserDevice>> bindDevice(@RequestBody BindDeviceForm bindForm) {

		ReqResult<BUserDevice> result = BUserDeviceHandler.getInstance().bindDevice(bindForm);
		ResponseEntity<RestResp<BUserDevice>> respEntity = result.buildRespEntity();
		return respEntity;

	}
	
	/**
	 * 解绑仪器
	 */
	@RequestMapping(value = "/unbinding", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUserDevice>> unbindDevice(@RequestBody BindDeviceForm bindForm) {

		ReqResult<BUserDevice> result = BUserDeviceHandler.getInstance().unbindDevice(bindForm);
		ResponseEntity<RestResp<BUserDevice>> respEntity = result.buildRespEntity();
		return respEntity;

	}

	//管理者BUser
	@RequestMapping(value = "/getByBUser", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUserDevice>> getByBUser(@RequestParam(value = "buserId") long buserId) {

		ReqResult<BUserDevice> result = BUserDeviceHandler.getInstance().getByBUser(buserId);
		ResponseEntity<RestResp<BUserDevice>> respEntity = result.buildRespEntity();
		return respEntity;

	}

	//仪器记录id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<BUserDevice>> get(@PathVariable("id") long id) {

		ReqResult<BUserDevice> result = BUserDeviceHandler.getInstance().get(id);
		ResponseEntity<RestResp<BUserDevice>> respEntity = result.buildRespEntity();
		return respEntity;

	}
	
	/**
	 * 锁定/解锁 
	 * {id} ->后台设备记录iotRecordId
	 */
	@RequestMapping(value = "/lock/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<MClient>> lockDevice(@PathVariable("id") long id,
			@RequestBody MCtrlLockApiForm lockForm) {
		ReqResult<MClient> result = BUserDeviceHandler.getInstance().lockDevice(id, lockForm);

		ResponseEntity<RestResp<MClient>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/***
	 * 获取设备参数 {id} ->后台设备记录iotRecordId
	 */
	@RequestMapping(value = "/getParam/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<IotKeyValue>> getClientParam(@PathVariable("id") long id) {
		ReqResult<IotKeyValue> result = BUserDeviceHandler.getInstance().getClientParam(id);
		ResponseEntity<RestResp<IotKeyValue>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/***
	 * 获取设备操作历史
	 */
	@RequestMapping(value = "/operateHistory" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<OperateHistory>> getOperateHistory(
    		@RequestParam(value="clientId", required=true) String clientId,
			@RequestParam(value="createdDate", required=true) String createdDate) {  
		OperateHistoryQueryForm params = OperateHistoryQueryForm.newInstance();
		params.setClientId(clientId);
		params.setCreatedDate(createdDate);
		ReqResult<OperateHistory> result = BUserDeviceHandler.getInstance().getOperateHistory(params);
		ResponseEntity<RestResp<OperateHistory>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	

}
