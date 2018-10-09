package com.hq.storeManagerMS.service.mngDevice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeClient.service.buser.data.BUserCount;
import com.hq.storeManagerMS.service.common.ReqResult;
import com.hq.storeManagerMS.service.common.RestResp;
import com.hq.storeManagerMS.service.mngDevice.apiData.MngDeviceQueryForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BUserQueryApiForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice.BindDeviceForm;
import com.hq.storeManagerMS.service.mngDevice.apiData.mctrl.MCtrlLockApiForm;
import com.hq.storeManagerMS.service.mngDevice.bs.MngDeviceHandler;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserBindInfo;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.BUserDevice;
import com.hq.storeManagerMS.service.mngDevice.data.buserDevice.DeviceDetail;
import com.hq.storeManagerMS.service.mngDevice.data.mclient.MClient;

/**
 * 仪器管理
 * @Description:  
 * @author: wujunwei 
 * @date: 2018年2月3日  
 * @version: v1.0  
 * @since: JDK 1.8
 */
@RestController
@RequestMapping(value = "/mngDevice" )
public class MngDeviceAPI {
	
	/*******************所有仪器管理***********************/
	/**
	 * 根据SN或商家账号查询仪器
	 * @param snCode
	 * @param bandingAccount
	 * @return
	 */
	@RequestMapping(value = "/findDeviceList", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<DeviceDetail>> findList(
			@RequestParam(value="snCode", required=false, defaultValue="") String snCode,
			@RequestParam(value="bandingAccount", required=false, defaultValue="") String bandingAccount,
			@RequestParam(value="pageItemCount",required=false)int pageItemCount,
    		@RequestParam(value="pageNo",required=false)int pageNo) {
		MngDeviceQueryForm params = MngDeviceQueryForm.newInstance();
		params.setSnCode(snCode);
		params.setBandingAccount(bandingAccount);
		ReqResult<DeviceDetail> result = MngDeviceHandler.getInstance().findDeviceDetailList(params);
		ResponseEntity<RestResp<DeviceDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

	/**
	 * 锁定和解锁仪器
	 * @param id 设备后台记录ID(iotRecordId)
	 * @param lockForm
	 * @return
	 */
	@RequestMapping(value = "/lock/{id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<MClient>> lockDevice(@PathVariable("id") long id, @RequestBody MCtrlLockApiForm lockForm) {
		ReqResult<MClient> result = MngDeviceHandler.getInstance().lockDevice(id,lockForm);
		ResponseEntity<RestResp<MClient>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 绑定仪器
	 */
	@RequestMapping(value = "/binding", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUserDevice>> binding(@RequestBody BindDeviceForm bindForm) {
		String actionName = "binding";
		ReqResult<BUserDevice> result = MngDeviceHandler.getInstance().bindDevice(bindForm,actionName);
		ResponseEntity<RestResp<BUserDevice>> respEntity = result.buildRespEntity();
		return respEntity;

	}
	
	/**
	 * 解绑仪器
	 */
	@RequestMapping(value = "/unbinding", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestResp<BUserDevice>> unbinding(@RequestBody BindDeviceForm bindForm) {
		String actionName = "unbinding";
		ReqResult<BUserDevice> result = MngDeviceHandler.getInstance().bindDevice(bindForm,actionName);
		ResponseEntity<RestResp<BUserDevice>> respEntity = result.buildRespEntity();
		return respEntity;

	}
	
	/**
	 * 绑定商户列表接口
	 * @param buserPhone
	 * @param pageItemCount
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/findBUserBindInfoList" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<BUserBindInfo>> findBUserBindInfoList(
    		@RequestParam(value="buserPhone", required=false, defaultValue="-1")  long buserPhone,
    		@RequestParam(value="pageItemCount",required=true)int pageItemCount,
    		@RequestParam(value="pageNo",required=true)int pageNo) {  
		BUserQueryApiForm queryForm = BUserQueryApiForm.newInstance();
		queryForm.setBuserPhone(buserPhone);
		queryForm.setPageItemCount(pageItemCount);
		queryForm.setPageNo(pageNo);
		ReqResult<BUserBindInfo> result = MngDeviceHandler.getInstance().findBUserBindInfoList(queryForm);
		ResponseEntity<RestResp<BUserBindInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	/**
	 * 绑定商户列表总记录数
	 */
	@RequestMapping(value = "/getBUserBindInfoCount" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<BUserCount>> getBUserBindInfoCount(
			@RequestParam(value="buserPhone", required=false, defaultValue="-1")  long buserPhone) {
		BUserQueryApiForm queryForm = BUserQueryApiForm.newInstance();
		queryForm.setBuserPhone(buserPhone);
		ReqResult<BUserCount> result = MngDeviceHandler.getInstance().getBUserBindInfoCount(queryForm);
		ResponseEntity<RestResp<BUserCount>> respEntity = result.buildRespEntity();
		return respEntity;
	}
	
	/**
	 * 查询设备
	 * @param snCode
	 * @return
	 */
	@RequestMapping(value = "/findMClientList" ,method = RequestMethod.GET,  produces="application/json")
    public ResponseEntity<RestResp<MClient>> findBUserBindInfoList(
    		@RequestParam(value="snCode", required=true)  String snCode) {  
		ReqResult<MClient> result = MngDeviceHandler.getInstance().findMClientList(snCode);
		ResponseEntity<RestResp<MClient>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
}
