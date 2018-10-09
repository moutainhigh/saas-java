package com.hq.customerRestClient.service.appointment.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "appointment")
public class Appointment {
	@Id
	private long id;
	private long storeId;

	// 会员ID
	private String leaguerId;
	private String name;

	// 预约时间
	private long appointTime;

	// 状态 AppointmentStatusEnum
	private int status;
	// 实体状态
	private int entityState;
	
	// 渠道 AppointFromEnums
	private int origin;

	// 多个项目
	private List<AppointProduct> appointProducts = new ArrayList<AppointProduct>();
	// 创建者ID
	private long creatorId;
	// 创建者名称
	private String creatorName;

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	public static Appointment newInstance() {
		Appointment data = new Appointment();
		return data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAppointTime() {
		return appointTime;
	}

	public void setAppointTime(long appointTime) {
		this.appointTime = appointTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
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

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVer() {
		return ver;
	}

	public void setVer(int ver) {
		this.ver = ver;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}
}
