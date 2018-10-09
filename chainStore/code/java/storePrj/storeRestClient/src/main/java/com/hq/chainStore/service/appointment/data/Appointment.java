package com.hq.chainStore.service.appointment.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import com.hq.common.dataDetial.bs.IntfDetailData;

@Table(name = "appointment")
public class Appointment implements IntfDetailData {
	@Id
	private long id;
	private long storeId;

	// 会员ID
	private String leaguerId;
	private String name;
	private long phone;
	private long cuserId;

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

	// 取消原因
	private CancelReason cancelReason;

	private long createdTime;
	private long lastUpdateTime;
	private int ver;

	/** =========================遗留的字段========================= **/
	private int sex;
	private int age;
	// 项目ID
	private long productId;
	// 推荐人 介绍人
	private String referrer;
	// 0:普通消费者 1:是会员
	private int leaguerType;
	// 医美师ID
	private long beauticianId;
	private String beauticianName;
	// 操作记录
	@Deprecated
	private List<OperationNote> operationNotes = new ArrayList<OperationNote>();

	public static Appointment newInstance() {
		Appointment appointment = new Appointment();

		long curTime = System.currentTimeMillis();

		appointment.status = AppointmentStatusEnum.NEW.ordinal();
		appointment.createdTime = curTime;
		appointment.lastUpdateTime = curTime;

		return appointment;
	}

	@Override
	public Object targetId() {
		return this.id;
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

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public long getCuserId() {
		return cuserId;
	}

	public void setCuserId(long cuserId) {
		this.cuserId = cuserId;
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

	public String getBeauticianName() {
		return beauticianName;
	}

	public void setBeauticianName(String beauticianName) {
		this.beauticianName = beauticianName;
	}

	@Deprecated
	public List<OperationNote> getOperationNotes() {
		return operationNotes;
	}

	@Deprecated
	public void setOperationNotes(List<OperationNote> operationNotes) {
		this.operationNotes = operationNotes;
	}

	public List<AppointProduct> getAppointProducts() {
		return appointProducts;
	}

	public void setAppointProducts(List<AppointProduct> appointProducts) {
		this.appointProducts = appointProducts;
	}

	public int getEntityState() {
		return entityState;
	}

	public void setEntityState(int entityState) {
		this.entityState = entityState;
	}

	public CancelReason getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(CancelReason cancelReason) {
		this.cancelReason = cancelReason;
	}

}
