package com.hq.storeMS.service.appointment.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.storeMS.service.appointment.data.AppointProduct;

public class AppointmentUpdateInfoApiForm {
	// 客户ID
	private String leaguerId;
	// 预约时间
	private long appointTime;
	// 多个项目
	private List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();
	
	
	/** =========================遗留的字段========================= **/
	private String name;
	private int sex;
	private long phone;
	private int age;
	private int leaguerType;
	private long beauticianId;
	private String beauticianName;
	private long productId;
	private String referrer;

	public static AppointmentUpdateInfoApiForm newInstance() {
		return new AppointmentUpdateInfoApiForm();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLeaguerType() {
		return leaguerType;
	}

	public void setLeaguerType(int leaguerType) {
		this.leaguerType = leaguerType;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
	}

	public String getBeauticianName() {
		return beauticianName;
	}

	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

}
