package com.hq.storeMS.service.buserDevice.apiData;

import com.zenmind.common.StringFormatUtil;

public class OperateHistoryQueryForm {
	// 设备ID
	private String clientId;

	// 创建日期
	private String createdDate;

	// 每页条数
	private int pageItemCount;
	// 页号
	private int pageNo;

	public static OperateHistoryQueryForm newInstance() {
		OperateHistoryQueryForm params = new OperateHistoryQueryForm();
		params.pageItemCount = 31; // 月的最大天数
		params.pageNo = 1;
		return params;
	}

	public String getListId() {
		String format = "{}_{}_{}_{}";
		return StringFormatUtil.format(format, clientId, createdDate, pageItemCount, pageNo);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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
