package com.hq.storeMS.service.appointment.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.appointment.data.AppointProduct;

public class AppointmentAddApiForm {
	// 店铺ID
	private long storeId;
	// 客户ID
	private String leaguerId;
	// 预约时间
	private long appointTime;
	// 多个项目
	private List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();
	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;
	
	/******************* customerMS使用的额外字段 **********************/
	// C端用户ID
	private long cuserId;
	// 智美预约小程序，登录时获取的code
	private String jsCode;
	// 智美预约小程序，表单提交场景下，为 submit 事件带上的 formId；
	private String formId;
	
	public static AppointmentAddApiForm newInstance() {
		return new AppointmentAddApiForm();
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(long appointTime) {
		this.appointTime = appointTime;
	}

	public List<AppointProduct> getAppointProducts() {
		return appointProducts;
	}

	public void setAppointProducts(List<AppointProduct> appointProducts) {
		this.appointProducts = appointProducts;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getJsCode() {
		return jsCode;
	}

	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
	}
	
}
