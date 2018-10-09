package com.hq.storeManagerMS.service.mngDevice.apiData.buserDevice;

import com.zenmind.common.StringFormatUtil;

public class DeviceQueryForm {
	
	private long buserId;
	//每页条数
	private int pageItemCount;
	//页号
	private int pageNo;
	
	public static DeviceQueryForm newInstance() {
		DeviceQueryForm params = new DeviceQueryForm();
		params.pageItemCount = 10;
		params.pageNo = 1;
		return params;
	}
	
	public String getListId() {
		String format = "{}_{}_{}";
		return StringFormatUtil.format(format,buserId,pageItemCount, pageNo);
	}

	public long getBuserId() {
		return buserId;
	}

	public void setBuserId(long buserId) {
		this.buserId = buserId;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
