package com.hq.storeMS.service.buserDevice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hq.storeMS.service.buserDevice.bs.BUserDeviceHandler;
import com.hq.storeMS.service.buserDevice.data.vo.DeviceDetail;
import com.hq.storeMS.service.common.ReqResult;
import com.hq.storeMS.service.common.RestResp;

/**
 * @ClassName: DeviceDetailListAPI
 * @Description: B端 获取组装后的仪器数据接口
 * @author helen
 * @date 2018年2月2日 下午4:12:41
 * 
 */

@RestController
@RequestMapping(value = "/deviceDetail")
public class DeviceDetailAPI {

	// 获取用户绑定的仪器列表
	@RequestMapping(value = "/getList/{buserId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestResp<DeviceDetail>> getList(@PathVariable("buserId") long buserId) {
		ReqResult<DeviceDetail> result = BUserDeviceHandler.getInstance().getList(buserId);
		ResponseEntity<RestResp<DeviceDetail>> respEntity = result.buildRespEntity();
		return respEntity;
	}

}
