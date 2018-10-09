package com.hq.storeMS.service.buserDevice.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.buserDevice.data.vo.IotKeyValue;

public class MCtrlSendParamApiForm {
	//设备ID
	private String clientId;
	//设备参数一次解析后的KeyValues
	private List<IotKeyValue> originParams = new ArrayList<IotKeyValue>();
	
	public static MCtrlSendParamApiForm newInstance() {
		return new MCtrlSendParamApiForm();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public List<IotKeyValue> getOriginParams() {
		return originParams;
	}

	public void setOriginParams(List<IotKeyValue> originParams) {
		this.originParams = originParams;
	}

}
