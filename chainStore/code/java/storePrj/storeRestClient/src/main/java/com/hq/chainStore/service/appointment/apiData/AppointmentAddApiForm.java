package com.hq.chainStore.service.appointment.apiData;

import java.util.ArrayList;
import java.util.List;

import com.hq.chainStore.service.appointment.data.AppointProduct;

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

	public static AppointmentAddApiForm newInstance() {
		return new AppointmentAddApiForm();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
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

	public long getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(long appointTime) {
		this.appointTime = appointTime;
	}

	public int getLeaguerType() {
		return leaguerType;
	}

	public void setLeaguerType(int leaguerType) {
		this.leaguerType = leaguerType;
	}

	public String getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(String leaguerId) {
		this.leaguerId = leaguerId;
	}

	public long getBeauticianId() {
		return beauticianId;
	}

	public void setBeauticianId(long beauticianId) {
		this.beauticianId = beauticianId;
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

	public String getBeauticianName() {
		return beauticianName;
	}

	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}

	public List<AppointProduct> getAppointProducts() {
		return appointProducts;
	}

	public void setAppointProducts(List<AppointProduct> appointProducts) {
		this.appointProducts = appointProducts;
	}

}
