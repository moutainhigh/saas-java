package com.hq.storeMS.service.buserDevice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buser.apiData.BUserQueryApiForm;
import com.hq.storeMS.service.buser.data.BUserCount;
import com.hq.storeMS.service.buserDevice.bs.MngDeviceHandler;
import com.hq.storeMS.service.buserDevice.data.vo.BUserBindInfo;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

/**
 * 绑定商户+所有仪器的解绑
 * @Description: 提供给智美通平台管理后台的API
 * @author: wujunwei 
 * @date: 2018年2月6日  
 * @version: v1.0  
 * @since: JDK 1.8
 */

@RestController
@RequestMapping(value = "/mngDevice")
public class MngDeviceAPI {
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
    		@RequestParam(value="pageItemCount",required=false)int pageItemCount,
    		@RequestParam(value="pageNo",required=false)int pageNo) {  
		BUserQueryApiForm queryForm = BUserQueryApiForm.newInstance();
		queryForm.setBuserPhone(buserPhone);
		queryForm.setPageItemCount(pageItemCount);
		queryForm.setPageNo(pageNo);
		ReqResult<BUserBindInfo> result = MngDeviceHandler.getInstance().findBUserBindInfoList(queryForm);
		ResponseEntity<RestResp<BUserBindInfo>> respEntity = result.buildRespEntity();
		return respEntity;
    }
	
	@RequestMapping(value = "/getBUserBindInfoCount" ,method = RequestMethod.GET,  produces="application/json")
	public ResponseEntity<RestResp<BUserCount>> getBUserBindInfoCount(@RequestParam(value="buserPhone", required=false, defaultValue="-1")  long buserPhone) {
		BUserQueryApiForm queryForm = BUserQueryApiForm.newInstance();
		queryForm.setBuserPhone(buserPhone);
		ReqResult<BUserCount> result = MngDeviceHandler.getInstance().getBUserBindInfoCount(queryForm);
		ResponseEntity<RestResp<BUserCount>> respEntity = result.buildRespEntity();
		return respEntity;
	}
}
